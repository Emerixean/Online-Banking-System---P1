package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.service.AuthServiceImpl;
import com.revature.service.CustomerHandler;
import com.revature.service.EmployeeHandler;

import io.javalin.http.Context;

public class AuthControllerImpl {

private AuthServiceImpl authService = new AuthServiceImpl(); 
	
	
	public void customerLogin(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(authService.authenticateCustomer(username, password)) {
			Customer c =authService.retrieveCustomerInformation(username, password);
			ctx.status(200);

			ctx.cookieStore("user", authService.createToken(username));
			ctx.cookieStore("customerId",c.getId());

			ctx.cookieStore("approved",c.isAccountApproved());


			
			ctx.redirect("accounts-home.html");
		}else {
			ctx.status(407);
			ctx.redirect("customer-login.html");
		}
		
	}
	
	public void employeeLogin(Context ctx) {
 
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(authService.authenticateEmployee(username, password)) {
			Employee e =authService.retrieveEmployeeInformation(username, password);
			
			ctx.status(200);
			
			ctx.cookieStore("user",authService.createToken(username));
			ctx.cookieStore("id",e.getId());
			
			ctx.redirect("employee-portal.html");
			//if user doesn't exists you'd set it to 407 
			
			
		}else {
			
			ctx.status(407);
			ctx.redirect("employee-login.html");
		}
	}
	
	public void logout(Context ctx) {
		ctx.clearCookieStore();
		ctx.redirect("welcome-menu.html");
		
	}
	
	public boolean checkUser(Context ctx) {
		if(authService.validateToken(ctx.cookieStore("user"))){
			return true;
		}else {

		return false;
		}
		

	}
	public void createCustomerAccount(Context ctx) {
		authService.createNewCustomer(ctx);
	}
	
	public void getAllUnaprovedCustomers(Context ctx) {
		if(this.checkUser(ctx)) {

			ctx.status(200);
			List<Customer> accList = new ArrayList<>();
			accList = authService.getAllUnaprovedCustomers();

			ctx.json(accList);

		}else {


			ctx.redirect("welcome-menu.html",401);
		}
	}
	
	public void updateCustomerApproval(Context ctx) {
		if(this.checkUser(ctx)) {
			Employee e = authService.retrieveEmployeeById(ctx.cookieStore("id"));
			Customer c = authService.retrieveCustomerById(Integer.valueOf(ctx.formParam("customerId")));
			ctx.status(200);

			authService.updateCustomerApproval(ctx,c,e);

		}else {


			ctx.redirect("welcome-menu.html",401);
		}
	}
}
