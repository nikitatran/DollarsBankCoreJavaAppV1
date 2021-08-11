package com.dollarsbank.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ConsolePrinterUtil {
	
	public static final String NONINT_INPUT = ColorsUtil.ANSI_RED + "Error: Non-integer input" + ColorsUtil.ANSI_RESET;
	
	public static final String EXIT = ColorsUtil.ANSI_GREEN + "Exiting system." + ColorsUtil.ANSI_RESET;
	
	public static String enterChoice(int optAmt) {
		String prompt = "Enter Choice (";
		for(int i = 1; i <= optAmt; i++) {
			if(i != optAmt)
			prompt = prompt.concat(i + ", ");
			else prompt = prompt.concat("or " + i + "):");
		}
		
		prompt = ColorsUtil.ANSI_CYAN + prompt + ColorsUtil.ANSI_RESET;
		
		return prompt;
	}
	
	public static String invalidChoice(int optAmt) {
		return ColorsUtil.ANSI_RED + "Please enter a value between 1-" + optAmt + "." + ColorsUtil.ANSI_RESET;
	}
	
	public static String boxedText(String s) {
		String str = "";
		for(int i = 0; i < s.length()+2; i++) {
			str += "-";
		}
		
		str = "+" + str + "+";
		s = "| " + s + " |";
		
		return ColorsUtil.ANSI_BLUE + str + "\n" + s + "\n" + str + ColorsUtil.ANSI_RESET;
	}
	
	public static String transactionMsg(String beginning, double amount, String toFrom, String accountType, String accountName, double checkingAmt, double savingsAmt, Timestamp timestamp) {
		
		String formattedTimestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
		
		String msg = beginning + " $" + amount + " " + toFrom + " " + accountType + " in Account [" + accountName + "].\n"
				+ "Balance (Checking) - " + checkingAmt + ", Balance (Savings) - " + savingsAmt + " as on " + formattedTimestamp;
		
		return msg;
	}
}
