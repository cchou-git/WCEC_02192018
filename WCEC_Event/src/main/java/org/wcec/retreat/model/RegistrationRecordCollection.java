package org.wcec.retreat.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.app.VaadinUI;
import org.wcec.retreat.entity.EmailTbl;
import org.wcec.retreat.entity.JustPersonEntity;
import org.wcec.retreat.entity.PersonTbl;

public class RegistrationRecordCollection {
	
	List<RegistrationRecord> collection = new ArrayList<RegistrationRecord>();
	
	
	public void addRecord(RegistrationRecord aRecord) {
		collection.add(aRecord);
	}
	
	public void removeRecord(RegistrationRecord aRecord) {
		collection.remove(aRecord); 
	}
	
	public RegistrationRecordCollection() { 
	}
	
	public Collection<RegistrationRecord> populateDefaultRecords() {
		RegistrationRecord record = new RegistrationRecord();
		record.setChineseName("周从迪");
		collection.add(record);
		record = new RegistrationRecord();
		collection.add(record);
		record = new RegistrationRecord();
		collection.add(record);
		record = new RegistrationRecord();
		collection.add(record); 
		record = new RegistrationRecord();
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
	 * Note - If a person does not exist, the program will create a new person using the following
	 * info. Otherwise, it must check for all of these fields to ensure they are not null.
	 * 
	 * These are mandatory fileds for a person:
	 * last_nm  - Default to Doe
	 * first_nm - Default to John
	 * chinese_nm - MUST have
	 * church_id  Default to 1
	 * address_id Default to 1
	 * birth_dt   Default to 2000-01-01
	 * gender -   MUST have
	 * group_id   Default to 1
	 *
	 * A person must have email -
	 * Default to default_email@heaven.kingdom
	 * 
	 * We will NOT create a user record if this is a new person. 
	 * @return
	 */
	public RegistrationRecord validateInput(JpaRepository justPersonEntityRepo, JpaRepository personTblRepo) { 
		personTblCollection = new ArrayList<PersonTbl>();
		List<JustPersonEntity> all = justPersonEntityRepo.findAll();
		for (RegistrationRecord each : this.getCollection())
		{
			boolean found = false;
			if (each.validate()) {
				for (JustPersonEntity eachDBPerson : all) {
					System.out.println("Comparing " + eachDBPerson.getChineseNm() + "  and " + each.getChineseName());
					if (eachDBPerson.getChineseNm().equals(each.getChineseName())) {
						// This individual is already in the person_tbl table -
						PersonTbl theRecord = (PersonTbl) personTblRepo.findOne(eachDBPerson.getId());
						personTblCollection.add(theRecord);
						found = true;
					}  
				}
				if (!found) {
					/*
					 * 
insert into person_tbl (last_nm, first_nm, chinese_nm, church_id, address_id, birth_dt, gender, primary_group_id) select 'Doe', 'John', '谢福祥', 1, 1, '2000-01-01', 'M', group_tbl.id from group_tbl where group_tbl.group_nm = '恩友團契';
insert into email_tbl (person_id, email) values  (LAST_INSERT_ID(), 'default_email@heaven.kingdom');
					 */
					EmailTbl newEmailRecord = new EmailTbl();
					newEmailRecord.setEmail("added_default_@heaven.kingdom");
					PersonTbl newPersonRecord = new PersonTbl();
					newPersonRecord.setLastNm(each.getLastName());
					newPersonRecord.setFirstNm(each.getFirstName());
					newPersonRecord.setChurchTbl(VaadinUI.AllChurch.get(0));
					newPersonRecord.setAddressTbl(VaadinUI.AllAddress.get(0));
					newPersonRecord.setBirthDt(new Date());
					newPersonRecord.setGender(each.getGender());
					newPersonRecord.setGroupTbl(VaadinUI.AllGroup.get(0));
					newPersonRecord.setLastUpdtTs(new Date());
					// Need to create the person record!
					personTblCollection.add(newPersonRecord);
				}
			} else {
				return each;
			}
		}
		return null;
	}
	
}
