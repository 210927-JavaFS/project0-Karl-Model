package com.revature.daos;
/*
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

//import com.revature.main.BankCore;
import com.revature.model.BankAccount;
import com.revature.util.BankConnectionUtil;
//import com.revature.util.BankUserInputValidation;

public class BankAccountDaoImpl implements BankAccountDao {
	
	Scanner sc = new Scanner(System.in);
	private static final Logger logger = Logger.getLogger(BankDriver.class);

	@Override
	public List<BankAccount> getAllBankAccounts() {
	List<BankAccount> bankAccountList = new ArrayList<>();
		
		String sql = "SELECT * FROM bankuseraccounts";
		
		try (Connection con = BankConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
		
			while(rs.next()) {
				BankAccount b = new BankAccount();
				
				int bankAccountId = rs.getInt("account_number");
				b.setId(bankAccountId);
				
				bankAccountList.add(b);
			}
			
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return Collections.emptyList();
	}

	@Override
	public BankAccount getBankAccountById(int id) {
		return null;
	}

	@Override
	public int createBankAccount(BankAccount bankAccount) {
		int createdBankAccounts = 0;
		
		String sql = "INSERT INTO bankuseraccounts (user_ss) VALUES (?)";
		
		try (Connection con = BankConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, bankAccount.getUserId());
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		
		if (createdBankAccounts > 0) {
			logger.info("your bank account has been successfully created");
		}
		
		return createdBankAccounts;
	}
	
	public int createBankAccount(int userId) {
		int createdBankAccounts = 0;
		String sql = "INSERT INTO bankuseraccounts (user_ss) VALUES (?)";
		
		try(Connection con = BankConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, userId);
			createdBankAccounts = ps.executeUpdate();
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		
		if (createdBankAccounts > 0) {
			logger.info("your bank account has been successfully created!");
		}
		
		return createdBanks;
	}
	
	@Override
	public Bank getBankAccountsByUserId(int id) {
		BankAccount bankAccount = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bankuseraccounts WHERE account_number = ?";
		
		try(Connection con = BankConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int bankAccountNumber = rs.getInt("account_number");
				float accountBalance = rs.getFloat("account_balance");
				int userId = rs.getInt("user_ss");
				
				bankAccount = new BankAccount(bankAccountNumber, accountBalance, userId);
			}
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return bank;
		
	}

	@Override
	public int updateBankAccount(BankAccount bankAccount) {
		return 0;
	}

	@Override
	public boolean deposit(int id, float balance) {
		return false;
	}

	@Override
	public void withdraw(int id) {
		float currentBalance = viewBalanceByBankId(id);
		float balance = getPositiveDecimalNumber();
		
		if(currentBalance - balance <= 0) {
			logger.info("You may not overdraw your account. Operation denied.");
			logger.info("");
			return;
		}
		/*
		String sql = "{call WITHDRAW(?, ?)}"; //implement WITHDRAW procedure
		try(Connection con = BankConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)) {
			cs.setInt(1, id);
			cs.setFloat(2, balance);
			cs.execute();
			logger.info("You withdrew $" + UserInputValidation.floatConfig(balance));
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		*/
	/*	
	}
	
	public float viewBalanceByUserId(int id) {
		String sql = "SELECT account_balance FROM bankuseraccounts WHERE user_ss = ?";
		ResultSet rs = null;
		float balance = 0;
		
		try (Connection con = BankConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				balance = rs.getInt("account_balance");
			}
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return balance;
	}
	
	public float viewBalanceByBankId(int id) {
		String sql = "SELECT account_balance FROM bankuseraccounts WHERE account_number = ?";
		ResultSet rs = null;
		float balance = 0;
		
		try(Connection con = BankConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				balance = rs.getFloat("account_balance");
			}
			
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return balance;
	}
	
	public float getPositiveDecimalNumber() {
		float balance = 0;
		
		do {
			try {
				logger.info("Enter a valid positive number.");
				String userInput = sc.nextLine();
				balance = Float.parseFloat(userInput);
				
			} catch (Exception e) {
				
			}
		//}   while (balance < 10 || !UserInputValidation.determineFloatPrecision(balance));	
		}   while (balance < 10);
		
		return balance;
	}

	@Override
	public void deposit(int id) {
		float amount = getPositiveDecimalNumber();
		/*
		String sql = "{call DEPOSIT(?,?)}"; // MAKE STORED PROCEDURE FOR THIS!
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)) {
					cs.setInt(1, id);
					cs.setFloat(2, amount);
					cs.execute();
					
					logger.info("Deposited $" + UserInputValidation.floatConfig(amount));
		} catch (SQLException|IOException e) {
			logger.error(e.getMessage());
		}
		*/
/*
	}
}
*/