package edu.chl.hajo.comp.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Backing bean for detail page (i.e. hold page data)
 *
 * @author hajo
 */
@Named
@RequestScoped
public class PersonDetailBB implements Serializable {

    private static final Logger LOG = Logger.getLogger(PersonDetailBB.class.getName());

    private Long id;
    private String fname;
    private int age;

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "PersonDetailBB alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "PersonDetailBB to be destroyed {0}", this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        LOG.log(Level.INFO, "Set id {0}", id);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
        LOG.log(Level.INFO, "Set fname {0}", fname);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        LOG.log(Level.INFO, "Set age {0}", age);
    }

    @Override
    public String toString() {
        return "PersonDetailBB{" + "id=" + id + ", fname=" + fname + ", age=" + age + '}';
    }
    
    

}
