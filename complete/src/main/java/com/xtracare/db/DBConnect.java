package com.xtracare.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnect implements AutoCloseable{

	private static final Logger logger = Logger.getLogger(DBConnect.class);
	
	private static String databaseName;
	private static String hostAddress;
	private static String password;
	private static String userName;
	

	public static String getDatabaseName() {
		return databaseName;
	}

	public static void setDatabaseName(String databaseName) {
		DBConnect.databaseName = databaseName;
	}

	public static String getHostAddress() {
		return hostAddress;
	}

	public static void setHostAddress(String hostAddress) {
		DBConnect.hostAddress = hostAddress;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DBConnect.password = password;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		DBConnect.userName = userName;
	}

	

	static {
		PropertyLoader.loadProperties("xtracare.properties");
		databaseName = PropertyLoader.getProperty("databaseName");
		userName = PropertyLoader.getProperty("userName");
		hostAddress = PropertyLoader.getProperty("hostAddress");
		password = PropertyLoader.getProperty("password");
		
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + hostAddress + "/" + databaseName+"?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			logger.error("Exception: " + e);
			e.printStackTrace();
		} catch (SQLException se) {
			logger.error("Exception: " + se);
			se.printStackTrace();
		}
		return connection;
	}
	
	@Override
	public void close() throws Exception {
		this.close();
	}
}
