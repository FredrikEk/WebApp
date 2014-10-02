package jpa.mgn.core;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import jpa.mgn.persistence.AbstractEntity;

/**
 * Association class, connection Authors to Books
 * (eliminating the ManyToMany for Authors -)
 * @author hajo
 */
@Entity
@Table(name = "PUBLICATION", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"BOOK_ID", "AUTHOR_ID"})
})
public class Publication extends AbstractEntity  {
    
    // No cascade on remove!
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Book book;
    // No cascade on remove!
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Author author;
    
    @PreRemove
    public void preRemove() {
        setBook(null);
        setAuthor(null);
    }
    
    public Publication() {
    }
    
    public Publication(Book book, Author author) {
        this.book = book;
        this.author = author;
    }
    
    public Book getBook() {
        return book;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public void setAuthor(Author author) {
        this.author = author;
    }
   
    @Override
    public String toString() {
        return "Publication{" + "id=" + getId() + ", book=" + book + ", author=" + author + '}';
    }
    
}
