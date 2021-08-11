package com.dollarsbank.application;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dollarsbank.controller.Controller;
import com.dollarsbank.dao.CustomerDao;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtil;

public class DollarsBankApplication {

	private static final CustomerDao CUSTOMER_DAO = new CustomerDao();
	private static final Controller control = new Controller();

	public static void main(String[] args) {
		// System.out.println(ColorsUtil.ANSI_RED + "This text is red!" +
		// ColorsUtil.ANSI_RESET);

		// testing creation of customer
		/*
		 * Customer testCust = new Customer("testFirst", "testLast", "5551234567",
		 * "cityname", "WA", "USA"); if(CUSTOMER_DAO.createCustomer(testCust)) {
		 * System.out.println("Created customer"); } else
		 * System.out.println("customer creation failed :(");
		 */
		boolean running = true;
		while (running) {
			switch (control.initialOption()) {
			case 1: {
				control.createNewAccount();
				break;
			}
			case 2:

				break;
			case 3:
				System.out.println("Exiting system.");
				running = false;
				break;
			default:
				System.out.println("No valid option was selected.");
				break;
			}
		}

	}

}
