package com.project2.mediciERS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.mediciERS.dao.ReimbursementRepositoryDao;
import com.project2.mediciERS.entity.Reimbursement;
import com.project2.mediciERS.exception.ApplicationException;
import com.project2.mediciERS.pojo.ReimbursementPojo;

@Service
public class ReimbursementServiceImpl implements ReimbursementService {

	public static final Logger logger = LogManager.getLogger(ReimbursementServiceImpl.class);

	@Autowired
	ReimbursementRepositoryDao reimbursementRepositoryDao;

	public ReimbursementServiceImpl() {

	}

	// ADD REIMBURSEMENT
	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		logger.info("Entered submitRequest() in service.");
		reimbursementPojo.setRbStatus("pending");
		Reimbursement newReimbursement = new Reimbursement(reimbursementPojo.getRbId(),
				reimbursementPojo.getUserId(), reimbursementPojo.getRbDate(), reimbursementPojo.getRbAmount(),
				reimbursementPojo.getRbStatus(), reimbursementPojo.isRbResolved(), reimbursementPojo.isRbRemoved());
		Reimbursement returnReimbursement = reimbursementRepositoryDao.saveAndFlush(newReimbursement);
		reimbursementPojo.setRbId(returnReimbursement.getRbId());
		logger.info("Exited submitRequest() in service.");
		return reimbursementPojo;
	}

	// GET ALL PENDING REQUESTS (MANAGER)
	@Override
	public List<ReimbursementPojo> getAllPendingRequests(String rbStatus) throws ApplicationException {
		logger.info("Entered getAllPendingRequests() in service.");
		
		List<Reimbursement> allPendingRequestsEntity = this.reimbursementRepositoryDao.findByRbStatus(rbStatus);
		List<ReimbursementPojo> allReimbursementsPojo = new ArrayList<ReimbursementPojo>();
		
		allPendingRequestsEntity.forEach((reimbursement) -> {
			ReimbursementPojo reimbursementPojo1 = new ReimbursementPojo();
			reimbursementPojo1.setRbStatus("pending");
			ReimbursementPojo reimbursementPojo = new ReimbursementPojo(reimbursement.getRbId(),
					reimbursement.getUserId(), reimbursement.getRbDate(), reimbursement.getRbAmount(),
					reimbursement.getRbStatus(), reimbursement.isRbResolved(), reimbursement.isRbRemoved());
			allReimbursementsPojo.add(reimbursementPojo);
		});
		logger.info("Exited getAllPendingRequests() in service.");
		return allReimbursementsPojo;
	}

	// GET ALL RESOLVED REQUESTS (MANAGER)
	@Override
	public List<ReimbursementPojo> getAllResolvedRequests(boolean rbResolved) throws ApplicationException {
		logger.info("Entered getAllResolvedRequests() in service.");

		List<Reimbursement> allResolvedRequestsEntity = this.reimbursementRepositoryDao.findByRbResolved(rbResolved);
		List<ReimbursementPojo> allReimbursementsPojo = new ArrayList<ReimbursementPojo>();
		
		allResolvedRequestsEntity.forEach((reimbursement) -> {
			ReimbursementPojo reimbursementPojo1 = new ReimbursementPojo();
			reimbursementPojo1.setRbResolved(true);
			ReimbursementPojo reimbursementPojo = new ReimbursementPojo(reimbursement.getRbId(),
					reimbursement.getUserId(), reimbursement.getRbDate(), reimbursement.getRbAmount(),
					reimbursement.getRbStatus(), reimbursement.isRbResolved(), reimbursement.isRbRemoved());
			allReimbursementsPojo.add(reimbursementPojo);
		});
		logger.info("Exited getAllResolvedRequests() in service.");
		return allReimbursementsPojo;
	}

	// APPROVE A REIMBURSEMENT REQUEST
	@Override
	public ReimbursementPojo manageRequest(int rbId) throws ApplicationException {
		logger.info("Entered manageRequest() in service.");
		Optional<Reimbursement> optional = this.reimbursementRepositoryDao.findById(rbId);
		Reimbursement reimbursement = null;
		if(optional.isPresent()) {
			reimbursement = optional.get();
			reimbursement.setRbStatus("approved");
			reimbursement.setRbResolved(true);
			this.reimbursementRepositoryDao.save(reimbursement);
			}
		ReimbursementPojo reimbursementPojo = new ReimbursementPojo(reimbursement.getRbId(),
				reimbursement.getUserId(), reimbursement.getRbDate(), reimbursement.getRbAmount(),
				reimbursement.getRbStatus(), reimbursement.isRbResolved(), reimbursement.isRbRemoved());
		logger.info("Exited manageRequest() in service.");
		return reimbursementPojo;
	}

	// DENY A REIMBURSEMENT REQUEST
	@Override
	public ReimbursementPojo denyRequest(int rbId) throws ApplicationException {
		logger.info("Entered denyRequest() in service.");
		Optional<Reimbursement> optional = this.reimbursementRepositoryDao.findById(rbId);
		Reimbursement reimbursement = null;
		if(optional.isPresent()) {
			reimbursement = optional.get();
			reimbursement.setRbStatus("denied");
			reimbursement.setRbResolved(true);
			this.reimbursementRepositoryDao.save(reimbursement);
			}
		ReimbursementPojo reimbursementPojo = new ReimbursementPojo(reimbursement.getRbId(),
				reimbursement.getUserId(), reimbursement.getRbDate(), reimbursement.getRbAmount(),
				reimbursement.getRbStatus(), reimbursement.isRbResolved(), reimbursement.isRbRemoved());
		logger.info("Exited denyRequest() in service.");
		return reimbursementPojo;

	}

	// GET ALL OF A SPECIFIC EMPLOYEE'S REIMBURSEMENT REQUESTS
	@Override
	public List<ReimbursementPojo> getSpecificRequests(Integer userId, boolean rbRemoved) throws ApplicationException {
		logger.info("Entered getSpecificRequests() in service.");
		
		List<Reimbursement> specificRequestsEntity = this.reimbursementRepositoryDao.findByUserIdAndRbRemoved(userId, false);
		List<ReimbursementPojo> allReimbursementsPojo = new ArrayList<ReimbursementPojo>();
		
		specificRequestsEntity.forEach((reimbursement) -> {
			ReimbursementPojo reimbursementPojo = new ReimbursementPojo();
			reimbursementPojo.setUserId(userId);
			reimbursementPojo.setRbRemoved(false);
			ReimbursementPojo reimbursementPojo1 = new ReimbursementPojo(reimbursement.getRbId(),
					reimbursement.getUserId(), reimbursement.getRbDate(), reimbursement.getRbAmount(),
					reimbursement.getRbStatus(), reimbursement.isRbResolved(), reimbursement.isRbRemoved());
			allReimbursementsPojo.add(reimbursementPojo1);
		});
		logger.info("Exited getSpecificRequests() in service.");
		return allReimbursementsPojo;

	}

	// (HARD) DELETE A REIMBURSEMENT
	@Override
	public boolean deleteRequest(Integer rbId) throws ApplicationException {
		logger.info("Entered deleteRequest() in service.");
		this.reimbursementRepositoryDao.deleteById(rbId); // deleteByRbId
		logger.info("Exited deleteRequest() in service.");
		return true;
	}

	// GET ALL OF AN EMPLOYEE'S PENDING REQUESTS (EMPLOYEE)
	@Override
	public List<ReimbursementPojo> getPendingRequests(int userId, String rbStatus, boolean rbResolved) throws ApplicationException {
		logger.info("Entered getPendingRequests() in service.");
		System.out.println(userId + rbStatus + rbResolved);
		List<Reimbursement> allResolvedRequestsEntity = this.reimbursementRepositoryDao.findByUserIdAndRbStatusAndRbResolved(userId, rbStatus, rbResolved);
		List<ReimbursementPojo> allReimbursementsPojo = new ArrayList<ReimbursementPojo>();
		
		allResolvedRequestsEntity.forEach((reimbursement) -> {
			ReimbursementPojo reimbursementPojo = new ReimbursementPojo();
			reimbursementPojo.setUserId(userId);
			reimbursementPojo.setRbStatus("pending");
			reimbursementPojo.setRbResolved(false);
			ReimbursementPojo reimbursementPojo1 = new ReimbursementPojo(reimbursement.getRbId(),
					reimbursement.getUserId(), reimbursement.getRbDate(), reimbursement.getRbAmount(),
					reimbursement.getRbStatus(), reimbursement.isRbResolved(), reimbursement.isRbRemoved());
			allReimbursementsPojo.add(reimbursementPojo1);
		});
		logger.info("Exited getPendingRequests() in service.");
		return allReimbursementsPojo;
	}

	// GET ALL OF AN EMPLOYEE'S RESOLVED REQUESTS (EMPLOYEE)
	@Override
	public List<ReimbursementPojo> getResolvedRequests(int userId, boolean rbResolved) throws ApplicationException {
		logger.info("Entered getResolvedRequests() in service.");
		
		List<Reimbursement> allResolvedRequestsEntity = this.reimbursementRepositoryDao.findByUserIdAndRbResolved(userId, rbResolved);
		List<ReimbursementPojo> allReimbursementsPojo = new ArrayList<ReimbursementPojo>();
		
		allResolvedRequestsEntity.forEach((reimbursement) -> {
			ReimbursementPojo reimbursementPojo = new ReimbursementPojo();
			reimbursementPojo.setUserId(userId);
			reimbursementPojo.setRbResolved(true);
			ReimbursementPojo reimbursementPojo1 = new ReimbursementPojo(reimbursement.getRbId(),
					reimbursement.getUserId(), reimbursement.getRbDate(), reimbursement.getRbAmount(),
					reimbursement.getRbStatus(), reimbursement.isRbResolved(), reimbursement.isRbRemoved());
			allReimbursementsPojo.add(reimbursementPojo1);
		});
		logger.info("Exited getResolvedRequests() in service.");
		return allReimbursementsPojo;
	}

	// UPDATE A REIMBURSEMENT
	@Override
	public ReimbursementPojo updateReimbursement(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		logger.info("Entered updateReimbursement() in service.");
		reimbursementPojo.setRbStatus("pending");		
		Reimbursement updateReimbursement = new Reimbursement(reimbursementPojo.getRbId(),
				reimbursementPojo.getUserId(), reimbursementPojo.getRbDate(), reimbursementPojo.getRbAmount(),
				reimbursementPojo.getRbStatus(), reimbursementPojo.isRbResolved(), reimbursementPojo.isRbRemoved());
		Reimbursement returnReimbursement = reimbursementRepositoryDao.save(updateReimbursement);
		logger.info("Exited updateReimbursement() in service.");
		return reimbursementPojo;
	}

	// GET A REIMBURSEMENT
	@Override
	public ReimbursementPojo getAReimbursement(int rbId) throws ApplicationException {
		logger.info("Entered getAReimbursement() in service.");
		ReimbursementPojo reimbursementPojo = null;
		Optional<Reimbursement> optional = this.reimbursementRepositoryDao.findById(rbId);
		if (optional.isPresent()) {
			Reimbursement reimbursement = optional.get();
			reimbursementPojo = new ReimbursementPojo(reimbursement.getRbId(), reimbursement.getUserId(),
					reimbursement.getRbDate(), reimbursement.getRbAmount(), reimbursement.getRbStatus(),
					reimbursement.isRbResolved(), reimbursement.isRbRemoved());
			logger.info("Exited getAReimbursement() in service.");
		}
		logger.info("Exited getAReimbursement() in service.");
		return reimbursementPojo;
	}

	@Override
	public void exitApplication() {
		// TODO Auto-generated method stub

	}
}

