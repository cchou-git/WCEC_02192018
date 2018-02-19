package org.wcec.retreat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.EventTbl;  

public interface EventTblRepo extends JpaRepository<EventTbl, Integer> {


} 
