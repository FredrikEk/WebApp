package jpa_assoc.many2many;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * 
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
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)  // No project may have same name
    private String name;

    public Project(String name) {
        this.name = name;
    }
   
    public Project() {
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
        int hash = 5;
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
        final Project other = (Project) obj;
        return Objects.equals(this.id, other.id);
    }

}
