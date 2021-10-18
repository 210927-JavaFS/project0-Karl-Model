package com.revature.services;

import java.util.List;

import com.revature.daos.BankUserDAO;
import com.revature.daos.BankUserDAOImpl;
import com.revature.daos.BankAccountDAO;
import com.revature.daos.BankAccountDAOImpl;
import com.revature.models.BankUser;
import com.revature.models.BankUserDomicile;
import com.revature.models.BankAccount;


public class BankAccountService {

	private BankAccountDAO bankAccountDao = new BankAccountDAOImpl();
	
	  public List<BankAccount> displayAllLedgers(){ //return bankAccountDao.findAll();
	  return bankAccountDao.getAllBankAccounts(); }
	 

	/*
	public BankAccount findByName(String name) {
		return bankAccountDao.findByName(name);
		
	}
	*/
	
	public BankAccount findByUserId(int id) {
		return bankAccountDao.getBankAccountByUserId(id);	
	}	
	
	public boolean newLedger(BankAccount bankAccount) {
		//return bankAccountDao.addAccount(bankAccount);
		if (bankAccountDao.createBankAccount(bankAccount) == 1) {
			return true;
		}else if (bankAccountDao.createBankAccount(bankAccount) == 0) {
			return false;			
		}else {
			//System.out.println("unrecognized value set to False");			
			return false;
		}	
	}
	
	/*
	public BankAccount getBalance(String username) {
		return bankAccountDao.getBalance(username);
	}

	public BankAccount getWithdraw(String username, double withdrawAmount) {
		return bankAccountDao.withdraw(username, withdrawAmount);
	}

	public BankAccount getDeposit(String username, double depositAmount) {
		return bankAccountDao.deposit(username, depositAmount);
	}

	public BankAccount getTransfer(String username1, String username2, double transferAmount) {
		return bankAccountDao.transfer(username1, username2, transferAmount);
	}	
	*/

	public float balanceMenu(int id) {
		return bankAccountDao.viewBalanceByBankAccountId(id);	
	}
	
	public BankAccount depositMenu(int id, float depositAmount) {
		return bankAccountDao.deposit(id, depositAmount);
	}
	
	public BankAccount withdrawMenu(int id, float withdrawAmount) {
		return bankAccountDao.withdraw(id, withdrawAmount);
	}
	
	public BankAccount transferMenu(int id, int id_out, float transferAmount) {
		return bankAccountDao.transfer(id, id_out, transferAmount);	
	}

}
