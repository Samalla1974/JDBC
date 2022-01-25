package com.bridgelabz.jdbcsql;
import java.time.LocalDate;
import com.mysql.cj.protocol.InternalDate;
public class EmployeePayRollData {
	public int employeeId;
	public String  employeeName;
	public double employeeSalary;
	public InternalDate startDate;
	
	public EmployeePayRollData(Integer id,String name,double salary) {
	this.employeeId= id;
	this.employeeName=name;
	this.employeeSalary=salary;	
	}
	public EmployeePayRollData(int id,String name,double salary,LocalDate statDate) {
		this(id,name,salary);
		this.startDate = startDate;
	}
	@Override
	public String toString() {
		return "EmployeeId=" +employeeId+",EmployeeName='" + employeeName + '/'  + ", salary=" + employeeSalary;
	}	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		EmployeePayRollData that = (EmployeePayRollData) o;
		return employeeId == that.employeeId &&
				Double.compare(that.employeeSalary, employeeSalary) == 0 &&
				employeeName.equals(that.employeeName);
	}
}
