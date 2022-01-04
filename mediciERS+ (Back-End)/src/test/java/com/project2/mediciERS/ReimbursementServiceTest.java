package com.project2.mediciERS;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project2.mediciERS.dao.ReimbursementRepositoryDao;
import com.project2.mediciERS.entity.Reimbursement;
import com.project2.mediciERS.exception.ApplicationException;
import com.project2.mediciERS.service.ReimbursementService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimbursementServiceTest {
	
	@Autowired
	private ReimbursementService reimbursementService;
	
	
	
	@MockBean
	private ReimbursementRepositoryDao reimbursementRepositoryDao;
	
	@Test
	public void getSpecificRequests() throws ApplicationException {
		when(reimbursementRepositoryDao.findByUserIdAndRbRemoved(2, false)).thenReturn(Stream
				.of(new Reimbursement(1, 2, new Date(12-02-2021), 500, "pending", false, false)).
				collect(Collectors.toList()));
		assertEquals(1,reimbursementService.getSpecificRequests(2,false).size());
		
		}
	
	@Test
	public void getAllPendingRequestsTest() throws ApplicationException {
		when(reimbursementRepositoryDao.findByRbStatus("pending")).thenReturn(Stream
				.of(new Reimbursement(1, 2, new Date(12-02-2021), 500, "pending", false, false)).
				collect(Collectors.toList()));
		assertEquals(1,reimbursementService.getAllPendingRequests("pending").size());
		
		}
	
	@Test
	public void getAllResolvedRequestsTest() throws ApplicationException {
		when(reimbursementRepositoryDao.findByRbResolved(true)).thenReturn(Stream
				.of(new Reimbursement(1, 2, new Date(12-02-2021), 500, "resolved", true, false)).
				collect(Collectors.toList()));
		assertEquals(1,reimbursementService.getAllResolvedRequests(true).size());
		
		}
	@Test
	public void getAReimbursementTest() throws ApplicationException {
		
		Reimbursement reimbursement = new Reimbursement(2, 2, new Date(03-12-2021), 200, "pending", false, false);
		when(reimbursementRepositoryDao.findById(2)).thenReturn(Optional.of(reimbursement));
		
		}
	@Test
	public void updateReimbursementTest() throws ApplicationException {
		
		Reimbursement updateReimbursement = new Reimbursement(2, 2, new Date(03-12-2021), 200, "pending", false, false);
		when(reimbursementRepositoryDao.save(updateReimbursement)).thenReturn(updateReimbursement);
		
		}
	@Test
	public void deleteRequestTest() throws ApplicationException {
		Reimbursement reimbursement = new Reimbursement(2, 2, new Date(03-12-2021), 200, "pending", false, false);
		reimbursementService.deleteRequest(2);
		verify(reimbursementRepositoryDao, times(1)).deleteById(2);
	}
	@Test
	public void denyRequestTest() throws ApplicationException {
		
		Reimbursement reimbursement = new Reimbursement(2, 2, new Date(03-12-2021), 200, "denied", true, false);
		when(reimbursementRepositoryDao.findById(2)).thenReturn(Optional.of(reimbursement));
		
		}
	@Test
	public void manageRequestTest() throws ApplicationException {
		
		Reimbursement reimbursement = new Reimbursement(3, 2, new Date(03-12-2021), 200, "approved", true, false);
		when(reimbursementRepositoryDao.findById(3)).thenReturn(Optional.of(reimbursement));
		
		}
	@Test
	public void submitRequestTest() throws ApplicationException {
		Reimbursement newReimbursement = new Reimbursement(4, 2, new Date(03-12-2021), 200, "pending", false, false);
		when(reimbursementRepositoryDao.saveAndFlush(any(Reimbursement.class))).thenReturn(newReimbursement);
	}
	@Test
	public void getPendingRequestsTest() throws ApplicationException {
		when(reimbursementRepositoryDao.findByUserIdAndRbStatusAndRbResolved(2, "pending", false)).thenReturn(Stream
				.of(new Reimbursement(1, 2, new Date(12-02-2021), 500, "pending", false, false)).
				collect(Collectors.toList()));
		assertEquals(1,reimbursementService.getPendingRequests(2,"pending", false).size());
		
		}
	@Test
	public void getResolvedRequestsTest() throws ApplicationException {
		when(reimbursementRepositoryDao.findByUserIdAndRbResolved(2, true)).thenReturn(Stream
				.of(new Reimbursement(1, 2, new Date(12-02-2021), 500, "pending", false, false)).
				collect(Collectors.toList()));
		assertEquals(1,reimbursementService.getResolvedRequests(2,true).size());
		
		}
}