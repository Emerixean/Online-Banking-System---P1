package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.util.ConnectionFactory;

public interface EmployeeDao {

	public Employee selectEmployeeByLogin(String accountName, String accountPassword);

	public Employee selectEmployeeByEmployeeId(int id);
}
