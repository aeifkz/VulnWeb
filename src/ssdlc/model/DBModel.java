package ssdlc.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBModel {
	
	public Connection getConnection() throws Exception {		
		Class.forName("com.mysql.cj.jdbc.Driver");			
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/simple?" + "user=root&password=root&serverTimezone=CST");		
		return conn;
	}

}
