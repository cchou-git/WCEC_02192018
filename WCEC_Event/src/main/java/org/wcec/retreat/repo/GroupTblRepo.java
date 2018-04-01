package org.wcec.retreat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.GroupTbl;  

public interface GroupTblRepo extends JpaRepository<GroupTbl, Integer> {

	public List<GroupTbl> findByGroupNm(String groupName);
} 
