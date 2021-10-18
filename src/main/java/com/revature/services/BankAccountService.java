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

}
