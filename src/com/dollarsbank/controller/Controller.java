package com.dollarsbank.controller;

import java.util.Scanner;

import com.dollarsbank.dao.AccountDao;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtil;

public class Controller {
	public static final Scanner scan = new Scanner(System.in);
	private static final AccountDao accountDao = new AccountDao();

	public Controller() {

	}

	public int initialOption() {
		int selectedOption = 0;

		System.out.println("DOLLARSBANK Welcomes You!" + "\n1. Create New Account" + "\n2. Login" + "\n3. Exit" + "\n\n"
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
		if (scan.hasNextLine())
			scan.nextLine();

		System.out.println("\nEnter Details For New Account\n");
/*
		String[] fullName = validateName();
		String firstName = fullName[0];
		String lastName = fullName[1];

		String phoneNum = validatePhoneNum();

		System.out.println("\nCustomer address:\n" + "Example: 123 Longs Pond Rd");
		String address = scan.nextLine();

		System.out.println("\nCustomer city:\n" + "Example: Los Angeles");
		String city = scan.nextLine();

		String state = validateState();

		String country = validateCountry();

		String userId = validateUserId();

		String password = validatePassword();

*/
		double amount = validateInitAmt();

		return false;
	}

	private String[] validateName() {
		System.out.println("Customer name (first and last name):\n" + "Example: John Smith");

		boolean invalid = true;
		String[] fullName = null;

		while (invalid) {
			System.out.print("> ");
			fullName = scan.nextLine().split("\\s+");

			if (fullName.length > 2) {
				System.out.println(ColorsUtil.ANSI_RED
						+ "Too many names. Please enter 2 names, separated by 1 space. Please try again."
						+ ColorsUtil.ANSI_RESET);
			} else if (fullName.length < 2) {
				System.out.println(ColorsUtil.ANSI_RED
						+ "Missing input. Please enter 2 names, separated by 1 space. Please try again."
						+ ColorsUtil.ANSI_RESET);
			} else
				invalid = false;
		}

		return fullName;
	}

	private String validatePhoneNum() {
		System.out.println("\nCustomer contact number:\n" + "Example: 5551234567");

		boolean invalid = true;
		String num = null;

		while (invalid) {
			System.out.print("> ");
			num = scan.nextLine();

			if (num.length() > 10 || num.length() < 10) {
				System.out.println(ColorsUtil.ANSI_RED + "Please enter a 10-digit phone number. Try again."
						+ ColorsUtil.ANSI_RESET);
			} else if (!num.matches("^[0-9]*$")) {
				System.out
						.println(ColorsUtil.ANSI_RED + "Please enter numbers only. Try again." + ColorsUtil.ANSI_RESET);
			} else
				invalid = false;
		}

		return num;
	}

	private String validateState() {
		System.out.println("\nCustomer state (N/A if not applicable):\n" + "Example: GA");

		boolean invalid = true;
		String state = null;

		while (invalid) {
			System.out.print("> ");
			state = scan.nextLine();

			if (!state.equals("N/A") && (state.length() > 2 || state.length() < 2)) {
				System.out.println(
						ColorsUtil.ANSI_RED + "Please enter a 2-letter state code. Try again." + ColorsUtil.ANSI_RESET);
			} else
				invalid = false;
		}

		return state.toUpperCase();
	}

	private String validateCountry() {
		System.out.println("\nCustomer country code (3 letters):\n" + "Example: CAN for Canada");

		boolean invalid = true;
		String country = null;

		while (invalid) {
			System.out.print("> ");
			country = scan.nextLine();

			if ((country.length() > 3 || country.length() < 3)) {
				System.out.println(ColorsUtil.ANSI_RED + "Please enter a 3-letter country code. Try again."
						+ ColorsUtil.ANSI_RESET);
			} else if (!country.matches("[a-zA-Z]+")) {
				System.out
						.println(ColorsUtil.ANSI_RED + "Please enter letters only. Try again." + ColorsUtil.ANSI_RESET);
			} else
				invalid = false;
		}

		return country.toUpperCase();
	}

	private String validateUserId() {
		System.out.println("\nUser Id:\n" + "Alphanumericals and underscores only; no spaces");

		boolean invalid = true;
		String userId = null;

		while (invalid) {
			System.out.print("> ");
			userId = scan.nextLine();

			if (accountDao.getAccountByUserId(userId) != null) {
				System.out.println(ColorsUtil.ANSI_RED + "This user id is already taken. Please try again."
						+ ColorsUtil.ANSI_RESET);
			} else if (!userId.matches("^[a-zA-Z0-9_]*$")) {
				System.out.println(ColorsUtil.ANSI_RED
						+ "User id can only contain alphanumerical characters and underscores. No spaces. Please try again."
						+ ColorsUtil.ANSI_RESET);
			} else
				invalid = false;
		}

		return userId.toLowerCase();
	}

	private String validatePassword() {
		System.out.println("\nPassword:\n" + "At least 8 characters long, no whitespaces");

		boolean invalid = true;
		String password = null;

		while (invalid) {
			System.out.print("> ");
			password = scan.nextLine();

			if (password.length() < 8) {
				System.out.println(ColorsUtil.ANSI_RED + "Password must be 8 or more characters long. Please try again."
						+ ColorsUtil.ANSI_RESET);
			} else if (password.matches(".*\\s.*")) {
				System.out.println(
						ColorsUtil.ANSI_RED + "Do not include whitespaces. Please try again." + ColorsUtil.ANSI_RESET);
			} else
				invalid = false;
		}

		return password;
	}

	private double validateInitAmt() {
		System.out.println("\nInitial Deposit Amount:\n");

		boolean invalid = true;
		double amount = 0.0;

		while (invalid) {
			System.out.print("> $");
			if (!scan.hasNextDouble()) {
				System.out.println(ColorsUtil.ANSI_RED + "Invalid input. Please try again." + ColorsUtil.ANSI_RESET);
			} else {
				amount = scan.nextDouble();
				if (amount < 0.0)
					System.out.println(ColorsUtil.ANSI_RED + "Please enter $0 or more." + ColorsUtil.ANSI_RESET);
				else
					invalid = false;
			}
			if (scan.hasNextLine())
				scan.nextLine();
		}

		return amount;
	}
}
