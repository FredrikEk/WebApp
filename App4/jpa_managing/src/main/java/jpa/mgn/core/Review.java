

package jpa.mgn.core;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import jpa.mgn.persistence.AbstractEntity;

/**
 * A book review
 * @author hajo
 */
@Entity
public class Review extends AbstractEntity {
   
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reviewDate;
    private String text;
    // Using a bidirectional, just as an example
    @ManyToOne
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public Review() {
    }

    public Review(String text, Date reviewDate) {
        this.text = text;
        this.reviewDate = reviewDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
 
    @Override
    public String toString() {
        return "Review{" + "id=" + getId() + ", text=" + text + "}";
    }  
}
