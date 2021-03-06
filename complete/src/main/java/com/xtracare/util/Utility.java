package com.xtracare.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.SpringApplication;

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
	public static void main(String[] args) {
		ConcurrentMap<String, String>  map = new ConcurrentHashMap<String,String>();
		map.put("A", "1");
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			System.out.println(type);
		}
		
 	}
}
