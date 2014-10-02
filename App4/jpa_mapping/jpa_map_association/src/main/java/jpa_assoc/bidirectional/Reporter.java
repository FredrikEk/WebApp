package jpa_assoc.bidirectional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * An Reporter (strange modeling, just for now)
 *
 * This is bidirectional one to many
 * 
 * We avoid bidirectional (bad OO model) but sometimes
 * necessary for technical reasons (cascading deletes)
 * @author hajo
 */
@Entity
public class Reporter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    /*
     * Reporter has written many articlaes so "one (reporter) 2 many (articles)" 
     * This class shall have OneToMany
     */
    // This is the inverse side (i.e. column with
    // foreign keys will be in other table
    // Must have mappedBy here (using name of attribute of owning side)
    @OneToMany(mappedBy = "reporter") 
    private List<Article> articles;

    public Reporter() {
    }

    public Reporter(String name, List<Article> articles) {
        this.name = name;
        this.articles = articles;
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Reporter other = (Reporter) obj;
        return Objects.equals(this.id, other.id);
    }

    
}
