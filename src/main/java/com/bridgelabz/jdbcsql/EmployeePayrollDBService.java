package com.bridgelabz.jdbcsql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.bridgelabz.jdbcsql.EmployeePayRollService1.IOService;
import com.mysql.cj.xdevapi.Statement;

public class EmployeePayrollDBService {
	private PreparedStatement employeePayrollDataStatement;
	private static EmployeePayrollDBService employeePayrollDBService;

	EmployeePayrollDBService() {		
	}
	
	public static EmployeePayrollDBService getInstance() {
		if(employeePayrollDBService == null)
			employeePayrollDBService = new EmployeePayrollDBService();
		return employeePayrollDBService;
	}
	
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
		java.sql.Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(sql);
		while(result.next()) {
			int id = result.getInt("name");
			String name = result.getString("name");
			double salary = result.getDouble("salary");
		    LocalDate startDate = (result.getDate("start")).toLocalDate();
		    employeePayrollList.add(new EmployeePayRollData(id,name,salary,startDate));
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
		}
}



