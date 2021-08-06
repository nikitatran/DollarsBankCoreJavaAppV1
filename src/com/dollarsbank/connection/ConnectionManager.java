package com.dollarsbank.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	private static Connection connection;
	
	//This is a Singleton class
	
	private static void makeConnection() {
		try {
			//access the info in our properties file
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("com/dollarsbank/connection/config.properties");
			
			Properties props = new Properties();
			
			props.load(input);
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected.");

		} catch (SQLException e) {
			System.out.println("Couldn't connect");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			makeConnection();
		}
		return connection;
	}
	
//	public static void main(String[] args) {
//		Connection conn = ConnectionManager.getConnection();
//		
//		try {
//			conn.close();
//			System.out.println("Connection closed");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
