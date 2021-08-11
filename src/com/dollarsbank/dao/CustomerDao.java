package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtil;


public class CustomerDao {
	private static final Connection conn = ConnectionManager.getConnection();
	
	private static final String GET_MAX_CUST_ID = "SELECT MAX(customer_id) as maxId FROM customer";
	private static final String GET_CUST_BY_ID = "SELECT * FROM customer WHERE customer_id = ?";
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
	
	public Customer getCustomerByCustomerId(int custId) {
		Customer cust = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(GET_CUST_BY_ID)) {
			pstmt.setInt(1, custId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String phoneNum = rs.getString("phonenum");
				String address = rs.getString("address");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String country = rs.getString("country");
				cust = new Customer(firstName, lastName, phoneNum, address, city, state, country);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cust;
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
