package com.revature;


import org.apache.log4j.Logger;

import com.revature.controller.AccountControllerImpl;
import com.revature.controller.AuthControllerImpl;


import io.javalin.Javalin;

public class MainDriver {
	
	private static final Logger loggy=Logger.getLogger("GLOBAL");
	private static final String CUSTOMER_LOGIN_PATH="/customerLogin";
	private static final String EMPLOYEE_LOGIN_PATH="/employeeLogin";
	private static AuthControllerImpl authController = new AuthControllerImpl();
	private static AccountControllerImpl accController = new AccountControllerImpl();

	public static void main(String[] ptoatot) {
		
		
		Javalin app = Javalin.create(
				config -> {
					config.addStaticFiles("/public");
				}
			).start(8080);
		
		app.post(CUSTOMER_LOGIN_PATH, ctx -> authController.customerLogin(ctx));
		app.post(EMPLOYEE_LOGIN_PATH, ctx -> authController.employeeLogin(ctx));
		app.post("/applyAccount", ctx -> accController.applyForAccount(ctx));
		app.post("/createCustomer", ctx -> authController.createCustomerAccount(ctx));
		app.post("/customerApproval",ctx -> authController.updateCustomerApproval(ctx));
		app.post("/accountApproval", ctx -> accController.updateAccountApproval(ctx));
		
		
		
		app.get("/logout", ctx -> authController.logout(ctx));
		app.get("/account", ctx -> accController.getCustomerAccounts(ctx));
		app.get("/allUnapprovedAccounts", ctx -> accController.getAllUnapprovedAccounts(ctx));
		app.get("/allUnapprovedCustomerAccounts", ctx -> authController.getAllUnaprovedCustomers(ctx));
		
		
		app.put("/accountwithdrawl", ctx -> accController.withdrawAccount(ctx));
		app.put("/accountdeposit", ctx -> accController.depositAccount(ctx));
		app.put("/accounttransfer", ctx -> accController.transferAccount(ctx));

//		
//		app.get(PLANET_PATH, ctx -> planetController.getAllPlanets(ctx));
//		app.post(PLANET_PATH, ctx -> planetController.postPlanet(ctx));
//		app.post("/deletePlanet", ctx -> planetController.deletePlanet(ctx));
//		
//		app.put(PLANET_PATH, ctx -> planetController.updatePlanet(ctx));
//		CustomerDaoImpl cDao = new CustomerDaoImpl();
//		AccountDaoImpl aDao = new AccountDaoImpl();
//		EmployeeDaoImpl eDao= new EmployeeDaoImpl();
//		
//		TransactionHandler tH= new TransactionHandler(aDao);
//		CustomerHandler cH = new CustomerHandler(cDao);
//		AccountHandler aH = new AccountHandler(aDao);
//		EmployeeHandler eH = new EmployeeHandler(eDao);
//		
//		BankTerminal bT = new BankTerminal(tH,cH,aH,eH);
//
//		bT.displayWelcomeMenu();
	}
	
	

}
