package com.revature.daos;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankAccountDAO {
	
	public List<BankAccount> getAllBankAccounts();
	public BankAccount findById(int id);
	public int createBankAccount(BankAccount bankAccount);
	public int updateBankAccount(BankAccount bankAccount);
	public BankAccount getBankAccountByUserId(int id);
	public boolean deposit(int id, float balance);
	public void withdraw(int id);
	public void deposit(int id);
}
