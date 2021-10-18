package com.revature.daos;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankAccountDAO {
	
	public List<BankAccount> getAllBankAccounts();
	public BankAccount findById(int id);
	public int createBankAccount(BankAccount bankAccount);
	public int updateBankAccount(BankAccount bankAccount);
	public BankAccount getBankAccountByUserId(int id);
	//public boolean deposit(int id, float balance);
	//public BankAccount deposit(String accountNumber, float depositAmount);
	public BankAccount deposit(Integer accountNumber, float depositAmount);
	//public BankAccount withdraw(String accountNumber, float withdrawAmount);
	public BankAccount withdraw(Integer accountNumber, float withdrawAmount);
	public void withdraw(int id);
	public void deposit(int id);
	//public BankAccount transfer(String accountNumber1, String accountNumber2, float transferAmount);
	public BankAccount transfer(Integer accountNumber1, Integer accountNumber2, float transferAmount);
	public float viewBalanceByBankAccountId(int id);
	//public BankAccount viewBalanceByBankAccountId(int id);
}
