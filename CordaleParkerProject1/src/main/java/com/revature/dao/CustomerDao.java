package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;

public interface CustomerDao {

	//CRUD
	
	//Create
	boolean insertCustomer(Customer c);
	
	//Read
	List<Customer> selectAllCustomers();
	List<Customer> selectAllApproved();
	List<Customer> selectAllUnapproved();
	Customer selectCustomerByCustomerId(int id);
	Customer selectCustomerByAccountName(String accountName);
	Customer selectCustomerByLogin(String accountName, String accountPassword);
	
	//Update
	boolean updateCustomerApproval(boolean b,int customerId, Employee e);
	boolean updateCustomer(Customer c);
	
	//Delete
	boolean deleteCustomer(Customer c);
}
