
package edu.chl.hajo.ejbsb.basic;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * A Stateful session bean
 *
 * @author hajo
 */
@Stateful
public class StatefulSB {

    private int i;   // Have state, avoid...!
    @EJB
    private ToInjectBean toInject; // Possible to inject EJB's (use Insert Code ...)

    public String sayHello() {
        toInject.doIt();
        return "Hello from stateful " + i++;
    }
    
    
    
    
}
