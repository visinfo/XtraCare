package com.xtracare.booking.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.JsonParser;
import com.xtracare.db.DBConnect;
import com.xtracare.util.ConfigDetails;
import com.xtracare.util.Utility;

public class ServiceBookingDao {

	public static ServiceBooking serviceBooking(ServiceBooking serviceBooking){
		 
		String insertServiceBooking ="insert into xt_service_booking(bookingDetails,bookingId,bookDate,userId) values(?,?,?,?)";
		String selectBookingCount ="select count(*) bookingCount,user_id  from  xt_service_booking right  join xt_user_master  on user_id=userId  where user_id=? and date(bookDate)=date(now())";
		
		try(Connection con =DBConnect.getConnection();PreparedStatement selectBookingCountPstmt = con.prepareStatement(selectBookingCount);PreparedStatement insertPstmt = con.prepareStatement(insertServiceBooking,Statement.RETURN_GENERATED_KEYS)) {
			selectBookingCountPstmt.setLong(1, serviceBooking.getUserId());
			ResultSet rs =selectBookingCountPstmt.executeQuery();
			if(rs.next()){
				
				if(rs.getLong("user_id")>0&&Utility.getConfigValue("maxServiceBookPerDay")!=null&&rs.getInt("bookingCount")<=Integer.parseInt(Utility.getConfigValue("maxServiceBookPerDay"))){
					String serviceBookingId = Utility.generateBookingId(serviceBooking.getUserId());
					JsonParser jp =new JsonParser();
					insertPstmt.setString(1, jp.parse(serviceBooking.getServiceBookingJson()).getAsJsonObject().toString());
					insertPstmt.setString(2,serviceBookingId);
					insertPstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
					insertPstmt.setLong(4,serviceBooking.getUserId());
					System.out.println("serviceBooking"+insertPstmt);
					int i =insertPstmt.executeUpdate();
					if(i>0){
						System.out.println("serviceBookingi"+i);
						serviceBooking.setSuccess(true);
						serviceBooking.setServiceBookingId(serviceBookingId);
					}
				
				}else {
					
					serviceBooking.setSuccess(false);
					if(rs.getLong("user_id")>0){
						serviceBooking.setErrorMsg("Daily Booking Limit Execeeded");
					}else {
						serviceBooking.setErrorMsg("Invalid Request");
					}
				}
				
				
				
				
			}else {
				serviceBooking.setSuccess(false);
				serviceBooking.setErrorMsg("Invalid Request");
		
			}
			
		
			
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return serviceBooking;
		
	}
	/*public static void main(String[] args) {
		ServiceBooking serviceBooking = new ServiceBooking();
		serviceBooking.setServiceBookingJson("{'userId': '1001', 'carModel': 'I20', 'jsession': 'JsessionId', 'mobileNbr': '8882747169', 'isPickNDrop': 'YES', 'serviceDate': 'yyyymmdd', 'serviceDesc': 'Normal service with wash', 'serviceType': 'PaidService'}");
		serviceBooking.setUserId(1017);
		System.out.println(serviceBooking(serviceBooking).getServiceBookingId());
	}*/
	public static ConfigDetails login(long userId) {
		String selectUser ="select * from   xt_user_master  where user_id=?";
		ConfigDetails cnfDetails = new ConfigDetails();
		try(Connection con =DBConnect.getConnection();PreparedStatement selectPstmt = con.prepareStatement(selectUser);) {
	
			selectPstmt.setLong(1, userId);
			ResultSet rs =selectPstmt.executeQuery();
			if(rs.next()){
				cnfDetails.setName(rs.getString("fname")+" "+rs.getString("lname"));
				cnfDetails.setEmailVerified(rs.getString("isVerified").equalsIgnoreCase("Y") ? true :false);
			
			}
		  cnfDetails.setCarModels(Utility.getConfigValue("carModels"));
		  cnfDetails.setServiceType(Utility.getConfigValue("serviceType"));
		  cnfDetails.setOwnerDetails(Utility.getConfigValue("ownerDetails"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return cnfDetails;
	}
}
