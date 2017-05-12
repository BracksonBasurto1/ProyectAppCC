package com.tecsup.lab8.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDB {

	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection completed.");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} finally {
		}
		return con;
	}

}
