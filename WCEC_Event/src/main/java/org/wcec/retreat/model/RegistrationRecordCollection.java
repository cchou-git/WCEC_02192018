package org.wcec.retreat.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	
	public boolean validateInput() {
		/** TODO 
		 *  Code up this work so it returns validated inputs.
		 */
		return true;
	}
	
}
