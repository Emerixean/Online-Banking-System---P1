package com.revature.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Customer;
import com.revature.models.Employee;

import io.javalin.http.Context;

public class CustomerHandler {

	private CustomerDaoImpl cDao;
;
	
	final static Logger loggy = Logger.getLogger("GLOBAL");
	

	
	
	
	public CustomerHandler() {
		this.cDao = new CustomerDaoImpl();
	}
	
	public CustomerHandler(CustomerDaoImpl cDao) {
		this.cDao = cDao;
	}
	
	public boolean makeNewCustomerLogin(Context ctx){
		Customer newCustomer = new Customer();
		
		newCustomer.setAccountName(ctx.formParam("username"));
		newCustomer.setAccountPassword(ctx.formParam("password"));
		newCustomer.setFirstName(ctx.formParam("firstName"));
		newCustomer.setLastName(ctx.formParam("lastName"));
		newCustomer.setPhoneNumber(ctx.formParam("phoneNumber"));
		newCustomer.setEmail(ctx.formParam("email"));
		newCustomer.setDateOfBirth(ctx.formParam("dateOfBirth"));
		newCustomer.setAddress(ctx.formParam("address"));
		newCustomer.setApartment(ctx.formParam("apartment"));
		newCustomer.setCity(ctx.formParam("city"));
		newCustomer.setState(ctx.formParam("state"));
		newCustomer.setCountry(ctx.formParam("country"));
		newCustomer.setPostalCode(ctx.formParam("postalCode"));
		try {
			cDao.insertCustomer(newCustomer);
		}catch(Exception e) {
			
			return false;
		}
		ctx.redirect("welcome-menu.html");
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
	
	public List<Customer> retrieveAllUnapprovedCustomers(){
		List<Customer> databaseCustomers = new ArrayList<>();
		
		try {
			databaseCustomers = this.cDao.selectAllUnapproved();	
		} catch(Exception e) {
			
		}
		return databaseCustomers;
		
	}
	

	

	

}
