package com.revature.service;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Employee;

import io.javalin.http.Context;

public interface AccountService {

		
		public List<Account> retrieveCustomerAccounts(int id);
		
		public List<Account> retrieveAllUnapprovedAccounts();
		
		public boolean checkNumberValid(String amount);
		
		public Account retrieveAccount(String accountNumber);
		
		public Employee retrieveEmployeeById(int id);
		
		public void withdrawCustomerAccount(Context ctx, String amount,Account a);
		
		public void depositCustomerAccount(Context ctx, String amount,Account a);
		
		public void makeTransfer(Context ctx,Account a, String b, String amount);
		
		public void applyForAccount(Context ctx);
		
		public void approveAccount(Account a,Employee e);
		
		public void denyAccount(Account a,Employee e);

}
