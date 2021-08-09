package com.dollarsbank.model;

public class Account {
	private int userId;
	private String password;
	private double amount;
	private int customerId;
	
	public Account() {
		
	}

	public Account(int userId, String password, double amount, int customerId) {
		super();
		this.userId = userId;
		this.password = password;
		this.amount = amount;
		this.customerId = customerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password + ", amount=" + amount + ", customerId="
				+ customerId + "]";
	}
}
