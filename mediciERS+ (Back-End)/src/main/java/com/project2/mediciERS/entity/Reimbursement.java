package com.project2.mediciERS.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reimbursement_details")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rb_id")
	private int rbId;
	
	@Column(name = "user_id")
	private int userId;
	
	@NotNull
	@Column(name = "rb_date")
	private Date rbDate;

	@NotNull
	@Column(name = "rb_amount")
	private int rbAmount;

	@Column(name = "rb_status")
	private String rbStatus;

	@Column(name = "rb_resolved")
	private boolean rbResolved;

	@Column(name = "rb_removed")
	private boolean rbRemoved;

	public Reimbursement() {
		super();
	}

	
	public Reimbursement(int rbId, int userId, Date rbDate, int rbAmount, String rbStatus, boolean rbResolved, boolean rbRemoved) {
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
		return "Reimbursement [rbId=" + rbId + ", userId=" + userId + ", rbDate=" + rbDate + ", rbAmount=" + rbAmount
				+ ", rbStatus=" + rbStatus + ", rbResolved=" + rbResolved + ", rbRemoved=" + rbRemoved + "]";
	}

}
