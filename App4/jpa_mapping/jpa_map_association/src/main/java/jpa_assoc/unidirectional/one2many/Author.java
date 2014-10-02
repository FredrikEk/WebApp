
package jpa_assoc.unidirectional.one2many;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 * This is the case: 
 * 
 * One authors has written many books, one2many  
 * This is mapped with a list in Author, so direction Author->Book
 * 
 * The author also possible have written many plays, yet another one2many mapping. 
 * This is mapped from Play to author using many2one in Play
 * 
 * @author hajo
 */
@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    /*
     * One Author has many written books
     *
     * One to many unidirectional
     *
     * Will default create joint table AUTHOR1MUNI_BOOK1MUNI
     * Can avoid Join table using @JoinColumn
     * If using @JoinColumn Book table will have extra column (AUTHOR_FK)
     */
    @OneToMany // This is "one" books is "many"
    @JoinColumn(name = "AUTHOR_FK")  // Comment out and inspect
    private List<Book> books;
    
    public Author() {
    }

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // No equals hasCode...
}
