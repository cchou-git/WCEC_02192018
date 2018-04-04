package org.wcec.retreat.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.wcec.retreat.app.VaadinUI;
import org.wcec.retreat.entity.AttendingTbl;
import org.wcec.retreat.entity.EmailTbl;
import org.wcec.retreat.entity.EventTbl;
import org.wcec.retreat.entity.GroupTbl;
import org.wcec.retreat.entity.JustPersonEntity;
import org.wcec.retreat.entity.LodgingAssignmentTbl;
import org.wcec.retreat.entity.PaymentTbl;
import org.wcec.retreat.entity.PersonTbl;
import org.wcec.retreat.entity.RegistrationTbl;
import org.wcec.retreat.repo.AttendingTblRepo;
import org.wcec.retreat.repo.EmailTblRepo;
import org.wcec.retreat.repo.JustPersonEntityRepository;
import org.wcec.retreat.repo.PaymentTblRepo;
import org.wcec.retreat.repo.PersonTblRepository;
import org.wcec.retreat.repo.RegistrationTblRepo;

import com.vaadin.server.VaadinSession;
@Component
public class RegistrationRecordCollection {

	List<RegistrationRecord> collection = new ArrayList<RegistrationRecord>();

	public void addRecord(RegistrationRecord aRecord) {
		collection.add(aRecord);
	}

	public void removeRecord(RegistrationRecord aRecord) {
		collection.remove(aRecord);
	}

	/**
	 * Take the registration data from database to create the corresponding RegistrationRecord.
	 *  
	 * @param record
	 * @param anEvent
	 * @return
	 */
	private RegistrationRecord convertToRegistrationRecord(RegistrationTbl record, EventTbl anEvent) {
		RegistrationRecord aRegistration = new RegistrationRecord();
		aRegistration.setChineseName(record.getPersonTbl().getChineseNm());
		String firstName, lastName;
		lastName = record.getPersonTbl().getLastNm();
		firstName = record.getPersonTbl().getFirstNm();
		aRegistration.setLastName(lastName);
		aRegistration.setFirstName(firstName);
		aRegistration.setRegistrationEnglishName(firstName + " " + lastName);
		aRegistration.setFormNumber("" + record.getId());
		Set<AttendingTbl> dates = record.getAttendingTbls();
		for (AttendingTbl each: dates){
			DateTime dTime = new DateTime(each.getAttendingDate());
			aRegistration.addAttendingDate(dTime);
		}
		byte ptime = record.getPartTimeInd();
		if (ptime == 0) {
			aRegistration.setFullTimeFlag(true);
		} else {
			aRegistration.setFullTimeFlag(false);
		} 
		PersonTbl person = record.getPersonTbl();
		if (person.getIsAdultFlag()) {
			aRegistration.setAdultFlag(true);
		} else {
			aRegistration.setAdultFlag(false);
		}
		aRegistration.setAgeFlag(person);
		if (person.getGender().equalsIgnoreCase("M")) {
			aRegistration.setGender("M");
		} else {
			aRegistration.setGender("F");
		} 
		aRegistration.setComment(record.getComment());
		aRegistration.initialize(anEvent);
		aRegistration.setDbRecord(record);
		LodgingAssignmentTbl lodging = record.getLodgingAssignmentTbl();
		if (lodging != null) {
			if(lodging.getBuildingTbl() != null) {
				aRegistration.setBuildingName(lodging.getBuildingTbl().getName());
			} else {
				aRegistration.setBuildingName("Not set!");
			}
			if(lodging.getRoomTbl() != null) {
				aRegistration.setRoomNumber(lodging.getRoomTbl().getRoomNo());
			} else {
				aRegistration.setRoomNumber("Not set!");
			} 
		}
		return aRegistration;
	}
	
	/**
	 * Note that every time one person registers for the whole period, there
	 * will be as many records as the attending dates for the event.
	 * 
	 * The person can attend full time or part time. The conversion needs to
	 * take that into consideration.
	 * 
	 * Find from the collection whether the person's name is already in it or
	 * not.
	 * 
	 * @param anEvent
	 * @param aList
	 * @return
	 */
	public Collection<RegistrationRecord> populateFromDatabase(EventTbl anEvent, List<RegistrationTbl> aList) {

		for (RegistrationTbl each : aList) {
			RegistrationRecord aRecord = convertToRegistrationRecord(each, anEvent);
			collection.add(aRecord); 
		}
		return collection;
	}

	public Collection<RegistrationRecord> populateDefaultRecords(EventTbl anEvent) {
		RegistrationRecord record = new RegistrationRecord();
		record.setChineseName("");
		record.initialize(anEvent);
		collection.add(record);
		record = new RegistrationRecord();
		record.initialize(anEvent);
		collection.add(record);
		return collection;
	}

	public List<RegistrationRecord> getCollection() {
		return collection;
	}

	public void setCollection(List<RegistrationRecord> collection) {
		this.collection = collection;
	}

	List<PersonTbl> personTblCollection;

	/**
	 * Note - If a person does not exist, the program will create a new person
	 * using the following info. Otherwise, it must check for all of these
	 * fields to ensure they are not null.
	 * 
	 * These are mandatory fileds for a person: last_nm - Default to Doe
	 * first_nm - Default to John chinese_nm - MUST have church_id Default to 1
	 * address_id Default to 1 birth_dt Default to 2000-01-01 gender - MUST have
	 * group_id Default to 1
	 *
	 * A person must have email - Default to default_email@heaven.kingdom
	 * 
	 * We will NOT create a user record if this is a new person.
	 * 
	 * @return
	 */
	public List<List<Object>> validateInput(RegistrationTblRepo regRepo) {
		List<Object> badList = new ArrayList<Object>();
		List<Object> adultList = new ArrayList<Object>();
		List<Object> badAttendingDateList = new ArrayList<Object>();
		List<Object> alreadyRegisteredList = new ArrayList<Object>();
		for (RegistrationRecord each : this.getCollection()) {
			if (!each.validateBasicInformation()) {
				badList.add(each); // must have last/first name, chinese name,
									// and gender
			}
			if (each.getAdultFlag()) {
				adultList.add(each); // must have at least one adult
			}
			if (!each.validateAttendingDate()) {
				badAttendingDateList.add(each);
			}
			if (each.alreadyRegistered(regRepo)) { // want to prevent people
													// from registrating
													// multiple times
				alreadyRegisteredList.add(each);
			}
		}
		List<List<Object>> resultList = new ArrayList<List<Object>>();
		resultList.add(badList);
		resultList.add(adultList);
		resultList.add(badAttendingDateList);
		resultList.add(alreadyRegisteredList);
		return resultList;
	}

	/**
	 * Now check to see if every person on the list can be identified in the
	 * database.
	 * 
	 * @param justPersonEntityRepo
	 * @param personTblRepo
	 */
	public List<RegistrationRecord> checkForDataValidation(JpaRepository justPersonEntityRepo) {
		List<RegistrationRecord> toBeAddedList = new ArrayList<RegistrationRecord>();
		JustPersonEntityRepository jp = (JustPersonEntityRepository) justPersonEntityRepo;
		// JustPersonEntity example = new JustPersonEntity();
		for (RegistrationRecord each : this.getCollection()) {
			// example.setChineseNm(each.getChineseName());
			JustPersonEntity thePerson = jp.findByChineseName(each.getChineseName());
			if (thePerson == null) {
				toBeAddedList.add(each);
			} // otherwise, the person record exists, and user/email must exist
				// based on design
		}
		return toBeAddedList;
	}

	/**
	 * Create the necessary person and email from the given list of registration
	 * record. They need to be grouped into one group!
	 * 
	 * Note - this group is organized by the members in the RegistrationRecord -
	 * NOT at person level. That means, a person may be in a different group.
	 * But this "registration" group is to group those people on the same submit
	 * form, which can be a family, a group of youth, or a group of friends
	 * only.
	 * 
	 * This kind of group in the group_type table belongs to the following type:
	 * insert into wcec.group_type_tbl(description) values ('Registration
	 * group');
	 * 
	 * @param recordList
	 * @return
	 */
	public void createBasicUserRecord(List<RegistrationRecord> recordList, 
			PersonTblRepository personRepo,
			EmailTblRepo emailRepo,
			GroupTbl aGroup) {
		EntityManager entityManager = VaadinUI.TheEMF.createEntityManager();
		EntityTransaction tx = null;

		Session session = null;

		try {
			session = entityManager.unwrap(Session.class);
			tx = entityManager.getTransaction();

			List<BasicUserRecord> basicList = new ArrayList<BasicUserRecord>();
			tx.begin();
			for (RegistrationRecord each : recordList) {
				// everyone in this list does not exist in the database
				// create the necessary records in the database
				PersonTbl newPerson = each.createPerson(aGroup);
				personRepo.save(newPerson);
				EmailTbl newEmail = each.createEmail(newPerson);
				emailRepo.save(newEmail);
				basicList.add(new BasicUserRecord(newPerson, newEmail, null));
			}
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return;
	}

	/**
	 * Create one registration record for each member in the collection.
	 * 
	 * Note - there is no consideration of part time for this yet.
	 * 
	 * @param registrationRepo
	 * @param newGroup
	 */
	public void createNewRegistrationGroup(RegistrationTblRepo registrationRepo, 
			PersonTblRepository personRepo,
			GroupTbl newGroup,
			AttendingTblRepo dateRepo) {
		EntityManager entityManager = VaadinUI.TheEMF.createEntityManager();
		EntityTransaction tx = null;

		Session session = null;

		try {
			session = entityManager.unwrap(Session.class);
			tx = entityManager.getTransaction();
			tx.begin();
			EventTbl activeEvent = (EventTbl) VaadinSession.getCurrent().getAttribute(VaadinUI.SESSION_ACTIVE_EVENT);
			for (RegistrationRecord each : collection) {
				RegistrationTbl newRecord = null;
				if (each.getDbRecord() != null) {
					newRecord = each.getDbRecord();
				} else {
					newRecord = new RegistrationTbl();
				}
				newRecord.setEventTbl(activeEvent);
				newRecord.setGroupTbl(newGroup);
				newRecord.setLastUpdtTs(new Date());
				byte flag = 0;
				if (!each.getFullTimeFlag()) {
					flag = 1;
					newRecord.setPartTimeInd(flag);
				}
				List<PersonTbl> personList = personRepo.findByChineseNm(each.getChineseName());
				if (personList != null && personList.size() > 0) {
					newRecord.setPersonTbl(personList.get(0));
				} else {
					throw new RuntimeException("Can't create a registration record without a person record!");
				}
				newRecord.setRegistrationDate(new Date());
				Set<AttendingTbl> attendingDateSet = new HashSet<AttendingTbl>();
				if (each.getFullTimeFlag()) {
					Date startDate0 = activeEvent.getStartDt();
					Date endDate0 = activeEvent.getEndDt();
					DateTime startDate = new DateTime(startDate0);
					DateTime endDate = new DateTime(endDate0);
					
					int days = Days.daysBetween(startDate, endDate).getDays() + 1;
					for (int i=0; i < days; i++) {
					    DateTime d = startDate.withFieldAdded(DurationFieldType.days(), i);
					    AttendingTbl anAttendingDate = new AttendingTbl();
					    anAttendingDate.setAttendingDate(d.toDate());
					    anAttendingDate.setRegistrationTbl(newRecord);
					    attendingDateSet.add(anAttendingDate); 
					} 					
				 }
				newRecord.setAttendingTbls(attendingDateSet);
				registrationRepo.save(newRecord);
				System.out.println("The new registration record id = " + newRecord.getId());
			}
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
	}


	/**
	 * Create a new payment record and associate it with the registration 
	 * record.
	 * 
	 * @param pRepo
	 * @param amount
	 * @param remark
	 * @param paidInFullFlag
	 * @param registration
	 * @param registrationRepo
	 */
	public void createNewPayment(PaymentTblRepo pRepo, 
			double amount,
			String remark,
			boolean paidInFullFlag,
			RegistrationTbl registration,
			RegistrationTblRepo registrationRepo) {
		EntityManager entityManager = VaadinUI.TheEMF.createEntityManager();
		EntityTransaction tx = null;

		Session session = null;

		try {
			session = entityManager.unwrap(Session.class);
			tx = entityManager.getTransaction();
			tx.begin();
			PaymentTbl payment = new PaymentTbl(); 
			BigDecimal b = new BigDecimal(amount, MathContext.DECIMAL64);
			payment.setAmountPaid(b);
			payment.setPaymentTextTx(remark);
			byte pFull = 0;
			if (paidInFullFlag) {
				pFull = 1;
			} else {
				pFull = 0;
			}
			payment.setPaidInFull(pFull);
			registration.setPaymentTbl(payment);
			registrationRepo.save(registration);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
	} 
}
