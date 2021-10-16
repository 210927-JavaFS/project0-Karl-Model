package com.revature.daos;

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

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.BankDriver;
//import com.revature.main.BankCore;
import com.revature.models.BankAccount;
import com.revature.utils.BankConnectionUtil;
//import com.revature.utils.BankUserInputValidation;

public class BankAccountDAOImpl implements BankAccountDAO {
	
	Scanner sc = new Scanner(System.in);
	//private static final Logger log = Logger.getLogger(BankDriver.class);
	//private static final Logger log = Logger.getLogger(BankAccountDAOImpl.class);
	private static Logger log = LoggerFactory.getLogger(BankAccountDAOImpl.class);
	
	@Override
	public List<BankAccount> getAllBankAccounts() {			
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM bankuseraccounts;";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			List<BankAccount> bankAccountList = new ArrayList<>();
			while(rs.next()) {
				BankAccount b = new BankAccount();
				
				int bankAccountId = rs.getInt("account_number");
				b.setId(bankAccountId);
				
				bankAccountList.add(b);
			}
			return bankAccountList;
		//} catch (IOException e) {
		//	log.error(e.getMessage());
		//} catch (SQLException e) {
		//	log.error(e.getMessage());
		//}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		//return Collections.emptyList();
		return null;
	}

	@Override
	public BankAccount getBankAccountById(int id) {
		return null;
	}

	@Override
	public int createBankAccount(BankAccount bankAccount) { // question: how can a "valid" BankAccount object exist before createBankAccount() is called?
		int createdBankAccounts = 0;
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "INSERT INTO bankuseraccounts (user_ss) VALUES (?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bankAccount.getUserId());
			//ResultSet result = ps.executeQuery();
			//BankAccount bankAccount = new BankAccount;
			ps.execute();
			if (createdBankAccounts > 0) {
				log.info("your bank account has been successfully created");
			}
			return 1;
		//} catch (SQLException|IOException e) {
		//	log.error(e.getMessage());
		//}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;	
	}
	
	public int createBankAccount(int userId) {
		int createdBankAccounts = 0;
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "INSERT INTO bankuseraccounts (user_ss) VALUES (?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			createdBankAccounts = ps.executeUpdate();
			//createdBankAccounts = ps.execute();
		//} catch (SQLException|IOException e) {
		//	log.error(e.getMessage());
		//}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		if (createdBankAccounts > 0) {
			log.info("your bank account has been successfully created!");
		}
		
		return createdBankAccounts;
	}
	
	@Override
	public BankAccount getBankAccountByUserId(int id) {
		//BankAccount bankAccount = null;
		//ResultSet rs = null;
		//String sql = "SELECT * FROM bankuseraccounts WHERE account_number = ?;";
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "SELECT * FROM bankuseraccounts WHERE account_number = ?;";	
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			BankAccount bankAccount = new BankAccount();
			
			//while (rs.next()) {
			//	int bankAccountNumber = rs.getInt("account_number");
			//	float accountBalance = rs.getFloat("account_balance");
			//	int userId = rs.getInt("user_ss");
				
				//bankAccount = new BankAccount(bankAccountNumber, accountBalance, userId);
			//}
			
			if(rs.next()) {
				bankAccount.setId(rs.getInt("account_number"));
				bankAccount.setBalance(rs.getFloat("account_balance"));
				bankAccount.setUserId(rs.getInt("user_ss"));
			}
			
			return bankAccount;
			
		//} catch (SQLException|IOException e) {
		//	log.error(e.getMessage());
		//} finally {
		//	if (rs != null) {
		//		try {
		//			rs.close();
		//		} catch (SQLException e) {
		//			log.error(e.getMessage());
		//		}
		//	}
		//}
		
		//return bankAccount;
	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
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
		float currentBalance = viewBalanceByBankAccountId(id);
		float balance = getPositiveDecimalNumber();
		
		if(currentBalance - balance <= 0) {
			log.info("You may not overdraw your account. Operation denied.");
			log.info("");
			return;
		}
		/*
		String sql = "{call WITHDRAW(?, ?)}"; //implement WITHDRAW procedure
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
				CallableStatement cs = conn.prepareCall(sql)) {
			cs.setInt(1, id);
			cs.setFloat(2, balance);
			cs.execute();
			log.info("You withdrew $" + UserInputValidation.floatConfig(balance));
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		}
		*/
	
	}
	
	public float viewBalanceByUserId(int id) {
		//ResultSet rs = null;
		float balance = 0;
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "SELECT account_balance FROM bankuseraccounts WHERE user_ss = ?;"; // toDo: replace user_ss with user_id (a PK distinct from user_name)
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				balance = rs.getInt("account_balance");
			}

			//if(rs.next()) {
			//	bankAccount.setId(rs.getInt("account_number"));
			//	bankAccount.setBalance(rs.getFloat("account_balance"));
			//	bankAccount.setUserId(rs.getInt("user_ss"));
			//}
			
			//return bankAccount;
			
			return balance;			
			
		//} catch (SQLException|IOException e) {
		//	log.error(e.getMessage());
		//} finally {
		//	if(rs != null) {
		//		try {
		//			rs.close();
		//		} catch (SQLException e) {
		//			log.error(e.getMessage());
		//		}
		//	}
		//}
		
		//return balance;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}			
		//return null;
		return (float)-999999.99; // hack: hardcoded value as flag			
	}
	
	public float viewBalanceByBankAccountId(int id) {
		//ResultSet rs = null;
		float balance = 0;
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "SELECT account_balance FROM bankuseraccounts WHERE account_number = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				balance = rs.getFloat("account_balance");
			}
			
			//if(rs.next()) {
			//	bankAccount.setId(rs.getInt("account_number"));
			//	bankAccount.setBalance(rs.getFloat("account_balance"));
			//	bankAccount.setUserId(rs.getInt("user_ss"));
			//}
			
			//return bankAccount;
			
			return balance;
			
		//} catch (SQLException|IOException e) {
		//	log.error(e.getMessage());
		//} finally {
		//	if(rs != null) {
		//		try {
		//			rs.close();
		//		} catch (SQLException e) {
		//			log.error(e.getMessage());
		//		}
		//	}
		//}
		
		//return balance;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}			
		//return null;
		return (float)-999999.99; // hack: hardcoded value as flag
	}
	
	public float getPositiveDecimalNumber() {
		float balance = 0;
		
		do {
			try {
				log.info("Enter a valid positive number.");
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
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
				CallableStatement cs = conn.prepareCall(sql)) {
					cs.setInt(1, id);
					cs.setFloat(2, amount);
					cs.execute();
					
					log.info("Deposited $" + UserInputValidation.floatConfig(amount));
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		}
		*/

	}
}
