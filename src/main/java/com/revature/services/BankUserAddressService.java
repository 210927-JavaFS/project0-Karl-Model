package com.revature.services;

import java.util.List;

import com.revature.daos.BankUserAddressDAO;
import com.revature.daos.BankUserAddressDAOImpl;
import com.revature.models.BankUserAddress;

public class BankUserAddressService {

	private BankUserAddressDAO bankUserAddressDao = new BankUserAddressDAOImpl();
	
	public List<BankUserAddress> findAllResidences() {
		return bankUserAddressDao.findAll();
	}

	public BankUserAddress findByName(String name) {
		return bankUserAddressDao.findByName(name);
		
	}
	
	public boolean newResidence(BankUserAddress bankUserAddress) {
		return bankUserAddressDao.addResidence(bankUserAddress);
	}
	

}
