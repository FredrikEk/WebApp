
package jpa.mgn.persistence;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all entities, defines id, equal etc
 *   
 * The below implementations can be disputed
 * - No id before saved to database, problems with equals (storing in Sets)
 * - See https://community.jboss.org/wiki/EqualsAndHashCode
 * 
 * @author hajo
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Version
    //private Long version;
    
    public Long getId() {
        return id;
    }

    // Must have default ctor (make it package private)
    protected AbstractEntity() {
    }

    protected AbstractEntity(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final AbstractEntity other = (AbstractEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}
