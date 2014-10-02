
package edu.chl.hajo.ejbsb.basic;

import javax.ejb.Stateless;

/**
 * A Stateless session bean 
 * @author hajo
 */
@Stateless
public class StatelessSB {

    public String sayHello() {
        return "Hello from stateless";
    }
}
