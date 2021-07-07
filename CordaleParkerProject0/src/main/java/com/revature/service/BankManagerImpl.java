package com.revature.service;

import java.sql.Date;
import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Account;
import com.revature.models.Customer;

public class BankManagerImpl {

	CustomerDaoImpl cDao;
	AccountDaoImpl aDao;


	
	
	public BankManagerImpl(CustomerDaoImpl cDao,AccountDaoImpl aDao) {
		this.cDao = cDao;
		this.aDao = aDao;
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
			
			e.printStackTrace();
			return false;
		}
		return userExists;
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
	public Customer retrieveCustomerInformation(String accountName, String accountPassword) {
		

		Customer c = null;
		
		try {
			 c =this.cDao.selectCustomerByLogin(accountName, accountPassword);
		}catch(Exception e) {
			
			e.printStackTrace();
		}

		return c;
	}

	public List<Account> retrieveCustomerAccounts(int id){
		return aDao.selectAccountByCustomerId(id);
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
	
	public boolean checkNumberValid(String number) {
		double input;
		if(number.trim() == null) {
			return false;
		}
		
		
		try{
			 input = Double.parseDouble(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		if(input < 0 ) {
			return false;
		}
		return true;
	}

	public void applyForAccount(String balance, String type, int id) {
		boolean accountCreated = false;
		Account newAccount;
		
		while(accountCreated == false) {
		newAccount = new Account();
		newAccount.setAccountNumber((int)(Math.random()* 1000000));
		newAccount.setAccountBalance(Double.parseDouble(balance));
		newAccount.setCustomerId(id);
		newAccount.setAccountApproved(false);
		newAccount.setApprovedBy(null);
		if(type.equals("1")) {
			newAccount.setAccountType("SAVINGS");
		} else if (type.equals("2")) {
			newAccount.setAccountType("CHECKINGS");
			}
		System.out.println(newAccount);
		 aDao.insertAccount(newAccount);
		}
	}

	public boolean depositCustomerAccount(String deposit,Account a) {
		if(a.isAccountApproved()) {
		double oldBalance = a.getAccountBalance();

		double newBalance = oldBalance + Double.parseDouble(deposit);
		String accountNumber = String.valueOf(a.getAccountNumber());

		aDao.updateAccountBalance(newBalance,accountNumber);
		return true;
		}
		return false;
	}
	public boolean withdrawCustomerAccount(String withdraw,Account a) {
		
		if(a.isAccountApproved()) {
		double oldBalance = a.getAccountBalance();
		
		if(oldBalance < Double.parseDouble(withdraw)) {
			return false;
		}

		double newBalance = oldBalance - Double.parseDouble(withdraw);
		String accountNumber = String.valueOf(a.getAccountNumber());

		aDao.updateAccountBalance(newBalance,accountNumber);
		return true;
		}
		return false;
	}
	
	public boolean makeTransfer(Account a, String accountNumber, String amount) {
		Account b = aDao.selectAccountByAccountNumber(accountNumber);
		double transferAmount = Double.parseDouble(amount);
		if(b == null) {
			return false;
		}
		if(a.getAccountBalance()< Double.parseDouble(amount)) {
			return false;
		}
		if(a.isAccountApproved() == false|| b.isAccountApproved() == false) {
			return false;
		}
		if(a.isAccountApproved()&& b.isAccountApproved()) {
			double oldBalanceA = a.getAccountBalance();
			double newBalanceA = oldBalanceA - Double.parseDouble(amount);
			a.setAccountBalance(newBalanceA);
			
			double oldBalanceB = b.getAccountBalance();
			double newBalanceB = oldBalanceB + Double.parseDouble(amount);
			String aAccountNumber = String.valueOf(a.getAccountNumber());
			String bAccountNumber = String.valueOf(b.getAccountNumber());
			
			aDao.updateAccountBalance(newBalanceA, aAccountNumber);
			aDao.updateAccountBalance(newBalanceB, bAccountNumber);
			return true;
		}
		
		return false;
	}
}
