package com.dollarsbank.application;

import com.dollarsbank.dao.CustomerDao;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtil;

public class DollarsBankApplication {

	private static final CustomerDao CUSTOMER_DAO = new CustomerDao();
	
	public static void main(String[] args) {
		//System.out.println(ColorsUtil.ANSI_RED + "This text is red!" + ColorsUtil.ANSI_RESET);
		Customer testCust = new Customer("testFirst", "testLast", "5551234567", "cityname", "WA", "USA");
		if(CUSTOMER_DAO.createCustomer(testCust)) {
			System.out.println("Created customer");
		}
		else System.out.println("customer creation failed :(");
	}

}
