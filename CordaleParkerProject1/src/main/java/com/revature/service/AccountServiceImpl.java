package com.revature.service;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Employee;

import io.javalin.http.Context;

public class AccountServiceImpl implements AccountService {
	private AccountHandler accHandler = new AccountHandler();
	private TransactionHandler tranHandler = new TransactionHandler();
	private EmployeeHandler empHandler = new EmployeeHandler();
	
	@Override
	public List<Account> retrieveCustomerAccounts(int id){
		return accHandler.retrieveCustomerAccounts(id);
	}
	
	@Override
	public List<Account> retrieveAllUnapprovedAccounts(){
		return accHandler.retrieveAllUnapprovedAccounts();
	}
	
	@Override
	public boolean checkNumberValid(String amount){
		return tranHandler.checkNumberValid(amount);
	}
	
	@Override
	public Account retrieveAccount(String accountNumber){
		return accHandler.retrieveAccount(accountNumber);
	}
	
	@Override
	public Employee retrieveEmployeeById(int id) {
		return empHandler.retrieveEmployeeById(id);
	}
	
	@Override
	public void withdrawCustomerAccount(Context ctx, String amount,Account a) {
		 tranHandler.withdrawCustomerAccount(ctx, amount, a);
	}
	
	@Override
	public void depositCustomerAccount(Context ctx, String amount,Account a) {
		tranHandler.depositCustomerAccount(ctx, amount, a);
	}
	
	@Override
	public void makeTransfer(Context ctx,Account a, String b, String amount) {
		tranHandler.makeTransfer(ctx, a, b, amount);
	}
	
	@Override
	public void applyForAccount(Context ctx) {
		accHandler.applyForAccount(ctx);
	}
	
	@Override
	public void approveAccount(Account a,Employee e) {
		accHandler.approveAccount(a, e);
	}
	
	@Override
	public void denyAccount(Account a,Employee e) {
		System.out.println("deny called from controller");
		accHandler.denyAccount(a, e);
	}
}
