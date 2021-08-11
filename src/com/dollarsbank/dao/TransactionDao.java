package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Transaction;
import com.dollarsbank.utility.ColorsUtil;


public class TransactionDao {
	private static final Connection conn = ConnectionManager.getConnection();
	
	private static final String CREATE_TRANSACTION = "INSERT INTO transaction"
			+ "(user_id, customer_id, timestamp, message) VALUES(?, ?, ?, ?)";

	
	public boolean createTransaction(Transaction trans) {
		try (PreparedStatement pstmt = conn.prepareStatement(CREATE_TRANSACTION)) {
			pstmt.setString(1, trans.getUserId());
			pstmt.setInt(2, trans.getCustomerId());
			pstmt.setTimestamp(3, trans.getTimestamp());
			pstmt.setString(4, trans.getMessage());
			if (pstmt.executeUpdate() > 0) {
				System.out.println(ColorsUtil.ANSI_GREEN + "Transaction created successfully" + ColorsUtil.ANSI_RESET);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ColorsUtil.ANSI_RED + "Transaction creation failed" + ColorsUtil.ANSI_RESET);
		return false;
	}
	
}
