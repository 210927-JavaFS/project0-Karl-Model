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
import com.revature.utils.BankUserInputValidation;

public class BankAccountDAOImpl implements BankAccountDAO {
	
	Scanner sc = new Scanner(System.in);
	//private static final Logger log = Logger.getLogger(BankDriver.class);
	//private static final Logger log = Logger.getLogger(BankAccountDAOImpl.class);
	private static Logger log = LoggerFactory.getLogger(BankAccountDAOImpl.class);
	
	@Override
	public List<BankAccount> getAllBankAccounts() {			
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM bankuseraccount;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<BankAccount> bankAccountList = new ArrayList<>();
			while(result.next()) {
				BankAccount bankAccount = new BankAccount();
				
				bankAccount.setId(result.getInt("account_id"));
				bankAccount.setBalance(result.getFloat("account_balance")); 
				bankAccount.setUserId(result.getInt("account_id")); // note: user_id is not a field in bankuseraccount
				
				bankAccountList.add(bankAccount);
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
	public BankAccount findById(int id) {
		return null;
	}

	@Override
	public int createBankAccount(BankAccount bankAccount) { // question: how can a "valid" BankAccount object exist before createBankAccount() is called?
		int createdBankAccounts = 0;
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "INSERT INTO bankuseraccount (balance, user_id) "
					+ "VALUES (?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//statement.setString(++count, bankAccount.getId()); // commented out to allow SERIALIZE constraint to create Primary Key in database via SQL
			statement.setFloat(++count, bankAccount.getBalance()); 
			statement.setInt(++count, bankAccount.getUserId()); // note: user_id is not a field in bankuseraccount			
			
			statement.execute();
			
			//return true;
			return 1;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if (createdBankAccounts > 0) {
			log.info("your bank account has been successfully created!");
		}
		
		//return false;
		return 0;
	}
	
	public int createBankAccount(int userId) { // deprecate unnecessary method?
		int createdBankAccounts = 0;
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "INSERT INTO bankuseraccount (balance, user_id) " // toDo: implement SQL statement specific to userId or simply account id (first case requires a JOIN because userId is not a field in table bankuseraccount)
					+ "VALUES (?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			BankAccount bankAccount = new BankAccount();
			
			//statement.setString(++count, bankAccount.getId()); // commented out to allow SERIALIZE constraint to create Primary Key in database via SQL
			statement.setFloat(++count, bankAccount.getBalance()); 
			statement.setInt(++count, bankAccount.getUserId()); // note: user_id is not a field in bankuseraccount			
			
			statement.execute();
			
			//return true;
			return 1;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if (createdBankAccounts > 0) {
			log.info("your bank account has been successfully created!");
		}
		
		//return false;
		return 0;
	}
	
	@Override
	public BankAccount getBankAccountByUserId(int id) { // temporary hack: ambiguous method name (toDo: refactor or clone)
		//BankAccount bankAccount = null;
		//ResultSet rs = null;
		//String sql = "SELECT * FROM bankuseraccounts WHERE account_number = ?;";
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "SELECT * FROM bankuseraccount WHERE account_id = ?;";	
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
				bankAccount.setId(rs.getInt("account_id"));
				bankAccount.setBalance(rs.getFloat("account_balance"));
				bankAccount.setUserId(rs.getInt("account_id")); // note: user_id is not a field in bankuseraccount
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

	/*
	@Override
	public boolean deposit(int id, float balance) {
		return false;
	}
	*/

	/*
	@Override
	public BankAccount withdraw(String accountNumber, float withdrawAmount) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM bankuseraccount WHERE account_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, accountNumber);
			ResultSet result = statement.executeQuery();
			BankAccount bankAccount = new BankAccount();
			if (result.next()) {
				bankAccount.setBalance(result.getFloat("account_balance"));
			}

			if (withdrawAmount < 0) {
				System.out.println("withdraw may not be negative");
			} else if (bankAccount.getBalance() <= 0) {
				System.out.println("Account balance is 0. Withdraw denied");
			} else {
				float newBalance = bankAccount.getBalance() - withdrawAmount;
				String sql2 = "UPDATE account_balance SET account_balance = ? WHERE account_id = ?";
				PreparedStatement statement2 = conn.prepareStatement(sql2);
				statement2.setFloat(1, newBalance);
				statement2.setString(2, accountNumber);
				statement2.execute();
			}

			return bankAccount;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	*/
	
	@Override
	public BankAccount withdraw(Integer accountNumber, float withdrawAmount) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM bankuseraccount WHERE account_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, accountNumber);
			ResultSet result = statement.executeQuery();
			BankAccount bankAccount = new BankAccount();
			if (result.next()) {
				bankAccount.setBalance(result.getFloat("account_balance"));
			}

			if (withdrawAmount < 0) {
				System.out.println("withdraw may not be negative");
			} else if (bankAccount.getBalance() <= 0) {
				System.out.println("Account balance is 0. Withdraw denied");
			} else {
				float newBalance = bankAccount.getBalance() - withdrawAmount;
				String sql2 = "UPDATE bsnkuseraccount SET account_balance = ? WHERE account_id = ?";
				PreparedStatement statement2 = conn.prepareStatement(sql2);
				statement2.setFloat(1, newBalance);
				statement2.setInt(2, accountNumber);
				statement2.execute();
				
				System.out.println("You withdrew $"+withdrawAmount);
				log.info("You withdrew $" + BankUserInputValidation.floatConfig(withdrawAmount));
			}

			return bankAccount;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
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
	/*
	@Override
	public BankAccount getBalance(String accountNumber) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM bankuseraccount WHERE account_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, accountNumber);
			ResultSet result = statement.executeQuery();
			BankAccount bankAccount = new BankAccount();

			// statement3.execute();
			while (result.next()) {
				bankAccount.SetBalance(result.getFloat("account_balance"));
			}

			return bankAccount;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	*/
	public float viewBalanceByUserId(int id) {
		//ResultSet rs = null;
		float balance = 0;
		
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources
			String sql = "SELECT account_balance FROM bankuseraccount WHERE user_id = ?;"; // toDo: replace with a JOIN statement (because user_id is not a field in bankaccount)
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
			String sql = "SELECT account_balance FROM bankuseraccount WHERE account_id = ?;";
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
	/*
	@Override
	public BankAccount deposit(String accountNumber, float depositAmount) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM bankuseraccount WHERE account_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, accountNumber);
			ResultSet result = statement.executeQuery();
			BankAccount bankAccount = new BankAccount();
			if (result.next()) {
				bankAccount.setBalance(result.getFloat("account_balance"));
			}

			if (depositAmount < 0) {
				System.out.println("deposit may not be negative");
			} else {
				double newBalance = bankAccount.getBalance() + depositAmount;
				String sql2 = "UPDATE account_balance SET account_balance = ? WHERE account_id = ?";
				PreparedStatement statement2 = conn.prepareStatement(sql2);
				statement2.setDouble(1, newBalance);
				statement2.setString(2, accountNumber);
				statement2.execute();
			}

			return bankAccount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	@Override
	public BankAccount deposit(Integer accountNumber, float depositAmount) {
		try (Connection conn = BankConnectionUtil.getConnection()) {
			String sql = "SELECT account_balance FROM bankuseraccount WHERE account_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, accountNumber);
			ResultSet result = statement.executeQuery();
			BankAccount bankAccount = new BankAccount();
			if (result.next()) {
				bankAccount.setBalance(result.getFloat("account_balance"));
			}

			if (depositAmount < 0) {
				System.out.println("Cdeposit may not be negative");
			} else {
				float newBalance = bankAccount.getBalance() + depositAmount;
				String sql2 = "UPDATE bankuseraccount SET account_balance = ? WHERE account_id = ?";
				PreparedStatement statement2 = conn.prepareStatement(sql2);
				statement2.setFloat(1, newBalance);
				statement2.setInt(2, accountNumber);
				statement2.execute();
				
				System.out.println("You deposited $"+depositAmount);
				log.info("You deposited $" + BankUserInputValidation.floatConfig(depositAmount));
			}

			return bankAccount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	@Override
	public BankAccount transfer(String accountNumber1, String accountNumber2, float transferAmount) {
		BankAccount bankAccount = new BankAccount();
		withdraw(accountNumber1, transferAmount);
		deposit(accountNumber2, transferAmount);
		return bankAccount;	
	*/
	
	@Override
	public BankAccount transfer(Integer accountNumber1, Integer accountNumber2, float transferAmount) {
		BankAccount bankAccount = new BankAccount();
		withdraw(accountNumber1, transferAmount);
		deposit(accountNumber2, transferAmount);
		
		System.out.println("You transfered $"+transferAmount);
		log.info("You transfered $" + BankUserInputValidation.floatConfig(transferAmount));		
		
		return bankAccount;	
	}	
	
}
