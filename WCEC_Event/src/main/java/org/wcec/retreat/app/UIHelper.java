package org.wcec.retreat.app;

import java.util.List;

import org.wcec.retreat.model.Meal;
import org.wcec.retreat.model.RegistrationRecord;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.renderers.HtmlRenderer;

public class UIHelper {
	/**
	 * Add a Select into the column of the grid, where the selection values of String is passed 
	 * to this method as well.
	 * @param theGrid
	 * @param propertyName
	 * @param caption
	 * @param theOptionList
	 */
	public void addSelectPropertyColumn(Grid theGrid, String propertyName, String caption, List theOptionList) {
		ListSelect select = new ListSelect(caption);
		Column<RegistrationRecord, String> propertyColumn = theGrid.addColumn(propertyName);
		select.setItems(theOptionList); 
		propertyColumn.setEditorComponent(select)   // need a second argument for the setter!!
        .setExpandRatio(1);  
	}

	/**
	 * Add a Column to the grid where the member type is Boolean. 
	 * Add a CheckBox for the renderer and editor.
	 * 
	 * @param theGrid
	 * @param propertyName
	 * @param caption
	 */
	public void addBooleanPropertyColumn(Grid theGrid, String propertyName, String caption) {
		CheckBox bBox = new CheckBox();  
		Column<RegistrationRecord, String> adultFlagColumn = theGrid.addColumn(record->
			"<span class=\"v-checkbox v-widget\"><input type=\"checkbox\" id=\"my-uid-1\" " + returnChecked(propertyName, record) + "  > <label for=\"my-uid-1\"></label> </span>",
			new HtmlRenderer());
		adultFlagColumn.setId(propertyName)
		.setCaption(caption)
		.setEditorComponent(bBox) 
		.setExpandRatio(1); 
	}

	String returnChecked(String propName, Object inputRecord) {
		if (inputRecord instanceof RegistrationRecord) {
			RegistrationRecord record = (RegistrationRecord) inputRecord;
			if (propName.equalsIgnoreCase("adultFlag")) {
				if (record.getAdultFlag()) 
					return "checked";
				else 
					return "";	
			} else if (propName.equalsIgnoreCase("fullTimeFlag")) {
				if (record.getFullTimeFlag()) 
					return "checked";
				else 
					return "";
			} else if (propName.equalsIgnoreCase("greaterThanFiveYearsOldFlag")) {
				if (record.getGreaterThanFiveYearsOldFlag()) 
					return "checked";
				else 
					return "";
			}
		} else if (inputRecord instanceof Meal) {
			Meal record = (Meal) inputRecord;
			if (propName.equalsIgnoreCase("Selected")) {
				if (record.isSelected()) 
					return "checked";
				else 
					return "";	
			}  
		}
		return "checked";
		
	}


	/**
	 * Add a column of text along with an editor.
	 * @param theGrid
	 * @param propertyName
	 * @param caption
	 */
	public void addTextPropertyColumn(Grid theGrid, String propertyName, String caption) {
		TextField editorField = new TextField();
		Column<RegistrationRecord, String> propertyColumn = theGrid.addColumn(propertyName);
		propertyColumn.setEditorComponent(editorField)    
			.setExpandRatio(1); 
	}
	
}
