package com.revature.service;


import org.apache.log4j.Logger;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeHandler  {

	EmployeeDaoImpl eDao;


	final static Logger loggy = Logger.getLogger("GLOBAL");
	
	public EmployeeHandler( EmployeeDaoImpl eDao) {


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

}
