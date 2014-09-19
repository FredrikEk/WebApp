package edu.chl.hajo.cycle;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

/**
 * A simple managed bean. 
 * Data set by JSF cycle
 * @author hajo 
 */
@Named("MyBean")
@SessionScoped //@RequestScoped 
public class MyBean implements Serializable {

    private String data = "default value";

    @PostConstruct
    public void post() {
        LogUtil.isAlive(this, LogUtil.GREEN);
    }
    
    @PreDestroy
    public void pre() {
        LogUtil.info(this + " to be destroyed", LogUtil.GREEN);
    }

    public String getData() {
        LogUtil.info("*** Getting data from model. Data is: " + data, LogUtil.RED);
        return data;
    }

    public void setData(String data) {
        LogUtil.info("*** Setting data in update model to " + data, LogUtil.RED);
        this.data = data;
    }

    public void render(ComponentSystemEvent evt) {
        LogUtil.info("*** Call prerender " +  evt.toString());
    }
}
