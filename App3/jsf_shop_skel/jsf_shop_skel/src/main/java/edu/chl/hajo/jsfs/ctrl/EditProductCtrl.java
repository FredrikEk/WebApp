package edu.chl.hajo.jsfs.ctrl;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.jsfs.view.EditProductBB;
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
public class EditProductCtrl{

    private static final Logger LOG = Logger.getLogger(AddProductCtrl.class.getName());
    private IProductCatalogue pc;
    private EditProductBB productBB;

    protected EditProductCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "EditProductCtrl alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "EditProductCtrl to be destroyed {0}", this);
    }

    @Inject
    public EditProductCtrl(SingletonShop shop) {
        this.pc = shop.getShop().getProductCatalogue();
    }
    
    // Lie this --------------------
    @Inject
    public void setProductBB(EditProductBB productBB) {
        this.productBB = productBB;
    }
   
    public String update() {
        LOG.log(Level.INFO, "Save: {0}" + productBB);
        pc.update(new Product(productBB.getId(), productBB.getName(), productBB.getPrice()));
        return "productList?faces-redirect=true";
    }

}
