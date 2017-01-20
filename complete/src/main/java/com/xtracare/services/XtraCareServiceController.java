package com.xtracare.services;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xtracare.booking.management.ServiceBooking;
import com.xtracare.user.management.User;
import com.xtracare.util.ConfigDetails;
import com.xtracare.util.EmailSend;
import com.xtracare.util.Utility;

@RestController
public class XtraCareServiceController {

	
	@RequestMapping(value="/login", method=RequestMethod.POST,headers="Accept=application/json") 
	public String login(@RequestBody String requestData){

		JsonObject responseData = null;
		User user = null;
		try {
			responseData = new JsonObject();
			if (requestData == null || requestData.trim().isEmpty()) {
				return "";
			}
			System.out.print("request Data :" +requestData);
			
			user = new User();
			user = new Gson().fromJson(requestData, User.class);
			ConfigDetails cnfDetails =user.login(user.getUserId());
			responseData.addProperty("responseCode", "100");
			responseData.addProperty("responseMessage", "Login Succesfull");
			responseData.addProperty("name", cnfDetails.getName());
			JsonParser jp =new JsonParser();
			responseData.add("ownerDetails",jp.parse(cnfDetails.getOwnerDetails()).getAsJsonObject() );
			responseData.add("carModels",jp.parse(cnfDetails.getCarModels()).getAsJsonArray());
			responseData.add("serviceType",jp.parse(cnfDetails.getServiceType()).getAsJsonArray() );
			responseData.addProperty("emailVerfied", cnfDetails.isEmailVerified());
			responseData.addProperty("userId", cnfDetails.getUserId());
			responseData.addProperty("emailId", cnfDetails.getEmailId());
			responseData.addProperty("mobileNumber", cnfDetails.getMobileNumber());
			
			
		}catch (Exception e) {
			e.printStackTrace();
			responseData.addProperty("responseCode", "400");
			responseData.addProperty("responseMessage", "Unable To Process Request!! Contact Back Office");
		}
		System.out.print("final response data:" + responseData);
	
		return new Gson().toJson(responseData);
	
	
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST,headers="Accept=application/json") 
	public String register(@RequestBody String requestData){

		JsonObject responseData = null;
		User user = null;
		try {
			responseData = new JsonObject();
			if (requestData == null || requestData.trim().isEmpty()) {
				return "";
			}
			System.out.print("request Data :" +requestData);
			
			user = new User();
			user = new Gson().fromJson(requestData, User.class);
			User newuser =user.registerUser();
			if(newuser.getUserId()>0){
				responseData.addProperty("responseCode", "100");
				responseData.addProperty("responseMessage", "Registration Successfull !!Welcome To Xtracare");
				responseData.addProperty("name", newuser.getFname()+" "+newuser.getLname());
				responseData.addProperty("userId", newuser.getUserId());
				EmailSend  sendMail = new EmailSend(Utility.getConfigValue("sysEmail"),"<strong>User Registration Mail</strong></br> <table><thead>User Details:-</thead><tbody><tr><td>Name:-"+user.getFname()+" "+user.getLname()+"</td></tr><tr><td>Email:-"+user.getEmailId()+" <td></tr><tr><td>Mobile:-"+user.getMobileNumber()+" <td></tr></tbody></table>");
				sendMail.setDaemon(true);
				sendMail.start();
			
			}else if(newuser.getUserId()==-1){
				responseData.addProperty("responseCode", "500");
				responseData.addProperty("responseMessage", "Unable To Process Request!! Limit Exceeded");
			}else if(newuser.getUserId()==-2){
				responseData.addProperty("responseCode", "500");
				responseData.addProperty("responseMessage", "User already register with Email Id or Mobile Number!!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			responseData.addProperty("responseCode", "400");
			responseData.addProperty("responseMessage", "Unable To Process Request!! Contact Back Office");
		}
		System.out.print("final response data:" + responseData);
	
		return new Gson().toJson(responseData);
	
	}
	
	@RequestMapping(value="/serviceBooking", method=RequestMethod.POST,headers="Accept=application/json") 
	public String serviceBooking(@RequestBody String requestData){


		JsonObject responseData = null;
		ServiceBooking serviceBooking = null;
		try {
			responseData = new JsonObject();
			if (requestData == null || requestData.trim().isEmpty()) {
				return "";
			}
			System.out.print("request Data :" +requestData);
			
			serviceBooking = new ServiceBooking();
			serviceBooking = new Gson().fromJson(requestData, ServiceBooking.class);
			serviceBooking.setServiceBookingJson(requestData);
			ServiceBooking serviceBookingResp =serviceBooking.bookService();
			if(serviceBookingResp.isSuccess()){
				responseData.addProperty("responseCode", "100");
				responseData.addProperty("responseMessage", "Service Booking Request Received");
				responseData.addProperty("bookignId", serviceBookingResp.getServiceBookingId());
				EmailSend sendMail = new EmailSend(serviceBookingResp.getEmailId(),"<strong>Service Booking</strong></br> <table><thead>Thanks For Choosing Xtra Care</thead><tbody><tr><td>Your booking id is:-<strong>"+serviceBookingResp.getServiceBookingId()+"</strong> </br>Please use this to reference any booking realted queries </td></tr></tbody></table>");
				sendMail.setDaemon(true);
				sendMail.start();
				
			 }else{
				responseData.addProperty("responseCode", "500");
				responseData.addProperty("responseMessage",serviceBookingResp.getErrorMsg());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			responseData.addProperty("responseCode", "400");
			responseData.addProperty("responseMessage", "Unable To Process Request!! Contact Back Office");
		}
		System.out.print("final response data:" + responseData);
	
		return new Gson().toJson(responseData);
	
	
	}
	/*public static void main(String[] args) {
		EmailSend  sendMail = new EmailSend(Utility.getConfigValue("sysEmail"),"<strong>User Registration Mail</strong></br> <table><thead>User Details:-</thead><tbody><tr><td>Name:-Neera jain</td></tr></tbody></table>");
		//sendMail.setDaemon(true);
		sendMail.start();
	}*/
	
}
