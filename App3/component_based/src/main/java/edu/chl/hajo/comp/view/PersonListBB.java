package edu.chl.hajo.comp.view;

import edu.chl.hajo.comp.core.Person;
import edu.chl.hajo.comp.core.PersonRegistry;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;  // NOTE : NOT beans.ViewScoped; 
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean for personList.xhtml
 *
 * Using View scoped must call with AJAX  (i.e. same page, else
 * navigation data lost)
 * 
 * @author hajo
 */
@Named
@ViewScoped // @SessionScoped also possible
public class PersonListBB implements Serializable {

    private PersonRegistry reg;
    private static final Logger LOG = Logger.getLogger(PersonListBB.class.getName());
    private int currentPage;
    private int pageSize = 10;
    private int count;

    protected PersonListBB() {
        // Must have for CDI
    }

    @Inject
    public PersonListBB(PersonRegistry reg) {
        this.reg = reg;
    }

    @PostConstruct
    public void post() {
        count = reg.count();
    }

    public Collection<Person> getList() {       
        return reg.findRange(pageSize * currentPage, pageSize);
    }

    public void next() {
        if (currentPage * pageSize + 1 < count) {
            currentPage = currentPage + 1;
        }
    }

    public void prev() {
        if (currentPage > 0) {
            currentPage = currentPage - 1;
        }
    }

    // ---- Get/Set -------------
   
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int count() {
        return count;
    }

}
