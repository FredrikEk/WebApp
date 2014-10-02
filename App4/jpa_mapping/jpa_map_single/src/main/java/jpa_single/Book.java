package jpa_single;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A default book (no customization)
 * @author 
 */
@Entity
public class Book implements Serializable {

    // Let database generate primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Float price;
    private String description;

    protected Book() {
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

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    } 

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", price=" + price + '}';
    }
    
    
}
