package org.wcec.retreat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.UserTbl;  

public interface UserTblRepo   extends JpaRepository<UserTbl, Integer> {


} 
