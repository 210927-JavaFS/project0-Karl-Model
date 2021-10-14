package com.revature.services;

import java.util.List;

import com.revature.daos.BankUserDAO;
import com.revature.daos.BankUserDAOImpl;
import com.revature.models.BankUser;

public class BankUserService {

	private BankUserDAO bankUserDao = new BankUserDAOImpl();
	
	public List<BankUser> peopleAssemble(){
		return bankUserDao.findAll();
	}

}
