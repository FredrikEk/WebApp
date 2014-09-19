
package edu.chl.hajo.cycle;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility to trace and colorize output
 * @author hajo
 */
public class LogUtil {
    
    private LogUtil(){}

    // ANSI terminal foreground color codes
    public static final String RED = "\033[31m ";
    public static final String GREEN = "\033[32m ";
    public static final String BLUE = "\033[34m ";
    public static final String MAGENTA = "\033[35m ";
    public static final String CYAN = "\033[36m ";
    public static final String RESET = "\033[0m ";
    

    public static void isAlive(Object o) {
        Logger.getLogger(o.toString()).log(Level.INFO, o.toString());
    }

    public static void isAlive(Object o, String color) {
       Logger.getAnonymousLogger().log(Level.INFO, "{0}{1}" +  RESET, new Object[]{color, o.toString()});     
    }

    public static void info(Object o, String message) {
        Logger.getLogger(o.toString()).log(Level.INFO, "{0} : {1}", new Object[]{o, message});
    }

    public static void info(String message) {
        Logger.getAnonymousLogger().log(Level.INFO, message );
    }

    public static void info(String message, String color) {
        Logger.getAnonymousLogger().log(Level.INFO, "{0}{1}" + RESET, new Object[]{color, message});
    }

    public static void info(Object o, String message, String color) {
        Logger.getLogger(o.toString()).log(Level.INFO, "{0}{1} : {2}" + RESET, new Object[]{color, o, message});
    }
}
