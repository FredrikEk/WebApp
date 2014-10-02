package jpa_single;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 * @author antonio/hajo
 */
@Entity
public class BookWithCollection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "book_title", nullable = false, updatable = false)
    private String title;
    private Float price;
    @Column(length = 50)
    private String description;
    
    /*
     *   Mapping a collection (also possible with a Map)
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "Tag" ) // Will create extra table Tag
    private List<String> tags;

    public BookWithCollection() {
    }

    public BookWithCollection(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookWithCollection other = (BookWithCollection) obj;
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
        return "BookWithCollection{" + "id=" + id + ", title=" + title + ", tags=" + tags + '}';
    }

  
    
    
}
