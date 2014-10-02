
package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to order book
 * @author hajo
 */
@Local
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {

    List<PurchaseOrder> getByCustomer(Customer c);
    
}
