package com.project2.mediciERS.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.mediciERS.exception.ApplicationException;
import com.project2.mediciERS.pojo.ReimbursementPojo;
import com.project2.mediciERS.pojo.UserPojo;
import com.project2.mediciERS.service.ReimbursementService;
import com.project2.mediciERS.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api")
public class Controller {

	@Autowired
	ReimbursementService reimbursementService;

	@Autowired
	UserService userService;


	// ______________________________ REIMBURSEMENT ENDPOINTS _______________________________

	// GET A REIMBURSEMENT
	// http://localhost:8080/api/reimbursements/1 (200 OK)
	@GetMapping("reimbursements/{rbid}")
	ReimbursementPojo getAReimbursement(@PathVariable("rbid") int rbId) throws ApplicationException {
		return reimbursementService.getAReimbursement(rbId);

	}

	// GET ALL PENDING REIMBURSEMENTS (MANAGER)
	// http://localhost:8080/api/reimbursements/manager/view/pending (200 OK)
	@GetMapping("reimbursements/manager/view/{pending}")
	List<ReimbursementPojo> findByRbStatus(@PathVariable("pending") String rbStatus) throws ApplicationException {
		return reimbursementService.getAllPendingRequests(rbStatus);

	}

	// GET ALL RESOLVED REIMBURSEMENTS (MANAGER)
	// http://localhost:8080/api/reimbursements/manager/resolved (200 OK)
	@GetMapping("reimbursements/manager/{resolved}")
	List<ReimbursementPojo> getAllResolvedRequests(@PathParam("resolved") String rbStatus) throws ApplicationException {
		return reimbursementService.getAllResolvedRequests(true);
	}

	// GET A SPECIFIC EMPLOYEE'S REIMBURSEMENTS (MANAGER)
	// http://localhost:8080/api/reimbursements/all/employee/2 (200 OK)
	@GetMapping("reimbursements/all/employee/{userid}")
	List<ReimbursementPojo> getSpecificRequests(@PathVariable("userid") int userId, String rbStatus)
			throws ApplicationException {
		return reimbursementService.getSpecificRequests(userId, false);
	}

	// GET ALL OF AN EMPLOYEE'S PENDING REIMBURSEMENTS (EMPLOYEE)
	// http://localhost:8080/api/reimbursements/pending/employee/2 (200 OK)
	@GetMapping("reimbursements/pending/employee/{userid}")
	List<ReimbursementPojo> getPendingRequests(@PathVariable("userid") int userId) throws ApplicationException {
		return reimbursementService.getPendingRequests(userId, "pending", false);

	}

	// GET ALL OF AN EMPLOYEE'S RESOLVED REIMBURSEMENTS (EMPLOYEE)
	// http://localhost:8080/api/reimbursements/resolved/employee/2 (200 OK)
	@GetMapping("reimbursements/resolved/employee/{userid}")
	List<ReimbursementPojo> getResolvedRequests(@PathVariable("userid") int userId, String rbStatus)
			throws ApplicationException {
		return reimbursementService.getResolvedRequests(userId, true);
	}

	// (HARD) DELETE A REIMBURSEMENT
	// http://localhost:8080/api/reimbursements/remove/1 (200 OK)
	@DeleteMapping("reimbursements/remove/{rbid}")
	boolean deleteRequest(@PathVariable("rbid") Integer rbId) throws ApplicationException {
		return reimbursementService.deleteRequest(rbId);

	}

	// ADD A REIMBURSEMENT
	// http://localhost:8080/api/reimbursements/add (200 OK)
	@PostMapping("reimbursements/add")
	ReimbursementPojo submitRequest(@Valid @RequestBody ReimbursementPojo reimbursementPojo)
			throws ApplicationException {
		return reimbursementService.submitRequest(reimbursementPojo);

	}

	// UPDATE A REIMBURSEMENT
	// http://localhost:8080/api/reimbursements/update/2 (200 OK)
	@PutMapping("reimbursements/update/{rbid}")
	ReimbursementPojo updateReimbursement(@Valid @RequestBody ReimbursementPojo reimbursementPojo)
			throws ApplicationException {
		return reimbursementService.updateReimbursement(reimbursementPojo);
	}

	// APPROVE A REIMBURSEMENT
	// http://localhost:8080/api/reimbursements/approve/2 (200 OK)
	@PutMapping("reimbursements/approve/{rbid}")
	ReimbursementPojo manageRequest(@PathVariable("rbid") int rbId) throws ApplicationException {
		return reimbursementService.manageRequest(rbId);

	}

	// DENY A REIMBURSEMENT
	// http://localhost:8080/api/reimbursements/deny/2 (200 OK)
	@PutMapping("reimbursements/deny/{rbid}")
	ReimbursementPojo denyRequest(@PathVariable("rbid") int rbId) throws ApplicationException {
		return reimbursementService.denyRequest(rbId);

	}

	// ________________________________ USER ENDPOINTS _____________________________________

	// ADD A USER
	// http://localhost:8080/api/users/register (200 0K)
	@PostMapping("users/register")
	UserPojo register(@Valid @RequestBody UserPojo userPojo) throws ApplicationException {
		return userService.register(userPojo);

	}

	// VALIDATE A USER
	// http://localhost:8080/api/users/login (200 OK)
	@PostMapping("users/login")
	UserPojo validateUser(@Valid @RequestBody UserPojo userPojo) throws ApplicationException {
		return userService.validateUser(userPojo);

	}

	// UPDATE A USER
	// http://localhost:8080/api/users/update/1 (200 OK)
	@PutMapping("users/update/{userid}")
	UserPojo updateUser(@Valid @RequestBody UserPojo userPojo) throws ApplicationException {
		return userService.updateUser(userPojo);

	}

	// GET ALL USERS
	// http://localhost:8080/api/users/employees (200 OK)
	@GetMapping("users/employees")
	List<UserPojo> getAllUsers() throws ApplicationException {
		return userService.getAllUsers();

	}

	// (HARD) DELETE A USER
	// http://localhost:8080/api/users/remove/6 (200 OK)
	@DeleteMapping("users/remove/{userid}")
	boolean deleteUser(@PathVariable("userid") int userId) throws ApplicationException {
		return userService.deleteUser(userId);

	}

	// GET A USER
	// http://localhost:8080/api/users/employee/3 (200 OK)
	@GetMapping("users/employee/{userid}")
	UserPojo getAUser(@PathVariable("userid") int userId) throws ApplicationException {
		return userService.getAUser(userId);

	}

}
