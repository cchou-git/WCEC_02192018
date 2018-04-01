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

/**
 * When the user deselects a registration record, remove it from the
 * lodging table and move it to the other one. Also, remove the lodgingAssignment
 * record from the origial grid.
 * 
 * Delete the loading record.
 * 
 * @author chout
 *
 */
public class UnAssignLodgingListener implements ClickListener {

	
	Grid<RegistrationRecord> fromGrid;
	Grid<RegistrationRecord> toGrid;  
	 
	RegistrationRecordCollection fromMgr;
	RegistrationRecordCollection toMgr;
	
	LodgingAssignmentTblRepo lodgingRepo;
	RegistrationTblRepo      registrationRepo; 
 
	public void setLodginRepo(LodgingAssignmentTblRepo lodginRepo) {
		this.lodgingRepo = lodginRepo;
	}

	public void setRegistrationRepo(RegistrationTblRepo registrationRepo) {
		this.registrationRepo = registrationRepo;
	} 

	public UnAssignLodgingListener(RegistrationRecordCollection mgr1, 
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
		EntityManager entityManager = VaadinUI.TheEMF.createEntityManager();
		EntityTransaction tx = null; 
		Session session = null; 
		try {
			session = entityManager.unwrap(Session.class);
			tx = entityManager.getTransaction();
			tx.begin();
			Set<RegistrationRecord> selItems = toGrid.getSelectedItems();
			for (RegistrationRecord each: selItems) {
				// carry out creation of LodgingAssignment
				LodgingAssignmentTbl newLodging = each.getDbRecord().getLodgingAssignmentTbl();
				// remove the LodginAssignment from the registration
				each.getDbRecord().setLodgingAssignmentTbl(null);
				registrationRepo.save(each.getDbRecord());
				lodgingRepo.delete(newLodging); 
				// remove the record from the fromMgr
				toMgr.removeRecord(each);
				// add the record to the toMgr
				fromMgr.addRecord(each); 
			}  
			tx.commit();
			fromGrid.setItems(fromMgr.getCollection());
			toGrid.setItems(toMgr.getCollection());
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}  
	}
	

}
