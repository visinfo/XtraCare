package com.xtracare.util;

public class ConfigDetails {

	private String name ;
	private String ownerDetails;
	private String carModels;
	private String serviceType;
	private boolean emailVerified;
	
	private long mobileNumber;
	private String emailId;
	private long userId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwnerDetails() {
		return ownerDetails;
	}
	public void setOwnerDetails(String ownerDetails) {
		this.ownerDetails = ownerDetails;
	}
	public String getCarModels() {
		return carModels;
	}
	public void setCarModels(String carModels) {
		this.carModels = carModels;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
