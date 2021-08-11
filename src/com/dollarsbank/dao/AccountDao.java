package com.dollarsbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.dollarsbank.connection.ConnectionManager;
import com.dollarsbank.model.Account;
import com.dollarsbank.utility.ColorsUtil;

public class AccountDao {
	private static final Connection conn = ConnectionManager.getConnection();
	
	private static final String ACCOUNT_BY_USERID = "SELECT * FROM account WHERE user_id = ?";
	private static final String CREATE_ACCOUNT = "INSERT INTO account"
			+ "(user_id, password, checkingamount, savingsamount, customer_id) VALUES(?, ?, ?, ?, ?)";
	private static final String UPDATE_SAVINGS = "update account set savingsamount = ? where customer_id = ?";
	private static final String UPDATE_CHECKING = "update account set checkingamount = ? where customer_id = ?";
	private static final String GET_CHECKING = "select checkingamount from account where customer_id = ?";
	private static final String GET_SAVINGS = "select savingsamount from account where customer_id = ?";
	
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
	
	public boolean updateSavings(double amt, int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_SAVINGS)) {
			pstmt.setDouble(1, amt);
			pstmt.setInt(2, id);
			if (pstmt.executeUpdate() > 0) {
				System.out.println(ColorsUtil.ANSI_GREEN + "Savings amount updated successfully" + ColorsUtil.ANSI_RESET);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ColorsUtil.ANSI_RED + "Savings amount update failed" + ColorsUtil.ANSI_RESET);
		return false;
	}
	
	public boolean updateChecking(double amt, int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_CHECKING)) {
			pstmt.setDouble(1, amt);
			pstmt.setInt(2, id);
			if (pstmt.executeUpdate() > 0) {
				System.out.println(ColorsUtil.ANSI_GREEN + "Checking amount updated successfully" + ColorsUtil.ANSI_RESET);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ColorsUtil.ANSI_RED + "Checking amount update failed" + ColorsUtil.ANSI_RESET);
		return false;
	}
	
	public double getCheckingAmtByCustId(int id) {
		double amt = 0.0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(GET_CHECKING)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				amt = rs.getDouble("checkingamount");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}
	
	public double getSavingsAmtByCustId(int id) {
		double amt = 0.0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(GET_SAVINGS)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				amt = rs.getDouble("savingsamount");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}
}
