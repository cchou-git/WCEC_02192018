package org.wcec.retreat.app;

import java.util.ArrayList;
import java.util.List;

import org.wcec.retreat.entity.BuildingTbl;
import org.wcec.retreat.entity.RegistrationTbl;
import org.wcec.retreat.entity.RoomTbl;
import org.wcec.retreat.model.RegistrationRecord;
import org.wcec.retreat.model.RegistrationRecordCollection;

import com.vaadin.server.Setter;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AdminDisplayManager {
	final Grid<RegistrationRecord> registrationGrid = new Grid(RegistrationRecord.class); 
	RegistrationRecordCollection mgr; 
	UIHelper uiHelper = new UIHelper();

	public void populateAssignedLodgingRegistrationGrid(VerticalLayout mLayout, List<BuildingTbl> bList, RegistrationRecordCollection mgrOnRight) {
		mgr = mgrOnRight;
		registrationGrid.setColumns(
				"roomNumber", 
				"formNumber",
				"chineseName", 
				"registrationEnglishName",
				"age",
				"paymentAmount",
				"paymentExpected", 
				"paymentMethod",
				"paymentLogDate");
				
		
		registrationGrid.setHeight(900, Unit.PIXELS);
		registrationGrid.setWidth(100, Unit.PERCENTAGE);
		registrationGrid.setVisible(true);
		//BiConsumer<RegistrationRecord, String> chineseNameSetter = RegistrationRecord::setChineseName;
		
		Setter<RegistrationRecord, String> aSetter = RegistrationRecord::setChineseName;
		registrationGrid.getEditor().setEnabled(true);  
		registrationGrid.getColumn("chineseName")
                .setCaption("中文名")
                .setEditorComponent(new TextField())   // need a second argument for the setter!!
                .setExpandRatio(1);
		List<String> genderList = new ArrayList<String>();
		genderList.add("M");
		genderList.add("F");
		uiHelper.addSelectPropertyColumn(registrationGrid, "genders", "Gender", genderList);
		
		uiHelper.addBooleanPropertyColumn(registrationGrid, "lessThanFiveYearsOldFlag", "Less Than 5 yrs?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "fiveToTwelveYearsOldFlag", "Less Than 5 yrs?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "thirteenToEighteenYearsOldFlag", "Less Than 5 yrs?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "adultFlag", "Adult?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "fullTimeFlag", "Full Time?");
	 	
	 	uiHelper.addTextPropertyColumn(registrationGrid, "comment", "Comment"); 
	 	
		registrationGrid.getEditor().addSaveListener(event -> {
            try {
            	// this sc is a JpaController
                // sc.edit(event.getBean());
            	int test = 1;
            	test++;
            } catch (Exception ex) {
                //LOG.log(Level.SEVERE, null, ex);
            	ex.printStackTrace();
            }
        });
		 
		registrationGrid.setItems(mgr.getCollection());		
		mLayout.addComponent(registrationGrid); 
			
	}
	
	/**
	 * User needs to be able to make some preference for displaying the building.
	 * @param mLayout
	 * @param bList
	 * @param aList
	 */
	public void populateAdminRegistrationGrid(VerticalLayout mLayout, List<BuildingTbl> bList, List<RegistrationTbl> aList) { 
		
		registrationGrid.setColumns(
				"buildingName",
				"roomNumber", 
				"formNumber",
				"chineseName", 
				"registrationEnglishName",
				"age",
				"paymentAmount",
				"paymentExpected", 
				"paymentMethod",
				"paymentLogDate");
				
		if (aList != null) {
			registrationGrid.setItems(mgr.populateFromDatabase(VaadinUI.TheActiveEvent, aList));
		}
		registrationGrid.setHeight(300, Unit.PIXELS);
		registrationGrid.setWidth(100, Unit.PERCENTAGE);
		registrationGrid.setVisible(true);
		//BiConsumer<RegistrationRecord, String> chineseNameSetter = RegistrationRecord::setChineseName;
		
		Setter<RegistrationRecord, String> aSetter = RegistrationRecord::setChineseName;
		registrationGrid.getEditor().setEnabled(true);  
		registrationGrid.getColumn("chineseName")
                .setCaption("中文名")
                .setEditorComponent(new TextField())   // need a second argument for the setter!!
                .setExpandRatio(1);
		List<String> genderList = new ArrayList<String>();
		genderList.add("M");
		genderList.add("F");
		uiHelper.addSelectPropertyColumn(registrationGrid, "genders", "Gender", genderList);
		
		uiHelper.addBooleanPropertyColumn(registrationGrid, "lessThanFiveYearsOldFlag", "Less Than 5 yrs?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "fiveToTwelveYearsOldFlag", "Less Than 5 yrs?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "thirteenToEighteenYearsOldFlag", "Less Than 5 yrs?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "adultFlag", "Adult?");
		uiHelper.addBooleanPropertyColumn(registrationGrid, "fullTimeFlag", "Full Time?");
	 	
	 	uiHelper.addTextPropertyColumn(registrationGrid, "comment", "Comment"); 
	 	
		registrationGrid.getEditor().addSaveListener(event -> {
            try {
            	// this sc is a JpaController
                // sc.edit(event.getBean());
            	int test = 1;
            	test++;
            } catch (Exception ex) {
                //LOG.log(Level.SEVERE, null, ex);
            	ex.printStackTrace();
            }
        }); 
		mLayout.addComponent(registrationGrid); 
	}
}
