package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.sun.tools.javac.util.List; // wrong implementation?
import java.util.ArrayList; // import the ArrayList class
import java.util.List;

import com.revature.models.BankUserDomicile;
import com.revature.utils.BankConnectionUtil;

public class BankUserDomicileDAOImpl implements BankUserDomicileDAO {
	
	@Override
	public List<BankUserDomicile> findAll() {
		try(Connection conn = BankConnectionUtil.getConnection()) { //try-with-resources 
			String sql = "SELECT * FROM bankuseraddress;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<BankUserDomicile> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				BankUserDomicile bankUserDomicile = new BankUserDomicile();
				bankUserDomicile.setName(result.getString("home_name"));
				bankUserDomicile.setStreetNumber(result.getString("home_number"));
				bankUserDomicile.setStreetName(result.getString("home_street"));
				bankUserDomicile.setCity(result.getString("home_city"));
				bankUserDomicile.setRegion(result.getString("home_region"));
				bankUserDomicile.setZip(result.getString("home_zip"));
				bankUserDomicile.setCountry(result.getString("home_country"));
				bankUserDomicile.setEmail(result.getString("user_email"));
				bankUserDomicile.setSs(result.getInt("user_ss"));
				bankUserDomicile.setDone(result.getBoolean("home_profile_done"));
				bankUserDomicile.setApproved(result.getBoolean("home_profile_approved"));
				list.add(bankUserDomicile);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BankUserDomicile findByName(String name) {
		try(Connection conn = BankConnectionUtil.getConnection()) { //try-with-resources			
			// start: vulnerable to sql injection attacks
			/*
			//String sql = "SELECT * FROM bankuseraddress";
			String sql = "SELECT * FROM bankuseraddress WHERE home_name = +names+"; // vulnerable to SQL injection attacks"
				
			Statement statement = conn.createStatement();		
				
			ResultSet result = statement.executeQuery(sql);
				
			//List<BankUserDomicile> list = new ArrayList<>();
			
			BankUserDomicile bankUserDomicile = new BankUserDomicile();
				
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used
			//with a while loop to iterate through all the data.
				
			while(result.next()) { should work with simple Statement
				bankUserDomicile.setName(result.getString("home_name"));
				bankUserDomicile.setStreetNumber(result.getString("home_number"));
				bankUserDomicile.setStreetName(result.getString("home_street"));
				bankUserDomicile.setCity(result.getString("home_city"));
				bankUserDomicile.setRegion(result.getString("home_region"));
				bankUserDomicile.setZip(result.getString("home_zip"));
				bankUserDomicile.setCountry(result.getString("home_country"));
				bankUserDomicile.setEmail(result.getString("user_email"));
				bankUserDomicile.setSs(result.getInt("user_ss"));
				bankUserDomicile.setDone(result.getBoolean("home_profile_done"));
				bankUserDomicile.setApproved(result.getBoolean("home_profile_approved"));
			}	
			
			return bankUserDomicile;
			*/	
			// end: vulnerable to sql injection attacks
			
			// start: modification against attacks

			String sql = "SELECT * FROM bankuseraddress WHERE home_name = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, name);
			
			ResultSet result = statement.executeQuery();
			
			BankUserDomicile bankUserDomicile = new BankUserDomicile();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			if(result.next()) {
				
				bankUserDomicile.setName(result.getString("home_name"));
				bankUserDomicile.setStreetNumber(result.getString("home_number"));
				bankUserDomicile.setStreetName(result.getString("home_street"));
				bankUserDomicile.setCity(result.getString("home_city"));
				bankUserDomicile.setRegion(result.getString("home_region"));
				bankUserDomicile.setZip(result.getString("home_zip"));
				bankUserDomicile.setCountry(result.getString("home_country"));
				bankUserDomicile.setEmail(result.getString("user_email"));
				bankUserDomicile.setSs(result.getInt("user_ss"));
				bankUserDomicile.setDone(result.getBoolean("home_profile_done"));
				bankUserDomicile.setApproved(result.getBoolean("home_profile_approved"));

			}
			
			return bankUserDomicile;			
			
			// end: modification against attacks
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean updateResidence(BankUserDomicile bankUserDomicile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addResidence(BankUserDomicile bankUserDomicile) {
		try(Connection conn = BankConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO bankuseraddress (home_name, home_number, home_street, home_city, home_region, home_zip, home_country, user_email, user_ss, home_profile_done, hom_profile_approved) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, bankUserDomicile.getName());
			statement.setString(++count, bankUserDomicile.getStreetNumber());
			statement.setString(++count, bankUserDomicile.getStreetName());
			statement.setString(++count, bankUserDomicile.getCity());
			statement.setString(++count, bankUserDomicile.getRegion());
			statement.setString(++count, bankUserDomicile.getZip());
			statement.setString(++count, bankUserDomicile.getCountry());
			statement.setString(++count, bankUserDomicile.getEmail());
			statement.setInt(++count, bankUserDomicile.getSs());
			statement.setBoolean(++count, bankUserDomicile.getDone());
			statement.setBoolean(++count, bankUserDomicile.getApproved());
			
			statement.execute();
			
			return true;

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
