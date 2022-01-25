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

private List<EmployeePayRollData> getEmployeePayrollData(ResultSet resultSet) {		
		List<EmployeePayRollData> employeePayrollList = new ArrayList<>();		
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double basicSalary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new EmployeePayRollData(id, name, basicSalary, startDate));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;	
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
 public List<EmployeePayRollData> getEmployeePayrollData(String name) {		
		List<EmployeePayRollData> employeePayrollList = null;
		if(this.employeePayrollDataStatement == null)
			this.prepareStatementForEmployeeData();
		try {
			employeePayrollDataStatement.setString(1,name);
			ResultSet resultSet = employeePayrollDataStatement.executeQuery();
			employeePayrollList = this.getEmployeePayrollData(resultSet);	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}	

 public int updateEmployeeData(String name, double salary) {		
		return this.updateEmployeeDataUsingStatement(name,salary);
	}

 private int updateEmployeeDataUsingStatement(String name, double salary) {		
		String sqlStatement = String.format("UPDATE payroll ,employee SET net_pay = %2f WHERE employee.payroll_id = payroll.payroll_id AND emp_name = '%s';", salary, name);
		
		try (Connection connection = getConnection()){
			java.sql.Statement statement = connection.createStatement();
			return statement.executeUpdate(sqlStatement);
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
		return 0;
	} 
 
 private void prepareStatementForEmployeeData() {		
		try {
			Connection connection = this.getConnection();
			String sqlStatement = "SELECT * FROM employee,payroll WHERE employee.payroll_id = payroll.payroll_id AND name = ?;";
			employeePayrollDataStatement = connection.prepareStatement(sqlStatement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
 private void preparedStatementForEmployeeDataBasedOnStartDate() {	
		try {
			Connection connection = this.getConnection();
			String sqlStatement = "SELECT * FROM employee,payroll WHERE employee.payroll_id = payroll.payroll_id and start BETWEEN CAST(? AS DATE) AND DATE(NOW());";
			employeePayrollDataStatement = connection.prepareStatement(sqlStatement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
 public List<EmployeePayRollData> getEmployeeDetailsBasedOnNameUsingStatement(String name) {	
		String sqlStatement = String.format("SELECT * FROM employee,payroll WHERE employee.payroll_id = payroll.payroll_id and name = '%s';",name);
		List<EmployeePayRollData> employeePayrollList = new ArrayList<>();
				
		try (Connection connection = getConnection();){
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(sqlStatement);
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return employeePayrollList;
		
	}	
 
 public List<EmployeePayRollData> getEmployeeDetailsBasedOnStartDateUsingPreparedStatement(String startDate) {		
		List<EmployeePayRollData> employeePayrollList = null;
		if(this.employeePayrollDataStatement == null)
			this.preparedStatementForEmployeeDataBasedOnStartDate();
		try {
			employeePayrollDataStatement.setString(1,startDate);
			ResultSet resultSet = employeePayrollDataStatement.executeQuery();
			employeePayrollList = this.getEmployeePayrollData(resultSet);	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}
	
 public List<EmployeePayRollData> getEmployeeDetailsBasedOnStartDateUsingStatement(String startDate) {
		
		String sqlStatement = String.format("SELECT * FROM employee,payroll WHERE employee.payroll_id = payroll.payroll_id and start BETWEEN CAST('%s' AS DATE) AND DATE(NOW());",startDate);
		List<EmployeePayRollData> employeePayrollList = new ArrayList<>();
				
		try (Connection connection = getConnection();){
			java.sql.Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlStatement);
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return employeePayrollList;
	}
}   
 




