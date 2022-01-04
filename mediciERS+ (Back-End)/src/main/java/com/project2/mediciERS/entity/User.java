package com.project2.mediciERS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_first_name")
	private String userFirstName;

	@Column(name = "user_last_name")
	private String userLastName;

	@Column(name = "user_address")
	private String userAddress;

	@Column(name = "user_contact")
	private String userContact;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "user_removed")
	private boolean userRemoved;

	public User() {
		super();
	}

	public User(int userId, String userPassword, String userFirstName, String userLastName, String userAddress,
			String userContact, String userType, boolean userRemoved) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userAddress = userAddress;
		this.userContact = userContact;
		this.userType = userType;
		this.userRemoved = userRemoved;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isUserRemoved() {
		return userRemoved;
	}

	public void setUserRemoved(boolean userRemoved) {
		this.userRemoved = userRemoved;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPassword=" + userPassword + ", userFirstName=" + userFirstName
				+ ", userLastName=" + userLastName + ", userAddress=" + userAddress + ", userContact=" + userContact
				+ ", userType=" + userType + ", userRemoved=" + userRemoved + "]";
	}

}
