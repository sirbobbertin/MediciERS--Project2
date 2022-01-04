package com.project2.mediciERS.pojo;

import javax.validation.constraints.NotNull;

public class UserPojo {
	
	private int userId;
	
	@NotNull
	private String userPassword;
	
	@NotNull
	private String userFirstName;
	
	@NotNull
	private String userLastName;
	
	@NotNull
	private String userAddress;
	
	@NotNull
	private String userContact;
	
	private String userType;
	
	private boolean userRemoved;
	
	public UserPojo() {
		super();
	}
	
	public UserPojo(int userId, String userPassword, String userFirstName, String userLastName, String userAddress, String userContact, String userType, boolean userRemoved) {
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
	
	// GETTERS & SETTERS
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
		return "UserPojo [userId=" + userId + ", userPassword=" + userPassword + ", userFirstName=" + userFirstName
				+ ", userLastName=" + userLastName + ", userAddress=" + userAddress + ", userContact=" + userContact
				+ ", userType=" + userType + ", userRemoved=" + userRemoved + "]";
	}
	
	

}
