package com.bridgelabz.jdbcsql;
import java.time.LocalDate;

public class EmployeePayRollData {
	int id;
	String name;
	double salary;

	public EmployeePayRollData(Integer id,String name,double salary) {
	this.id=id;
	this.name=name;
	this.salary=salary;	
	}
	public EmployeePayRollData(int id,String name,double salary,LocalDate statDate) {
		this(id,name,salary);
		this.startDate = startDate;
	}
	@Override
	public String toString() {
		return "id=" +id+",name='" + name + '/'  + ", salary=" + salary;
	}	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		EmployeePayRollData that = (EmployeePayRollData) o;
		return id == that.id &&
				Double.compare(that.salary, salary) == 0 &&
				name.equals(that.name);
	}
}
