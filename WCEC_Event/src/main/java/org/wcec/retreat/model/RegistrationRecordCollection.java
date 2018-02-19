package org.wcec.retreat.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegistrationRecordCollection {
	
	List<RegistrationRecord> collection = new ArrayList<RegistrationRecord>();
	
	
	public void addRecord(RegistrationRecord aRecord) {
		collection.add(aRecord);
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

}
