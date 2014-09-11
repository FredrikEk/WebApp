
package jsp.beans;

import java.util.Random;

/**
 * Producing a random number table
 * This is a backing bean (putting data into a page)
 * 
 * Stored in request (preferred), session or application scope
 * (i.e Map's with specific lifespan)
 * 
 * @author hajo
 */
public class TableBean {

    public final static int MAX = 10;
    Random r = new Random();
    private String[][] data = new String[MAX][MAX - 5];

    public TableBean() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX - 5; j++) {
                data[i][j] = String.valueOf(r.nextLong());
            }
        }

    }

    public String[][] getData() {
        return data;
    }
}
