
package jpa.mgn.query;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import jpa.mgn.core.Author;
import jpa.mgn.core.AuthorRegistry;
import jpa.mgn.core.Book;
import jpa.mgn.core.Library;
import jpa.mgn.core.Publication;
import jpa.mgn.core.PublicationsList;

/**
 * General query service.
 * This is one abstraction layer above the DAOs
 * (using the DAOs)
 * 
 * NOTE: See also AuthorRegistry
 * 
 * @author hajo
 */

@Stateless
public class QueryService {
    
    @Inject
    private Library library;
    @Inject
    private PublicationsList pubList;
    @Inject
    private AuthorRegistry authReg;
    
    // Here because deleting an author should delete all
    // publications and books by author (probably not
    // the way the business people would do it)
    public void deleteAuthorInclBooksAndPublications(Author a){
        List<Book> books = library.getBooksFor(a);
        List<Publication> publications = pubList.getPublicationsFor(a);
        for( Book b : books){
            library.delete(b.getId());
        }
        for( Publication p : publications){
            pubList.delete(p.getId());
        } 
        authReg.delete(a.getId());
    }
    
    
}
