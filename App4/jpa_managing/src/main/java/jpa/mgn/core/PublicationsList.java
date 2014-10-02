package jpa.mgn.core;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.mgn.persistence.AbstractDAO;

/**
 * DAO for Publications
 *
 * @author hajo
 */
@Stateless
public class PublicationsList extends AbstractDAO<Publication, Long> {

    @PersistenceContext
    private EntityManager em;

    public PublicationsList() {
        super(Publication.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }



    public List<Publication> getPublicationsFor(Author author) {
        String jpql = "select p from Publication p where p.author = :author";
        return em.createQuery(jpql, Publication.class)
                .setParameter("author", author).getResultList();   
    }


    public List<NameTitle> getAllAuthorsNamesAndTitles() {
        String s = "select new jpa.mgn.core.PublicationsList.NameTitle(a.name, b.title) "
                + "from Publication p join p.author a join p.book b";
        return em.createQuery(s, NameTitle.class).getResultList();
    }

    // Class used as datatransfer class (when not returning a full object
    // use like this to select what to return, possible pices form more types)
    // Possible silly here
    public static class NameTitle {

        public String name;
        public String title;

        public NameTitle(String name, String title) {
            this.name = name;
            this.title = title;
        }

        @Override
        public String toString() {
            return "NameTitle{" + "name=" + name + ", title=" + title + '}';
        }

    };

}
