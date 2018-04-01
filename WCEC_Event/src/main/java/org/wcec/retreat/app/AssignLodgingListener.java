package org.wcec.retreat.app;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.wcec.retreat.entity.BuildingTbl;
import org.wcec.retreat.entity.LodgingAssignmentTbl;
import org.wcec.retreat.entity.RoomTbl;
import org.wcec.retreat.model.RegistrationRecord;
import org.wcec.retreat.model.RegistrationRecordCollection;
import org.wcec.retreat.repo.LodgingAssignmentTblRepo;
import org.wcec.retreat.repo.RegistrationTblRepo;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;

public class AssignLodgingListener implements ClickListener {

	
	Grid<RegistrationRecord> fromGrid;
	Grid<RegistrationRecord> toGrid;
	BuildingTbl selectedBuilding;
	

	RoomTbl selectedRoom;
	RegistrationRecordCollection fromMgr;
	RegistrationRecordCollection toMgr;
	
	LodgingAssignmentTblRepo lodgingRepo;
	RegistrationTblRepo      registrationRepo;
	BuildingChangeListener   callback;

	public void setSelectedBuilding(BuildingTbl selectedBuilding) {
		this.selectedBuilding = selectedBuilding;
	}

	public void setSelectedRoom(RoomTbl selectedRoom) {
		this.selectedRoom = selectedRoom;
	}
	public void setLodginRepo(LodgingAssignmentTblRepo lodginRepo) {
		this.lodgingRepo = lodginRepo;
	}

	public void setRegistrationRepo(RegistrationTblRepo registrationRepo) {
		this.registrationRepo = registrationRepo;
	}
	
	

	public void setCallback(BuildingChangeListener callback) {
		this.callback = callback;
	}

	public AssignLodgingListener(RegistrationRecordCollection mgr1, 
			RegistrationRecordCollection mgr2,
			Grid<RegistrationRecord> grid1, 
			Grid<RegistrationRecord> grid2) {
		fromMgr = mgr1;
		toMgr = mgr2; 
		fromGrid = grid1;
		toGrid = grid2;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		selectedRoom = callback.getSelectedRoom();  
		selectedBuilding = callback.getSelectedBuilding();
		if (selectedBuilding == null) {
			Notification.show("Please select a building!", Notification.Type.ERROR_MESSAGE);
			return;
		}

		if (selectedRoom == null) {
			Notification.show("Please select a room from a building!", Notification.Type.ERROR_MESSAGE);
			return;
		}
		
		EntityManager entityManager = VaadinUI.TheEMF.createEntityManager();
		EntityTransaction tx = null; 
		Session session = null; 
		try {
			session = entityManager.unwrap(Session.class);
			tx = entityManager.getTransaction();
			tx.begin();
			Set<RegistrationRecord> selItems = fromGrid.getSelectedItems();
			for (RegistrationRecord each: selItems) {
				// carry out creation of LodgingAssignment
				LodgingAssignmentTbl newLodging = new LodgingAssignmentTbl();
				newLodging.setBuildingTbl(this.selectedBuilding);
				newLodging.setRoomTbl(this.selectedRoom);
				newLodging.setLastUpdtTs(new Date());
				lodgingRepo.save(newLodging);
				// associate the LodginAssignment to the registration
				each.getDbRecord().setLodgingAssignmentTbl(newLodging);
				registrationRepo.save(each.getDbRecord());
				// remove the record from the fromMgr
				fromMgr.removeRecord(each);
				// add the record to the toMgr
				toMgr.addRecord(each); 
			}  
			tx.commit();
			fromGrid.clearSortOrder(); 
			fromGrid.setItems(fromMgr.getCollection());
			toGrid.setItems(toMgr.getCollection()); 
			  
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}  
	}
	

}
