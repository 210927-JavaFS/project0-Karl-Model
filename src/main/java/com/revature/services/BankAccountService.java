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
	
	public List<BankAccount> accountsAssemble(){
		return bankAccountDao.findAll();
	}
	
	public BankAccount findByName(String name) {
		return bankAccountDao.findByName(name);
		
	}
	
	public boolean newAccount(BankAccount bankAccount) {
		return bankAccountDao.addAccount(bankAccount);
	}

}