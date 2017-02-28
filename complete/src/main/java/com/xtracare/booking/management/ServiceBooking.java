package com.xtracare.booking.management;

public class ServiceBooking {

	private long userId;
	
	private long mobileNbr;
	private String  carModel;
	private String isPickNDrop;
	private String serviceDate;
	private String serviceDesc;
	private String serviceType;
	
	private String serviceBookingJson;

	private boolean isSuccess;
	private String  errorMsg;
	
	private String serviceBookingId;
	
	private String emailId;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	

	public long getMobileNbr() {
		return mobileNbr;
	}

	public void setMobileNbr(long mobileNbr) {
		this.mobileNbr = mobileNbr;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getIsPickNDrop() {
		return isPickNDrop;
	}

	public void setIsPickNDrop(String isPickNDrop) {
		this.isPickNDrop = isPickNDrop;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceBookingJson() {
		return serviceBookingJson;
	}

	public void setServiceBookingJson(String serviceBookingJson) {
		this.serviceBookingJson = serviceBookingJson;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getServiceBookingId() {
		return serviceBookingId;
	}

	public void setServiceBookingId(String serviceBookingId) {
		this.serviceBookingId = serviceBookingId;
	}
	
	public ServiceBooking bookService(){
	
		return	ServiceBookingDao.serviceBooking(this);
		
		
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
	
}
