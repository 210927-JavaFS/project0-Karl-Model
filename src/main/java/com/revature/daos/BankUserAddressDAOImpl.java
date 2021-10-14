package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.sun.tools.javac.util.List; // wrong implementation?
import java.util.ArrayList; // import the ArrayList class
import java.util.List;

import com.revature.models.BankUserAddress;
import com.revature.utils.BankConnectionUtil;

public class BankUserAddressDAOImpl implements BankUserAddressDAO {
	
	@Override
	public List<BankUserAddress> findAll() {
		try(Connection conn = BankConnectionUtil.getConnection()) { //try-with-resources 
			String sql = "SELECT * FROM bankuseraddress;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<BankUserAddress> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				BankUserAddress bankUserAddress = new BankUserAddress();
				bankUserAddress.setName(result.getString("home_name"));
				bankUserAddress.setStreetNumber(result.getString("home_number"));
				bankUserAddress.setStreetName(result.getString("home_street"));
				bankUserAddress.setCity(result.getString("home_city"));
				bankUserAddress.setRegion(result.getString("home_region"));
				bankUserAddress.setZip(result.getString("home_zip"));
				bankUserAddress.setCountry(result.getString("home_country"));
				bankUserAddress.setEmail(result.getString("user_email"));
				bankUserAddress.setSs(result.getInt("user_ss"));
				bankUserAddress.setDone(result.getBoolean("home_profile_done"));
				bankUserAddress.setApproved(result.getBoolean("home_profile_approved"));
				list.add(bankUserAddress);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BankUserAddress findByName(String name) {
		try(Connection conn = BankConnectionUtil.getConnection()) { //try-with-resources			
			// start: vulnerable to sql injection attacks
			/*
			//String sql = "SELECT * FROM bankuseraddress";
			String sql = "SELECT * FROM bankuseraddress WHERE home_name = +names+"; // vulnerable to SQL injection attacks"
				
			Statement statement = conn.createStatement();		
				
			ResultSet result = statement.executeQuery(sql);
				
			//List<BankUserAddress> list = new ArrayList<>();
			
			BankUserAddress bankUserAddress = new BankUserAddress();
				
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used
			//with a while loop to iterate through all the data.
				
			while(result.next()) { should work with simple Statement
				bankUserAddress.setName(result.getString("home_name"));
				bankUserAddress.setStreetNumber(result.getString("home_number"));
				bankUserAddress.setStreetName(result.getString("home_street"));
				bankUserAddress.setCity(result.getString("home_city"));
				bankUserAddress.setRegion(result.getString("home_region"));
				bankUserAddress.setZip(result.getString("home_zip"));
				bankUserAddress.setCountry(result.getString("home_country"));
				bankUserAddress.setEmail(result.getString("user_email"));
				bankUserAddress.setSs(result.getInt("user_ss"));
				bankUserAddress.setDone(result.getBoolean("home_profile_done"));
				bankUserAddress.setApproved(result.getBoolean("home_profile_approved"));
			}	
			
			return bankUserAddress;
			*/	
			// end: vulnerable to sql injection attacks
			
			// start: modification against attacks

			String sql = "SELECT * FROM bankuseraddress WHERE home_name = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
			
			BankUserAddress bankUserAddress = new BankUserAddress();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			if(result.next()) {
				
				bankUserAddress.setName(result.getString("home_name"));
				bankUserAddress.setStreetNumber(result.getString("home_number"));
				bankUserAddress.setStreetName(result.getString("home_street"));
				bankUserAddress.setCity(result.getString("home_city"));
				bankUserAddress.setRegion(result.getString("home_region"));
				bankUserAddress.setZip(result.getString("home_zip"));
				bankUserAddress.setCountry(result.getString("home_country"));
				bankUserAddress.setEmail(result.getString("user_email"));
				bankUserAddress.setSs(result.getInt("user_ss"));
				bankUserAddress.setDone(result.getBoolean("home_profile_done"));
				bankUserAddress.setApproved(result.getBoolean("home_profile_approved"));

			}
			
			return bankUserAddress;			
			
			// end: modification against attacks
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean updateResidence(BankUserAddress bankUserAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addResidence(BankUserAddress bankUserAddress) {
		try(Connection conn = BankConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO bankuseraddress (home_name, home_number, home_street, home_city, home_region, home_zip, home_country, user_email, user_ss, home_profile_done, hom_profile_approved) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, bankUserAddress.getName());
			statement.setString(++count, bankUserAddress.getStreetNumber());
			statement.setString(++count, bankUserAddress.getStreetName());
			statement.setString(++count, bankUserAddress.getCity());
			statement.setString(++count, bankUserAddress.getRegion());
			statement.setString(++count, bankUserAddress.getZip());
			statement.setString(++count, bankUserAddress.getCountry());
			statement.setString(++count, bankUserAddress.getEmail());
			statement.setInt(++count, bankUserAddress.getSs());
			statement.setBoolean(++count, bankUserAddress.getDone());
			statement.setBoolean(++count, bankUserAddress.getApproved());
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
