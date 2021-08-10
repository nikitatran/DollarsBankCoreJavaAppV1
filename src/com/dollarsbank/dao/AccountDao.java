package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;

public class AccountDao {
	private static final Connection conn = ConnectionManager.getConnection();
	
	private static final String ACCOUNT_BY_USERID = "SELECT * FROM account WHERE user_id = ?";
	
	public Account getAccountByUserId(String userId) {
		Account acc = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(ACCOUNT_BY_USERID)) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String user_id = rs.getString("user_id");
				String password = rs.getString("password");
				double amount = rs.getDouble("amount");
				int cust_id = rs.getInt("customer_id");
				acc = new Account(user_id, password, amount, cust_id);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc;
	}
	
}
