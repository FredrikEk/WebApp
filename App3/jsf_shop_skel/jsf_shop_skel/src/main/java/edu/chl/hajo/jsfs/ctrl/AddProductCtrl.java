package edu.chl.hajo.jsfs.ctrl;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.jsfs.view.AddProductBB;
import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.Product;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named
@RequestScoped
public class AddProductCtrl{
    
    
    private static final Logger LOG = Logger.getLogger(AddProductCtrl.class.getName());
    
    
    private IProductCatalogue pc;
    private AddProductBB productBB;

    protected AddProductCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "AddProductCtrl alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "AddProductCtrl to be destroyed {0}", this);
    }

    @Inject
    public AddProductCtrl(SingletonShop shop) {
        this.pc = shop.getShop().getProductCatalogue();
    }
    
    // Lie this --------------------
    @Inject
    public void setProductBB(AddProductBB productBB) {
        this.productBB = productBB;
    }
   
    public String create() {
        LOG.log(Level.INFO, "Save: {0}" + productBB);
        pc.create(new Product(productBB.getId(), productBB.getName(), productBB.getPrice()));
        return "productList?faces-redirect=true";
    }
    // ---- or like this 
    /*public String save(PersonDetailBB personBB) {
        LOG.log(Level.INFO, "Save: {0}" + personBB);
        reg.create(new Person(personBB.getId(), personBB.getFname(), personBB.getAge()));
        return "personList?faces-redirect=true";
    }*/
    
}
