package org.wcec.retreat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.EventTbl;  

public interface EventTblRepo extends JpaRepository<EventTbl, Integer> {
    public List<EventTbl> findAllByOrderByStartDtDesc();

} 
