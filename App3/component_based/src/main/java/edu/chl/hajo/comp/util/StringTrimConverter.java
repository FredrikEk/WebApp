package edu.chl.hajo.comp.util;

import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converter to remove leading/trailing blank space
 * This should kick in whenever a String is converted
 *
 * @author Adam Waldenberg
 */
@FacesConverter(forClass = String.class)
public class StringTrimConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(StringTrimConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.info("StringTrimConverterCalled");
        return value.trim();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LOG.info("StringTrimConverterCalled");
        return value.toString().trim();
    }
}
