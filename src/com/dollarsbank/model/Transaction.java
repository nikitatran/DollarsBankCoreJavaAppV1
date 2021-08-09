package com.dollarsbank.model;

import java.sql.Date;

public class Transaction {
	private int transactionId;
	private int userId;
	private int customerId;
	private Date timestamp;
	private String message;
	
	public Transaction() {
		
	}

	public Transaction(int transactionId, int userId, int customerId, Date timestamp, String message) {
		super();
		this.transactionId = transactionId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
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
