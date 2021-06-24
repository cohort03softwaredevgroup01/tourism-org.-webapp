package com.tour.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.webapp.model.Users;

public interface UserRepo extends JpaRepository<Users ,Integer>{
	
	

}
