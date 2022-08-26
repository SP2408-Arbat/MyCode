package com.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	static Connection con=null;

	public static Connection getConnection() 
	{

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/online_auction", "root", "");
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception is " + e);

		}
		return con;
	}

}

