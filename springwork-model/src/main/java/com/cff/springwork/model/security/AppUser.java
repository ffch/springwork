package com.cff.springwork.model.security;

import java.util.UUID;

public class AppUser {
	private String uuid;
	private String userName;
	private String password;
	private String userType;
	private String userNo;
	public AppUser() {
		
	}
	
	public AppUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.uuid = UUID.randomUUID().toString();
		this.userType = "1";
	}
	
	public AppUser(AppUser appUser) {
		this.userName = appUser.userName;
		this.password = appUser.password;
		this.uuid = appUser.uuid;
		this.userType = appUser.userType;
	}


	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
}
