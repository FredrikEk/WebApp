package jpa.mgn.core;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
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
 * Testing the persistence layer 
 * 
 * NOTE NOTE NOTE: JavaDB (Derby) must be running (not using an embedded database)
 * GlassFish not needed using embedded
 *
 * @author hajo
 */
@RunWith(Arquillian.class)
public class TestPersistenceLayer {

  
    @Inject
    Library library;
    @Inject
    PublicationsList pubList;
    @Inject
    AuthorRegistry authReg;
    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                // Add all classes
                .addPackage("jpa.mgn.core")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before  // Run before each test
    public void before() throws Exception {
        clearAll();
    }

    @Test
    public void testPersistASingleBook() throws Exception {
        Book book = new Book("book", 111F, "aaaa");
        library.create(book);
        List<Book> books = library.findAll();
        assertTrue(books.size() > 0);
        assertTrue(books.get(0).getTitle().equals(book.getTitle()));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book1 = new Book("book", 111F, "aaaa");
        library.create(book1);

        book1.setTitle("update");
        library.update(book1);

        Book book2 = library.find(book1.getId());
        assertTrue(book2.getTitle().equals(book1.getTitle()));
    }
    
    @Test
    public void testDeleteBook() throws Exception {
        Book book1 = new Book("book", 111F, "aaaa");
        library.create(book1);
        
        library.delete(book1.getId());
        assertTrue( library.findAll().isEmpty());
    }

    @Test
    public void testPersistASingleAuthor() throws Exception {
        Author author = new Author("author");
        authReg.create(author);
        List<Author> authors = authReg.findAll();
        assertTrue(authors.size() > 0);
        assertTrue(authors.get(0).getId().equals(author.getId()));
    }

    @Test
    public void testPersistAPublication() throws Exception {
        Book book = new Book("book", 111F, "aaaa");
        Author author = new Author("author");
        Publication pub = new Publication(book, author);

        // This should persist Book and Author (cascade)
        pubList.create(pub);

        List<Author> authors = authReg.findAll();
        List<Book> books = library.findAll();
        List<Publication> publications = pubList.findAll();

        assertTrue(publications.size() > 0);
        assertTrue(authors.get(0).getId().equals(author.getId()));
        assertTrue(books.get(0).getId().equals(book.getId()));
    }

    @Test
    public void testPersistAndRemovePublication() throws Exception {
        Book book = new Book("book", 111F, "aaaa");
        Author author = new Author("author");
        Publication pub = new Publication(book, author);

        // This should cascade Book and Author
        pubList.create(pub);

        // Remove publication but ...
        pubList.delete(pub.getId());
        List<Publication> publications = pubList.findAll();
        assertTrue(publications.isEmpty());

        List<Author> authors = authReg.findAll();
        List<Book> books = library.findAll();
        // ... Book and Author should still be there
        assertTrue(authors.get(0).getId().equals(author.getId()));
        assertTrue(books.get(0).getId().equals(book.getId()));
    }

    @Test
    public void testFindRange() throws Exception {
        String[] titles = "aaa, bbb, ccc, ddd, eee, fff, ggg, hhh".split(",");
        for (String s : titles) {
            Book book = new Book(s, 111F, "blabla");
            library.create(book);
        }
        List<Book> books = library.findAll();
        assertTrue(books.size() == titles.length);

        books = library.findRange(2, 2);
        assertTrue(books.get(0).getTitle().equals(titles[2]));

    }

    @Test
    public void testCount() throws Exception {
        String[] titles = "aaa, bbb, ccc, ddd, eee, fff, ggg, hhh".split(",");
        for (String s : titles) {
            Book book = new Book(s, 111F, "blabla");
            library.create(book);
        }
        int count = library.count();
        assertTrue(count == titles.length);
    }
    
    
    private void clearAll() throws Exception {
        utx.begin();
        EntityManager em = pubList.getEntityManager();
        em.joinTransaction();
        //Order matters
        em.createQuery("delete from Publication").executeUpdate();     
        em.createQuery("delete from Author").executeUpdate();
        em.createQuery("delete from Book").executeUpdate();
        utx.commit();
    }
    
/*
    private void clearAll() throws Exception {
        utx.begin();
        EntityManager p = pubList.getEntityManager();
        p.joinTransaction();
        //Order matters
        p.createQuery("delete from Publication").executeUpdate();
        EntityManager a = authReg.getEntityManager();
        a.joinTransaction();
        a.createQuery("delete from Author").executeUpdate();
        EntityManager l = library.getEntityManager();
        l.joinTransaction();
        l.createQuery("delete from Book").executeUpdate();
        utx.commit();
    }
*/
}
