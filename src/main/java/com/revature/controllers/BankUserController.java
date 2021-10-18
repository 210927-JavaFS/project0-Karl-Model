package com.revature.controllers;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.revature.models.BankUser;
import com.revature.models.BankUserDomicile;
import com.revature.models.BankAccount;
import com.revature.services.BankUserService;
import com.revature.services.BankUserDomicileService;
import com.revature.services.BankAccountService;
import com.revature.utils.IcePwd;

public class BankUserController {

	private BankUserService bankUserService = new BankUserService();
	private BankUserDomicileService bankUserDomicileService = new BankUserDomicileService();
	private BankAccountService bankAccountService = new BankAccountService();
	
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
	

	/*
	public void addAccount() {
		float accountBalance = 0.00;
		System.out.println("Enter information for new account");
		System.out.print("Enter your username: ");
		String username1 = scan.nextLine();
		String accountType = getAuthority(username1);
		if (accountType.equals("ADMINISTRATOR") || (accountType.equals("EMPLOYEE") || accountType.equals("ADMIN"))) {
			System.out.print("Enter your username for account to add: ");
			String username2 = scan.nextLine();
			int randomNumber = 100000000 + new Random().nextInt(90000000);
			String accountNumber = String.valueOf(randomNumber);
			BankAccount bankAccount = new BankAccount(accountNumber, accountBalance, username2);

			if (bankAccountService.newLedger(bankAccount)) {
				System.out.println("New user successfully created");
			} else {
				System.out.println("Something went wrong. We could not register user. Please contact an admin or employee.");
			}
		}
	}
	*/
	
	/*
	public void addAccount() {
		float accountBalance = (float)0.00;
		System.out.println("Enter information for new account");
		System.out.print("Enter your username: ");
		String username1 = scan.nextLine();
		String accountType = getAuthority(username1);
		if (accountType.equals("ADMINISTRATOR") || (accountType.equals("EMPLOYEE") || accountType.equals("ADMIN"))) {
			System.out.print("Enter a user_id number for the new account: ");
			Integer uid = Integer.parseInt(scan.nextLine());
			int randomNumber = 100000000 + new Random().nextInt(90000000);
			Integer accountNumber = randomNumber;
			BankAccount bankAccount = new BankAccount(accountNumber, accountBalance, uid);

			if (bankAccountService.newLedger(bankAccount)) {
				System.out.println("New user successfully created");
			} else {
				System.out.println("Something went wrong. We could not register user. Please contact an admin or employee.");
			}
		}
	}
	*/
	
	public String getAuthority(String username) {
		//BankUser bankUser = BankUserService.uRole(username);
		//return bankUser.getRole();
		return "CUSTOMER"; // temporary hack
	} 

	
	public String getPassphrase(String password) {
		//BankUser bankUser = BankUserService.uPassword(password);
		//return bankUser.getPwd();
		return "password"; // temporary hack		
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
		// start: basic encryption
		String encPassword = IcePwd.passwordEncryption(pwd);
		// end: basic encryption
		// start: toDo- stronger encryption
		String salt = "salt";
		// end: toDo- stronger encryption
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
		BankUser bankUser = new BankUser(email, userName, pwd, salt, firstName, lastName, role, done, approved, null, null); // terminal parameter is a null placeholder, intended to represent a Foreign Key in related Tables
		
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
