package com.project2.mediciERS.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.mediciERS.entity.Reimbursement;

@Repository
public interface ReimbursementRepositoryDao extends JpaRepository<Reimbursement, Integer> {
	
	// ______________________________ FINDER METHODS ______________________________________
	
	// GET ALL PENDING REQUESTS (MANAGER)
	// select * from reimbursement_details where rb_staus='pending' ";
	List<Reimbursement> findByRbStatus(String rbStatus);
	
	// GET ALL RESOLVED REQUESTS (MANAGER)
	// select * from reimbursement_details where rb_resolved=true";
	List<Reimbursement> findByRbResolved(boolean rbResolved);
	
	// GET ALL OF AN EMPLOYEE'S REIMBURSEMENT REQUESTS (MANAGER)
	// select * from reimbursement_details where user_id=" + userId;
	List<Reimbursement> findByUserIdAndRbRemoved(Integer userId, boolean rbRemoved);
	
	// GET AN EMPLOYEE'S PENDING REQUESTS (EMPLOYEE)
	// select * from reimbursement_details where user_id=" + userId + "and rb_status='pending' ";
	List<Reimbursement> findByUserIdAndRbStatusAndRbResolved(int userId, String rbStatus, boolean rbResolved);
	
	// GET AN EMPLOYEE'S RESOLVED REQUESTS (EMPLOYEE)
	// select * from reimbursement_details where user_id=" + userId + "and rb_resolved=true ";
	List<Reimbursement> findByUserIdAndRbResolved(Integer userId, boolean rbResolved);
	

}
