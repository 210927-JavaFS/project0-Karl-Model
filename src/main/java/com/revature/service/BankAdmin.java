package com.revature.service;

public class BankAdmin extends BankAccount {
	
	// placeholder: bank administrator operations (subclass of BankUser class, parent of BankEmployee class)
	
	// unique abilities for BankAdmin class
	// -edit accounts
	// -cancel accounts

	// abilities of BankAdmin class that subclass Employee may inherit:
	// -approve accounts
	// -deny accounts
	
	// abilities inherited from parent class BankAccount:
	// -withdrawing from accounts
	// -depositing to accounts
	// -transferring between accounts
	
	// abilities inherited from parent class BankAccount:
	// -view customer Account information
	// -view customer Account balances
	// -view customer Personal information
	
	// constructor
	public BankAdmin() {
		super();		
	}
	
	// fields

	   
	// methods
	
	// method which enables a user to edit customer Account information
	public void editCustomerAccountInfo() {

	}
	
	// method which enables a user to edit customer Personal information
	public void editCustomerPersonalInfo() {
	
	}

	// method which enables a user to cancel customer Account
	public void cancelCustomerAccount() {

	}
	
	// method which enables a user to approve customer Account application
	public void okayCustomerAccount() {

	}
	
	// method which enables a user to deny customer Account application
	public void denyCustomerAccount() {
	
	}
		
}
