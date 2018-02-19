package org.wcec.retreat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.AccessLevel;  

public interface AccessLevelRepo extends JpaRepository<AccessLevel, Integer> {


} 
