package com.revature.controller;

import io.javalin.http.Context;

public interface AccountController {
	
		
	public void getCustomerAccounts(Context ctx);
			
	public void getAllUnapprovedAccounts(Context ctx);
			
	public void withdrawAccount(Context ctx);
			
	public void depositAccount(Context ctx);
			
	public void transferAccount(Context ctx);
	
	public void applyForAccount(Context ctx);
			
	public void updateAccountApproval(Context ctx);

}
