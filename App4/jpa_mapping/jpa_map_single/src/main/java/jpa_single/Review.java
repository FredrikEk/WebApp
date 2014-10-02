/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_single;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * An embedded class used in BookWithEmbedded
 * @author hajo
 */
@Embeddable
public class Review implements Serializable {
    private String reviewText = "No review yet";

    protected Review(){
        
    }

    public Review(String reviewText ) {
        this.reviewText = reviewText;
    }
  
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

   
   
    
}
