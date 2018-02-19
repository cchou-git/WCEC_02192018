package org.wcec.retreat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcec.retreat.entity.UserLogin;  

public interface UserLoginRepo   extends JpaRepository<UserLogin, Integer> {


} 
