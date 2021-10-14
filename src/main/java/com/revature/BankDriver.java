package com.revature;

import com.revature.controllers.BankUserAddressController;
//import com.revature.controllers.BankMenuController;

public class BankDriver {

	private static BankUserAddressController bankUserAddressController = new BankUserAddressController();
	//private static BankMenuController bankMenuController = new BankMenuController();
	
	public static void main(String[] args) {
		bankUserAddressController.displayAllResidences();
		//bankMenuController.welcomeMenu();
	}
}
