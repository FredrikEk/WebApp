
package jsp.beans;

import java.util.Random;

/**
 *This is a backing bean (putting data into a page)
 * 
 * Stored in request (preferred), session or application scope
 * (i.e Map's with specific lifespan)
 * @author hajo
 */
public class RandomBean {

    private final Random rand  = new Random();

    public int getRandomInt() {
        return rand.nextInt(10);
    }
}
