package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import com.revature.models.BankUser;
//import com.revature.models.BankUserDomicile;
//import com.revature.models.BankAccount;
import com.revature.services.BankUserService;
//import com.revature.services.BankUserService;
//import com.revature.services.BankUserDomicileService;
//import com.revature.services.BankAccountService;

public class BankUserController {

	private BankUserService bankUserService = new BankUserService();
	private Scanner scan = new Scanner(System.in);
	
	public void displayAllCustomers() {
		System.out.println("Here are all the Customers:");
		List<BankUser> list = bankUserService.findAllPersons();
		for(BankUser u:list) {
			//ToDo: exclude Non-Customers
			System.out.println(u);
		}
	}
	
	public void displayOneCustomer(String name) {
		System.out.println("Here is the Customer whose Last Name is "+name+":");
		BankUser bankUser = bankUserService.findByName(name);
		//ToDo: exclude Non-Customers
		System.out.println(bankUser);
	}

	public void addPerson() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("        New Customer Registration Form");
		System.out.println("==================================================");
		System.out.println("         Part One: Personal Information");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What is your email?");
		String email = scan.nextLine();
		System.out.println("What is your username?");
		String userName = scan.nextLine();		
		System.out.println("What is your password?");
		String pwd = scan.nextLine();
		System.out.println("What is your first name?");
		String firstName = scan.nextLine();
		System.out.println("What is your last name?");
		String lastName = scan.nextLine();
		System.out.println("For Internal Use Only: What is your role? (CUSTOMER, EMPLOYEE, ADMIN)");
		String role = scan.nextLine();
		//System.out.println("For Internal Use Only: What is your social security number? (digits only)"); // toDo: exclude from table, object (add to external Table during "new bank account application" procedure)
		//Integer ss = scan.nextLine(); // error
		//Integer ss = Integer.parseInt(scan.nextLine());
		System.out.println("For Internal Use Only: is this identity profile complete? (T/F)");
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
		System.out.println("For Internal Use Only: is this identity profile approved? (T/F)");
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
		BankUser bankUser = new BankUser(email, userName, pwd, firstName, lastName, role, done, approved, null, null); // terminal parameter is a null placeholder, intended to represent a Foreign Key in related Tables
		
		if(bankUserService.newPerson(bankUser)) {
			System.out.println("Your Personal Information was successfully submitted");
		}else {
			System.out.println("Something went wrong. We could not register your Personal Information. Please try again.");
		}
	}
	
	/*
	 * public void showAllPeople() { System.out.println("Here are all the Users:");
	 * List<BankUser> list = bankUserService.findAllPersons(); for(BankUser u:list)
	 * { System.out.println(u); } }
	 * 
	 * public void displayOnePerson(String name) {
	 * System.out.println("Here is the Member whose Last Name is "+name+":");
	 * BankUser bankUser = bankUserService.findByName(name);
	 * System.out.println(bankUser); }
	 */	
	
}
