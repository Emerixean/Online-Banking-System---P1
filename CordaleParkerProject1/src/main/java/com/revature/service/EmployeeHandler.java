package com.revature.service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeHandler  {

	private EmployeeDaoImpl eDao;
	
	private Map<String, String> tokenRepo = new HashMap<>();
	
	private static byte[] salt = new SecureRandom().getSeed(16);


	final static Logger loggy = Logger.getLogger("GLOBAL");
	
	public EmployeeHandler() {


		this.eDao = new EmployeeDaoImpl();
	}

	
	public EmployeeHandler(EmployeeDaoImpl eDao) {
		this.eDao = eDao;
	}



	
	public boolean authenticateEmployee(String accountName, String accountPassword) {
		
		boolean userExists = false;
		
		try {
			Employee c = this.eDao.selectEmployeeByLogin(accountName, accountPassword);
			
			if (c == null){
				return false;
			}else {
			userExists = true;
			}
		}catch(Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return userExists;
	}

	
	public Employee retrieveEmployeeInformation(String accountName, String accountPassword) {
		

		Employee e = null;
		
		try {
			 e =this.eDao.selectEmployeeByLogin(accountName, accountPassword);
		}catch(Exception exception) {
			
			exception.printStackTrace();
		}

		return e;
	}
	
	public Employee retrieveEmployeeById(int id) {
		Employee e = null;
		
		try {
			 e =this.eDao.selectEmployeeByEmployeeId(id);
		}catch(Exception exception) {
			
			exception.printStackTrace();
		}

		return e;
	}

	public String createToken(String username) {

		String token = simpleHash(username);
		tokenRepo.put(token, username);
		
		return token;
	} 
	
	public boolean validateToken(String token) {

		return tokenRepo.containsKey(token);
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
}
