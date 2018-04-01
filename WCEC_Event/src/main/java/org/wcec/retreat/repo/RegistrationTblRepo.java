package org.wcec.retreat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wcec.retreat.entity.RegistrationTbl;  

public interface RegistrationTblRepo   extends JpaRepository<RegistrationTbl, Integer> {

	@Query(value="SELECT r.* FROM registration_tbl r, person_tbl p, event_tbl e WHERE e.id = ?1 AND p.chinese_nm = ?2 AND r.event_id = e.id AND r.person_id = p.id AND r.cancel_flag=0", nativeQuery = true)
	public RegistrationTbl findByChineseName(Integer eventId, String aName); 

	@Query(value="SELECT * from registration_tbl WHERE group_id = ?1 ", nativeQuery = true)
	public List<RegistrationTbl> findByGroupId(Integer groupId);  

	@Query(value="SELECT * from registration_tbl WHERE event_id = ?1 and cancel_flag = false", nativeQuery = true)
	public List<RegistrationTbl> findAllActiveEventRegistration(Integer eventId);  

	/**
	 * Get all registration records that have not been assigned a lodging.
	 * @param groupId
	 * @return
	 */
	@Query(value="SELECT * from registration_tbl WHERE event_id = ?1 and cancel_flag = false and lodging_assignment_id is  Null", nativeQuery = true) 
	public List<RegistrationTbl> findByNoLodging(Integer eventId);  


	/**
	 * Get all registration records that have been assigned a lodging.
	 * @param groupId
	 * @return
	 */
	@Query(value="SELECT * from registration_tbl WHERE event_id = ?1 and cancel_flag = false and lodging_assignment_id is not Null", nativeQuery = true) 
	public List<RegistrationTbl> findByHasLodging(Integer eventId);  

} 
