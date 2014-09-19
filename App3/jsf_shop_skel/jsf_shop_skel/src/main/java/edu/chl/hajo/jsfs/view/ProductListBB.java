package edu.chl.hajo.jsfs.view;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.Product;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 *
 * @author hajo
 */
@Named
@ViewScoped
public class ProductListBB implements Serializable {

    SingletonShop pc;
    int currentPage = 0;
    int pageSize = 10;
    int count;
    
    public ProductListBB() {
        
    }
    
    @Inject
    public ProductListBB(SingletonShop shop) {
        this.pc = shop;
    }
    
    @PostConstruct
    public void post() {
        count = pc.getShop().getProductCatalogue().count();
    }
    
    public void prev() {
        if(currentPage > 0) {
            currentPage--;
        }
    }
    
    public boolean isFirst() {
        return currentPage == 0;
    }
    
    public void next() {
        if(currentPage < pc.getShop().getProductCatalogue().count()/pageSize) {
            currentPage++;
        }
    }
    
    public Collection<Product> getList() {
        List<Product> products = pc.getShop().getProductCatalogue().findRange(currentPage*pageSize, pageSize);
        return products;
    }
    
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
