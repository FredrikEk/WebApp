package edu.chl.hajo.shop.core;


import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All products
 *
 * @author hajo
 */
@Stateless
public class ProductCatalogue extends AbstractDAO<Product, Long>
        implements IProductCatalogue {

    @PersistenceContext
    private EntityManager em;
    
    public ProductCatalogue() {
        super(Product.class);
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
    // Factory method
    public static IProductCatalogue newInstance() {
        return new ProductCatalogue();
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> found = new ArrayList<>();
        for (Product p : findRange(0, count())) {
            if (p.getName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }
}
