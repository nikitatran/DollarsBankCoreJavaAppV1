package com.dollarsbank.model;

import java.sql.Timestamp;

public class Transaction {
	private static int idCounter = 0;
	
	private int transactionId;
	private String userId;
	private int customerId;
	private Timestamp timestamp;
	private String message;
	
	public Transaction() {
		
	}

	public Transaction(String userId, int customerId, Timestamp timestamp, String message) {
		super();
		this.transactionId = ++idCounter;
		this.userId = userId;
		this.customerId = customerId;
		this.timestamp = timestamp;
		this.message = message;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", userId=" + userId + ", customerId=" + customerId
				+ ", timestamp=" + timestamp + ", message=" + message + "]";
	}
	
}
