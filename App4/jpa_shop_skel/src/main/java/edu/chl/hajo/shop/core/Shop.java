package edu.chl.hajo.shop.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * Shop is a container for other containers NOTE: Uses Java 7
 *
 * @author hajo
 */
@Named("main")
@ApplicationScoped
public final class Shop implements IShop {

    @EJB
    private ProductCatalogue productCatalogue;
    @EJB
    private CustomerRegistry customerRegistry;
    @EJB
    private OrderBook orderBook;

    public Shop() {
        //initTestData();
        Logger.getAnonymousLogger().log(Level.INFO, "Shop alive");
    }

    public static IShop newInstance() {
        return new Shop();
    }

    @Override
    public ICustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    @Override
    public IOrderBook getOrderBook() {
        return orderBook;
    }

    @Override
    public IProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }

    // If we have no database we can use this
    private void initTestData() {

        // Add new data
        productCatalogue.create(new Product("banana", 11));
        productCatalogue.create(new Product("apple", 22));
        productCatalogue.create(new Product("pear", 33));
        productCatalogue.create(new Product("pineapple", 44));
        
        productCatalogue.create(new Product("orange", 55));
        productCatalogue.create(new Product("blackberry", 66));
        productCatalogue.create(new Product("blueberry", 77));
        productCatalogue.create(new Product("avocado", 88));
        
        productCatalogue.create(new Product("apricot", 99));
        productCatalogue.create(new Product("lemon", 100));
        productCatalogue.create(new Product("mango", 110));
        productCatalogue.create(new Product("melon", 120));
        
        productCatalogue.create(new Product("plum", 130));
        productCatalogue.create(new Product("satsuma", 140));
        productCatalogue.create(new Product("nectarine", 150));
        productCatalogue.create(new Product("lime", 160));
        
        productCatalogue.create(new Product("grape", 170));
        productCatalogue.create(new Product("fig", 180));

        customerRegistry.create(new Customer(new Address("aaa", 1, "aaa"),
                "arne", "arnesson", "arne@gmail.com"));
        customerRegistry.create(new Customer(new Address("bbbb", 2, "bbb"),
                "berit", "beritsson", "berit@gmail.com"));
        customerRegistry.create(new Customer(new Address("ccc", 3, "ccc"),
                "cecilia", "ceciliasson", "cecila@gmail.com"));

        Customer c = customerRegistry.getByName("arne").get(0);
        for (Product p : productCatalogue.findRange(0, 2)) {
            c.addProductToCart(p);
        }
        orderBook.create(new PurchaseOrder(c, c.getCart().getAsOrderItems()));

    }
}
