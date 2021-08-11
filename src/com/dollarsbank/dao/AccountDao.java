package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtil;

public class AccountDao {
	private static final Connection conn = ConnectionManager.getConnection();
	
	private static final String ACCOUNT_BY_USERID = "SELECT * FROM account WHERE user_id = ?";
	private static final String CREATE_ACCOUNT = "INSERT INTO account"
			+ "(user_id, password, checkingamount, savingsamount, customer_id) VALUES(?, ?, ?, ?, ?)";
	
	public Account getAccountByUserId(String userId) {
		Account acc = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(ACCOUNT_BY_USERID)) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String user_id = rs.getString("user_id");
				String password = rs.getString("password");
				double checkingAmt = rs.getDouble("checkingamount");
				double savingsAmt = rs.getDouble("savingsamount");
				int cust_id = rs.getInt("customer_id");
				acc = new Account(user_id, password, checkingAmt, savingsAmt, cust_id);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc;
	}
	
	public boolean createAccount(Account acc) {
		try (PreparedStatement pstmt = conn.prepareStatement(CREATE_ACCOUNT)) {
			pstmt.setString(1, acc.getUserId());
			pstmt.setString(2, acc.getPassword());
			pstmt.setDouble(3, acc.getCheckingAmount());
			pstmt.setDouble(4, acc.getSavingsAmount());
			pstmt.setInt(5, acc.getCustomerId());
			if (pstmt.executeUpdate() > 0) {
				System.out.println(ColorsUtil.ANSI_GREEN + "Account created successfully" + ColorsUtil.ANSI_RESET);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ColorsUtil.ANSI_RED + "Account creation failed" + ColorsUtil.ANSI_RESET);
		return false;
	}
}
