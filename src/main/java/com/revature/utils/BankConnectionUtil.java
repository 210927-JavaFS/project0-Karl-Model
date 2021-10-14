package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankConnectionUtil {
	
	public static Connection getConnection() throws SQLException {

		//For many frameworks using JDBC or operation with JDBC it is necessary to "register" the driver
		//you are uinsg to make the framework aware of it.
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//String url  = "jdbc:postgresql://javafs-km-210927.c2n1h9km5cjn.us-east-1.rds.amazonaws.com:5432/demos";
		String url  = "jdbc:postgresql://javafs-km-210927.c2n1h9km5cjn.us-east-1.rds.amazonaws.com:5432/javafskm21097";
		String username = "postgres"; //It is possible to use env variables to hide this information
		String password = "password"; //you would access them with System.getenv("var-name");
		
		return DriverManager.getConnection(url, username, password);
		
	}
}
