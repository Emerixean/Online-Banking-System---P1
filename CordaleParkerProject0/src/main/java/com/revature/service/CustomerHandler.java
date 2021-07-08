package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Customer;
import com.revature.models.Employee;

public class CustomerHandler {

	CustomerDaoImpl cDao;
	
	final static Logger loggy = Logger.getLogger("GLOBAL");
	
	public CustomerHandler(CustomerDaoImpl cDao) {
		this.cDao = cDao;

	}
	
	public boolean makeNewCustomerLogin(String accountName, String accountPassword,String firstName,
			String lastName,String phoneNumber,String email,String dateOfBirth,
			String address,String apartment,String city,String state,String country,String postalCode){
		Customer newCustomer = new Customer();
		
		newCustomer.setAccountName(accountName);
		newCustomer.setAccountPassword(accountPassword);
		newCustomer.setFirstName(firstName);
		newCustomer.setLastName(lastName);
		newCustomer.setPhoneNumber(phoneNumber);
		newCustomer.setEmail(email);
		newCustomer.setDateOfBirth(dateOfBirth);
		newCustomer.setAddress(address);
		newCustomer.setApartment(apartment);
		newCustomer.setCity(city);
		newCustomer.setState(state);
		newCustomer.setCountry(country);
		newCustomer.setPostalCode(postalCode);
		try {
			cDao.insertCustomer(newCustomer);
		}catch(Exception e) {
			
			return false;
		}
		return true;
	}
	
	
	public boolean authenticateCustomer(String accountName, String accountPassword) {
		
		boolean userExists = false;
		
		try {
			Customer c = this.cDao.selectCustomerByLogin(accountName, accountPassword);
			
			if (c == null){
				return false;
			}else {
			userExists = true;
			}
		}catch(Exception e) {
			
			return false;
		}
		return userExists;
	}
	
	public Customer retrieveCustomerInformation(String accountName, String accountPassword) {
		

		Customer c = null;
		
		try {
			 c =this.cDao.selectCustomerByLogin(accountName, accountPassword);
		}catch(Exception e) {
			
			e.printStackTrace();
		}

		return c;
	}
	
	public boolean denyCustomer(Customer c, Employee employee) {
		if(c ==null) {
			return false;
		}
		
		try {
			cDao.deleteCustomer(c);
			
			loggy.info(employee.getFirstName() + " denied customer " + c.getFirstName() + " ID : "+c.getId()+ " \n Customer data purged" );
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			}
		}
	
	
	public boolean approveCustomer(Customer c, Employee employee) {
		if(c ==null) {
			return false;
		}
		try {
			cDao.updateCustomerApproval(true, c.getId(), employee);
			
			loggy.info(employee.getFirstName() + " approved customer " + c.getFirstName() + " ID : "+c.getId()+ " \n" );
			return true;
		}catch(Exception e) {
			return false;
			}
		}

	public Customer retrieveCustomer(int id) {
		Customer customer = null;
		
		try {
			customer = this.cDao.selectCustomerByCustomerId(id);		
		} catch(Exception e) {
			
		}
		return customer;
		
	}
	

	public List<Customer> retrieveAllCustomers(){
		List<Customer> databaseCustomers = new ArrayList<>();
		
		try {
			databaseCustomers = this.cDao.selectAllCustomers();		
		} catch(Exception e) {
			
		}
		return databaseCustomers;
		
	}
}
