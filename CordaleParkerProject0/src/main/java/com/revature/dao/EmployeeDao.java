package com.revature.dao;

import com.revature.models.Employee;

public interface EmployeeDao {

	public Employee selectEmployeeByLogin(String accountName, String accountPassword);

}
