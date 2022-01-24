package com.bridgelabz.jdbcsql;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class DBDemo {
public static void main(String[] args) {
	String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
	String userName = "root";
	String password = "Krishna369";
	Connection con;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded!");
	} catch (ClassNotFoundException e) {
		throw new IllegalStateException("cannot find the driver in the classpath!",e);	
	}
	listDrivers();
	
	try {
		System.out.println("connecting to databas:"+jdbcURL);
		con= DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("connection is successful!!!"+con);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
	private static void listDrivers() {
		Enumeration<Driver> driverlist = DriverManager.getDrivers();
		while (driverlist.hasMoreElements()) {
			Driver driverClass = (Driver) driverlist.nextElement();
			System.out.println(" "+driverClass.getClass().getName());
		}
	}
}

