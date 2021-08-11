package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Transaction;
import com.dollarsbank.utility.ColorsUtil;

public class TransactionDao {
	private static final Connection conn = ConnectionManager.getConnection();

	private static final String CREATE_TRANSACTION = "INSERT INTO transaction"
			+ "(user_id, customer_id, timestamp, message) VALUES(?, ?, ?, ?)";
	private static final String FIVE_TRANSACTIONS_RECENT = "SELECT * FROM transaction where customer_id = ? ORDER BY timestamp desc LIMIT 5";

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

	public List<Transaction> getUser5RecentTransactions(int id){
		List<Transaction> trans = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(FIVE_TRANSACTIONS_RECENT)){
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				trans.add(new Transaction(
							rs.getString("user_id"), 
							rs.getInt("customer_id"), 
							rs.getTimestamp("timestamp"), 
							rs.getString("message")
						));
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trans;
	}
}
