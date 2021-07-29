package com.revature.controller;

import io.javalin.http.Context;

public interface AuthController {
		
	public void customerLogin(Context ctx);
			
	public void employeeLogin(Context ctx);
			
	public void logout(Context ctx);
			
	public boolean checkUser(Context ctx);
			
	public void createCustomerAccount(Context ctx);
			
	public void getAllUnaprovedCustomers(Context ctx);
			
	public void updateCustomerApproval(Context ctx);
}
