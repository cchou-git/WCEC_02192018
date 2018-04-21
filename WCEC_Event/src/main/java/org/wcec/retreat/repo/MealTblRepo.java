package org.wcec.retreat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wcec.retreat.entity.MealTbl;  

public interface MealTblRepo  extends JpaRepository<MealTbl, Integer> {

	@Query(value="SELECT * from meal_tbl WHERE event_id = ?1 order by from_age", nativeQuery = true) 
	public List<MealTbl> findByEvent(Integer eventId);   

} 
