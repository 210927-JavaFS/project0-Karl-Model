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
				+"1) View Members Menu \n"
				+"2) View Residential Menu \n"
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

	private void membersMenu() {
		System.out.println("What would you like to do with the Members? \n"
				+ "1) Show all Members");
		String response = scan.nextLine();
		
		while(!response.equals("4")){
			switch (response){
				case "1":
					bankUserController.showAllPeople();
					System.out.println("What would you like to do with the Members? \n"
							+ "1) Show all Members");
					response = scan.nextLine();
					break;
				default:
					System.out.println("Type 4 to exit.");
					break;
			}
		}
	}

	private void domicilesMenu() {
		System.out.println("What would you like to do with your Residences? \n"
				+ "1) See all Residences \n"
				+ "2) See one Residence \n"
				+ "3) Add a Residence to the database. \n"
				+ "4) Return to previous menu.");
		String response = scan.nextLine();
		
		while(!response.equals("4")){
			switch (response){
				case "1":
					bankUserDomicileController.displayAllResidences();
					System.out.println("What would you like to do with your Residences? \n"
							+ "1) See all Residences \n"
							+ "2) See one Residence");
					response = scan.nextLine();
					break;
				case "2":
					System.out.println("What is the Residence name?");
					String name = scan.nextLine();
					bankUserDomicileController.displayOneResidence(name);
					
					System.out.println("What would you like to do with your Residences? \n"
							+ "1) See all Domiciles \n"
							+ "2) See one Domicile");
					response = scan.nextLine();
					break;
				case "3":
					bankUserDomicileController.addResidence();
					System.out.println("What would you like to do with your Residences? \n"
							+ "1) See all Residences \n"
							+ "2) See one Residence");
					response = scan.nextLine();
					break;
				case "4":
					break;
				default:
					System.out.println("That was not a valid input. Please try again.");
					System.out.println("What would you like to do with your Residences? \n"
							+ "1) See all Residences \n"
							+ "2) See one Domicile");
					response = scan.nextLine();
					break;
					
			}
		}
	}

}
