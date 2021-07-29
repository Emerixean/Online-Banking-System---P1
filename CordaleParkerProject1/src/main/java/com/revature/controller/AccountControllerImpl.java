package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transact;
import com.revature.service.AccountHandler;
import com.revature.service.AccountServiceImpl;
import com.revature.service.EmployeeHandler;
import com.revature.service.TransactionHandler;

import io.javalin.http.Context;

public class AccountControllerImpl implements AccountController {

	private AuthControllerImpl authController = new AuthControllerImpl();
	private AccountServiceImpl accService = new AccountServiceImpl();

	@Override
	public void getCustomerAccounts(Context ctx) {
		if(authController.checkUser(ctx)) {

			ctx.status(200);
			List<Account> accList = new ArrayList<>();
			accList = accService.retrieveCustomerAccounts(ctx.cookieStore("customerId"));

			ctx.json(accList);

		}else {


			ctx.redirect("welcome-menu.html",401);
		}
	}
	
	@Override
	public void getAllUnapprovedAccounts(Context ctx){
	if(authController.checkUser(ctx)) {

		ctx.status(200);
		List<Account> accList = new ArrayList<>();
		accList = accService.retrieveAllUnapprovedAccounts();

		ctx.json(accList);

	}else {


		ctx.redirect("welcome-menu.html",401);
	}
}
	
	@Override
	public void withdrawAccount(Context ctx) {
		if(authController.checkUser(ctx)) {
		ObjectMapper om = new ObjectMapper();

		 
		Transact t;
		try {
			t = om.readValue(ctx.body(), Transact.class);
			
			double amount = t.getAmount();
			if(accService.checkNumberValid(String.valueOf(amount))== true) {
				Account a = accService.retrieveAccount(String.valueOf(t.getAccountNumber()));
				
				accService.withdrawCustomerAccount(ctx, String.valueOf(amount), a);
			}
			

		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		}else {


			ctx.redirect("welcome-menu.html",401);
		}
	}
	
	@Override
	public void depositAccount(Context ctx) {
		if(authController.checkUser(ctx)) {
		ObjectMapper om = new ObjectMapper();

		 
		Transact t;
		try {
			t = om.readValue(ctx.body(), Transact.class);
			
			double amount = t.getAmount();
			
			if(accService.checkNumberValid(String.valueOf(amount))== true) {
			Account a = accService.retrieveAccount(String.valueOf(t.getAccountNumber()));
			
			accService.depositCustomerAccount(ctx, String.valueOf(amount), a);
			
			}
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}else {


		ctx.redirect("welcome-menu.html",401);
	}
	}
	
	@Override
	public void transferAccount(Context ctx) {
		if(authController.checkUser(ctx)) {
		ObjectMapper om = new ObjectMapper();	
		
		Transact t;
		try {
			t = om.readValue(ctx.body(), Transact.class);
			
			double amount = t.getAmount();
			
			if(accService.checkNumberValid(String.valueOf(amount))== true) {
			Account a = accService.retrieveAccount(String.valueOf(t.getAccountNumber()));
			String b = String.valueOf(t.getAccountNumber2());
			accService.makeTransfer(ctx,a, b, String.valueOf(amount));
			
			}}catch(JsonProcessingException e) {
				e.printStackTrace();
			}
		
		}else {


			ctx.redirect("welcome-menu.html",401);
		}
		

	}
	
	@Override
	public void applyForAccount(Context ctx) {
		if(accService.checkNumberValid((String)ctx.formParam("balance")) == false){
			ctx.status(406);

		}else { accService.applyForAccount(ctx);}
	}
	
	@Override
	public void updateAccountApproval(Context ctx) {
		if(authController.checkUser(ctx)) {
			
			Employee e = accService.retrieveEmployeeById(ctx.cookieStore("id"));
			Account a = accService.retrieveAccount(ctx.formParam("accountNumber"));
			
			boolean approval = Boolean.valueOf(ctx.formParam("type"));

			if(approval == true) {
				accService.approveAccount(a, e);
				ctx.status(200);
				ctx.redirect("employee-portal.html");
				
			}else if(approval == false) {
				System.out.println("Deny was recognized");
				accService.denyAccount(a, e);
				ctx.status(200);
				ctx.redirect("employee-portal.html");

			}else {
				ctx.status(500);
				ctx.redirect("employee-portal.html");
			}
		}else {
			ctx.status(405);
			ctx.redirect("welcome-menu.html");
	}
	}
}
