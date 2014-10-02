
package edu.gu.hajo.arquillian;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named
@SessionScoped
public class MyCDIBean implements Serializable{
    public int get(){
        return 1;
    }
}
