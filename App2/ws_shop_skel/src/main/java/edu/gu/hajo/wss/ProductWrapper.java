package edu.gu.hajo.wss;


import edu.chl.hajo.shop.core.Product;
import javax.xml.bind.annotation.*;

/**
 * Need this because we have an immutable class Product
 * (JAX-RS must have default constructor)
 * 
 * Possible other solution: Change in model to make Product mutable or 
 * http://blog.bdoughan.com/2010/12/jaxb-and-immutable-objects.html
 * @author hajo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Product", propOrder = {
    "id",
    "name",
    "price"
})
public class ProductWrapper {

    private Product product;

    protected ProductWrapper() { // Must have
    }
   
    public ProductWrapper(Product product) { 
        this.product = product; 
    }
    
    @XmlElement
    public String getName() {
        return product.getName();
    }

    @XmlElement //If serving XML we should use @XmlAttribute 
    public Long getId() {
        return product.getId();
    }

    @XmlElement
    public double getPrice() {
        return product.getPrice();
    }
}
