package edu.gu.hajo.wss;

import edu.chl.hajo.shop.core.IShop;
import edu.gu.hajo.wss.core.SingletonShop;
import javax.ws.rs.Path;

/**
 * REST Web Service for the product catalogue (an Adapter) We always use
 * Response as return value (probably easier to modify)
 *
 *
 * @author hajo
 */
@Path("products") // Leading trailing slash doesn't matter, see web.xml
public class ProductCatalogueResource {

    private final IShop shop = SingletonShop.INSTANCE.getShop();

   
}
