package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All orders
 *
 * @author hajo
 */
@Stateless
public class OrderBook extends AbstractDAO<PurchaseOrder, Long>
        implements IOrderBook {

    @PersistenceContext
    private EntityManager em;
    
    // Factory method
    public static IOrderBook newInstance() {
        return new OrderBook();
    }

    public OrderBook() {
        super(PurchaseOrder.class);
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<PurchaseOrder> getByCustomer(Customer c) {
        List<PurchaseOrder> found = new ArrayList<>();
        for (PurchaseOrder po : findRange(0, count())) {
            if (po.getCustomer().equals(c)) {
                found.add(po);
            }
        }
        return found;
    }
}
