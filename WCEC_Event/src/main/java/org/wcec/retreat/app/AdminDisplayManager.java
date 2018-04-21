package org.wcec.retreat.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.wcec.retreat.entity.BuildingTbl;
import org.wcec.retreat.entity.LodgingAssignmentTbl;
import org.wcec.retreat.entity.PaymentTbl;
import org.wcec.retreat.entity.RegistrationTbl;
import org.wcec.retreat.model.RegistrationRecord;
import org.wcec.retreat.model.RegistrationRecordCollection;
import org.wcec.retreat.repo.PaymentTblRepo;
import org.wcec.retreat.repo.RegistrationTblRepo;

import com.vaadin.server.Setter;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class AdminDisplayManager {
	RegistrationRecordCollection mgr; 
	Grid<RegistrationRecord> registrationGrid;
	UIHelper uiHelper = new UIHelper();
	UI myUI;  

	public void setMyUI(UI myUI) {
		this.myUI = myUI;
	}

	public void populateAssignedLodgingRegistrationGrid(VerticalLayout mLayout, 
			List<BuildingTbl> bList, RegistrationRecordCollection mgrOnRight, final Grid<RegistrationRecord> regGrid,
			PaymentTblRepo pRepo, RegistrationTblRepo rRepo
			) {
		mgr = mgrOnRight;
		registrationGrid = regGrid;
		paymentRepo = pRepo;
		regRepo = rRepo;
		registrationGrid.setColumns(
				"buildingName",
				"roomNumber", 
				"formNumber",
				"chineseName", 
				"englishName", 
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
		registrationGrid.setSelectionMode(SelectionMode.MULTI);
		registrationGrid.addComponentColumn(record -> {
		      Button button = new Button("Payment!");
		      button.addClickListener(click ->
		      	makePaymentRegistrationRecord(record, registrationGrid, mgr)); 
		      return button;
		}); 
				 
		registrationGrid.setItems(mgr.getCollection());
		Label aLabel = new Label("Assigned registrants:");
		mLayout.addComponent(aLabel);
		mLayout.addComponent(registrationGrid); 
			
	}
	PaymentTblRepo paymentRepo;
	RegistrationTblRepo regRepo;
	
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
	
	/**
	 * Make payment arrangement.
	 * @param aRecord
	 */
	void makePaymentRegistrationRecord(RegistrationRecord aRecord, Grid<RegistrationRecord> registrationGrid, RegistrationRecordCollection mgr) {
		// Plug in Jay's code here.
		displayPaymentEditor(aRecord, registrationGrid, mgr);
		
	}
	
	void displayPaymentEditor(RegistrationRecord aRecord, Grid<RegistrationRecord> registrationGrid, RegistrationRecordCollection mgr) {
		PaymentEditorWindow subWindow = new PaymentEditorWindow(aRecord, paymentRepo, regRepo, registrationGrid, mgr);
        subWindow.center(); 
        subWindow.setModal(true);
        // Open it in the UI
        myUI.addWindow(subWindow);
        
        return;
	}
}
