package com.dollarsbank.model;

public class Account {
	private String userId;
	private String password;
	private double checkingAmount;
	private double savingsAmount;
	private int customerId;
	
	public Account() {
		
	}

	public Account(String userId, String password, double checkingAmount, double savingsAmount, int customerId) {
		super();
		this.userId = userId;
		this.password = password;
		this.checkingAmount = checkingAmount;
		this.savingsAmount = savingsAmount;
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCheckingAmount() {
		return checkingAmount;
	}

	public void setCheckingAmount(double checkingAmount) {
		this.checkingAmount = checkingAmount;
	}

	public double getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(double savingsAmount) {
		this.savingsAmount = savingsAmount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password + ", checkingAmount=" + checkingAmount
				+ ", savingsAmount=" + savingsAmount + ", customerId=" + customerId + "]";
	}

}
