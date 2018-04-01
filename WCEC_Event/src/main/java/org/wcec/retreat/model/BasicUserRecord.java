package org.wcec.retreat.model;

import org.wcec.retreat.entity.EmailTbl;
import org.wcec.retreat.entity.PersonTbl;
import org.wcec.retreat.entity.UserTbl;

public class BasicUserRecord {
	PersonTbl thePerson;
	EmailTbl theEmail;
	UserTbl theUser;
	
	public BasicUserRecord(PersonTbl aPerson, EmailTbl anEmail, UserTbl aUser) {
		thePerson = aPerson;
		theEmail = anEmail;
		theUser = aUser;
	}

	public PersonTbl getThePerson() {
		return thePerson;
	}

	public void setThePerson(PersonTbl thePerson) {
		this.thePerson = thePerson;
	}

	public EmailTbl getTheEmail() {
		return theEmail;
	}

	public void setTheEmail(EmailTbl theEmail) {
		this.theEmail = theEmail;
	}

	public UserTbl getTheUser() {
		return theUser;
	}

	public void setTheUser(UserTbl theUser) {
		this.theUser = theUser;
	}
	
	

}
