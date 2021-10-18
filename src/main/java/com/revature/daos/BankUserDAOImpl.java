package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.BankUser;
import com.revature.models.BankUserDomicile;
import com.revature.models.BankAccount;
import com.revature.utils.BankConnectionUtil;

public class BankUserDAOImpl implements BankUserDAO {
	
	private BankUserDomicileDAO bankUserDomicileDao = new BankUserDomicileDAOImpl();
	private BankAccountDAO bankAccountDao = new BankAccountDAOImpl();

	@Override
	public List<BankUser> findAll() {
		try(Connection conn = BankConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM bankuseridentity;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<BankUser> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				BankUser bankUser = new BankUser(
						result.getInt("user_id"), 
						result.getString("user_email"),
						result.getString("user_name"),						
						result.getString("user_pwd"),
						result.getString("user_salt"),
						result.getString("user_first_name"),
						result.getString("user_last_name"),
						result.getString("user_role"),
						result.getBoolean("user_profile_done"),
						result.getBoolean("user_profile_approved"),
						null,
						null
						);
				//String abodeName = result.getString("home_name");
				//
				//if(abodeName!=null) {
				//	BankUserDomicile bankUserDomicile = bankUserDomicileDao.findByName(abodeName);
				//	bankUser.setBankUserDomicile(bankUserDomicile);
				//}
				 
				Integer abodeId = result.getInt("home_id");
				if(abodeId!=null) {
					BankUserDomicile bankUserDomicile = bankUserDomicileDao.findById(abodeId);
					bankUser.setBankUserDomicile(bankUserDomicile);
				}
				
				Integer treasureId = result.getInt("account_id");
				if(treasureId!=null) {
					BankAccount bankAccount = bankAccountDao.findById(treasureId);
					bankUser.setBankAccount(bankAccount);
				}				
				
				list.add(bankUser);
				
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public BankUser findByName(String name) {
		try(Connection conn = BankConnectionUtil.getConnection()) { //try-with-resources			
			// start: vulnerable to sql injection attacks
			/*
			//String sql = "SELECT * FROM bankuseridentity";
			String sql = "SELECT * FROM bankuseridentity WHERE user_name = +name+"; // vulnerable to SQL injection attacks"
				
			Statement statement = conn.createStatement();		
				
			ResultSet result = statement.executeQuery(sql);
				
			//List<BankUser> list = new ArrayList<>();
			
			BankUser bankUser = new BankUser();
				
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used
			//with a while loop to iterate through all the data.
				
			while(result.next()) { should work with simple Statement
				bankUser.setId(result.getInt("user_id")); 
				bankUser.setEmail(result.getString("user_email"));
				bankUser.setUserName(result.getString("user_name"));
				bankUser.setPwd(result.getString("user_pwd"));
				bankUser.setSalt(result.getString("user_salt"));
				bankUser.setFirstName(result.getString("user_first_name"));
				bankUser.setLastName(result.getString("user_last_name"));
				bankUser.setRole(result.getString("user_role"));
				bankUser.setDone(result.getBoolean("user_profile_done"));
				bankUser.setApproved(result.getBoolean("user_profile_approved"));
				//bankUser.setBankUserDomicile(result.getString("home_name"));
				
				//bankUser.setBankUserDomicile(null);
				//String abodeName = result.getString("home_name");
				//if(abodeName!=null) {
				//	BankUserDomicile bankUserDomicile = bankUserDomicileDao.findByName(abodeName);
				//	bankUser.setBankUserDomicile(bankUserDomicile);
				//}
				
				bankUser.setBankUserDomicile(null);
				Integer abodeId = result.getInt("home_id");
				if(abodeId!=null) {
					BankUserDomicile bankUserDomicile = bankUserDomicileDao.findById(abodeId);
					bankUser.setBankUserDomicile(bankUserDomicile);
				}
				
				bankUser.setBankAccount(null);
				Integer treasureId = result.getInt("account_id");
				if(treasureId!=null) {
					BankAccount bankAccount = bankAccountDao.findById(treasureId);
					bankUser.setBankAccount(bankAccount);
				}
			}	
			
			return bankUser;
			*/	
			// end: vulnerable to sql injection attacks
			
			// start: modification against attacks

			String sql = "SELECT * FROM bankuseridentity WHERE user_last_name = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
			
			BankUser bankUser = new BankUser();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			if(result.next()) {
				
				bankUser.setId(result.getInt("user_id")); 
				bankUser.setEmail(result.getString("user_email"));
				bankUser.setUserName(result.getString("user_name"));
				bankUser.setPwd(result.getString("user_pwd"));
				bankUser.setSalt(result.getString("user_salt"));
				bankUser.setFirstName(result.getString("user_first_name"));
				bankUser.setLastName(result.getString("user_last_name"));
				bankUser.setRole(result.getString("user_role"));
				bankUser.setDone(result.getBoolean("user_profile_done"));
				bankUser.setApproved(result.getBoolean("user_profile_approved"));				
				//bankUser.setBankUserDomicile(result.getString("home_name"));	
				
				//bankUser.setBankUserDomicile(null); 
				//String abodeName = result.getString("home_name");				  
				//if(abodeName!=null) {
				//	BankUserDomicile bankUserDomicile = bankUserDomicileDao.findByName(abodeName);
				//	bankUser.setBankUserDomicile(bankUserDomicile);
				//}
				 
				bankUser.setBankUserDomicile(null);
				Integer abodeId = result.getInt("home_id");
				if(abodeId!=null) {
					BankUserDomicile bankUserDomicile = bankUserDomicileDao.findById(abodeId);
					bankUser.setBankUserDomicile(bankUserDomicile);
				}
				
				bankUser.setBankAccount(null);
				Integer treasureId = result.getInt("account_id");
				if(treasureId!=null) {
					BankAccount bankAccount = bankAccountDao.findById(treasureId);
					bankUser.setBankAccount(bankAccount);
				}				
			}
			
			return bankUser;			
			
			// end: modification against attacks
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BankUser findPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean updatePerson(BankUser bankUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPerson(BankUser bankUser) {
		try(Connection conn = BankConnectionUtil.getConnection()){
			
//			String sql = "INSERT INTO bankuseridentity (user_email, user_name, user_pwd, user_salt, user_first_name, user_last_name, user_role, user_profile_done, user_profile_approved, home_id, account_id) "
//					+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
			
			String sql = "INSERT INTO bankuseridentity (user_email, user_name, user_pwd, user_salt, user_first_name, user_last_name, user_role, user_profile_done, user_profile_approved) "
					+ "VALUES (?,?,?,?,?,?,?,?);";			
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//statement.setInt(++count, bankUser.getId()); // commented out to allow SERIALIZE constraint to create Primary Key in database via SQL
			statement.setString(++count, bankUser.getEmail());
			statement.setString(++count, bankUser.getUserName());
			statement.setString(++count, bankUser.getPwd());
			statement.setString(++count, bankUser.getSalt());
			statement.setString(++count, bankUser.getFirstName());
			statement.setString(++count, bankUser.getLastName());
			statement.setString(++count, bankUser.getRole());
			statement.setBoolean(++count, bankUser.getDone());
			statement.setBoolean(++count, bankUser.getApproved());				
			//statement.setString(++count, bankUser.getBankUserDomicile()); // error example kept as a reminder
			
			//==============================================================
			// start: map database Foreign Key(s) to objects in OOP
			//==============================================================
			/*
			//BankUserDomicile bankUserDomicile = bankUser.getBankUserDomicile();
			//if(bankUserDomicile!=null) { 
			//	statement.setString(++count, bankUserDomicile.getName());
			//}else {
			//	statement.setString(++count, null);
			//}
			 
			BankUserDomicile bankUserDomicile = bankUser.getBankUserDomicile();
			if(bankUserDomicile!=null) {
				statement.setInt(++count, bankUserDomicile.getId());
			}else {
				//statement.setInt(++count, null); // compiler error
				statement.setNull(++count, java.sql.Types.NULL);
			}			
			BankAccount bankAccount = bankUser.getBankAccount();
			if(bankAccount!=null) {
				statement.setInt(++count, bankAccount.getId());
			}else {
				//statement.setInt(++count, null); // compiler error
				statement.setNull(++count, java.sql.Types.NULL);
			}
			*/
			//==============================================================
			// end: map database Foreign Key(s) to objects in OOP
			//==============================================================
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
