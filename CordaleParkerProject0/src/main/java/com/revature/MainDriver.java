package com.revature;


import org.apache.log4j.Logger;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.presentation.BankTerminal;
import com.revature.service.AccountHandler;
import com.revature.service.EmployeeHandler;
import com.revature.service.CustomerHandler;
import com.revature.service.TransactionHandler;

public class MainDriver {
	
	private static final Logger loggy=Logger.getLogger("GLOBAL");

	public static void main(String[] ptoatot) {
		
		
		CustomerDaoImpl cDao = new CustomerDaoImpl();
		AccountDaoImpl aDao = new AccountDaoImpl();
		EmployeeDaoImpl eDao= new EmployeeDaoImpl();
		
		TransactionHandler tH= new TransactionHandler(aDao);
		CustomerHandler cH = new CustomerHandler(cDao);
		AccountHandler aH = new AccountHandler(aDao);
		EmployeeHandler eH = new EmployeeHandler(eDao);
		
		BankTerminal bT = new BankTerminal(tH,cH,aH,eH);

		bT.displayWelcomeMenu();
	}
	
	

}
