package org.wcec.retreat.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wcec.retreat.entity.JustPersonEntity;

public interface JustPersonEntityRepository extends JpaRepository<JustPersonEntity, Integer> {

	//public List<JustPersonEntity> findAllByChineseNm();
	
	@Query(value="SELECT * FROM person_tbl t WHERE t.chinese_nm = ?1", nativeQuery = true)
	public JustPersonEntity findByChineseName(String aName); 
}   


 
 