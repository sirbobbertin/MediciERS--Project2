package com.project2.mediciERS.pojo;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReimbursementPojo {
	
	private int rbId; // REIMBURSEMENT ID
	
	@NotNull
	private int userId; // USER ID
	
	@NotNull
	private Date rbDate; // REIMBURSEMENT SUBMIT DATE
	
	@Min(0)
	private int rbAmount; // REIMBURSEMENT AMOUNT
	
	private String rbStatus; // PENDING, APPROVED, OR DENIED
	
	private boolean rbResolved; // TRUE OR FALSE
	
	private boolean rbRemoved; // TRUE OR FAlSE
	
	public ReimbursementPojo() {
		super();
	}

	public ReimbursementPojo(int rbId, int userId, Date rbDate, int rbAmount, String rbStatus, boolean rbResolved, boolean rbRemoved) {
		super();
		this.rbId = rbId;
		this.userId = userId;
		this.rbDate = rbDate;
		this.rbAmount = rbAmount;
		this.rbStatus = rbStatus;
		this.rbResolved = rbResolved;
		this.rbRemoved = rbRemoved;
	}


	public int getRbId() {
		return rbId;
	}


	public void setRbId(int rbId) {
		this.rbId = rbId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Date getRbDate() {
		return rbDate;
	}


	public void setRbDate(Date rbDate) {
		this.rbDate = rbDate;
	}


	public int getRbAmount() {
		return rbAmount;
	}


	public void setRbAmount(int rbAmount) {
		this.rbAmount = rbAmount;
	}


	public String getRbStatus() {
		return rbStatus;
	}


	public void setRbStatus(String rbStatus) {
		this.rbStatus = rbStatus;
	}


	public boolean isRbResolved() {
		return rbResolved;
	}


	public void setRbResolved(boolean rbResolved) {
		this.rbResolved = rbResolved;
	}


	public boolean isRbRemoved() {
		return rbRemoved;
	}


	public void setRbRemoved(boolean rbRemoved) {
		this.rbRemoved = rbRemoved;
	}


	@Override
	public String toString() {
		return "ReimbursementPojo [rbId=" + rbId + ", userId=" + userId + ", rbDate=" + rbDate + ", rbAmount="
				+ rbAmount + ", rbStatus=" + rbStatus + ", rbResolved=" + rbResolved + ", rbRemoved=" + rbRemoved + "]";
	}

}