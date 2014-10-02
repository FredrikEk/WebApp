
package edu.chl.hajo.ejbsb.basic;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 * Demonstrating injection of EJB's, see StatefulSB
 * @author hajo
 */
@Stateless
public class ToInjectBean implements Serializable {

    public void doIt() {
        Logger.getAnonymousLogger().log(Level.INFO, "Did it");
    }
    
}
