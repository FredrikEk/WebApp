package edu.chl.hajo.jsfs.ctrl;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.jsfs.view.DeleteProductBB;
import edu.chl.hajo.shop.core.IProductCatalogue;
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
public class DeleteProductCtrl{


    private static final Logger LOG = Logger.getLogger(AddProductCtrl.class.getName());
    private IProductCatalogue pc;
    private DeleteProductBB productBB;

    protected DeleteProductCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "DeleteProductCtrl alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "DeleteProductCtrl to be destroyed {0}", this);
    }

    @Inject
    public DeleteProductCtrl(SingletonShop shop) {
        this.pc = shop.getShop().getProductCatalogue();
    }
    
    // Lie this --------------------
    @Inject
    public void setProductBB(DeleteProductBB productBB) {
        this.productBB = productBB;
    }
   
    public String delete() {
        LOG.log(Level.INFO, "Save: {0}" + productBB);
        pc.delete(productBB.getId());
        return "productList?faces-redirect=true";
    }

}
