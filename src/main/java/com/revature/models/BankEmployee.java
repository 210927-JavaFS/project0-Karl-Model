package com.revature.models;

public class BankEmployee extends BankAdmin {
	
	// placeholder: bank employee operations (subclass of BankUser class)

	// no unique abilities for BankEmployee class
	
	// abilities inherited from parent class BankAdmin:
	//-approve open applications for accounts
	//-deny open applications for accounts
	
	// abilities inherited from grand-parent class BankAccount (to be disallowed via field):
	// -withdrawing from accounts
	// -depositing to accounts
	// -transferring between accounts
	
	// abilities inherited from grand-parent class BankAccount:
	// -view customer Account information
	// -view customer Account balances
	// -view customer Personal information
	
	// constructor
	public BankEmployee() {
		super();		
	}
	
	// fields

	   
	// methods	
		
}

