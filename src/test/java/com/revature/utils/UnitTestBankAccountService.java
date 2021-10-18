package com.revature.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.daos.BankAccountDAOImpl;


public class UnitTestBankAccountService {
	
	public static Connection connection;
	public static BankConnectionUtil conn;
	public static float withdrawAmount;
	public static float depositAmount;
	public static float transferAmount;
	//public static String accNum1;
	//public static String accNum2;
	public static Integer accNum1;
	public static Integer accNum2;
	public static float result;
	public static Logger log = LoggerFactory.getLogger(UnitTestBankAccountService.class);
	public static BankAccountDAOImpl bankAccountDao;
	
	
	@BeforeEach
	public void setFloats() {
		withdrawAmount = (float)5.00;
		depositAmount = (float)5.00;
		transferAmount = (float)5.00;
		
		//accNum1 = "138297266";
		//accNum2 = "179582108";
		accNum1 = 138297266;
		accNum2 = 179582108;
	}
	
	@Test
	public void checkWithdraw() {
		bankAccountDao.withdraw(accNum1, withdrawAmount);
	}
	
	@Test
	public void checkDeposit() throws SQLException {
		bankAccountDao.deposit(accNum2, depositAmount);
	}
	
	@Test
	public void checkTransfer() throws SQLException {
		bankAccountDao.transfer(accNum1, accNum2, transferAmount);
	}
	

}