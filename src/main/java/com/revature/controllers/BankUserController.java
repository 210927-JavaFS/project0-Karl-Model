package com.revature.controllers;

import java.util.List;

import com.revature.models.BankUser;
import com.revature.models.BankUserDomicile;
import com.revature.services.BankUserService;

public class BankUserController {

	private BankUserService bankUserService = new BankUserService();
	
	public void showAllPeople() {
		System.out.println("Here are all the Users:");
		List<BankUser> list = bankUserService.peopleAssemble();
		for(BankUser u:list) {
			System.out.println(u);
		}
	}
	
	public void showAllCustomers() {
		System.out.println("Here are all the Customers:");
		List<BankUser> list = bankUserService.peopleAssemble();
		for(BankUser u:list) {
			//ToDo: exclude Non-Customers
			System.out.println(u);
		}
	}
	
	public void displayOnePerson(String name) {
		System.out.println("Here is the Member named: "+name+":");
		BankUser bankUser = bankUserService.findByName(name);
		System.out.println(bankUser);
	}
	
	public void displayOneCustomer(String name) {
		System.out.println("Here is the Customer named: "+name+":");
		BankUser bankUser = bankUserService.findByName(name);
		//ToDo: exclude Non-Customers
		System.out.println(bankUser);
	}

}
