package edu.gu.hajo.arquillian;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Basic testing of beans
 *
 * @author hajo
 */
@RunWith(Arquillian.class)
public class TestMyBeans {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClass(MyCDIBean.class)
                .addClass(MyEJBBean.class)
                // Must have for @Inject to work (CDI) 
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(war.toString(true));
        return war;
    }

    @Inject
    private MyCDIBean myBean;

    @Test
    public void testCDIBean() throws Exception {
        int i = myBean.get();
        assertTrue(i == 1);
    }

    @Inject   // @EJB also ok
    private MyEJBBean ejbBean;

    @Test
    public void testEJBBean() throws Exception {
        int i = ejbBean.get();
        assertTrue(i == 1);
    }

}
