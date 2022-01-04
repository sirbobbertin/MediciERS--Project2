package com.project2.mediciERS.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.mediciERS.entity.User;

@Repository
public interface UserRepositoryDao extends JpaRepository<User, Integer>{
	
	// ______________________________ FINDER METHODS ______________________________________
	
	// VALIDATE USER
	/* select * from user_details where user_id=" + userPojo.getUserId() 
	 * + " and user_password='" + userPojo.getUserPassword() + "' and user_removed=false"; */
//	UserPojo findByUserIdAndUserPassword(UserPojo userPojo);
	List<User> findByUserIdAndUserPassword(Integer userId, String userPassword);
	

}
