package com.dollarsbank.application;

import com.dollarsbank.controller.Controller;
import com.dollarsbank.utility.ConsolePrinterUtil;

public class DollarsBankApplication {

	private static final Controller control = new Controller();

	public static void main(String[] args) {
		boolean running = true;
		while (running) {
			switch (control.initialOption()) {
			case 1: {
				control.createNewAccount();
				break;
			}
			case 2:
				control.onLoginSuccess(control.login());
				break;
			case 3:
				System.out.println(ConsolePrinterUtil.EXIT);
				running = false;
				break;
			default:
				System.out.println(ConsolePrinterUtil.invalidChoice(3));
				break;
			}
		}

	}

}
