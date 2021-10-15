package com.revature.controllers;

import java.util.Scanner;

public class BankMenuController {
	
	private static Scanner scan = new Scanner(System.in);
	private static BankUserDomicileController bankUserDomicileController = new BankUserDomicileController();
	private static BankUserController bankUserController = new BankUserController();


	public void welcomeMenu() {
		//System.out.println("Welcome to the FirstBankOfJava!");
		System.out.println("==================================================");
		System.out.println("                FIRST BANK OF JAVA");
		System.out.println("==================================================");
		System.out.println("                  Welcomes You");
		System.out.println("==================================================");
		System.out.println("What would you like to do? \n"
				+"1) Sign Up \n"
				+"2) Register \n"
				+"3) Exit the application");
		String response = scan.nextLine();
		
		while(!response.equals("3")) {
			switch (response) {
				case "1":
					// TODO call menu
					membersMenu();
					System.out.println("What would you like to do? \n"
							+"1) See Members Options \n"
							+"2) See Residential Options \n"
							+"3) Exit the application");
					response = scan.nextLine();
					break;
				case "2":
					domicilesMenu();
					System.out.println("What would you like to do? \n"
							+"1) See Members Options \n"
							+"2) See Residential Options \n"
							+"3) Exit the application");
					response = scan.nextLine();			
			}
		}
		
	}
	
	public void internalMenu() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("        Administrative and Employee Menu");
		System.out.println("==================================================");
		System.out.println("         Part One: Primary Residence");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("What would you like to do? \n"
				+"1) View Customer Identity Information \n"
				+"2) View Customer Residential Informmation \n"
				+"3) Exit the application");
		String response = scan.nextLine();
		
		while(!response.equals("3")) {
			switch (response) {
				case "1":
					// TODO call menu
					membersMenu();
					System.out.println("What would you like to do? \n"
							+"1) View Customer Identity Information \n"
							+"2) View Customer Residential Informmation \n"
							+"3) Exit the application");
					response = scan.nextLine();
					break;
				case "2":
					domicilesMenu();
					System.out.println("What would you like to do? \n"
							+"1) View Customer Identity Information \n"
							+"2) View Customer Residential Informmation \n"
							+"3) Exit the application");
					response = scan.nextLine();			
			}
		}
		
	}

	private void membersMenu() {
		System.out.println("Ways to View Customer Identity Information: \n"
				+ "1) Show all Members \n"
				+ "2) Show all Customers \n"
				+ "3) Show one Customer \n"
				+ "4) Return to previous menu.");
		String response = scan.nextLine();
		
		while(!response.equals("4")) {
			switch (response) {
				case "1":
					bankUserController.showAllPeople();
					System.out.println("Ways to View Customer Identity Information: \n"
							+ "1) Show all Members \n"
							+ "2) Show all Customers \n"
							+ "3) Show one Customer  \n"
							+ "4) Return to previous menu.");
					response = scan.nextLine();
					break;
				case "2":
					bankUserController.showAllCustomers();
					System.out.println("Ways to View Customer Identity Information: \n"
							+ "1) Show all Members  \n"
							+ "2) Show all Customers \n"
							+ "3) Show one Customer \n"
							+ "4) Return to previous menu.");
					response = scan.nextLine();
					break;
				case "3":
					System.out.println("What is the Customer's last name?");
					String name = scan.nextLine();
					bankUserController.displayOneCustomer(name);
					
					System.out.println("Ways to View Customer Identity Information: \n"
							+ "1) Show all Members \n"
							+ "2) Show all Customers \n"
							+ "3) Show one Customer \n"
							+ "4) Return to previous menu.");
					response = scan.nextLine();
				case "4":
					break;					
				default:
					System.out.println("Type 4 to exit.");
					break;
			}
		}
	}

	private void domicilesMenu() {
		System.out.println("Ways to View or Edit Customer Residential Information: \n"
				+ "1) Show all Residences \n"
				+ "2) Show one Residence \n"
				+ "3) Add a Residence to the database \n"
				+ "4) Return to previous menu.");
		String response = scan.nextLine();
		
		while(!response.equals("4")) {
			switch (response){
				case "1":
					bankUserDomicileController.displayAllResidences();
					System.out.println("Ways to View or Edit Customer Residential Information: \n"
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
					
					System.out.println("Ways to View or Edit Customer Residential Information: \n"
							+ "1) Show all Residences \n"
							+ "2) Show one Residence \n"
							+ "3) Add a Residence to the database \n"
							+ "4) Return to previous menu.");					
					response = scan.nextLine();
					break;
				case "3":
					bankUserDomicileController.addResidence();
					System.out.println("Ways to View or Edit Customer Residential Information: \n"
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
					System.out.println("Ways to View or Edit Customer Residential Information: \n"
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
