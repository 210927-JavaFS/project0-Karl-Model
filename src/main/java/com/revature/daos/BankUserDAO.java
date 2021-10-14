package com.revature.daos;

import java.util.List;

import com.revature.models.BankUser;

public interface BankUserDAO {
	
	public List<BankUser> findAll();
	public BankUser findPerson(int id);
	public boolean addPerson(BankUser bankUser);
	
}
