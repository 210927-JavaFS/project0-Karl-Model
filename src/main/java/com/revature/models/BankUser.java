package com.revature.models;

public class BankUser {

	// BankUser operations (parent class) -- Models Package
	
	// unique abilities for BankAdmin (potential subclass of BankUser)
	// -edit accounts
	// -cancel accounts

	// abilities of BankAdmin class that subclass Employee may inherit:
	// -approve accounts
	// -deny accounts
	
	// abilities of BankAdmin class that subclasses Employee and Customer may inherit:
	// -view customer Account information
	// -view customer Account balances
	// -view customer Personal information
	
	// no unique abilities for BankEmployee (potential subclass of BankUser and/or BankAdmin)
	
	// abilities inherited from parent class BankAdmin:
	//-approve open applications for accounts
	//-deny open applications for accounts
	
	// unique abilities for BankCustomer class (potential subclass of BankUser)
	// -register with username and password (encrypted)
	// -apply to open an account
	// ~apply for joint accounts	
	
	// constructor
	public BankUser() {
		super();		
	}
	
	// fields

	   
	// methods (admin role unique abilities)
	
	// method which enables a user to edit customer Account information
	public void editCustomerAccountInfo() {

	}
	
	// method which enables a user to edit customer Personal information
	public void editCustomerPersonalInfo() {
	
	}

	// method which enables a user to cancel customer Account
	public void cancelCustomerAccount() {

	}
	
	// methods (employee, abilities shared with admin role above and customer role below)
	
	// method which enables a user to view customer Account information
	public void viewCustomerAccountInfo() {

	}
	
	// method which enables a user to view customer Personal information
	public void viewCustomerPersonalInfo() {
	
	}
	
	// method which enables a user to view customer Account balances
	public void viewCustomerAccountBalance() {
	
	}
	
	// method which enables a user to approve customer Account application
	public void okayCustomerAccount() {

	}
	
	// method which enables a user to deny customer Account application
	public void denyCustomerAccount() {
	
	}
	
	// methods (customer role unique abilities)
	
	// method which enables a user to register with username and password (encrypted)
	public void registerCustomerAccount() {

	}
	
	// method which enables a user to apply to open an account
	public void applyIndividualAccount() {

	}
	
	// method which enables a user to apply for joint accounts
	public void applyJointAccount() {
	
	}
}
