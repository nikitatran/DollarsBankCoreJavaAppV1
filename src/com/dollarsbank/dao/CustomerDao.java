package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtil;


public class CustomerDao {
	private static final Connection conn = ConnectionManager.getConnection();
	
	private static final String GET_MAX_CUST_ID = "SELECT MAX(customer_id) as maxId FROM customer";
	private static final String CREATE_CUSTOMER = "INSERT INTO customer"
			+ "(firstname, lastname, phonenum, city, state, country, address) VALUES(?, ?, ?, ?, ?, ?, ?)";

	
	public boolean createCustomer(Customer cust) {
		try (PreparedStatement pstmt = conn.prepareStatement(CREATE_CUSTOMER)) {
			pstmt.setString(1, cust.getFirstName());
			pstmt.setString(2, cust.getLastName());
			pstmt.setString(3, cust.getPhoneNum());
			pstmt.setString(4, cust.getCity());
			pstmt.setString(5, cust.getState());
			pstmt.setString(6, cust.getCountry());
			pstmt.setString(7, cust.getAddress());
			if (pstmt.executeUpdate() > 0) {
				System.out.println(ColorsUtil.ANSI_GREEN + "Customer created successfully" + ColorsUtil.ANSI_RESET);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ColorsUtil.ANSI_RED + "Customer creation failed" + ColorsUtil.ANSI_RESET);
		return false;
	}
	
	public int getMaxCustomerId() {
		try (PreparedStatement pstmt = conn.prepareStatement(GET_MAX_CUST_ID)) {
			ResultSet rs = pstmt.executeQuery();
			int id = 1; //if there are no customers then default to 1
			if (rs.next()) {
				id = rs.getInt("maxId");
			}
			return id;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
