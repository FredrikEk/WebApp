
package edu.gu.hajo.arquillian;

import javax.ejb.Stateless;

/**
 *
 * @author hajo
 */
@Stateless
public class MyEJBBean {
    public int get(){
        return 1;
    }
}
