package com.revature.service;

public class BankAccount {
	
	// bank account operations (parent class) -- Service Layer
	
	// abilities of BankAccount class that subclasses may inherit:
	// -withdrawing from accounts
	// -depositing to accounts
	// -transferring between accounts
	
	// constructor
	public BankAccount() {
		super();
	}
	
	// constructor which takes five parameters as input
	public BankAccount(String uLevel, String uName, String uId, int uAcct, double balance) {
		this.levelUser = uLevel; // pass uLevel parameter to member field
		this.nameUser = uName; // pass uName parameter to member field
		this.idUser = uId; // pass uId parameter to member field
		this.acctUser = uAcct; // pass uAcct parameter to member field

		// validate that the balance is positive; otherwise
		// member field balance keeps its default value of zero
		if (balance > 0.0) // if the balance is valid                       
			this.balance = balance; // pass balance parameter to member field
	}

	// fields

	double balance;
	double transactionLast;
	String levelUser;
	String nameUser;
	String idUser;
	int    acctUser;
	   
	// methods
	
	// method which deposits only a valid amount (added to the balance)
	public void bankDeposit(double depositAmount) {
	      if (depositAmount > 0.0) { // if the depositAmount is valid
	         balance = balance + depositAmount; // add depositAmount to the balance
	         transactionLast = -depositAmount; // establish an offsetting equal and opposite transaction
	      }
	}

	// method which withdraws only a valid amount (subtracted from the balance)
	public void bankWithdraw(double withdrawAmount) {
	      if (withdrawAmount > 0.0) { // if the withdrawAmount is valid
	         balance = balance - withdrawAmount; // subtract withdrawAmount from the balance
	         transactionLast = -withdrawAmount; // establish an offsetting equal and opposite transaction
	      }
	}

	// method which summarizes the results of the most recent prior transaction as output to Command Line Interface (CLI) via Console
	public void getTransactionLast() {
		if (transactionLast > 0.0) { // if transactionLast is a deposit
			System.out.println("You have deposited: " + transactionLast);
		}
		else if (transactionLast < 0.0) { // if transactionLast is a withdrawal
			System.out.println("You have withdrawn: " + Math.abs(transactionLast));
		}
		else {
			transactionLast = 0;
			System.out.println("No transaction has occured.");
		}
	}
	
	// method which enables a user to transfer funds between accounts
	public void bankTransfer(double transferAmount) {
		bankWithdraw(-transferAmount);
		// validate that withdrawal has occured
		if (transactionLast < 0.0) {
			bankDeposit(transferAmount);
		}
		else {
			System.out.println("Unable to complete transfer.");
		}
	}

	// method which enables a user to view customer Account information
	public void viewCustomerAccountInfo() {

	}

	// method which enables a user to view customer Account balances
	public void viewCustomerAccountBalance() {
	
	}

	// method which enables a user to view customer Personal information
	public void viewCustomerPersonalInfo() {
	
	}
}

