package org.wcec.retreat.app;


import java.util.Locale;

import org.springframework.core.convert.ConversionException;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.server.FontAwesome;

public class BooleanToFontIconConverter implements Converter<String, Boolean> {
     
    
    
    
	@Override
	public Result<Boolean> convertToModel(String value, ValueContext context) {
	    throw new RuntimeException("Not supported");
	   }

	@Override
	public String convertToPresentation(Boolean value, ValueContext context) {
		 if (Boolean.TRUE.equals(value)) {
	            return FontAwesome.CHECK_SQUARE_O.getHtml();
	        } else {
	            return FontAwesome.SQUARE_O.getHtml();
	        } 
	}
}