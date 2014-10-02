package jpa.mgn.core;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import jpa.mgn.listeners.BookListener;
import jpa.mgn.persistence.AbstractEntity;

/**
 * @author hajo
 */
@Entity
@EntityListeners({BookListener.class})
public class Book extends AbstractEntity {

   
    @Column(name = "book_title", nullable = false, updatable = false)
    private String title;
    private Float price;
    @Column(length = 50)
    private String description;

    // Using a bidirectional, just as an example
    // orphanRemoval = if we remove a review from the list and save the book
    // the review should also be removed in database
    // Cascade = when saving/removing book all reviews should be saved/removed
    // Fetchtype = Don't load list from databbase before we need if (NOTE: Possible
    // nullpointer or other problems if book detached).
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public Book() {
    }

    public Book(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public boolean addReview(Review review) {
        return reviews.add(review);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

 
    @Override
    public String toString() {
        return "Book{" + "id=" + getId() + ", title=" + title + ", price=" + price
                + ", description=" + description + '}';
    }

}
