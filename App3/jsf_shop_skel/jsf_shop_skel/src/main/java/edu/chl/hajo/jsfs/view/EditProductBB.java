
package edu.chl.hajo.jsfs.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named
@RequestScoped
public class EditProductBB implements Serializable{

    
    private Long id;
    private String name;
    private float price;

    @PostConstruct
    public void post() {
        //LOG.log(Level.INFO, "PersonDetailBB alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        //LOG.log(Level.INFO, "PersonDetailBB to be destroyed {0}", this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        //LOG.log(Level.INFO, "Set id {0}", id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //LOG.log(Level.INFO, "Set fname {0}", fname);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        //LOG.log(Level.INFO, "Set age {0}", age);
    }

    @Override
    public String toString() {
        return "EditProductBB{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }

    
}
