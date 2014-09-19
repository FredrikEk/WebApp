package edu.chl.hajo.cycle;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Not an attached or managed bean object (can't use
 * @PostContruct).
 *
 * See faces-config.xml See use of FacesContext below
 *
 * @author localhajo
 */
public class MyPhaseListener implements PhaseListener {

    // Dump component tree or not
    private boolean dump = false;

    public MyPhaseListener() {
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RESTORE_VIEW) {
            if (event.getFacesContext().isPostback()) {
                LogUtil.info("------- THIS IS A POSTBACK ----------");
                HttpServletRequest req = (HttpServletRequest) event.getFacesContext().
                        getExternalContext().getRequest();
                LogUtil.info( "To: " +  req.getRequestURL().toString());
            } else {
                LogUtil.info("------- THIS IS AN INITIAL REQUEST ----------");
            }
        } else if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES) {
            if (dump) {
                FacesContext fC = event.getFacesContext();
                UIViewRoot root = fC.getViewRoot();
                if (root != null) {
                    LogUtil.info("View id is " + root.getViewId());
                    LogUtil.info("Number of children are : " + root.getChildCount());
                    dumpTree(root, "-");
                    System.out.println();
                }
            }
        }
        LogUtil.info("Before: " + event.getPhaseId(), LogUtil.BLUE);
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        LogUtil.info("After: " + event.getPhaseId(), LogUtil.BLUE);
        LogUtil.info("View root: " + event.getFacesContext().getViewRoot());
    }

    // Print out components in component tree
    private void dumpTree(UIComponent comp, String indent) {
        if (comp.getChildCount() == 0) {
            return;
        } else {
            List<UIComponent> children = comp.getChildren();
            for (UIComponent c : children) {
                LogUtil.info(indent + " Child " + c.getId()
                        + " family " + c.getFamily(), LogUtil.CYAN);
                dumpTree(c, indent + " > ");
            }
        }
    }
}
