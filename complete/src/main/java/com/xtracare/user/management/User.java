package com.xtracare.user.management;

import com.xtracare.util.ConfigDetails;

public class User {

	
	private long mobileNumber;
	private String emailId;
	private String fname;
	private String lname;
	private int userId;
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public User registerUser(){
		return UserDao.register(this);
		
	}
	public ConfigDetails login(long userId){
		return UserDao.login(userId);
		
	}
	
	
	
	
}
