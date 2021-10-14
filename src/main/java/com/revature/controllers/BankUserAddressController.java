package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import com.revature.models.BankUserAddress;
import com.revature.services.BankUserAddressService;

public class BankUserAddressController {
	
	private BankUserAddressService bankUserAddressService = new BankUserAddressService();
	private Scanner scan = new Scanner(System.in);
	
	public void displayAllResidences() {
		System.out.println("These are your Addresses:");
		List<BankUserAddress> list = bankUserAddressService.findAllResidences();
		for(BankUserAddress bankUserAddress:list) {
			System.out.println(bankUserAddress);
		}
	}

	public void displayOneResidence(String name) {
		System.out.println("Here is the Address named: "+name+":");
		BankUserAddress bankUserAddress = bankUserAddressService.findByName(name);
		System.out.println(bankUserAddress);
	}
	
	
	public void addResidence() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                FirstBankOfJava");
		System.out.println("==================================================");
		System.out.println("        New Bank Account Application Form");
		System.out.println("==================================================");
		System.out.println("         Part One: Residential Address");
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
		
		System.out.println("What is your email?");
		String email = scan.nextLine();
		System.out.println("What is your social security number? (digits only)");
		//Integer ss = scan.nextLine(); // error
		Integer ss = Integer.parseInt(scan.nextLine());
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
		BankUserAddress bankUserAddress = new BankUserAddress(name, number, stName, city, region, zip, country, email, ss, done, approved);
		
		if(bankUserAddressService.newResidence(bankUserAddress)) {
			System.out.println("Your Address profile was successfully completed");
		}else {
			System.out.println("Something went wrong. We could not register your Address. Please try again.");
		}
	}
	

}