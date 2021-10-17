package com.revature.daos;

import java.util.List;

import com.revature.models.BankUserDomicile;

public interface BankUserDomicileDAO {

	public List<BankUserDomicile> findAll();
	public BankUserDomicile findByName(String name);
	public BankUserDomicile findById(int id);
	public boolean updateResidence(BankUserDomicile bankUserDomicile);
	public boolean addResidence(BankUserDomicile bankUserDomicile);
	
	
}
