package com.revature.controllers;

import java.util.Scanner;
import com.revature.utils.UserInputValidation;

public class BankMenuController {
	
	private static Scanner scan = new Scanner(System.in);
	private static BankUserDomicileController bankUserDomicileController = new BankUserDomicileController();
	private static BankUserController bankUserController = new BankUserController();

	//UserInputValidation userInputValidation = new UserInputValidation();
	
	public void bankAtmMenu() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("            Banking ATM Services Menu");
		System.out.println("==================================================");
		System.out.println("          Create Financial Transactions");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What would you like to do? \n"
				+"1) Deposit Funds \n"
				+"2) Withdraw Funds \n"
				+"3) Transfer Funds \n"
				+"4) View Account Balance \n"
				+"5) Exit the application");
		String response = scan.nextLine();
		
		while(!response.equals("5")) {
			switch (response) {
				case "1":
					// TODO call menu
					//depositMenu();
					System.out.println("What would you like to do? \n"
							+"1) Deposit Funds \n"
							+"2) Withdraw Funds \n"
							+"3) Transfer Funds \n"
							+"4) View Account Balance \n"
							+"5) Exit the application");
					response = scan.nextLine();
					break;
				case "2":
					//withdrawMenu();
					System.out.println("What would you like to do? \n"
							+"1) Deposit Funds \n"
							+"2) Withdraw Funds \n"
							+"3) Transfer Funds \n"
							+"4) View Account Balance \n"
							+"5) Exit the application");
					response = scan.nextLine();
				case "3":
					// TODO call menu
					//transferMenu();
					System.out.println("What would you like to do? \n"
							+"1) Deposit Funds \n"
							+"2) Withdraw Funds \n"
							+"3) Transfer Funds \n"
							+"4) View Account Balance \n"
							+"5) Exit the application");
					response = scan.nextLine();
					break;
				case "4":
					//viewBalanceMenu();
					System.out.println("What would you like to do? \n"
							+"1) Deposit Funds \n"
							+"2) Withdraw Funds \n"
							+"3) Transfer Funds \n"
							+"4) View Account Balance \n"
							+"5) Exit the application");
					response = scan.nextLine();					
				case "5":
					break;
				default:
					System.out.println("That was not a valid input. Please try again.");
					System.out.println("What would you like to do? \n"
							+"1) Deposit Funds \n"
							+"2) Withdraw Funds \n"
							+"3) Transfer Funds \n"
							+"4) View Account Balance \n"
							+"5) Exit the application");					
					response = scan.nextLine();
					break;
			}
		}	
	}
	
	public void welcomeMenu() {
		//System.out.println("Welcome to the FirstBankOfJava!");
		System.out.println("==================================================");
		System.out.println("                FIRST BANK OF JAVA");
		System.out.println("==================================================");
		System.out.println("                  Welcomes You");
		System.out.println("==================================================");
		System.out.println("What would you like to do? \n"
				+"1) Sign In \n"
				+"2) Register \n"
				+"3) Exit the application");
		String response = scan.nextLine();
		
		while(!response.equals("3")) {
			switch (response) {
				case "1":
					// TODO call menu
					//internalMenu();
					signIn();
					System.out.println("What would you like to do? \n"
							+"1) Sign In \n"
							+"2) Register \n"
							+"3) Exit the application");
					response = scan.nextLine();
					break;
				case "2":
					//internalMenu();
					bankUserController.addPerson();
					System.out.println("What would you like to do? \n"
							+"1) Sign In \n"
							+"2) Register \n"
							+"3) Exit the application");
					response = scan.nextLine();			
			}
		}
		
	}

	public void signIn() {
		//System.out.println("What is your email?");
		//String email = scan.nextLine();
		
		System.out.println("What is your username?");
		//userInputValidation.getValidUsername();
		String userName = scan.nextLine();
		
		System.out.println("What is your password?");
		String pwd = scan.nextLine();
		
		//ToDo: if (BankUser.role.equals("CUSTOMER")) {
		if (true){            
			//bankAtmMenu();
			customerMenu();
		}
		else{
			employeeMenu();
		}		
	}
	
	public void customerMenu() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("                 Customer Menu");
		System.out.println("==================================================");
		System.out.println("               Available Services");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What would you like to do? \n"
				+"1) Use My Existing Account \n"
				+"2) Apply for a New Account \n"
				+"3) Exit the application");
		String response = scan.nextLine();
		
		while(!response.equals("3")) {
			switch (response) {
				case "1":
					// TODO call menu
					//internalMenu();
					//signIn();
					bankAtmMenu();
					//useMyExistingAcctMenu();
					System.out.println("What would you like to do? \n"
							+"1) Use My Existing Account \n"
							+"2) Apply for a New Account \n"
							+"3) Exit the application");
					response = scan.nextLine();
					break;
				case "2":
					//internalMenu();
					bankUserDomicileController.addResidence();
					//applyForNewAcctMenu();
					System.out.println("What would you like to do? \n"
							+"1) Use My Existing Account \n"
							+"2) Apply for a New Account \n"
							+"3) Exit the application");
					response = scan.nextLine();			
			}
		}
		
	}	
	
	public void employeeMenu() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("        Administrative and Employee Menu");
		System.out.println("==================================================");
		System.out.println("         View/Edit Customer Information");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What would you like to do? \n"
				+"1) View/Edit Customer Identity Information \n"
				+"2) View/Edit Customer Residential Informmation \n"
				+"3) Exit the application");
		String response = scan.nextLine();
		
		while(!response.equals("3")) {
			switch (response) {
				case "1":
					// TODO call menu
					identitiesMenu();
					System.out.println("What would you like to do? \n"
							+"1) View/Edit Customer Identity Information \n"
							+"2) View/Edit Customer Residential Informmation \n"
							+"3) Exit the application");
					response = scan.nextLine();
					break;
				case "2":
					residencesMenu();
					System.out.println("What would you like to do? \n"
							+"1) View/Edit Customer Identity Information \n"
							+"2) View/Edit Customer Residential Informmation \n"
							+"3) Exit the application");
					response = scan.nextLine();			
			}
		}
		
	}

	private void identitiesMenu() {
		System.out.println("View/Edit Customer Identity Information: \n"
				+ "1) Show all Customers \n"
				+ "2) Show one Customer \n"
				+ "3) Add a Customer to the database \n"
				+ "4) Return to previous menu.");
		String response = scan.nextLine();
		
		while(!response.equals("4")) {
			switch (response) {
				case "1":
					bankUserController.displayAllCustomers();
					System.out.println("View/Edit Customer Identity Information: \n"
							+ "1) Show all Customers \n"
							+ "2) Show one Customer \n"
							+ "3) Add a Customer to the database \n"
							+ "4) Return to previous menu.");
					response = scan.nextLine();
					break;
				case "2":
					System.out.println("What is the Customer's last name?");
					String name = scan.nextLine();
					bankUserController.displayOneCustomer(name);
					
					System.out.println("View/Edit Customer Identity Information: \n"
							+ "1) Show all Customers \n"
							+ "2) Show one Customer \n"
							+ "3) Add a Customer to the database \n"
							+ "4) Return to previous menu.");
					response = scan.nextLine();
				case "3":
					bankUserController.addPerson();
					System.out.println("View/Edit Customer Residential Information: \n"
							+ "1) Show all Customers \n"
							+ "2) Show one Customer \n"
							+ "3) Add a Customer to the database \n"
							+ "4) Return to previous menu.");				
					response = scan.nextLine();
					break;
				case "4":
					break;					
				default:
					System.out.println("That was not a valid input. Please try again.");
					System.out.println("View/Edit Customer Identity Information: \n"
							+ "1) Show all Customers \n"
							+ "2) Show one Customer \n"
							+ "3) Add a Customer to the database \n"
							+ "4) Return to previous menu.");;					
					response = scan.nextLine();
					break;
			}
		}
	}

	private void residencesMenu() {
		System.out.println("View/Edit Customer Residential Information: \n"
				+ "1) Show all Residences \n"
				+ "2) Show one Residence \n"
				+ "3) Add a Residence to the database \n"
				+ "4) Return to previous menu.");
		String response = scan.nextLine();
		
		while(!response.equals("4")) {
			switch (response){
				case "1":
					bankUserDomicileController.displayAllResidences();
					System.out.println("View/Edit Customer Residential Information: \n"
							+ "1) Show all Residences \n"
							+ "2) Show one Residence \n"
							+ "3) Add a Residence to the database \n"
							+ "4) Return to previous menu.");					
					response = scan.nextLine();
					break;
				case "2":
					System.out.println("What is the Residence name?");
					String name = scan.nextLine();
					bankUserDomicileController.displayOneResidence(name);
					
					System.out.println("View/Edit Customer Residential Information: \n"
							+ "1) Show all Residences \n"
							+ "2) Show one Residence \n"
							+ "3) Add a Residence to the database \n"
							+ "4) Return to previous menu.");					
					response = scan.nextLine();
					break;
				case "3":
					bankUserDomicileController.addResidence();
					System.out.println("View/Edit Customer Residential Information: \n"
							+ "1) Show all Residences \n"
							+ "2) Show one Residence \n"
							+ "3) Add a Residence to the database \n"
							+ "4) Return to previous menu.");					
					response = scan.nextLine();
					break;
				case "4":
					break;
				default:
					System.out.println("That was not a valid input. Please try again.");
					System.out.println("View/Edit Customer Residential Information: \n"
							+ "1) Show all Residences \n"
							+ "2) Show one Residence \n"
							+ "3) Add a Residence to the database. \n"
							+ "4) Return to previous menu.");					
					response = scan.nextLine();
					break;
					
			}
		}
	}
	
}
