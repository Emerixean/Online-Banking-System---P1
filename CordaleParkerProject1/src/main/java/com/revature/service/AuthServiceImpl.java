package com.revature.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.models.Customer;
import com.revature.models.Employee;

import io.javalin.http.Context;

public class AuthServiceImpl {
	private CustomerHandler custHandler = new CustomerHandler();
	private EmployeeHandler empHandler = new EmployeeHandler();
	private static Map<String, String> tokenRepo = new HashMap<>();
	private static byte[] salt = new SecureRandom().getSeed(16);
	
	public boolean authenticateCustomer(String accountName, String accountPassword) {
		

		return custHandler.authenticateCustomer(accountName, accountPassword);
	}
	
	public boolean authenticateEmployee(String accountName, String accountPassword) {
		
		return empHandler.authenticateEmployee(accountName, accountPassword);
	}
	
	public Customer retrieveCustomerInformation(String accountName, String accountPassword) {
		

		return custHandler.retrieveCustomerInformation(accountName, accountPassword);
	}
	
	public Customer retrieveCustomerById(int id) {
		

		return custHandler.retrieveCustomer(id);
	}
	
	public Employee retrieveEmployeeInformation(String accountName, String accountPassword) {
		return empHandler.retrieveEmployeeInformation(accountName, accountPassword);
	}
	
	public Employee retrieveEmployeeById(int id) {
		return empHandler.retrieveEmployeeById(id);
	}
	
	public String createToken(String username) {

		String token = simpleHash(username);

		tokenRepo.put(token, username);

		
		return token;
	} 
	
	private String simpleHash(String username) {
		String hash = null;
		
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
			
			md.update(salt);
			
			byte[] bytes = md.digest(username.getBytes());
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
			}
			
			hash = sb.toString();
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return hash;
	}
	
	public boolean validateToken(String token) {
		return tokenRepo.containsKey(token);
	}
	
	public void createNewCustomer(Context ctx) {
		custHandler.makeNewCustomerLogin(ctx);
	}
	
	public List<Customer> getAllUnaprovedCustomers() {
	return custHandler.retrieveAllUnapprovedCustomers();
	}
	
	public void updateCustomerApproval(Context ctx,Customer c, Employee e) {

		boolean approval = Boolean.valueOf(ctx.formParam("type"));
		
		if(approval == true) {
			System.out.println("approval recognized");
			custHandler.approveCustomer(c, e);
			ctx.status(200);
			ctx.redirect("employee-portal.html");
			
		}else if(approval == false) {
			custHandler.denyCustomer(c, e);
			ctx.status(200);
			ctx.redirect("employee-portal.html");

		}else {
			System.out.println("true or false block fails");
			ctx.status(500);
			ctx.redirect("employee-portal.html");
		}
		
	}
}
