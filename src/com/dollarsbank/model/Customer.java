package com.dollarsbank.model;

import com.dollarsbank.dao.CustomerDao;

public class Customer {
	private static CustomerDao custDao = new CustomerDao();
	private static int idCounter = custDao.getMaxCustomerId();
	//get top id from database
	
	private int customerId;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String address;
	private String city;
	private String state;
	private String country;
	
	public Customer() {
		this.customerId = ++idCounter;
	}
	
	public Customer(String firstName, String lastName, String phoneNum, String address, String city, String state,
			String country) {
		super();
		this.customerId = ++idCounter;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Name = " + firstName + " " + lastName 
				+ "\nPhone number = " + phoneNum
				+ "\nAddress = " + address 
				+ "\nCity = " + city 
				+ "\nState = " + state
				+ "\nCountry= " + country;
	}
}
