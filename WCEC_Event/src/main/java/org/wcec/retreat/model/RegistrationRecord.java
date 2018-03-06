package org.wcec.retreat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegistrationRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	int count;
	
	String chineseName = "";
	String firstName = "John";
	String lastName = "Doe";
	String gender = "M";
	Boolean adultFlag = true;
	Boolean greaterThanFiveYearsOldFlag = false;
	String freeWillOffering = "0.0";
	Set<Date> attendingDates = new HashSet<Date>();
	Boolean fullTimeFlag = true;
	
	

    public Boolean getFullTimeFlag() {
		return fullTimeFlag;
	}
	public void setFullTimeFlag(Boolean fullTimeFlag) {
		this.fullTimeFlag = fullTimeFlag;
	}
	public Set<Date> getAttendingDates() {
		return attendingDates;
	}
	public void setAttendingDates(Set<Date> attendingDates) {
		this.attendingDates = attendingDates;
	}
	Integer    age = 23;
	String specialRequest = "none";
	Boolean needFancialFlag = false;
	boolean definedFlag = false;
	public static Set<Date> AttendingDates = new HashSet<Date>();
	
	static {
		AttendingDates.add(new Date());
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
	public Integer getAge() {
		return age;
	}
	public String getFreeWillOffering() {
		return freeWillOffering;
	}
	public void setFreeWillOffering(String freeWillOffering) {
		this.freeWillOffering = freeWillOffering;
	}
	public void setAge(Integer age) {
		this.age = age;
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
	
	public boolean validate() {
		return 
				((chineseName != null && chineseName.trim().length() > 0) 
				&& (lastName != null && lastName.trim().length() > 0) 
				&& (firstName != null && firstName.trim().length() > 0) 
				&& (gender != null && gender.trim().length() > 0));
	}
	
	public Boolean getAdultFlag() {
		return adultFlag;
	}
	public void setAdultFlag(Boolean adultFlag) {
		this.adultFlag = adultFlag;
	}
	public Boolean getNeedFancialFlag() {
		return needFancialFlag;
	}
	public void setNeedFancialFlag(Boolean needFancialFlag) {
		this.needFancialFlag = needFancialFlag;
	}
	
	
}
