package com.project2.mediciERS.service;

import java.util.List;

import com.project2.mediciERS.exception.ApplicationException;
import com.project2.mediciERS.pojo.UserPojo;

public interface UserService {
	
	UserPojo register(UserPojo userPojo) throws ApplicationException; // CREATE A NEW USER (ADD)
	UserPojo validateUser(UserPojo userPojo) throws ApplicationException; // VALIDATE LOGIN (EMPLOYEE OR MANAGER)
	List<UserPojo> getAllUsers() throws ApplicationException; // VIEW ALL USERS (EMPLOYEES)
	UserPojo getAUser(Integer userId) throws ApplicationException; // VIEW THE INFORMATION OF A USER
	UserPojo updateUser(UserPojo userPojo) throws ApplicationException; // UPDATE INFORMATION OF A USER
	boolean deleteUser(Integer userId) throws ApplicationException; // DELETE A USER
	void exitApplication();

}
