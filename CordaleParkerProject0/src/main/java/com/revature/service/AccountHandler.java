package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDaoImpl;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;



public class AccountHandler {

	
	AccountDaoImpl aDao;
	
	final static Logger loggy = Logger.getLogger("GLOBAL");
	
	public AccountHandler(AccountDaoImpl aDao) {
		this.aDao = aDao;
	}
	
	
	public Account retrieveCustomerAccount(String accountNumber , Customer c) {
		Account account = aDao.selectAccountByAccountNumber(accountNumber);
		
		if(account == null) {
			return null;
		}
		if(account.getCustomerId() != c.getId()) {
			return null;
		}
		
		return aDao.selectAccountByAccountNumber(accountNumber);
	}
	
	public List<Account> retrieveCustomerAccounts(int id){
		return aDao.selectAccountByCustomerId(id);
	}
	
	public boolean applyForAccount(String balance, String type, Customer c) {
		boolean accountCreated = false;
		Account newAccount;
		if(c.isAccountApproved() == false) {
			return false;
		}
		while(accountCreated == false) {
		newAccount = new Account();
		newAccount.setAccountNumber((int)(Math.random()* 1000000));
		newAccount.setAccountBalance(Double.parseDouble(balance));
		newAccount.setCustomerId(c.getId());
		newAccount.setAccountApproved(false);
		newAccount.setApprovedBy(null);
		if(type.equals("1")) {
			newAccount.setAccountType("SAVINGS");
		} else if (type.equals("2")) {
			newAccount.setAccountType("CHECKINGS");
			}
		System.out.println(newAccount);
		if( aDao.insertAccount(newAccount) == true) {
			accountCreated = true;
		}
		}
		return true;
	}
	

	public Account retrieveAccount(String accountNumber) {
		Account account = aDao.selectAccountByAccountNumber(accountNumber);
		
		if(account == null) {
			return null;
		}
		
		return aDao.selectAccountByAccountNumber(accountNumber);
	}
	
	public boolean denyAccount(Account a, Employee employee) {
		if(a ==null) {
			return false;
		}
		String accountNumber = String.valueOf(a.getAccountNumber());
		try {
			aDao.updateAccountApproval(false, accountNumber, employee);
			
			loggy.info(employee.getFirstName() + " denied customer account" + a.getAccountNumber() +" \n" );
			return true;
		}catch(Exception e) {
			return false;
			}
		}
	
	public boolean approveAccount(Account a, Employee employee) {
		if(a ==null) {
			return false;
		}
		String accountNumber = String.valueOf(a.getAccountNumber());
		try {
			aDao.updateAccountApproval(true, accountNumber, employee);
			
			loggy.info(employee.getFirstName() + " approved customer account" + a.getAccountNumber() +" \n" );
			return true;
		}catch(Exception e) {
			return false;
			}
		}
	
}
