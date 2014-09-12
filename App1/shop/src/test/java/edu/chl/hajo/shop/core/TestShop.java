package edu.chl.hajo.shop.core;

import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestShop {

    IShop s;

    @Before // Run before each test to get fresh data
    public void before() {
        s = Shop.newInstance();
    }

    @Test
    public void findAllCustomers() {
        int count = s.getCustomerRegistry().count();
        List<Customer> cs = s.getCustomerRegistry().findAll();
        assertTrue(cs.size() == 3);
    }

    @Test
    public void testFindRangeProducts() {
        int count = s.getProductCatalogue().count();
        List<Product> ps = s.getProductCatalogue().findRange(0, count);
        assertTrue(ps.size() == 18);

        ps = s.getProductCatalogue().findRange(10, 4);
        assertTrue(ps.size() == 4);
        
        ps = s.getProductCatalogue().findRange(16, 4);
        assertTrue(ps.size() == 2);
        
        
    }

    @Test
    public void getAllOrdersAndItems() {
        List<Customer> cs = s.getCustomerRegistry().getByName("arne");
        List<PurchaseOrder> ps = s.getOrderBook().getByCustomer(cs.get(0));
        assertTrue(ps.size() == 1);
        //System.out.println("*****" + ps.get(0).getItems().size());
        assertTrue(ps.get(0).getItems().size() == 2);  // TODO Check
    }

    @Test
    public void removeOneItemFromOrder() {
        List<Product> ps = s.getProductCatalogue().getByName("pear");
        Product pear = ps.get(0);
        List<Customer> cs = s.getCustomerRegistry().getByName("arne");
        Customer arne = cs.get(0);
        List<PurchaseOrder> pos = s.getOrderBook().getByCustomer(arne);
        PurchaseOrder order = pos.get(0);
        List<OrderItem> ois = order.getItems();
        Iterator<OrderItem> iois = ois.iterator();
        while (iois.hasNext()) {
            OrderItem oi = iois.next();
            if (oi.getProduct().equals(pear)) {
                iois.remove();  // Have only one pear, so ok for now
            }
        }
        assertTrue(order.getCustomer().equals(arne) && order.getItems().size() == 2);
    }

    @Test
    public void buyThreeItems() {
        List<Customer> cs = s.getCustomerRegistry().getByName("arne");
        Customer c = cs.get(0);
        List<Product> pl = s.getProductCatalogue().getByName("banana");
        Product banana = pl.get(0);
        assertTrue(pl.size() == 1);
        pl = s.getProductCatalogue().getByName("apple");
        Product apple = pl.get(0);

        c.addProductToCart(banana);
        c.addProductToCart(banana);
        c.addProductToCart(apple);
        List<OrderItem> items = c.getCart().getAsOrderItems();
        assertTrue(items.size() == 2); // Bananas added up
        int total = 0;
        for (OrderItem i : items) {
            total += i.getQuantity();
        }
        assertTrue(total == 3);

        PurchaseOrder po = new PurchaseOrder(c, c.getCart().getAsOrderItems());
        IOrderBook ob = s.getOrderBook();
        ob.create(po);

        List<PurchaseOrder> os = ob.getByCustomer(c);
        int nOrders = os.size();
        assertTrue(nOrders == 2); // One is default
    }
}
