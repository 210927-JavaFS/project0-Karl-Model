package com.revature.daos;

import java.util.List;

import com.revature.models.BankUserAddress;

public interface BankUserAddressDAO {

	public List<BankUserAddress> findAll();
	public BankUserAddress findByName(String name);
	public boolean updateResidence(BankUserAddress bankUserAddress);
	public boolean addResidence(BankUserAddress bankUserAddress);
	
	
}
