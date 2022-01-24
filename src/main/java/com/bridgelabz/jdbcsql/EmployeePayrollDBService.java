package com.bridgelabz.jdbcsql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.xdevapi.Statement;

public class EmployeePayrollDBService {
	private Connection getConnection() throws SQLException{
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "Krishna369";
		Connection con;
		System.out.println("connecting to databas:"+jdbcURL);
		con= DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("connection is successful!!!"+con);
		return con;
		}
	public List<EmployeePayRollData> readData(){
		String sql ="Select * from employee_payroll;" ;
		List<EmployeePayRollData> employeePayrollList = new ArrayList<>();
		try {
		Connection con = this.getConnection();
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while(result.next()) {
			int id = result.getInt(columnLabel: "name");
			String name = result.getString(columnLabel: "name");
			double salary = result.getDouble(columnLabel: "salary");
		    LocalDate startDate = result.getDate(columnLabel:"start").localDate();
		    employeePayrollList.add(new EmployeePayRollData(id,name,salary,startDate));
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}		
}

