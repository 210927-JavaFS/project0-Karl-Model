package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

//import com.revature.models.BankUser;
import com.revature.models.BankUserDomicile;
//import com.revature.models.BankAccount;
//import com.revature.services.BankUserService;
import com.revature.services.BankUserDomicileService;
//import com.revature.services.BankUserService;
//import com.revature.services.BankUserDomicileService;
//import com.revature.services.BankAccountService;

public class BankUserDomicileController {
	
	private BankUserDomicileService bankUserDomicileService = new BankUserDomicileService();
	private Scanner scan = new Scanner(System.in);
	
	public void displayAllResidences() {
		System.out.println("These are your Residences:");
		List<BankUserDomicile> list = bankUserDomicileService.findAllResidences();
		for(BankUserDomicile bankUserDomicile:list) {
			//System.out.println(bankUserDomicile.getName()); // working
			System.out.println(bankUserDomicile.toString()); // working
			//System.out.println(bankUserDomicile); // prints location in memory?
		}
	}

	public void displayOneResidence(String name) {
		System.out.println("Here is the Residence named: "+name+":");
		BankUserDomicile bankUserDomicile = bankUserDomicileService.findByName(name);
		System.out.println(bankUserDomicile);
	}
	
	
	public void addResidence() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("        New Bank Account Application Form");
		System.out.println("==================================================");
		System.out.println("         Part One: Primary Residence");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What is your apartment or townhouse name (leave blank if not applicable)");
		String name = scan.nextLine();
		System.out.println("What is your residential street number?");
		String number = scan.nextLine();
		System.out.println("What is your residential street name?");
		String stName = scan.nextLine();
		System.out.println("What is your city of residence?");
		String city = scan.nextLine();
		System.out.println("What state within your country do you live in?");
		String region = scan.nextLine();
		System.out.println("What is your residential zip code?");
		String zip = scan.nextLine();
		System.out.println("What is your country of residence?");
		String country = scan.nextLine();
		
		//System.out.println("What is your email?"); // toDo: remove from table, object (retain in external Table)
		//String email = scan.nextLine();
		//System.out.println("What is your social security number? (digits only)"); // toDo: remove from table, object (retain in external Table)
		//Integer ss = scan.nextLine(); // error
		//Integer ss = Integer.parseInt(scan.nextLine());
		System.out.println("For Internal Use Only: is this address complete? (T/F)");
		//Boolean done = scan.nextLine(); // error
		String userInput = scan.nextLine();
		Boolean done = false;
		if (userInput.equals("T")){            
			done = true;
		}
		else if (userInput.equals("F")){            
			done = false;
		}
		else{
			System.out.println("unrecognized value set to False");
			done = false;
		}
		System.out.println("For Internal Use Only: is this address approved? (T/F)");
		//Boolean approved = scan.nextLine(); // error
		userInput = scan.nextLine();
		Boolean approved = false;
		if (userInput.equals("T")){            
			approved = true;
		}
		else if (userInput.equals("F")){            
			approved = false;
		}
		else{
			System.out.println("unrecognized value set to False");
			approved = false;
		}
		BankUserDomicile bankUserDomicile = new BankUserDomicile(name, number, stName, city, region, zip, country, done, approved);
		
		if(bankUserDomicileService.newResidence(bankUserDomicile)) {
			System.out.println("Your Primary Residence was successfully submitted");
		}else {
			System.out.println("Something went wrong. We could not register your Primary Residence. Please try again.");
		}
	}
	

}