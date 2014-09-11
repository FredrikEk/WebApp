package jsp.listener;

import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;
import jsp.beans.RandomBean;

/**
 * Used to put objects in the different scopes Displayed in JSP pages
 *
 * @author hajo
 */
@WebListener
public class InitListener implements ServletContextListener, HttpSessionListener,
        ServletRequestListener {

    private static final Logger LOG = Logger.getLogger(InitListener.class.getName());

    Random r = new Random();

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        evt.getServletContext().setAttribute("date", new Date() );
        LOG.log(Level.INFO, "context initialized, app starting");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.log(Level.INFO, "context destroyed, app shut down");
    }

    @Override
    public void sessionCreated(HttpSessionEvent evt) {
        evt.getSession().setAttribute("random", new RandomBean() );
        LOG.log(Level.INFO, "Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOG.log(Level.INFO, "Session destroyed");
    }

    // ---- Unused for now ---
    @Override
    public void requestInitialized(ServletRequestEvent evt) {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent evt) {
    }

}
