
package jpa_ige.generic;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * Storing generic types
 * @author hajo
 * @param <T>
 */
@Entity
public class GenericEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;  
    T t; 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final GenericEntity<?> other = (GenericEntity<?>) obj;
        return Objects.equals(this.id, other.id);
    }

   
    
}
