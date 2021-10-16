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
import com.revature.utils.BankConnectionUtil;

public class BankUserDAOImpl implements BankUserDAO {
	
	private BankUserDomicileDAO bankUserDomicileDao = new BankUserDomicileDAOImpl();

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
						result.getString("user_pwd"),
						result.getString("user_first_name"),
						result.getString("user_last_name"),
						result.getString("user_role"),
						result.getInt("user_ss"),
						result.getBoolean("user_profile_done"),
						result.getBoolean("user_profile_approved"),
						null
						);
				String abodeName = result.getString("home_name");

				if(abodeName!=null) {
					BankUserDomicile bankUserDomicile = bankUserDomicileDao.findByName(abodeName);
					bankUser.setBankUserDomicile(bankUserDomicile);
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
				bankUser.setPwd(result.getString("user_pwd"));
				bankUser.setFirstName(result.getString("user_first_name"));
				bankUser.setLastName(result.getString("user_last_name"));
				bankUser.setRole(result.getString("user_role"));
				bankUser.setSs(result.getInt("user_ss"));
				bankUser.setDone(result.getBoolean("user_profile_done"));
				bankUser.setApproved(result.getBoolean("user_profile_approved"));
				//bankUser.setBankUserDomicile(result.getString("home_name"));	
				bankUser.setBankUserDomicile(null);
				String abodeName = result.getString("home_name");

				if(abodeName!=null) {
					BankUserDomicile bankUserDomicile = bankUserDomicileDao.findByName(abodeName);
					bankUser.setBankUserDomicile(bankUserDomicile);
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
				bankUser.setPwd(result.getString("user_pwd"));
				bankUser.setFirstName(result.getString("user_first_name"));
				bankUser.setLastName(result.getString("user_last_name"));
				bankUser.setRole(result.getString("user_role"));
				bankUser.setSs(result.getInt("user_ss"));
				bankUser.setDone(result.getBoolean("user_profile_done"));
				bankUser.setApproved(result.getBoolean("user_profile_approved"));				
				//bankUser.setBankUserDomicile(result.getString("home_name"));	
				bankUser.setBankUserDomicile(null);
				String abodeName = result.getString("home_name");

				if(abodeName!=null) {
					BankUserDomicile bankUserDomicile = bankUserDomicileDao.findByName(abodeName);
					bankUser.setBankUserDomicile(bankUserDomicile);
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
			
			String sql = "INSERT INTO bankuseridentity (user_id, user_email, user_pwd, user_first_name, user_last_name, user_role, user_ss, user_profile_done, user_profile_approved, home_name) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(++count, bankUser.getId()); 
			statement.setString(++count, bankUser.getEmail());
			statement.setString(++count, bankUser.getPwd());
			statement.setString(++count, bankUser.getFirstName());
			statement.setString(++count, bankUser.getLastName());
			statement.setString(++count, bankUser.getRole());
			statement.setInt(++count, bankUser.getSs());
			statement.setBoolean(++count, bankUser.getDone());
			statement.setBoolean(++count, bankUser.getApproved());				
			//statement.setString(++count, bankUser.getBankUserDomicile());
			BankUserDomicile bankUserDomicile = bankUser.getBankUserDomicile();
			if(bankUserDomicile!=null) {
				statement.setString(++count, bankUserDomicile.getName());
			}else {
				statement.setString(++count, null);	
			}
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
