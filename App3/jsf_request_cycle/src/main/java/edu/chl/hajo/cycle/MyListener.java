package edu.chl.hajo.cycle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Also a managed bean. This bean have the listener methods, like
 * actionPerformed in Swing Often you create a single bean with all methods form
 * this and MyBean
 *
 * @author hajo
 */
@Named("MyListener")
@RequestScoped
public class MyListener {

    @PostConstruct
    public void postContruct() {
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }

    // Any method with this param works!
    public void valueChange(ValueChangeEvent evt) {
        LogUtil.info("*** valueChanged", LogUtil.RED);
        LogUtil.info("(new) " + evt.getNewValue());
        LogUtil.info("(old) " + evt.getOldValue());
    }

    // Any method with this param works!
    public void somethingClicked(ActionEvent evt) {
        LogUtil.info("*** somethingClicked", LogUtil.RED);
    }

    // Any method with returntype String ok
    public String navigate() {
        LogUtil.info("*** navigate ", LogUtil.RED);
        return null;  // This means return to same page.
    }
}
