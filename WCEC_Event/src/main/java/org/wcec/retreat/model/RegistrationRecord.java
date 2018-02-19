package org.wcec.retreat.model;

import java.io.Serializable;

public class RegistrationRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	int count;
	String chineseName = "阿泽";
	String firstName = "Smart";
	String lastName = "Wu";
	String gender = "M";
	boolean adultFlag = true;
	boolean greaterThanFiveYearsOldFlag = true;
	double freeWillOffering = 0.0;
	int    age = 23;
	String specialRequest = "none";
	boolean needFancialFlag = true;
	boolean definedFlag = false;
	boolean deleteButton = false;
	
	
	
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
	public boolean isAdultFlag() {
		return adultFlag;
	}
	public void setAdultFlag(boolean adultFlag) {
		this.adultFlag = adultFlag;
	}
	public boolean isGreaterThanFiveYearsOldFlag() {
		return greaterThanFiveYearsOldFlag;
	}
	public void setGreaterThanFiveYearsOldFlag(boolean greaterThanFiveYearsOldFlag) {
		this.greaterThanFiveYearsOldFlag = greaterThanFiveYearsOldFlag;
	}
	public double getFreeWillOffering() {
		return freeWillOffering;
	}
	public void setFreeWillOffering(double freeWillOffering) {
		this.freeWillOffering = freeWillOffering;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
	public boolean isNeedFancialFlag() {
		return needFancialFlag;
	}
	public void setNeedFancialFlag(boolean needFancialFlag) {
		this.needFancialFlag = needFancialFlag;
	}
	public boolean isDeleteButton() {
		return deleteButton;
	}
	public void setDeleteButton(boolean deleteButton) {
		this.deleteButton = deleteButton;
	}
	
	

}
