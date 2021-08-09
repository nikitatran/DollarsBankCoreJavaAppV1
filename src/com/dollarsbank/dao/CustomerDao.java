package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Customer;

public class CustomerDao {
	private static final Connection conn = ConnectionManager.getConnection();
	
	private static final String CREATE_CUSTOMER = "INSERT INTO customer"
			+ "(firstname, lastname, phonenum, city, state, country) VALUES(?, ?, ?, ?, ?, ?)";
	
	public boolean createCustomer(Customer cust) {
		try (PreparedStatement pstmt = conn.prepareStatement(CREATE_CUSTOMER)) {
			pstmt.setString(1, cust.getFirstName());
			pstmt.setString(2, cust.getLastName());
			pstmt.setString(3, cust.getPhoneNum());
			pstmt.setString(4, cust.getCity());
			pstmt.setString(5, cust.getState());
			pstmt.setString(6, cust.getCountry());
			if (pstmt.executeUpdate() > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
