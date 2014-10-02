
package jpa_assoc.many2many;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * This is an association class connecting
 * projects and consults
 * 
 * Every row in table must be unique
 * (so that not the same allocation is done more than once)
 * 
 * @author hajo
 */
@Entity
@Table(name="ALLOCATION", uniqueConstraints={
   @UniqueConstraint(columnNames={"CONSULT_ID", "PROJECT_ID"})
})
public class Allocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // Unidirectional
    @ManyToOne
    private Consult consult;
    @ManyToOne
    private Project project;

    public Allocation(Consult consult, Project project) {
        this.consult = consult;
        this.project = project;
    }

    public Allocation() {
    }

    public Consult getConsult() {
        return consult;
    }

    public void setConsult(Consult consult) {
        this.consult = consult;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Allocation other = (Allocation) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
