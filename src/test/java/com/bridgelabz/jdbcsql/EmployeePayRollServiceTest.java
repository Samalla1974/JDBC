package com.bridgelabz.jdbcsql;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayRollServiceTest {

	@Test
	public void givenEmployeePayRollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayRollService employeepayrollservice = new EmployeePayRollService();
		List<EmployeePayRollData> employeepayrolldata = employeepayrollservice.readEmployeePayRollData(DB_IO);
		Assert.assertArrayEquals(expected: 3, employeepayrolldata.size());
	}
}
