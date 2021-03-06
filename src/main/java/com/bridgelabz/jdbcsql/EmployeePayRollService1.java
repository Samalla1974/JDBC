package com.bridgelabz.jdbcsql;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class EmployeePayRollService1 {		
	private List<EmployeePayRollData> employeePayRollList;
	private EmployeePayrollDBService employeePayRollDBService;
	public enum IOService {CONSOLE_IO,FILE_IO,DB_IO,REST_IO}
	
	public EmployeePayRollService1() {
		employeePayRollDBService =  EmployeePayrollDBService.getInstance();
	}
	public EmployeePayRollService1(List<EmployeePayRollData> employeePayRollList) {
		this();
		this.employeePayRollList = employeePayRollList;
	}
private void readEmployeePayrollData(Scanner consoleInputReader) {		
		System.out.println("Enter the Employee Id : ");
 		int id = consoleInputReader.nextInt();
		System.out.println("Enter the Employee Name : ");
		String name = consoleInputReader.next();
		System.out.println("Enter the Employee Salary : ");
		double salary = consoleInputReader.nextDouble();
		
		employeePayRollList.add(new EmployeePayRollData(id, name, salary));
	}
	private EmployeePayRollData getEmployeePayrollData(String name) {	
		return this.employeePayRollList.stream()
				.filter(EmployeePayRollDataItem -> EmployeePayRollDataItem.employeeName.equals(name))
				.findFirst()
				.orElse(null);
	}	
public List<EmployeePayRollData> readEmployeePayrollData(IOService ioService) {
		if(ioService.equals(IOService.DB_IO))
			this.employeePayRollList = employeePayRollDBService.readData();
		return this.employeePayRollList;		
	}

public void writeEmployeePayrollData(IOService ioService) {
	if(ioService.equals(IOService.CONSOLE_IO)) 
		System.out.println("\nWriting Employee Payroll Roster to Console\n" + employeePayRollList);
	else 
		if(ioService.equals(IOService.FILE_IO))
		new EmployeePayrollFileIOService().writeData(employeePayRollList);
}
public void updateEmployeeSalary(String name, double salary) {
	
	int result = employeePayRollDBService.updateEmployeeData(name,salary);
	if(result == 0) 
		return;	
	EmployeePayRollData employeePayrollData = this.getEmployeePayrollData(name);
	if(employeePayrollData != null)
		employeePayrollData.employeeSalary = salary;
}
public boolean checkEmployeePayrollInSyncWithDB(String name) {
		
		List<EmployeePayRollData> employeePayrollDataList = employeePayRollDBService.getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}

public List<EmployeePayRollData> getEmployeeDetailsBasedOnStartDate(IOService ioService, String startDate) {
	if(ioService.equals(IOService.DB_IO))
		this.employeePayRollList = employeePayRollDBService.getEmployeeDetailsBasedOnStartDateUsingStatement(startDate);
	return this.employeePayRollList;
}

public List<EmployeePayRollData> getEmployeeDetailsBasedOnStartDateUsingPreparedStatement(IOService ioService, String startDate) {
	
	if(ioService.equals(IOService.DB_IO))
		this.employeePayRollList = employeePayRollDBService.getEmployeeDetailsBasedOnStartDateUsingPreparedStatement(startDate);
	return this.employeePayRollList;
}
public List<EmployeePayRollData> getEmployeeDetailsBasedOnName(IOService ioService, String name) {
	if(ioService.equals(IOService.DB_IO))
		this.employeePayRollList = employeePayRollDBService.getEmployeeDetailsBasedOnNameUsingStatement(name);
	return this.employeePayRollList;
}

public static void main(String[] args) {
	
	System.out.println(" Welcome To Employee Payroll Application \n");
	ArrayList<EmployeePayRollData> employeePayrollList  = new ArrayList<EmployeePayRollData>();
	EmployeePayRollService1 employeePayrollService = new EmployeePayRollService1(employeePayrollList);
	Scanner consoleInputReader = new Scanner(System.in);
	
	employeePayrollService.readEmployeePayrollData(consoleInputReader);
	employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);		
	System.out.println("exit the application");
}
}



	
		
		
		
		
		
		
	
