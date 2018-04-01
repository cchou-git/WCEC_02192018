package org.wcec.retreat.app;

import java.util.Date;
import java.util.List;

import org.wcec.retreat.entity.GroupTbl;
import org.wcec.retreat.entity.GroupTypeTbl;
import org.wcec.retreat.model.RegistrationRecord;
import org.wcec.retreat.model.RegistrationRecordCollection;
import org.wcec.retreat.repo.AttendingTblRepo;
import org.wcec.retreat.repo.EmailTblRepo;
import org.wcec.retreat.repo.GroupTblRepo;
import org.wcec.retreat.repo.JustPersonEntityRepository;
import org.wcec.retreat.repo.PersonTblRepository;
import org.wcec.retreat.repo.RegistrationTblRepo;
import org.wcec.retreat.repo.UserTblRepo;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class RegistrationSubmitButtonListener implements ClickListener {

	RegistrationRecordCollection manager;
	JustPersonEntityRepository repo;
	PersonTblRepository personRepo;
	EmailTblRepo emailRepo;
	UserTblRepo userRepo;
	RegistrationTblRepo registrationRepo;
	AttendingTblRepo dateRepo;
	GroupTblRepo groupRepo;
	final int REGISTRATION_GROUP_TYPE_ID = 14;

	public void setEmailRepo(EmailTblRepo emailRepo) {
		this.emailRepo = emailRepo;
	}

	public void setPersonRepo(PersonTblRepository personRepo) {
		this.personRepo = personRepo;
	}

	public void setUserRepo(UserTblRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public void setRegistrationRepo(RegistrationTblRepo regRepo) {
		this.registrationRepo = regRepo;
	}
	
	public void setAttendingTblRepo(AttendingTblRepo dRepo) {
		this.dateRepo = dRepo;
	}
	
	public void setGroupRepo(GroupTblRepo gRepo) {
		groupRepo = gRepo;
	}

	public RegistrationSubmitButtonListener(RegistrationRecordCollection aMgr, JustPersonEntityRepository aRepo) {

		manager = aMgr;
		repo = aRepo;
	}
	
	void displayNotificaiton(String msg) {
		Notification notif = new Notification(
			    "Warning",
			    msg,
			    Notification.TYPE_WARNING_MESSAGE);

			// Customize it
			notif.setDelayMsec(20000);
			notif.setPosition(Position.MIDDLE_CENTER);
			notif.setStyleName("mystyle"); 

			// Show it in the page
			notif.show(Page.getCurrent());
	}

	@Override
	public void buttonClick(ClickEvent event) {
		List<List<Object>> resultList = manager.validateInput(registrationRepo);
		/**
		 * badList, adultList, badAttendingDateList, alreadyRegisteredList
		 */
		String message = "Success";
		if (resultList.get(0).size() > 0) {
			message = formatBadList(resultList.get(0));
			displayNotificaiton(message);
		} else if (resultList.get(1).size() == 0) {
			message = formatMissingAdultList(resultList.get(1));
			displayNotificaiton(message);
		} else if (resultList.get(2).size() > 0) {
			message = formatMissingAttendingDate(resultList.get(2));
			displayNotificaiton(message);
		} else { 
			// If this is the first time the list of persons are submitting, then
			// create a new group and those persons who are not in the registration
			// into that group.
			// Create a new registration group!!!
			
			GroupTbl newGroup = new GroupTbl();
			GroupTypeTbl groupType = new GroupTypeTbl();
			groupType.setId(REGISTRATION_GROUP_TYPE_ID);
			// Take the first person in the adultList to name the group. 
			RegistrationRecord firstAdult = (RegistrationRecord) resultList.get(1).get(0);
			newGroup.setGroupNm("Reg Group - " + firstAdult.getChineseName());
			newGroup.setGroupTypeTbl(groupType);
			newGroup.setLastUpdtTs(new Date());
			try {
				groupRepo.save(newGroup); 
			} catch (Exception ex) {
				// group already created -- could be of the same group name
				List<GroupTbl> list = groupRepo.findByGroupNm(newGroup.getGroupNm());
				if (list != null && list.size() > 0) {
					newGroup = list.get(0);
				} else {
					throw new RuntimeException("Can't find group with name: " + newGroup.getGroupNm());
				}
				
			}
			// First of all, if a person doesn't even exist in the database, add the
			// person - NOT the associated user.
			List<RegistrationRecord> toBeAddedList = manager.checkForDataValidation(repo);
			if (toBeAddedList.size() > 0) {  
				manager.createBasicUserRecord(toBeAddedList, personRepo, emailRepo, newGroup);
				// Do NOT add UserTbl after person/email are added.
			}
			// Must update the PersonTbl in the finalList with the new group
			// id!
			// Now add everyone in the manager's collection - each one create a new RegistrationRecord.
			
			manager.createNewRegistrationGroup(registrationRepo, personRepo, newGroup, dateRepo);
			
		}

	}

	/*
	 * Notification.show("Thank you for registering!"); } else { Notification.
	 * show("There are issues with the input data. Please fix them an then submit again!"
	 * ); } } else { Notification.
	 * show("There are issues with the input data. Please fix them an then submit again!"
	 * ); }
	 */

	String formatBadList(List<Object> aList) {
		return "basic information missing from person!";
	}

	String formatMissingAdultList(List<Object> aList) {
		return "missing at least one adult from this registration!";
	}

	String formatMissingAttendingDate(List<Object> aList) {
		return "someone in the list doesn't have correct attending date!";
	}

}
