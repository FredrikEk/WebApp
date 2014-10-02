package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All customers
 *
 * @author hajo
 */
@Stateless
public class CustomerRegistry extends AbstractDAO<Customer, Long>
        implements ICustomerRegistry {

    @PersistenceContext
    private EntityManager em;
    
    // Factory method
    public static ICustomerRegistry newInstance() {
        return new CustomerRegistry();
    }

    public CustomerRegistry() {
        super(Customer.class);
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Customer> getByName(String name) {
        List<Customer> found = new ArrayList<>();
        for (Customer c : findRange(0, count())) {
            if (c.getFname().equals(name) || c.getLname().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
}
