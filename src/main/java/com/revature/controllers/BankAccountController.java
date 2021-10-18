package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import com.revature.models.BankAccount;
import com.revature.models.BankUserDomicile;
import com.revature.services.BankAccountService;

public class BankAccountController {
	
	private BankAccountService bankAccountService = new BankAccountService();
	private Scanner scan = new Scanner(System.in);
	
	public void displayAllLedgers() {
		System.out.println("These are your Accounts:");
		List<BankAccount> list = bankAccountService.displayAllLedgers();
		for(BankAccount bankAccount:list) {
			//System.out.println(bankAccount.getName()); // working
			System.out.println(bankAccount.toString()); // working
			//System.out.println(bankAccount); // prints location in memory?
		}
	}
	
//	public void displayOneLedger(String name) {
//		System.out.println("Here is the Account with name "+name+":");
//		BankAccount bankAccount = bankAccountService.findByName(name);
//		System.out.println(bankAccount);
//	}

	public void displayOneLedger(Integer id) {
		System.out.println("Here is the Account with id "+id+":");
		BankAccount bankAccount = bankAccountService.findByUserId(id);
		System.out.println(bankAccount);
	}
	
	
	public void addLedger() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("        New Bank Account Application Form");
		System.out.println("==================================================");
		System.out.println("         Part Two: Account Configuration");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What is your account id?");
		Integer id = Integer.parseInt(scan.nextLine());
		System.out.println("What is your account balance?");
		Float balance = scan.nextFloat();
		System.out.println("What is your user id?");
		Integer userId = Integer.parseInt(scan.nextLine());
		
		//System.out.println("What is your email?"); // toDo: remove from table, object (retain in external Table)
		//String email = scan.nextLine();
		//System.out.println("What is your social security number? (digits only)"); // toDo: remove from table, object (retain in external Table)
		//Integer ss = scan.nextLine(); // error
		//Integer ss = Integer.parseInt(scan.nextLine());
		System.out.println("For Internal Use Only: is this account complete? (T/F)");
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
		System.out.println("For Internal Use Only: is this account approved? (T/F)");
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
		//BankAccount bankAccount = new BankAccount(name, number, stName, city, region, zip, country, done, approved);
		BankAccount bankAccount = new BankAccount(id, balance, userId); // int id, float balance, int userId
		
		if(bankAccountService.newLedger(bankAccount)) {
			System.out.println("Your Bank Account was successfully submitted");
		}else {
			System.out.println("Something went wrong. We could not register your Bank Account. Please try again.");
		}
	}

}
