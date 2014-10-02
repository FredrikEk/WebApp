
package jpa_assoc.many2many;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A consult can participate in many projects
 * and a project can involve many consults,
 * so many2many
 * 
 * To solve we introduce an association class
 * connecting authors and books (this is also better OO design)
 * 
 * WE DON'T USE many2many annotation
 * 
 * @author hajo
 */
@Entity
public class Consult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Consult() {
    }

    public Consult(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Consult other = (Consult) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
