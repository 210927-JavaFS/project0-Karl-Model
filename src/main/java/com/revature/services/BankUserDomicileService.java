package com.revature.services;

import java.util.List;

import com.revature.daos.BankUserDomicileDAO;
import com.revature.daos.BankUserDomicileDAOImpl;
import com.revature.models.BankUserDomicile;

public class BankUserDomicileService {

	private BankUserDomicileDAO bankUserDomicileDao = new BankUserDomicileDAOImpl();
	
	public List<BankUserDomicile> findAllResidences() {
		return bankUserDomicileDao.findAll();
	}

	public BankUserDomicile findByName(String name) {
		return bankUserDomicileDao.findByName(name);
		
	}
	
	public boolean newResidence(BankUserDomicile bankUserDomicile) {
		return bankUserDomicileDao.addResidence(bankUserDomicile);
	}
	

}
