
package edu.chl.hajo.ejbsb.basic;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;

/**
 * A Singleton EJB also demonstrating asynch calls
 * @author hajo
 */
@Singleton  // NOTE EJB Singleton
public class SingletonSB {

    public String sayHello() {
        return "Hello from singleton";
    }
    
    // Could also be void if client doesn't care about result...
    @Asynchronous
    public Future<String> sayHelloAsync() {
        // Brutal delay, bad style probably don't work
        // More realistic scenario: Send email
        String s = "async";
        for(int i = 0 ; i < 50000 ; i++){
            s += String.valueOf(i);
        }
        Logger.getAnonymousLogger().log(Level.INFO, s.substring(0, 3));
        
        return new AsyncResult<>("Asynch Hello");
    }
}
