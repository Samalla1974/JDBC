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
		
		
}
