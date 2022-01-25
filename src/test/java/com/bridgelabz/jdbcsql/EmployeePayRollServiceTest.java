package com.bridgelabz.jdbcsql;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.jdbcsql.EmployeePayRollService1.IOService;

public class EmployeePayRollServiceTest {

	@Test
	public void givenEmployeePayRollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayRollService1 employeepayrollservice = new EmployeePayRollService1();
		List<EmployeePayRollData> employeepayrolldata = employeepayrollservice.readEmployeePayrollData(IOService.DB_IO);
		Assert.assertEquals( 5, employeepayrolldata.size());
	}
		
		@Test 
		public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {		
			EmployeePayRollService1 employeePayrollService = new EmployeePayRollService1();
			List<EmployeePayRollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
			employeePayrollService.updateEmployeeSalary("Bill", 7000000.00);
			
			boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Bill");
			Assert.assertTrue(result);
	}
		@Test
		public void givenName_WhenFound_ShouldReturnEmployeeDetails() {
			
			EmployeePayrollService employeePayrollService = new EmployeePayrollService();
			String name = "Rosa Diaz";
			List<EmployeePayrollData> employeePayrollData = employeePayrollService.getEmployeeDetailsBasedOnName(IOService.DB_IO, name);
			String resultName = employeePayrollData.get(0).employeeName;
			Assert.assertEquals(name, resultName);
		}
		

}
