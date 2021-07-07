package com.revature;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Customer;
import com.revature.presentation.BankTerminal;
import com.revature.service.BankManager;
import com.revature.service.BankManagerImpl;

public class MainDriver {

	public static void main(String[] ptoatot) {
		CustomerDaoImpl cDao = new CustomerDaoImpl();
		AccountDaoImpl aDao = new AccountDaoImpl();
		BankManagerImpl bM = new BankManagerImpl(cDao,aDao);
		BankTerminal bT = new BankTerminal(bM);
//		if(	cDao.selectCustomerByLogin("Jeremy11", "123Pass") == null) {
//			System.out.println("There are no accounts that match!");
//		}
		bT.displayWelcomeMenu();
//		bT.testMenu();
	}
	
	
//	CustomerDao cDao = new CustomerDaoImpl();
//	AccountDao aDao = new AccountDaoImpl();
////	Account a = new Account();
////	a.setAccountNumber(12345);
////	a.setCustomerId(1);
////	a.setAccountBalance(2.41);
////	a.setAccountType("CHECKING");
////	a.setAccountApproved(false);
//	
////	aDao.insertAccount(a);
////	aDao.selectAllAccounts();
////	aDao.selectAllApproved();
////	aDao.selectAllUnapproved();
//	aDao.selectByAccountType("CHECKING");
//	aDao.selectByAccountType("SAVING");
//	
//	Customer c = new Customer();
////	c.setAccountName("Jeremy12");
////	c.setAccountPassword("123Pass");
////	c.setFirstName("Jeremy");
////	c.setEmail("test123@test.db.com");
////	c.setDateOfBirth("1999-02-05");
////	
////	cDao.insertCustomer(c);
////	cDao.selectCustomerByCustomerId(1);
////	cDao.selectCustomerByAccountName("Jeremy12");
////	cDao.selectAllCustomers();
////	if(cDao.selectAllApproved().size() == 0) {
////		System.out.println("There are none that have been approved!");
////	}
////	cDao.selectAllUnapproved();
////	cDao.updateCustomerApproval(true, 1);
//
//	if(	cDao.selectCustomerByLogin("Jeremy11", "123Pass") == null) {
//		System.out.println("There are no accounts that match!");
//	}
}
