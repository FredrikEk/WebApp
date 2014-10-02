package jpa.mgn.query;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import jpa.mgn.core.Author;
import jpa.mgn.core.AuthorRegistry;
import jpa.mgn.core.Book;
import jpa.mgn.core.Library;
import jpa.mgn.core.Publication;
import jpa.mgn.core.PublicationsList;
import jpa.mgn.core.Review;
import jpa.mgn.query.QueryService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testing queries from the persistence layer
 *
 * NOTE NOTE NOTE: JavaDB (Derby) must be running (not using an embedded
 * database). GlassFish not needed using embedded
 *
 * @author hajo
 */
@RunWith(Arquillian.class)
public class TestPersistenceLayerQueries {

    @Inject
    Library library;
    @Inject
    PublicationsList pubList;
    @Inject
    AuthorRegistry authReg;
    @Inject
    QueryService qs;
   
    @Inject
    UserTransaction utx;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                // Add all classes
                .addPackage("jpa.mgn.core")
                .addPackage("jpa.mgn.query")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before
    public void before() throws Exception {
        clearAll();
    }



    @Test
    public void testSearch() throws Exception {


        library.create(new Book("aaa", 1f, "abc"));
        library.create(new Book("bbb", 2f, "def"));
        library.create(new Book("ccc", 3f, "xyz"));



        // Should find all
        List<Book> found = library.search(new Book("aaa", 2f, "xyz"));
        assertTrue(3 == found.size());

       

        for (Book b : found) {  // Bad style, but just to be shure during development of test
            System.out.println(b);
        }
        final String skip = "SKIP";
        found = library.search(new Book("aaa", -1f, skip));
        assertTrue(1 == found.size());

    }



    @Test
    public void tesGetReviewsForBookAndDeleteBook() throws Exception {
        Calendar c = new GregorianCalendar();
        Date d = c.getTime();
        Book book = new Book("aaa", 1f, "abc");
        List<Review> rs = Arrays.asList(new Review("bla bla", d), new Review("blo blo", d),
                new Review("ble ble", d));
        book.setReviews(rs);
        // Will cascade
        library.create(book);

        List<Review> found = library.getReviewsFor(book);
        assertTrue(3 == found.size());

        // Should remove all reviews
        library.delete(book.getId());

        found = library.getAllReviews();
        // ... no content should be find
        assertTrue(found.isEmpty());

    }


    @Test
    public void testNameTitle() throws Exception {
        Book b1 = new Book("book1", 111F, "aaaa");
        Author a1 = new Author("xxx");
        Book b2 = new Book("book2", 111F, "aaaa");
        Author a2 = new Author("yyy");
        Book b3 = new Book("book3", 111F, "aaaa");
        Author a3 = new Author("zzz");
        Publication pub1 = new Publication(b1, a1);
        Publication pub2 = new Publication(b2, a2);
        Publication pub3 = new Publication(b3, a3);

        pubList.create(pub1);
        pubList.create(pub2);
        pubList.create(pub3);
        // Using non entity class as returnvalue
        List<PublicationsList.NameTitle> found = pubList.getAllAuthorsNamesAndTitles();
        // Bad style
        for (PublicationsList.NameTitle nt : found) {
            System.out.println(nt);
        }
    }


    @Test
    public void testGetAuthorForBook() throws Exception {
        Book b = new Book("aaa", 1f, "abc");
        utx.begin();
        pubList.create(new Publication(b, new Author("xxx")));
        pubList.create(new Publication(b, new Author("yyy")));
        pubList.create(new Publication(b, new Author("zzz")));
        utx.commit();
        List<Author> found = authReg.getAuthorsFor(b);
        assertTrue(3 == found.size());
        for (Author a : found) {
            System.out.println(a);
        }
    }



    @Test
    public void testGetBooksForAuthor() throws Exception {
        Author a = new Author("xyz");
        utx.begin();
        pubList.create(new Publication(new Book("aaa", 1f, "abc"), a));
        pubList.create(new Publication(new Book("bbb", 2f, "abc"), a));
        pubList.create(new Publication(new Book("ccc", 3f, "abc"), a));
        utx.commit();
        List<Book> found = library.getBooksFor(a);
        assertTrue(3 == found.size());
        for (Book b : found) {
            System.out.println(b);
        }
    }



    @Test
    public void testRemoveAuthor() throws Exception {
        String[] titles = "aaa, bbb, ccc, ddd, eee".split(",");
        /*
         * Must make this a single transaction, else key violation
         * when persisting alredy persisted Authors (if not single
         * each DAO call runs in own transaction, objects detached after call)
         * Operations like this one should probably be done in a higher
         * level service layer using the DAOs
         */
        utx.begin();
        // Create the data
        Author a1 = new Author("xxx");
        Author a2 = new Author("yyy");
        for (int i = 0; i < titles.length; i++) {
            Book b = new Book(titles[i], 111F, "blabla");
            if (i % 2 == 0) {  //Even
                // Will cascade book, author
                pubList.create(new Publication(b, a1)); // xxx
            } else {
                pubList.create(new Publication(b, a2)); // yyy
            }
        }
        utx.commit();

        assertTrue(pubList.count() == titles.length);
        assertTrue(authReg.count() == 2);
        assertTrue(library.count() == titles.length);

        List<Author> authors = authReg.findByName("xxx");
        assertTrue(authors.size() == 1);

        // Deleting author should also delete
        // all books and publications by the author
        // So use the QueryService
        qs.deleteAuthorInclBooksAndPublications(authors.get(0));
        
        // Publications for author gone? Yes...
        assertTrue(pubList.count() == 2);

        // Books by author gone? Yes...
        assertTrue(library.count() == 2);
        final String skip = "SKIP";
        assertTrue(library.search(new Book("aaa", -1f, skip)).isEmpty());

    }


    private void clearAll() throws Exception {
        utx.begin();
        EntityManager p = pubList.getEntityManager();
        p.joinTransaction();
        // Order matters
        p.createQuery("delete from Publication").executeUpdate();
        EntityManager a = authReg.getEntityManager();
        a.joinTransaction();
        a.createQuery("delete from Author").executeUpdate();
        EntityManager l = library.getEntityManager();
        l.joinTransaction();
        l.createQuery("delete from Book").executeUpdate();
        utx.commit();
    }
    
}
