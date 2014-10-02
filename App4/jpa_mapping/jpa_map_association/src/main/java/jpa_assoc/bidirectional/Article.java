package jpa_assoc.bidirectional;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * An Article
 * 
 * This is bidirectional many to one
 * 
 * We avoid bidirectional (bad OO model) but sometimes
 * necessary for technical reasons (cascading deletes)
 * 
 * @author hajo
 */
@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_title", nullable = false, updatable = false)
    private String title;
    private Float price;
    @Column(length = 50)
    private String description;
    /*
    *  This article knows it's *only* Reporter, but Reporter possible
    *  have written more articles so "many (articles) 2 one (reporter)" 
    *  I.e this class shall have ManyToOne
    */
    // This is the owning side, customize JoinColumn here
    // i.e. column with foreign keys in this table 
    // NOTE: Many side always owning for bidirectional relations
    @ManyToOne 
    @JoinColumn(name = "REPORTER_FK")  // The column with foreign keys
    private Reporter reporter;
 
    public Article() {
    }

    public Article(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        return Objects.equals(this.id, other.id);
    }

    
   
}
