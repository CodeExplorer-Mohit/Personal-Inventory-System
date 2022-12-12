package com.mohit.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	static Connection connect = null;
	
	public static Connection connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalinventorysystem", "root", "0766");
			System.out.println("Conection Success.👍");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return connect;
	}
	
	public static void main(String args[]) {
		connectDB();
	}
}
