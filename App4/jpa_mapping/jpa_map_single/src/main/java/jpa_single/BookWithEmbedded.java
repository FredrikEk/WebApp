package jpa_single;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Using an embedded class (will end up in same table)
 * @author hajo
 */
@Entity
public class BookWithEmbedded implements Serializable {

    // Let database generate primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "book_title", nullable = false, updatable = false)
    private String title;
    private Float price;
    @Column(length = 50)
    private String description;
    @Embedded
    private Review review;

    public BookWithEmbedded() {
    }

    public BookWithEmbedded(String title, Float price, String description) {
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

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
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
        final BookWithEmbedded other = (BookWithEmbedded) obj;
        return Objects.equals(this.id, other.id) || (this.id != null && this.id.equals(other.id));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "BookWithEmbedded{" + "id=" + id + ", title=" + title + ", price=" + price + '}';
    }
    // TODO
}
