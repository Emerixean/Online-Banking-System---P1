package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Account;

import io.javalin.http.Context;

public class TransactionHandler {

	private AccountDaoImpl aDao;
	
	final static Logger loggy = Logger.getLogger("GLOBAL");
	
	public TransactionHandler() {
		this.aDao = new AccountDaoImpl();;

	}
	

	
	public TransactionHandler(AccountDaoImpl aDao) {
		this.aDao = aDao;
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
	public boolean makeTransfer(Context ctx,Account a, String accountNumber, String amount) {
		if(a == null) {
			ctx.status(404);
			return false;
			
		}
		Account b = aDao.selectAccountByAccountNumber(accountNumber);
		
		if(b == null) {
			ctx.status(404);
			return false;
		}
		
		if(a.getCustomerId() != (Integer)ctx.cookieStore("customerId")) {
			ctx.status(405);
			return false;
		}
		
		double transferAmount = Double.parseDouble(amount);

		if(a.getAccountBalance()< transferAmount) {
			ctx.status(406);
			loggy.warn("Account number " + a.getAccountNumber() + " tried to transfer without enough funds"+ "\n");
			return false;
		}
		
		if(a.isAccountApproved() == false|| b.isAccountApproved() == false) {
			ctx.status(405);
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
			
			ctx.status(200);
			return true;
		}
		
		return false;
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
			loggy.warn("Account number " + a.getAccountNumber() + " tried to transfer without enough funds"+ "\n");
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
	
	public boolean withdrawCustomerAccount(Context ctx, String withdraw,Account a) {
		if(a == null) {
			ctx.status(404);
			return false;
			
		}
		
		if(a.getCustomerId() != (Integer)ctx.cookieStore("customerId")) {
			ctx.status(405);
			return false;
		}
		
		if(a.isAccountApproved()) {
		double oldBalance = a.getAccountBalance();
		
		if(oldBalance < Double.parseDouble(withdraw)) {
			ctx.status(406);
			return false;
		}

		double newBalance = oldBalance - Double.parseDouble(withdraw);
		String accountNumber = String.valueOf(a.getAccountNumber());

		aDao.updateAccountBalance(newBalance,accountNumber);
		loggy.info("Account number " + a.getAccountNumber() + " made a withdrawal of : " + withdraw + "\n" );
		
		ctx.status(200);
		return true;
		}
		ctx.status(405);
		return false;
	}
	
	public boolean depositCustomerAccount(Context ctx,String deposit,Account a) {
		if(a == null) {
			ctx.status(404);
			return false;
			
		}
		
		if(a.getCustomerId() != (Integer)ctx.cookieStore("customerId")) {
			ctx.status(405);
			return false;
		}
		
		if(a.isAccountApproved()) {
		double oldBalance = a.getAccountBalance();

		double newBalance = oldBalance + Double.parseDouble(deposit);
		String accountNumber = String.valueOf(a.getAccountNumber());

		aDao.updateAccountBalance(newBalance,accountNumber);
		loggy.info("Account number " + a.getAccountNumber() + " made a deposit of : " + deposit + "\n" );
		
		ctx.status(200);
		return true;
		}
		ctx.status(405);
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
