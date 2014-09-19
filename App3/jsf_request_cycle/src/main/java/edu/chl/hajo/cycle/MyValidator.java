
package edu.chl.hajo.cycle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Don't need this it's just to demonstrate when validation 
 * is performed
 * @author hajo
 */
@FacesValidator(value = "myValidator")
public class MyValidator implements Validator{

    public MyValidator(){
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        LogUtil.info("*** Validating" , LogUtil.RED);
    }

}
