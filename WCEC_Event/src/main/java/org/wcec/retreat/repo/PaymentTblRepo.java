package org.wcec.retreat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.PaymentTbl;  

public interface PaymentTblRepo   extends JpaRepository<PaymentTbl, Integer> {


} 
