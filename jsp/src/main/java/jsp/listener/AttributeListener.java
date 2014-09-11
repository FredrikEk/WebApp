
package jsp.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Used to listen when session attributes added/modified/removed 
 *
 * @author hajo
 */
@WebListener
public class AttributeListener implements HttpSessionAttributeListener {

    private static final Logger LOG = Logger.getLogger(AttributeListener.class.getName());

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession s = event.getSession();
        String name = event.getName();
        Object value = event.getValue();
        LOG.log(Level.INFO, "Attribute added {0} {1}", new Object[]{name, value});
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSession s = event.getSession();
        String name = event.getName();
        Object value = event.getValue();
        LOG.log(Level.INFO, "Attribute removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSession s = event.getSession();
        String name = event.getName();
        Object value = event.getValue();
        LOG.log(Level.INFO, "Attribute replaces {0} {1}", new Object[]{name, value});
    }

}
