
package jsp.beans;

/**
 * This is a backing bean (putting data into a page)
 * 
 * Stored in request (preferred), session or application scope
 * (i.e Map's with specific lifespan)
 * 
 * @author hajo
 */
public class DataBean {
    
    private String data = "SillySillySilly";
    /** Creates a new instance of SillyBean */
    public DataBean() {
        System.out.println("SillyBB instantiated at " + this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
