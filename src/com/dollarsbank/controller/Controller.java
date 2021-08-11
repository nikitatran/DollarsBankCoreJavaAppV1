package com.dollarsbank.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.dollarsbank.dao.AccountDao;
import com.dollarsbank.dao.CustomerDao;
import com.dollarsbank.dao.TransactionDao;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Transaction;
import com.dollarsbank.utility.ColorsUtil;

public class Controller {
	public static final Scanner scan = new Scanner(System.in);
	private static final AccountDao accountDao = new AccountDao();
	private static final CustomerDao customerDao = new CustomerDao();
	private static final TransactionDao transactionDao = new TransactionDao();

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
		if (scan.hasNextLine())
			scan.nextLine();
		return selectedOption;
	}

	public boolean createNewAccount() {

		System.out.println("\nEnter Details For New Account\n");

		String[] fullName = validateName();
		String firstName = fullName[0];
		String lastName = fullName[1];

		String phoneNum = validatePhoneNum();
		System.out.println("phone num: " + phoneNum);

		System.out.println("\nCustomer address:\n" + "Example: 123 Longs Pond Rd");
		String address = scan.nextLine();

		System.out.println("\nCustomer city:\n" + "Example: Los Angeles");
		String city = scan.nextLine();

		String state = validateState();

		String country = validateCountry();

		String userId = validateUserId();

		String password = validatePassword();

		double amount = validateInitAmt();

		Customer newCust = new Customer(firstName, lastName, phoneNum, address, city, state, country);
		System.out.println("asdhfjkads: " + newCust.getPhoneNum());
		Account newAcc = new Account(userId, password, amount, 0.0, newCust.getCustomerId());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String formattedTimestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		String initDepositTransMsg = "Initial Deposit in Account [" + newAcc.getUserId() + "].\n" + "Balance (Checking) - "
				+ amount + ", Balance (savings) - 0.0 as on " + formattedTimestamp;

		Transaction newTransaction = new Transaction(newAcc.getUserId(), newCust.getCustomerId(), timestamp,
				initDepositTransMsg);

		// add to database
		customerDao.createCustomer(newCust);
		accountDao.createAccount(newAcc);
		transactionDao.createTransaction(newTransaction);

		return false;
	}
	

	public Account login() {
		System.out.println("\nEnter Login Details\n");
		System.out.println("User id: ");

		String userId = scan.nextLine();

		System.out.println("Password: ");

		String password = scan.nextLine();

		Account acc = accountDao.getAccountByUserId(userId);

		if (acc == null || !acc.getPassword().equals(password)) {
			System.out.println(ColorsUtil.ANSI_RED + "Invalid Credentials. Please try again." + ColorsUtil.ANSI_RESET);
			return null;
		} else {
			System.out.println(ColorsUtil.ANSI_GREEN + "Successfully logged in." + ColorsUtil.ANSI_RESET);
		}

		return acc;
	}

	public void onLoginSuccess(Account acc) {
		if (acc != null) {
			boolean loggedIn = true;
			while (loggedIn) {

				System.out.println("\nWELCOME Customer!!!\n" + "1. Deposit Amount\n" + "2. Withdraw Amount\n"
						+ "3. Funds Transfer\n" + "4. View 5 Recent Transactions\n"
						+ "5. Display Customer Information\n" + "6. Sign Out\n\n"
						+ "CHECKING: $" + acc.getCheckingAmount()
						+ "\nSAVINGS: $" + acc.getSavingsAmount()
						+ "\n\nEnter choice (1, 2, 3, 4, 5, or 6):");

				int selectedOption = 0;

				if (scan.hasNextInt()) {
					selectedOption = scan.nextInt();
					if (selectedOption < 1 || selectedOption > 6) {
						System.out.println("Please enter a value between 1-6.");
					}
				} else {
					System.out.println("Error: Non-integer input");
				}

				if (scan.hasNextLine())
					scan.nextLine();

				switch (selectedOption) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					transferFunds(acc);
					acc.setCheckingAmount(accountDao.getCheckingAmtByCustId(acc.getCustomerId()));
					acc.setSavingsAmount(accountDao.getSavingsAmtByCustId(acc.getCustomerId()));
					break;
				case 4:
					viewTransactions(acc);
					break;
				case 5:
					displayCustomerInfo(acc);
					break;
				case 6:
					loggedIn = false;
					System.out.println("Logging out...");
					break;
				default:
					System.out.println("No valid option was selected.");
					break;
				}
			}
		}
	}

	private void displayCustomerInfo(Account acc) {
		System.out.println("\nYour Information:");

		Customer cust = customerDao.getCustomerByCustomerId(acc.getCustomerId());
		System.out.println(cust.toString());
	}

	private void viewTransactions(Account acc) {
		System.out.println("\n5 Recent transactions\n");

		List<Transaction> trans = transactionDao.getUser5RecentTransactions(acc.getCustomerId());

		for (Transaction t : trans) {
			System.out.println(t.getMessage() + "\n");
		}
	}

	private void transferFunds(Account acc) {
		boolean loop = true;
		while (loop) {
			System.out.println("\nTransfer Funds\n\n" + "What would you like to do?\n" + "1. Checking to Savings\n"
					+ "2. Savings to Checking\n" + "3. Go back\n\n" 
					+ "Your checking account currently has $" + acc.getCheckingAmount()
					+ "\nYour savings account currently has $" + acc.getSavingsAmount()
					+ "\n\nEnter option (1, 2, or 3):");

			int selectedOption = 0;

			if (scan.hasNextInt()) {
				selectedOption = scan.nextInt();
				if (selectedOption < 1 || selectedOption > 3) {
					System.out.println("Please enter a value between 1-3.");
				}
			} else {
				System.out.println("Error: Non-integer input");
			}

			if (scan.hasNextLine())
				scan.nextLine();
			
			switch (selectedOption) {
			case 1:
				transferCheckingtoSavings(acc);
				acc.setCheckingAmount(accountDao.getCheckingAmtByCustId(acc.getCustomerId()));
				acc.setSavingsAmount(accountDao.getSavingsAmtByCustId(acc.getCustomerId()));
				break;
			case 2:
				break;
			case 3:
				loop = false;
				break;
			default:
				System.out.println("No valid option was selected.");
				break;
			}
		}
	}
	
	private void transferCheckingtoSavings(Account acc) {
		System.out.println("\nTransferring Checking to Savings\n"
				+ "How much to do you want to transfer?\n"
				+ "Your checking account currently has $" + acc.getCheckingAmount() + "\n"
				+ "Your savings account currently has $" + acc.getSavingsAmount() + "\n");
		
		boolean invalid = true;
		double amount = 0.0;
		
		while (invalid) {
			System.out.print("> $");
			if (!scan.hasNextDouble()) {
				System.out.println(ColorsUtil.ANSI_RED + "Invalid input. Please try again." + ColorsUtil.ANSI_RESET);
			} 
			else {
				amount = scan.nextDouble();
				if (amount < 0.0)
					System.out.println(ColorsUtil.ANSI_RED + "Please enter $0 or more." + ColorsUtil.ANSI_RESET);
				else if(amount > acc.getCheckingAmount())
					System.out.println(ColorsUtil.ANSI_RED + "You cannot enter more than you have in your checking account." + ColorsUtil.ANSI_RESET);
				else
					invalid = false;
			}
			
			if (scan.hasNextLine())
				scan.nextLine();
		}
		
		double newSavingsAmt = acc.getSavingsAmount() + amount;
		double newCheckingAmt = acc.getCheckingAmount() - amount;
		
		//update transaction table	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String formattedTimestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		String msg = "Transfered " + amount + " from Checking to Savings in Account [" + acc.getUserId() + "].\n" + "Balance (Checking) - "
				+ newCheckingAmt + ", Balance (savings) - " + newSavingsAmt + " as on " + formattedTimestamp;

		Transaction newTransaction = new Transaction(acc.getUserId(), acc.getCustomerId(), timestamp,
				msg);
		
		accountDao.updateSavings(newSavingsAmt, acc.getCustomerId());
		accountDao.updateChecking(newCheckingAmt, acc.getCustomerId());
		transactionDao.createTransaction(newTransaction);
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
			} else if (!state.matches("[a-zA-Z]+")) {
				System.out
						.println(ColorsUtil.ANSI_RED + "Please enter letters only. Try again." + ColorsUtil.ANSI_RESET);
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
