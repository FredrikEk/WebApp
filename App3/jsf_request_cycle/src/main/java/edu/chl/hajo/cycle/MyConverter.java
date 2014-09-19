
package edu.chl.hajo.cycle;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * A custom converter, converting in and outgoing values
 * Don't need this it's just to see when conversion id performed during 
 * the request cycle
 * @author hajo
 */
@FacesConverter(value = "myConverter")
public class MyConverter implements Converter {

    public MyConverter(){
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LogUtil.info("*** Converting to Object", LogUtil.RED);
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LogUtil.info("*** Converting to String", LogUtil.RED);
        return "";
    }
}
