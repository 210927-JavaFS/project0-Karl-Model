package com.revature.services;

import java.util.List;

import com.revature.daos.BankUserDAO;
import com.revature.daos.BankUserDAOImpl;
import com.revature.models.BankUser;
import com.revature.models.BankUserDomicile;

public class BankUserService {

	private BankUserDAO bankUserDao = new BankUserDAOImpl();
	
	public List<BankUser> findAllPersons(){
		return bankUserDao.findAll();
	}
	
	public BankUser findByName(String name) {
		return bankUserDao.findByName(name);
		
	}
	
	public boolean newPerson(BankUser bankUser) {
		return bankUserDao.addPerson(bankUser);
	}
	
	public BankUser uPassword(String password) {
		return bankUserDao.getPasscode(password);
	}
	
	public BankUser uRole(String username) {
		return bankUserDao.getAuthcode(username);
	}	

}
