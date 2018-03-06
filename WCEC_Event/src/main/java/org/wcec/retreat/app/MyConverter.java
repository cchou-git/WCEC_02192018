package org.wcec.retreat.app;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class MyConverter implements Converter<String, Boolean> {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	  public Result<Boolean> convertToModel(String fieldValue, ValueContext context) {
	    // Produces a converted value or an error
	    try {
	    	if (fieldValue != null && fieldValue.length() > 0 && fieldValue.toLowerCase().equals("true")) 
	    	{
	    		return Result.ok(Boolean.valueOf(true));
	    	} else 
	    	{
	    		return Result.ok(Boolean.valueOf(false));
	    	}
	    } catch (Exception e) {
	      // error is a static helper method that creates a Result
	      return Result.error("Please enter true or false");
	    }
	  }

	  @Override
	  public String convertToPresentation(Boolean aBoolean, ValueContext context) {
	    // Converting to the field type should always succeed,
	    // so there is no support for returning an error Result.
		if (aBoolean) {
			return "true";
		} else {
			return "false";
		}
	  }

}