package com.revature.service;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Employee;

import io.javalin.http.Context;

public class AccountServiceImpl {
	private AccountHandler accHandler = new AccountHandler();
	private TransactionHandler tranHandler = new TransactionHandler();
	private EmployeeHandler empHandler = new EmployeeHandler();
	
	public List<Account> retrieveCustomerAccounts(int id){
		return accHandler.retrieveCustomerAccounts(id);
	}
	
	public List<Account> retrieveAllUnapprovedAccounts(){
		return accHandler.retrieveAllUnapprovedAccounts();
	}
	
	public boolean checkNumberValid(String amount){
		return tranHandler.checkNumberValid(amount);
	}
	
	public Account retrieveAccount(String accountNumber){
		return accHandler.retrieveAccount(accountNumber);
	}
	
	public Employee retrieveEmployeeById(int id) {
		return empHandler.retrieveEmployeeById(id);
	}
	
	public void withdrawCustomerAccount(Context ctx, String amount,Account a) {
		 tranHandler.withdrawCustomerAccount(ctx, amount, a);
	}
	
	public void depositCustomerAccount(Context ctx, String amount,Account a) {
		tranHandler.depositCustomerAccount(ctx, amount, a);
	}
	
	public void makeTransfer(Context ctx,Account a, String b, String amount) {
		tranHandler.makeTransfer(ctx, a, b, amount);
	}
	
	public void applyForAccount(Context ctx) {
		accHandler.applyForAccount(ctx);
	}
	
	public void approveAccount(Account a,Employee e) {
		accHandler.approveAccount(a, e);
	}
	
	public void denyAccount(Account a,Employee e) {
		accHandler.denyAccount(a, e);
	}
}
