package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDaoImpl;
import com.revature.models.Account;

public class TransactionHandler {

	AccountDaoImpl aDao;
	
	final static Logger loggy = Logger.getLogger("GLOBAL");
	
	public TransactionHandler(AccountDaoImpl aDao) {
		this.aDao = aDao;

	}
	

	public boolean makeTransfer(Account a, String accountNumber, String amount) {
		if(a == null) {
			return false;
		}
		Account b = aDao.selectAccountByAccountNumber(accountNumber);
		
		if(b == null) {
			return false;
		}
		double transferAmount = Double.parseDouble(amount);

		if(a.getAccountBalance()< transferAmount) {
			return false;
		}
		if(a.isAccountApproved() == false|| b.isAccountApproved() == false) {
			return false;
		}
		if(a.isAccountApproved()&& b.isAccountApproved()) {
			double oldBalanceA = a.getAccountBalance();
			double newBalanceA = oldBalanceA - transferAmount;
			a.setAccountBalance(newBalanceA);
			
			double oldBalanceB = b.getAccountBalance();
			double newBalanceB = oldBalanceB + transferAmount;
			String aAccountNumber = String.valueOf(a.getAccountNumber());
			String bAccountNumber = String.valueOf(b.getAccountNumber());
			
			aDao.updateAccountBalance(newBalanceA, aAccountNumber);
			aDao.updateAccountBalance(newBalanceB, bAccountNumber);
			loggy.info("Account number " + a.getAccountNumber() + " made a transfer to account number "
					+ b.getAccountNumber() + " for a total value of: " + transferAmount + "\n");
			return true;
		}
		
		return false;
	}
	
	public boolean withdrawCustomerAccount(String withdraw,Account a) {
		if(a == null) {
			return false;
		}
		
		if(a.isAccountApproved()) {
		double oldBalance = a.getAccountBalance();
		
		if(oldBalance < Double.parseDouble(withdraw)) {
			return false;
		}

		double newBalance = oldBalance - Double.parseDouble(withdraw);
		String accountNumber = String.valueOf(a.getAccountNumber());

		aDao.updateAccountBalance(newBalance,accountNumber);
		loggy.info("Account number " + a.getAccountNumber() + " made a withdrawal of : " + withdraw + "\n" );
		return true;
		}
		return false;
	}
	
	public boolean depositCustomerAccount(String deposit,Account a) {
		if(a == null) {
			return false;
		}
		if(a.isAccountApproved()) {
		double oldBalance = a.getAccountBalance();

		double newBalance = oldBalance + Double.parseDouble(deposit);
		String accountNumber = String.valueOf(a.getAccountNumber());

		aDao.updateAccountBalance(newBalance,accountNumber);
		loggy.info("Account number " + a.getAccountNumber() + " made a deposit of : " + deposit + "\n" );
		return true;
		}
		return false;
	}
	
	public boolean checkNumberValid(String number) {
		double input;
		if(number.trim() == null) {
			return false;
		}
		
		
		try{
			 input = Double.parseDouble(number);
		}catch(NumberFormatException e) {
			return false;
		}
		if(input < 0 ) {
			return false;
		}
		return true;
	}
}
