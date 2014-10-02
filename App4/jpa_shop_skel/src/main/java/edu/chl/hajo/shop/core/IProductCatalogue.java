
package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to product catalogue
 * @author hajo
 */
@Local
public interface IProductCatalogue extends IDAO<Product, Long> {

    public List<Product> getByName(String name);
     
}
