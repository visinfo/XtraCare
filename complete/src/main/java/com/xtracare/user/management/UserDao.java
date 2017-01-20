package com.xtracare.user.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.xtracare.db.DBConnect;
import com.xtracare.util.ConfigDetails;
import com.xtracare.util.Utility;

public class UserDao {

	public static User register(User user){
		 
		String insertUser ="insert into xt_user_master(fname,lname,mobile_number,email,reg_date) values(?,?,?,?,?)";
		String updateUserCount ="update xt_config_master set value=value+1 where code='currentUserCount'";
		int userId=0;
		User newUser=null;
		try(Connection con =DBConnect.getConnection();PreparedStatement insertPstmt = con.prepareStatement(insertUser,Statement.RETURN_GENERATED_KEYS);PreparedStatement updatePstmt = con.prepareStatement(updateUserCount)) {
			newUser = new User();
			if(Utility.getConfigValue("currentUserCount")!=null&&Integer.parseInt(Utility.getConfigValue("currentUserCount"))<Integer.parseInt(Utility.getConfigValue("maxUserCount"))){
				insertPstmt.setString(1, user.getFname());
				insertPstmt.setString(2, user.getLname());
				insertPstmt.setLong(3, user.getMobileNumber());
				insertPstmt.setString(4, user.getEmailId());
				insertPstmt.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
				System.out.println("register"+insertPstmt);
				insertPstmt.executeUpdate();
				
				ResultSet rs =insertPstmt.getGeneratedKeys();
				if(rs.next()){
					userId=	rs.getInt(1);
					
				}
				
				newUser.setUserId(userId);
				newUser.setFname(user.getFname());
				newUser.setLname(user.getLname());
				int i=updatePstmt.executeUpdate();
				
				 
			}else {
				newUser.setUserId(-1);
				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("Duplicate entry")){
				newUser.setUserId(-2);
			}
		}
		return newUser;
		
	}
	/*public static void main(String[] args) {
		User user = new User();
		user.setEmailId("neeraj.jain@skilrock.com");
		user.setFname("neeraj");
		user.setLname("jaiva");
		long nbr =8883774759l;
		user.setMobileNumber(nbr);
		
		UserDao.register(user);
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
				cnfDetails.setEmailId(rs.getString("email"));
				cnfDetails.setMobileNumber(rs.getLong("mobile_number"));
				cnfDetails.setUserId(userId);
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
