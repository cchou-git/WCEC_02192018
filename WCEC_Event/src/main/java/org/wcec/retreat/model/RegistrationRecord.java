package org.wcec.retreat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.wcec.retreat.app.VaadinUI;
import org.wcec.retreat.entity.EmailTbl;
import org.wcec.retreat.entity.EventTbl;
import org.wcec.retreat.entity.GroupTbl;
import org.wcec.retreat.entity.MealTbl;
import org.wcec.retreat.entity.PersonTbl;
import org.wcec.retreat.entity.RegistrationTbl;
import org.wcec.retreat.entity.RoomTbl;
import org.wcec.retreat.repo.RegistrationTblRepo;

import com.vaadin.server.VaadinSession;

/**
 * @author chout
 *
 */

public class RegistrationRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	int count;
	
	String comment = "";
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	String chineseName = "";
	String firstName = "John";
	String lastName = "Doe";
	String gender = "M";
	Boolean adultFlag = true;
	Boolean greaterThanFiveYearsOldFlag = false;
	Boolean thirteenToEighteenFlag = false;
	
	
	
	String freeWillOffering = "0.0";
	Set<DateTime> attendingDates = new HashSet<DateTime>();
	RegistrationTbl dbRecord = null;
	Boolean fullTimeFlag = true; 
	
	/*
	 * These are for the most stupid Vaadin checkboxes!
	 * We reserve five dates - that should be sufficient for 
	 * retreat.
	 */
	Boolean attendingDate1 = true;
	public RegistrationTbl getDbRecord() {
		return dbRecord;
	}

	public void setDbRecord(RegistrationTbl dbRecord) {
		this.dbRecord = dbRecord;
	}
	Boolean attendingDate2 = true;
	Boolean attendingDate3 = true;
	Boolean attendingDate4 = true;
	Boolean attendingDate5 = true; 

	public RegistrationRecord() {
		genders.add("M");
		genders.add("F");
		mealPlan = new MealPlan();
		mealPlan.init();
	}

	public Boolean getAdultFlag() {
		return adultFlag;
	}

	public void setAdultFlag(Boolean adultFlag) {
		this.adultFlag = adultFlag;
	}

	public Boolean getAttendingDate1() {
		return attendingDate1;
	}

	public void setAttendingDate1(Boolean attendingDate1) {
		if (attendingDate1) {
			this.addAttendingDate(eventDates.get(0));
		} else {
			this.removeAttendingDate(eventDates.get(0));
		}
		this.attendingDate1 = attendingDate1;
	}

	public Boolean getAttendingDate2() {
		
		return attendingDate2;
	}

	public void setAttendingDate2(Boolean attendingDate2) {
		if (attendingDate2) {
			this.addAttendingDate(eventDates.get(1));
		} else {
			this.removeAttendingDate(eventDates.get(1));
		}
		this.attendingDate2 = attendingDate2;
	}

	public Boolean getAttendingDate3() {
		return attendingDate3;
	}

	public void setAttendingDate3(Boolean attendingDate3) {
		if (attendingDate3) {
			this.addAttendingDate(eventDates.get(2));
		} else {
			this.removeAttendingDate(eventDates.get(2));
		}
		this.attendingDate3 = attendingDate3;
	}

	public Boolean getAttendingDate4() {
		return attendingDate4;
	}

	public void setAttendingDate4(Boolean attendingDate4) {
		if (attendingDate4) {
			this.addAttendingDate(eventDates.get(3));
		} else {
			this.removeAttendingDate(eventDates.get(3));
		}
		this.attendingDate4 = attendingDate4;
	}

	public Boolean getAttendingDate5() {
		return attendingDate5;
	}

	public void setAttendingDate5(Boolean attendingDate5) {
		if (attendingDate5) {
			this.addAttendingDate(eventDates.get(4));
		} else {
			this.removeAttendingDate(eventDates.get(4));
		}
		this.attendingDate5 = attendingDate5;
	}
	List<DateTime> eventDates;
	
	EventTbl myEvent;
	 
	public void initialize(EventTbl anEvent, MealTemplate aTemplate) {
		myEvent = anEvent;
		Date startDate0 = anEvent.getStartDt();
		Date endDate0 = anEvent.getEndDt();
		DateTime startDate = new DateTime(startDate0);
		DateTime endDate = new DateTime(endDate0);
		
		int days = Days.daysBetween(startDate, endDate).getDays() + 1;
		eventDates = new ArrayList<DateTime>(days);  // Set initial capacity to `days`.
		for (int i=0; i < days; i++) {
		    DateTime d = startDate.withFieldAdded(DurationFieldType.days(), i);
		    eventDates.add(d);
		    addAttendingDate(d);
		} 
	}

    public Boolean getFullTimeFlag() {
		return fullTimeFlag;
	}
	public void setFullTimeFlag(Boolean fullTimeFlag) {
		this.fullTimeFlag = fullTimeFlag;
	}
	public Set<DateTime> getAttendingDates() {
		return attendingDates;
	}
	public void setAttendingDates(Set<DateTime> attendingDates) {
		this.attendingDates = attendingDates;
	}
	
	/**
	 * When starting, all dates are initialized to the global EventTbl
	 * dates. They are managed through the checkboxes form the VaadinGUI.
	 * @param aDate
	 */
	public void addAttendingDate(DateTime aDate) { 
		if (!attendingDates.contains(aDate)) {
			this.attendingDates.add(aDate);
		}
	}
	
	public void removeAttendingDate(DateTime aDate) {  
		this.attendingDates.remove(aDate);
	} 
	
	
	String specialRequest = "";
	Boolean needFancialFlag = false;
	boolean definedFlag = false; 
	Set<String> genders = new HashSet<String>();
	
	
	public Set<String> getGenders() {
		return genders;
	}

	public void setGenders(Set<String> genders) {
		this.genders = genders;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isDefinedFlag() {
		return definedFlag;
	}
	public void setDefinedFlag(boolean definedFlag) {
		this.definedFlag = definedFlag;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	 
	public String getFreeWillOffering() {
		return freeWillOffering;
	}
	public void setFreeWillOffering(String freeWillOffering) {
		this.freeWillOffering = freeWillOffering;
	}
	 
	public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
	
	
	public Boolean getGreaterThanFiveYearsOldFlag() {
		return greaterThanFiveYearsOldFlag;
	}
	public void setGreaterThanFiveYearsOldFlag(Boolean greaterThanFiveYearsOldFlag) {
		this.greaterThanFiveYearsOldFlag = greaterThanFiveYearsOldFlag;
	}
	
	
	 
	public Boolean getNeedFancialFlag() {
		return needFancialFlag;
	}
	public void setNeedFancialFlag(Boolean needFancialFlag) {
		this.needFancialFlag = needFancialFlag;
	}
	
	public boolean validateBasicInformation() {
		return 
				((chineseName != null && chineseName.trim().length() > 0) 
				&& (lastName != null && lastName.trim().length() > 0) 
				&& (firstName != null && firstName.trim().length() > 0) 
				&& (gender != null && gender.trim().length() > 0));
	}

	/**
	 * If you check "fullTimeFlag" as true and it is missing some attending dates in the
	 * HashSet then flag it.
	 * 
	 * On the other hand, if you didn't check "fullTimeFlag" but none of the dates are 
	 * selected, then flag it.  
	 * 
	 * @return
	 */
	public boolean validateAttendingDate() {
		if (this.getFullTimeFlag()) {
			for (DateTime aDT : eventDates) {
				if (!attendingDates.contains(aDT)) {
					return false;
				}
			}
		} else {
			boolean result = true;
			for (DateTime aDT : eventDates) {
				result = result && (!attendingDates.contains(aDT));
			}
			if (result) {
				return false;
			}
		}
		return true;
	}
	
	public PersonTbl createPerson(GroupTbl aGroup) {
		PersonTbl newPersonRecord = new PersonTbl();
		newPersonRecord.setChineseNm(this.getChineseName());
		newPersonRecord.setLastNm(getLastName());
		newPersonRecord.setFirstNm(getFirstName());
		newPersonRecord.setChurchTbl(VaadinUI.AllChurch.get(0));
		newPersonRecord.setAddressTbl(VaadinUI.AllAddress.get(0));
		newPersonRecord.setBirthDt(new Date());
		newPersonRecord.setGender(getGender());
		Integer groupID = (Integer) VaadinSession.getCurrent().getAttribute(VaadinUI.SESSION_GROUP_ID);
		if (groupID != null) {
			newPersonRecord.setGroupTbl(VaadinUI.AllGroup.get(0));
			newPersonRecord.setPrimaryGroupId(VaadinUI.AllGroup.get(0).getId());
		} else {
			newPersonRecord.setGroupTbl(aGroup);
			newPersonRecord.setPrimaryGroupId(aGroup.getId()); 
		}
		 
		if (this.getAdultFlag()) {
			newPersonRecord.setIsAdultFlag(true);
			newPersonRecord.setIsLessThanFive(false);
			newPersonRecord.setIsFiveToTwelve(false);
			newPersonRecord.setIsGreaterThanFive(false);
			newPersonRecord.setIsThirteenToEighteen(false);

		} else if (this.getLessThanFiveYearsOldFlag()) {
			newPersonRecord.setIsLessThanFive(true);
			newPersonRecord.setIsAdultFlag(false);
			newPersonRecord.setIsFiveToTwelve(false);
			newPersonRecord.setIsGreaterThanFive(false);
			newPersonRecord.setIsThirteenToEighteen(false);
		} else if (this.getFiveToTwelveYearsOldFlag()) {
			newPersonRecord.setIsFiveToTwelve(true);
			newPersonRecord.setIsLessThanFive(false); 
			newPersonRecord.setIsAdultFlag(false); 
			newPersonRecord.setIsGreaterThanFive(false);
			newPersonRecord.setIsThirteenToEighteen(false);
		} else if (this.getThirteenToEighteenFlag()) {
			newPersonRecord.setIsThirteenToEighteen(true);
			newPersonRecord.setIsLessThanFive(false);
			newPersonRecord.setIsLessThanFive(false); 
			newPersonRecord.setIsAdultFlag(false); 
			newPersonRecord.setIsGreaterThanFive(false); 
		}
		
		newPersonRecord.setLastUpdtTs(new Date());
		return newPersonRecord;
	}
	
	public void setAgeFlag(PersonTbl person) {
		if (person.getIsAdultFlag()) {
			this.setAdultFlag(true); 
			this.setLessThanFiveYearsOldFlag(false); 
			this.setFiveToTwelveYearsOldFlag(false);
			this.setGreaterThanFiveYearsOldFlag(false);
			this.setThirteenToEighteenYearsOldFlag(false); 
		} else if (person.getIsLessThanFive()) { 
			this.setAdultFlag(false); 
			this.setLessThanFiveYearsOldFlag(true); 
			this.setFiveToTwelveYearsOldFlag(false);
			this.setGreaterThanFiveYearsOldFlag(false);
			this.setThirteenToEighteenYearsOldFlag(false); 
		} else if (person.getIsFiveToTwelve()) {
			this.setAdultFlag(false); 
			this.setLessThanFiveYearsOldFlag(false); 
			this.setFiveToTwelveYearsOldFlag(true);
			this.setGreaterThanFiveYearsOldFlag(false);
			this.setThirteenToEighteenYearsOldFlag(false); 
		} else if (person.getIsThirteenToEighteen()) {
			this.setAdultFlag(false); 
			this.setLessThanFiveYearsOldFlag(false); 
			this.setFiveToTwelveYearsOldFlag(false);
			this.setGreaterThanFiveYearsOldFlag(false);
			this.setThirteenToEighteenYearsOldFlag(true); 
		} 
	}
	
	public EmailTbl createEmail(PersonTbl aPerson) {
		EmailTbl newEmailRecord = new EmailTbl();
		newEmailRecord.setEmail("default_code@wcec.org");
		newEmailRecord.setLastUpdtTs(new Date());
		newEmailRecord.setPersonTbl(aPerson);
		return newEmailRecord;
	}
	
	//@Autowired
	//RegistrationTblRepo                        myRegistrationTblRepo;
	
	/**
	 * Need to query to find out whether there is a registeration 
	 * record for this person in the database.
	 * @return
	 */
	public boolean alreadyRegistered(RegistrationTblRepo repo) {
		//this.myRegistrationTblRepo.findAll();
		//RepositoryFactorySupport factory =  
			//	RegistrationTblRepo repository = factory.getRepository(RegistrationTblRepo.class);
		//VaadinUI.TheApplicationContext;
		RegistrationTbl registrationRecord = repo.findByChineseName(myEvent.getId(), this.getChineseName());

		return (registrationRecord != null);
	}
	
	/**
	 * Following are added AFTER the database registration records have been added.
	 */
	String buildingName; // assignment
	String roomNumber; // assignment
	String formNumber; // registration group id
	String englishName; // both first and last name 
	Double paymentAmount;
	Boolean paymentExpected;
	String paymentMethod; // chk, cash, unpaid
	DateTime paymentLogDate; 
	 

	public Boolean getThirteenToEighteenFlag() {
		return thirteenToEighteenFlag;
	}

	public void setThirteenToEighteenFlag(Boolean thirteenToEighteenFlag) {
		this.thirteenToEighteenFlag = thirteenToEighteenFlag;
	}
	 

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getFormNumber() {
		return formNumber;
	}

	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String registrationEnglishName) {
		this.englishName = registrationEnglishName;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Boolean getPaymentExpected() {
		return paymentExpected;
	}

	public void setPaymentExpected(Boolean paymentExpected) {
		this.paymentExpected = paymentExpected;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public DateTime getPaymentLogDate() {
		return paymentLogDate;
	}

	public void setPaymentLogDate(DateTime paymentLogDate) {
		this.paymentLogDate = paymentLogDate;
	}
 
	
	Boolean lessThanFiveYearsOldFlag;
	Boolean fiveToTwelveYearsOldFlag;
	Boolean thirteenToEighteenYearsOldFlag;
	public Boolean getLessThanFiveYearsOldFlag() {
		return lessThanFiveYearsOldFlag;
	}

	public void setLessThanFiveYearsOldFlag(Boolean lessThanFiveYearsOldFlag) {
		this.lessThanFiveYearsOldFlag = lessThanFiveYearsOldFlag;
	}

	public Boolean getFiveToTwelveYearsOldFlag() {
		return fiveToTwelveYearsOldFlag;
	}

	public void setFiveToTwelveYearsOldFlag(Boolean fiveToTwelveYearsOldFlag) {
		this.fiveToTwelveYearsOldFlag = fiveToTwelveYearsOldFlag;
	}

	public Boolean getThirteenToEighteenYearsOldFlag() {
		return thirteenToEighteenYearsOldFlag;
	}

	public void setThirteenToEighteenYearsOldFlag(Boolean thirteenToEighteenYearsOldFlag) {
		this.thirteenToEighteenYearsOldFlag = thirteenToEighteenYearsOldFlag;
	}
	
	/**
	 * Set everthing to empty.
	 */
	private void initEmpty() {
		this.setEnglishName("");
		this.setChineseName("");
		this.setGender("");
	}  
	
	MealPlan mealPlan;
	MealTemplate mTemplate;
	 
	public void addMeal(Meal aRecord) {
		mealPlan.addMeal(aRecord);
	}
	
	public void removeMeal(Meal aRecord) {
		mealPlan.removeMeal(aRecord);
	}
	
	public List<Meal> getMeals() {
		return mealPlan.getMealList();
	}
}
