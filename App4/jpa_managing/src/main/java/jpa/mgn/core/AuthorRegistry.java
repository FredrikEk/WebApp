package jpa.mgn.core;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.mgn.persistence.AbstractDAO;

/**
 * DAO for Authors
 *
 * @author hajo
 */
@Stateless
public class AuthorRegistry extends AbstractDAO<Author, Long> {

    @PersistenceContext
    private EntityManager em;

    public AuthorRegistry() {
        super(Author.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }


    /*
     // Other implementation of delete Author (incl books and reviews)
     // See QueryService
     @Override
     public void delete(Long id) {
     // NOTE: This is probably not the way the business people would do it

     // Get all books by author
     String jpql = "select b from Publication p join p.book b where p.author.id=:id";
     List<Book> books = em.createQuery(jpql).setParameter("id", id).getResultList();

     // Delete all publications for this author
     String jpql1 = "delete from Publication p where p.author.id=:id";
     em.createQuery(jpql1).setParameter("id", id).executeUpdate();

     // Now remove the books
     for (Book b : books) {
     em.remove(b);
     }
     // Detelet the author
     super.delete(id);
     }*/
    public List<Author> findByName(String name) {
        String jpql = "select a from Author a where a.name=:name";
        return em.createQuery(jpql, Author.class).
                setParameter("name", name).getResultList();
    }

    public List<Author> getAuthorsFor(Book book) {
        String jpql = "select a from Publication p join p.author a join p.book b where b = :book";
        return em.createQuery(jpql, Author.class)
                .setParameter("book", book).getResultList();
    }
}
