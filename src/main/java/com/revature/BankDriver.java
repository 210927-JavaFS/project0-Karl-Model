package com.revature;

import com.revature.controllers.BankUserDomicileController;
//import com.revature.controllers.BankMenuController;

public class BankDriver {

	private static BankUserDomicileController bankUserDomicileController = new BankUserDomicileController();
	//private static BankMenuController bankMenuController = new BankMenuController();
	
	public static void main(String[] args) {
		bankUserDomicileController.displayAllResidences();
		//bankMenuController.welcomeMenu();
	}
}
