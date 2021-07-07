package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String USERNAME = System.getenv("TRAINING_DB_USERNAME");
	private static final String PASSWORD = System.getenv("TRAINING_DB_PASSWORD");
	private static final String URL= "jdbc:postgresql://localhost/postgres";
	
	private static Connection conn;
	
	public static Connection getConnection() {
			try {
				conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}catch(SQLException e) {
				
			}
			return conn;
	}
	
}
