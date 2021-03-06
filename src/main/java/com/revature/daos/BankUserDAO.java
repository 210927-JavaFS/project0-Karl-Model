package com.revature.daos;

import java.util.List;

import com.revature.models.BankUser;
//import com.revature.models.BankUserDomicile;
//import com.revature.models.BankAccount;

public interface BankUserDAO {
	
	public List<BankUser> findAll();
	public BankUser findByName(String name);
	public BankUser findPerson(int id);
	public boolean updatePerson(BankUser bankUser);
	public boolean addPerson(BankUser bankUser);
	public BankUser getPasscode(String password);
	public BankUser getAuthcode(String username);
	public BankUser setAuthcode(String username);
	
}
