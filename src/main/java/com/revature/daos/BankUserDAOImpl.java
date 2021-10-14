package com.revature.daos;

import java.sql.Connection;
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
	public BankUser findPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPerson(BankUser bankUser) {
		// TODO Auto-generated method stub
		return false;
	}

}
