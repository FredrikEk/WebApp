package edu.chl.hajo.comp.ctrl;

import edu.chl.hajo.comp.view.PersonDetailBB;
import edu.chl.hajo.comp.core.Person;
import edu.chl.hajo.comp.core.PersonRegistry;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Control bean for detail page
 * Execute business logic and return navigation outcome
 *
 * @author hajo
 */
@Named
@RequestScoped
public class PersonDetailCtrl implements Serializable {

    private static final Logger LOG = Logger.getLogger(PersonDetailCtrl.class.getName());
    private PersonRegistry reg;
    private PersonDetailBB personBB;

    protected PersonDetailCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "PersonDetailCtrl alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "PersonDetailCtrl to be destroyed {0}", this);
    }

    @Inject
    public PersonDetailCtrl(PersonRegistry reg) {
        this.reg = reg;
    }
    
    // Lie this --------------------
    @Inject
    public void setPersonBB(PersonDetailBB personBB) {
        this.personBB = personBB;
    }
   
    public String save() {
        LOG.log(Level.INFO, "Save: {0}" + personBB);
        reg.create(new Person(personBB.getId(), personBB.getFname(), personBB.getAge()));
        return "personList?faces-redirect=true";
    }
    // ---- or like this 
    /*public String save(PersonDetailBB personBB) {
        LOG.log(Level.INFO, "Save: {0}" + personBB);
        reg.create(new Person(personBB.getId(), personBB.getFname(), personBB.getAge()));
        return "personList?faces-redirect=true";
    }*/
    
    

    public String update() {
        LOG.log(Level.INFO, "Update: {0}" + personBB);
        reg.update(new Person(personBB.getId(), personBB.getFname(), personBB.getAge()));
        return "personList?faces-redirect=true";
    }

    public void delete() {
        LOG.log(Level.INFO, "Delete: {0}" + personBB);
        reg.delete(personBB.getId());
    }
    
}
