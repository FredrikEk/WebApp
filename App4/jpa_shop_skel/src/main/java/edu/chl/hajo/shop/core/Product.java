package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;



/**
 * A Product
 * @author hajo
 */
@Entity
public class Product extends AbstractEntity {

    @Column
    private String name;
    @Column
    private double price;

    public Product() {
        name = "";
        price = 0;
    }
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
     
    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + name + ", price=" + price + '}';
    }
}
