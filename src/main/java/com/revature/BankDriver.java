package com.revature;

//import com.revature.controllers.BankUserDomicileController;
//import com.revature.controllers.BankUserController;
import com.revature.controllers.BankMenuController;

public class BankDriver {

	//private static BankUserDomicileController bankUserDomicileController = new BankUserDomicileController();
	//private static BankUserController bankUserController = new BankUserController();
	private static BankMenuController bankMenuController = new BankMenuController();
	
	public static void main(String[] args) {
		//bankUserDomicileController.displayAllResidences();
		//bankUserController.showAllPeople();
		bankMenuController.welcomeMenu();
	}
}
