package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * A Customer
 * @author hajo
 */
@Entity
public class Customer extends AbstractEntity {

    private transient Cart cart = new Cart();
    @Embedded
    private Address address;
    @Column
    private String fname;
    @Column
    private String lname;
    @Column
    private String email;

    public Customer() {
        address = new Address();
        fname = "";
        lname = "";
        email = "";
    }
    
    public Customer(Address address, String fname,
            String lname, String email) {
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    
    public Customer(Long id, Address address, String fname,
            String lname, String email) {
        super(id);
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public void addProductToCart(Product product) {
        cart.add(product);
    }

    public void removeProductFromCart(Product product) {
        cart.remove(product);
    }

    public void emptyCart() {
        cart = new Cart();
    }

    public Address getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + getId() + ", address=" + address + ", "
                + "fname=" + fname + ", lname=" + lname + ", email=" + email + '}';
    }
}
