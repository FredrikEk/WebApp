package jpa.mgn.core;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.mgn.persistence.AbstractDAO;

/**
 * DAO for books
 *
 * @author hajo
 */
@Stateless

public class Library extends AbstractDAO<Book, Long> {

    @PersistenceContext
    private EntityManager em;

    public Library() {
        super(Book.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    // Search on all fields
    // This is a very basic serach
    public List<Book> search(Book book) {
        String jpql = "select b from Book b where b.id = :id" + 
                " or b.price=:price or b.title=:title or b.description like :desc";
        return em.createQuery(jpql,
                Book.class)
                .setParameter("id", book.getId())
                .setParameter("price", book.getPrice())
                .setParameter("title", book.getTitle())
                // Probably not very good pattern matching
                .setParameter("desc", "%" + book.getDescription() + "%")
                .getResultList();
    }

    public List<Book> getBooksFor(Author author){
        String jpql = "select b from Publication p join p.author a join p.book b where a = :author";
        return em.createQuery(jpql, Book.class)
                .setParameter("author", author).getResultList();    
    }
    
    public List<Review> getReviewsFor(Book book){
        String jpql = "select b.reviews from Book b where b = :book";
        return em.createQuery(jpql, Review.class)
                .setParameter("book", book).getResultList();

    }
    
    public List<Review> getAllReviews(){
        String jpql = "select b.reviews from Book b";
        return em.createQuery(jpql, Review.class).getResultList();
    }
    
}
