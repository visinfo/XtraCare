package com.xtracare.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import com.xtracare.db.DBConnect;

public class Utility {

	private static HashMap<String, String> configMap=null;
	
	public static String getConfigValue(String configCode){
    	
		if(configMap==null){
			configMap= new HashMap<String,String>();
			String selectConfigValues ="select * from   xt_config_master  where status=?";
			try(Connection con =DBConnect.getConnection();PreparedStatement selectPstmt = con.prepareStatement(selectConfigValues);) {
							
				selectPstmt.setString(1,"ACTIVE");
				ResultSet rs =selectPstmt.executeQuery();
				while(rs.next()){
					configMap.put(rs.getString("code"), rs.getString("value"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(configCode.equalsIgnoreCase("currentUserCount")) {
			String selectConfigValues ="select * from   xt_config_master  where code=?";
			try(Connection con =DBConnect.getConnection();PreparedStatement selectPstmt = con.prepareStatement(selectConfigValues);) {
							
				selectPstmt.setString(1,"currentUserCount");
				ResultSet rs =selectPstmt.executeQuery();
				while(rs.next()){
					configMap.put(rs.getString("code"), rs.getString("value"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
    	return configMap.get(configCode);
    	
    }
	public static String generateBookingId(long userId){
		int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);

		return randomNum+""+userId;
	}
	
}
