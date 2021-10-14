package com.revature.controllers;

import java.util.List;

import com.revature.models.BankUser;
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

}
