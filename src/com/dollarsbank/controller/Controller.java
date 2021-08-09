package com.dollarsbank.controller;

import java.util.Scanner;

import com.dollarsbank.model.Customer;

public class Controller {
	public static final Scanner scan = new Scanner(System.in);
	
	public Controller() {
		
	}
	
	public int initialOption() {
		int selectedOption = 0;
		
		System.out.println("DOLLARSBANK Welcomes You!"
				+ "\n1. Create New Account"
				+ "\n2. Login"
				+ "\n3. Exit"
				+ "\n\n"
				+ "Enter choice (1, 2, or 3): ");
		
		if (scan.hasNextInt()) {
			selectedOption = scan.nextInt();
			if (selectedOption < 1 || selectedOption > 3) {
				System.out.println("Please enter a value between 1-5.");
			} 
		} else {
			System.out.println("error: Non-integer input");
		}
		
		return selectedOption;
	}
	
	public boolean createNewAccount() {
		if (scan.hasNextLine()) scan.nextLine();
		
		System.out.println("\nEnter Details For New Account\n");
		System.out.println("Customer name (first and last name):\n"
				+ "Example: John Smith");
		
		String[] fullName = scan.nextLine().split("\\s+");
		String firstName = fullName[0];
		String lastName = fullName[1];
		//add validation to make sure only 2 names are entered
		
		System.out.println("\nCustomer contact number:\n"
				+ "Example: 5551234567");
		String phoneNum = scan.nextLine();
		
		System.out.println("\nCustomer address:\n"
				+ "Example: 123 Longs Pond Rd");
		String address = scan.nextLine();
		
		System.out.println("\nCustomer city:\n"
				+ "Example: Los Angeles");
		String city = scan.nextLine();
		
		System.out.println("\nCustomer state (N/A if not applicable):\n"
				+ "Example: GA");
		String state = scan.nextLine();
		
		System.out.println("\nCustomer country code (3 letters):\n"
				+ "Example: CAN for Canada");
		String country = scan.nextLine();
		
		System.out.println("\nUser Id:\n");
		String userId = scan.nextLine();
		
		System.out.println("\nUser Id:\n");
		String password = scan.nextLine();
		
		System.out.println("\nUser Id:\n");
		double amount = scan.nextDouble();
		
		return false;
	}


}
