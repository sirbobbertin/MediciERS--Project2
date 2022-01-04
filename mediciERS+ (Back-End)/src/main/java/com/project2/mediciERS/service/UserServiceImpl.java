package com.project2.mediciERS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.mediciERS.dao.UserRepositoryDao;
import com.project2.mediciERS.entity.User;
import com.project2.mediciERS.exception.ApplicationException;
import com.project2.mediciERS.pojo.UserPojo;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepositoryDao userRepositoryDao;

	public UserServiceImpl() {

	}

	// ADD A USER
	@Override
	public UserPojo register(UserPojo userPojo) throws ApplicationException {
		logger.info("Entered register() in service.");
				userPojo.setUserType("employee");
				User newUser = new User(userPojo.getUserId(), userPojo.getUserPassword(), userPojo.getUserFirstName(), userPojo.getUserLastName(), 
						userPojo.getUserAddress(), userPojo.getUserContact(), userPojo.getUserType(), userPojo.isUserRemoved());
//				newUser.setUserType("employee");
				User returnUser = userRepositoryDao.saveAndFlush(newUser);
				userPojo.setUserId(returnUser.getUserId());
		logger.info("Exited register() in service.");
		return userPojo;
	}

	// VALIDATE A USER
	@Override
	public UserPojo validateUser(UserPojo userPojo) throws ApplicationException {
		logger.info("Entered getAReimbursement() in service.");
		// BookPojo returnBookPojo = this.bookDao.addBook(bookPojo); 
		List<User> returnUserCollection = userRepositoryDao.findByUserIdAndUserPassword(userPojo.getUserId(), userPojo.getUserPassword());
		UserPojo returnUserPojo = null;
		if(returnUserCollection.size() != 0) {
		User returnUser = returnUserCollection.get(0);
		returnUserPojo  = new UserPojo (returnUser.getUserId(), returnUser.getUserPassword(), returnUser.getUserFirstName(), returnUser.getUserLastName(), 
				returnUser.getUserAddress(), returnUser.getUserContact(), returnUser.getUserType(), returnUser.isUserRemoved()); 
		}else {
			throw new ApplicationException("Invalid User ID / Password");
		}
		logger.info("Exited getAReimbursement() in service.");
		return returnUserPojo;
	}
	

	// UPDATE A USER
	@Override
	public UserPojo updateUser(UserPojo userPojo) throws ApplicationException {
		logger.info("Entered updateUser() in service.");
		User updateUser = new User(userPojo.getUserId(), userPojo.getUserPassword(), userPojo.getUserFirstName(), userPojo.getUserLastName(), 
				userPojo.getUserAddress(), userPojo.getUserContact(), userPojo.getUserType(), userPojo.isUserRemoved());
		User returnUser = userRepositoryDao.save(updateUser);
		logger.info("Exited updateUser() in service.");
		return userPojo;
	}

	// DELETE A USER
	@Override
	public boolean deleteUser(Integer userId) throws ApplicationException {
		logger.info("Entered deleteUser() in service.");
		this.userRepositoryDao.deleteById(userId);
		logger.info("Exited deleteItem() in service.");
		return true;
	}

	// GET ALL USERS (EMPLOYEES)
	@Override
	public List<UserPojo> getAllUsers() throws ApplicationException {
		logger.info("Entered getAllUsers() in service.");
		
		List<User> allUsersEntity = this.userRepositoryDao.findAll();

		List<UserPojo> allUsersPojo = new ArrayList<UserPojo>();
		
		allUsersEntity.forEach((user) -> {
			UserPojo userPojo1 = new UserPojo(user.getUserId(), user.getUserPassword(), user.getUserFirstName(), user.getUserLastName(), 
					user.getUserAddress(), user.getUserContact(), user.getUserType(), user.isUserRemoved());
			allUsersPojo.add(userPojo1);
		});
		logger.info("Exited getAllItems() in service.");
		return allUsersPojo;
	}

	// GET A USER (EMPLOYEE)
	@Override
	public UserPojo getAUser(Integer userId) throws ApplicationException {
		logger.info("Entered getAReimbursement() in service.");
		UserPojo userPojo = null;
		Optional<User> optional = this.userRepositoryDao.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			userPojo = new UserPojo(user.getUserId(), user.getUserPassword(), user.getUserFirstName(), user.getUserLastName(), 
					user.getUserAddress(), user.getUserContact(), user.getUserType(), user.isUserRemoved());
			logger.info("Exited getResolvedRequests() in service.");
		}
		logger.info("Exited getAReimbursement() in service.");
		return userPojo;
	}

	@Override
	public void exitApplication() {
		// TODO Auto-generated method stub

	}

}
