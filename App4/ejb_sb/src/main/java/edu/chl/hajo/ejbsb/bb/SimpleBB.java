
package edu.chl.hajo.ejbsb.bb;

import edu.chl.hajo.ejbsb.basic.SingletonSB;
import edu.chl.hajo.ejbsb.basic.StatefulSB;
import edu.chl.hajo.ejbsb.basic.StatelessSB;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Used to call the different EJB's
 * @author hajo
 */
@Named("bb")
@SessionScoped
public class SimpleBB implements Serializable {

    @EJB
    private StatelessSB statelessSB;
    @EJB
    private StatefulSB statefulSB;
    @EJB
    private SingletonSB singletonSB;
    private String msg = "No message yet";
    
    @PostConstruct
    public void post(){
         Logger.getAnonymousLogger().log(Level.INFO, "Msg is {0}", msg);
    }

    public void callBean(String s) {
        Logger.getAnonymousLogger().log(Level.INFO, "Backing bean {0} ", s);
        switch (s) {
            case "stateless":
                msg = statelessSB.sayHello();
                break;
            case "stateful":
                msg = statefulSB.sayHello();
                break;
            case "singleton":
                msg = singletonSB.sayHello();
                break;
            case "async":
                Future<String> fmsg = singletonSB.sayHelloAsync();
                try {
                    msg = fmsg.get();
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(SimpleBB.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                ;
        }
        Logger.getAnonymousLogger().log(Level.INFO, "Msg is {0}", msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
