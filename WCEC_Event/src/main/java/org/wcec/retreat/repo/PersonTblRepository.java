package org.wcec.retreat.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.PersonTbl;
 

public interface PersonTblRepository extends JpaRepository<PersonTbl, Integer> {

	List<PersonTbl> findByChineseNm(String chineseName);
}   


 
 