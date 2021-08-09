package com.dollarsbank.model;

public class Customer {
	private int customerId = -1;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String city;
	private String state;
	private String country;
	
	public Customer() {
		
	}
	
	public Customer(String firstName, String lastName, String phoneNum, String city, String state,
			String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.city = city;
		this.state = state;
		this.country = country;
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
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNum=" + phoneNum + ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}
	
}
