package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

//import com.revature.controllers.BankUserDomicileController;
//import com.revature.controllers.BankUserController;
//import com.revature.controllers.BankAccountController;
import com.revature.controllers.BankMenuController;

public class BankDriver {

	private static Logger log = LoggerFactory.getLogger(BankDriver.class);	
	
	//private static BankUserDomicileController bankUserDomicileController = new BankUserDomicileController();
	//private static BankUserController bankUserController = new BankUserController();
	private static BankMenuController bankMenuController = new BankMenuController();
	
	public static void main(String[] args) {
		
		log.debug("application has started");
		
		MDC.put("anonymous", "1");
		
		log.info("main method starting");
		log.error("placeholder for error logging");
		log.warn("transition to control layer");
		
		//bankUserDomicileController.displayAllResidences();
		//bankUserController.howAllPeople();
		bankMenuController.welcomeMenu();
		bankMenuController.employeeMenu(); // enabled temporarily for presentation
		
		
		log.debug("application is ending");
		
		log.info("main method completed");
		log.error("placeholder for error logging");		
		log.warn("program ending");

		
	}
}
