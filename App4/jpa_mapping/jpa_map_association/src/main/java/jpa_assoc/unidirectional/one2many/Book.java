package jpa_assoc.unidirectional.one2many;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * A book
 * This is unidirectional so no attribute for Author or Play here
 * @author antonio/hajo
 */
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "book_title", nullable = false, updatable = false)
    private String title;
    private Float price;
    @Column(length = 50)
    private String description;

    
    public Book() {
    }

    public Book(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Use "Insert code..."
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return Objects.equals(this.id, other.id) || 
                (this.id != null && this.id.equals(other.id));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", price=" + price + '}';
    }
    
    
}
