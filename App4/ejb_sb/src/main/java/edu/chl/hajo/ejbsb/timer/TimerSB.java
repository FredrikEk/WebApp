
package edu.chl.hajo.ejbsb.timer;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author hajo
 */
@Stateless
public class TimerSB {
   
    // PROBLEMS! Did work but not now (NetBeans 8.0.1 / GlassFish 4.0 (build 89)
    // HAVE TO RESTART NetBeans to get it to work.
    //@Schedule(minute = "*", second = "*/5", dayOfMonth = "*", month = "*", year = "*", 
      //     hour = "9-17", dayOfWeek = "Mon-Fri", persistent = false)
    public void myTimer() {
        Logger.getAnonymousLogger().log(Level.INFO, "Timer event: {0}", new Date());
    }     
}
