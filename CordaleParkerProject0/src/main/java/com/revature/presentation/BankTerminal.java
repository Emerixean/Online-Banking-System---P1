package com.revature.presentation;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.service.AccountHandler;
import com.revature.service.CustomerHandler;
import com.revature.service.EmployeeHandler;
import com.revature.service.TransactionHandler;


public class BankTerminal {
	
	TransactionHandler tH;
	CustomerHandler cH;
	AccountHandler aH;
	EmployeeHandler eH;
	

	Scanner sc = new Scanner(System.in);
	
	
	//Constructor
	public BankTerminal(TransactionHandler tH, CustomerHandler cH, AccountHandler aH, EmployeeHandler eH) {

		this.tH= tH;
		this.cH= cH;
		this.aH= aH;
		this.eH= eH;
	}
	

	
	/*This is the display Menu
	 * All users will end up here when they first interact with the system
	 * After finishing their business they will be returned here again
	 */
	public void displayWelcomeMenu() {
		boolean validInput = false;
		String input = null;
		while(validInput == false) {

		
		System.out.println("Welcome to Revature Bank!\n"
							+ "Press 1 if you are a customer\n"
							+ "Press 2 if you are an employee\n");   //Separate the users from the employee's

		
		input = sc.nextLine();
		
		if(input.equals("1") || input.equals("2")) {
		validInput = true;
			}
		
		else {System.out.println("Please enter a valid option.\n\n");}
		}
		
		switch(input) {
		case "1":
			this.customerWelcomeMenu();
			break;
		case "2":
			this.employeeLoginMenu();
			break;
		default:
			System.out.println("Invalid option recieved. Exiting program!\n\n\n");
		}
	}


	//Customer Menus
	private void customerWelcomeMenu() {
		
		boolean validInput = false;
		String input = null;
		

		

		
		while(validInput == false) {
		
		System.out.println("Thank you for banking with Revature Bank!\n"
				+ "Press 1 if you are an existing customer.\n"
				+ "Press 2 if you want to create a new account. \n");
		
		input = sc.nextLine();
		
		if(input.equals("1") || input.equals("2")) {

		validInput = true;
			}
		else {System.out.println("Please enter a valid option.\n\n");
			}
		}
		
		if(input.equals("1")) {
		this.customerLoginMenu();
		}
		else {
		this.newCustomerMenu();
		}
	}

	
	private void customerLoginMenu() {
		String accountName = null;
		String accountPassword = null;
		String input = null;
		boolean validLogin = false;
		Customer customer;
		

		
		while(validLogin == false) {
		System.out.println("Please input your username: \n");
		
		accountName = sc.nextLine();
		
		System.out.println("Please input your password: \n");
		
		accountPassword = sc.nextLine();
		
		if(this.cH.authenticateCustomer(accountName, accountPassword) == true) {
			
			customer = cH.retrieveCustomerInformation(accountName,accountPassword);
			validLogin = true;
			this.customerActionMenu(customer);
			}else {
				System.out.println("Would you like to try again? (y/n)\n");
				input = sc.nextLine();
				if(input.equalsIgnoreCase("n")) {
					this.displayWelcomeMenu();
				}else if(input.equalsIgnoreCase("y")){
					System.out.println("Try again\n");
				}else {
					System.out.println("Try again\n");
				}
					
			}
				

		}
	}
	
	private void customerActionMenu(Customer c) {
		System.out.println(c);
		String input;
		String accountType;
		boolean userActive = true;
		List<Account> customerAccounts;
		Account bankAccount;
		String transfer;
		
		while(userActive == true) {
		System.out.println("Press 1 to view your accounts\n"
							+ "Press 2 to view your account balance\n"
							+ "Press 3 to apply for a new bank account\n"
							+ "Press 4 to make a deposit\n"
							+ "Press 5 to make a withdraw\n"
							+ "Press 6 to make a transfer to another account\n"
							+ "Press 0 to logout");

			
			input = sc.nextLine();
			
			switch(input) {
			
			case "1":
				customerAccounts= aH.retrieveCustomerAccounts(c.getId());
				
				for(Account a:customerAccounts) {
					System.out.println(a);
				}
				break;
				
			case "2":
				System.out.println("Enter account number to view balance:");
				
				input = sc.nextLine();
				
				if(tH.checkNumberValid(input)) {
					
				bankAccount = aH.retrieveCustomerAccount(input , c);
				if(bankAccount == null) {
					System.out.println("Invalid account selected\n\n");
					break;
				}
				System.out.println("Balance: " +bankAccount.getAccountBalance());
				}
				else {System.out.println("Invalid account selected\n\n");
				}
				break;
				
			case "3":

				System.out.println("Please enter starting balance. (numbers only)\n ");
				input = sc.nextLine();
				if(tH.checkNumberValid(input)) {
					System.out.println("Press 1 for savings\n"
										+ "Press 2 for checkings\n");
					
					accountType = sc.nextLine();
					
					if(accountType.equals("1") || accountType.equals("2")) {
						
						if(aH.applyForAccount(input,accountType,c) == true) {
							System.out.println(" Account was created!\n");
						}else {
							System.out.println("Can not apply for an account until you are approved!\n");
						}
							}
					
						else {System.out.println("Please enter a valid option.");
							}
					}

				break;
				
			case "4":
					System.out.println("Please enter account number");
					input = sc.nextLine();
					
					if(tH.checkNumberValid(input)) {
						
						bankAccount = aH.retrieveCustomerAccount(input, c);
						
						if(bankAccount == null) {
							System.out.println("Could not select account!");
						}else {
						System.out.println("Please enter deposit amount with numbers only: ");
						
						input = sc.nextLine();
						
						if(tH.checkNumberValid(input)) {
							if(tH.depositCustomerAccount(input,bankAccount)== false) {
								System.out.println("Could not make deposit!");
								}
							}
						}
					}
					
				break;
				
			case "5":
				System.out.println("Please enter account number");
				input = sc.nextLine();
				
				if(tH.checkNumberValid(input)) {
					
					bankAccount = aH.retrieveCustomerAccount(input,c);
					
					if(bankAccount ==null) {
						System.out.println("Could not access account!");
					}else {
					System.out.println("Please enter withdrawl amount with numbers only: ");
					
					input = sc.nextLine();
					
					if(tH.checkNumberValid(input)) {
						if (tH.withdrawCustomerAccount(input,bankAccount) == false) {
							System.out.println("Could not make withdraw!");
							}
						}
					}
				}
				
				break;
				
			case "6":
					System.out.println("Please enter the account number you wish to withdraw from ");
					input = sc.nextLine();
					
					if(tH.checkNumberValid(input)) {
						bankAccount = aH.retrieveCustomerAccount(input,c);
						if(bankAccount ==null) {
							System.out.println("Could not access account!");
							break;
						}else {
							System.out.println("Please enter the account number you wish to deposit to ");
							input = sc.nextLine();
							if(tH.checkNumberValid(input)) {
								System.out.println("Enter amount to transfer:");
								transfer = sc.nextLine();
								if(tH.checkNumberValid(transfer)) {
									if(tH.makeTransfer(bankAccount, input, transfer) == false) {
										System.out.println("Transfer could not be processed!\nPlease check if your account is approved, you have enough funds,"
												+ " and you are transferring to a valid account number.");
									}
								}
							}
						}
					}
				break;
				
			case "0":
				userActive = false;
				break;
				
			default:
				System.out.println("Please select a valid option\n");
			}
	
		}
		this.displayWelcomeMenu();
	}

	private void newCustomerMenu() {
			

		String firstName;
		String lastName;
		String accountName = null;
		String accountPassword = null;
		String phoneNumber;
		String email;
		String dateOfBirth;
		String address;
		String apartment;
		String city;
		String state;
		String country;
		String postalCode;
		
		boolean userLoginAvailable = false;
		
		while(userLoginAvailable == false) {
		System.out.println("Please input your username name: ");
		
		accountName = sc.nextLine();
				
		System.out.println("Please input your password: ");
		
		accountPassword = sc.nextLine();
		
		if(cH.authenticateCustomer(accountName, accountPassword)) {
			System.out.println("Username already taken. Try again.");
		}else {
		
		
		System.out.println("Please enter your legal first name");
		
		firstName = sc.nextLine();
		
		System.out.println("Please enter your legal last name");
		
		lastName = sc.nextLine();
		
		System.out.println("Please enter your phone number");
		
		phoneNumber = sc.nextLine();
		
		System.out.println("Please enter your email");
		
		email = sc.nextLine();
		
		System.out.println("Please enter your date of birth YYYY-MM-DD");
		
		dateOfBirth = sc.nextLine();
		
		try {
			Date.valueOf(dateOfBirth);
		}catch(IllegalArgumentException e) {
			
			System.out.println("Please enter a valid date of birth. \n");
			this.displayWelcomeMenu();
		}
		
		System.out.println("Please enter your address (street number and name of street)");
		
		address = sc.nextLine();
		
		System.out.println("Please enter your apartment number (press enter key if not applicable)");
		
		apartment = sc.nextLine();

		System.out.println("Please enter your city of residence");
		
		city = sc.nextLine();
		
		System.out.println("Please enter your state of residence (full name)(press enter key if not applicable)");
		
		state = sc.nextLine();
		
		System.out.println("Please enter your country of residence (full name) ");
		
		country = sc.nextLine();
		
		System.out.println("Please enter your postal code");
		
		postalCode = sc.nextLine();

		
		if(cH.makeNewCustomerLogin(accountName, accountPassword,firstName, lastName, phoneNumber, email, dateOfBirth, address, apartment, city, state, country, postalCode)== false) {
			System.out.println("Could not create user account!");
		}else {
			userLoginAvailable = true;
			System.out.println("Applied for a customer account!");
		}
		this.displayWelcomeMenu();
		}
		
		}
	
	}

	
	//Employee Menus
	private void employeeLoginMenu() {
		String accountName = null;
		String accountPassword = null;
		String input = null;
		boolean validLogin = false;
		Employee employee;
		

		
		while(validLogin == false) {
		System.out.println("Please input your username: ");
		
		accountName = sc.nextLine();
		
		System.out.println("Please input your password: ");
		
		accountPassword = sc.nextLine();
		
		if(this.eH.authenticateEmployee(accountName, accountPassword) == true) {
			
			employee = eH.retrieveEmployeeInformation(accountName,accountPassword);
			validLogin = true;
			this.employeeActionMenu(employee);
			}else {
				System.out.println("Would you like to try again? (y/n)\n");
				input = sc.nextLine();
				if(input.equalsIgnoreCase("n")) {
					this.displayWelcomeMenu();
				}else if(input.equalsIgnoreCase("y")){
					System.out.println("Try again\n");
				}else {
					System.out.println("Try again\n");
				}
			}
		}
	}
	
	private void employeeActionMenu(Employee e) {
		System.out.println(e);
		String input;
		boolean userActive = true;
		List<Account> customerAccounts;
		List<Customer> customerProfiles;
		Account bankAccount;
		Customer customer;

		
		while(userActive == true) {
		System.out.println("Press 1 to view all customers\n"
							+ "Press 2 to view a customer\n"
							+ "Press 3 to view a customer's account(s)\n"
							+ "Press 4 to enter approval mode\n"
							+ "Press 0 to exit\n");

			
			input = sc.nextLine();
			
			switch(input) {
			
			case "1":
				customerProfiles= cH.retrieveAllCustomers();
				
				for(Customer c:customerProfiles) {
					System.out.println(c);
				}
				break;
				
			case "2":
				System.out.println("Enter customer id: \n");
				
				input = sc.nextLine();
				
				if(tH.checkNumberValid(input)) {
					int tempId = Integer.parseInt(input);
				customer = cH.retrieveCustomer(tempId);
				if(customer == null) {
					System.out.println("Invalid customer ID entered\n\n");
					break;
				}
				System.out.println(customer.displayAll());
				}
				else {System.out.println("Invalid input\n\n");
				}
				break;
				
			case "3":

				System.out.println("Enter customer id: \n");
				
				input = sc.nextLine();
				
				if(tH.checkNumberValid(input)) {
					
					int customerId= Integer.parseInt(input);
					customerAccounts = aH.retrieveCustomerAccounts(customerId);
					
					if(customerAccounts != null) {
						
							for(Account a: customerAccounts) {
								System.out.println(a);
							}
						
							}
					
						else {System.out.println("Please enter a valid customer ID.\n\n");
							}
					}

				break;
				
			case "4":
					System.out.println("Press 1 for customers\n"
										+ "Press 2 for customer accounts\n");
					
					input = sc.nextLine();
											
					if(input.equals("1")) {
							
						System.out.println("Enter customer id: \n");
							
						input = sc.nextLine();
							
						if(tH.checkNumberValid(input)) {
							
							int tempId = Integer.parseInt(input);
							customer = cH.retrieveCustomer(tempId);
								
							if(customer == null) {
								System.out.println("Invalid customer ID entered\n\n");
								break;
							}
							
							System.out.println(customer.displayAll());
							
							System.out.println("Press 1 to approve customer\n"
												+ "Press 2 to deny customer\n");
							
							input = sc.nextLine();
							
							if(input.equals("1")) {
								cH.approveCustomer(customer, e);
								System.out.println("Customer was approved!\n");
								break;
								
							}
							else if(input.equals("2")) {
								cH.denyCustomer(customer, e);
								System.out.println("Customer was denied! Account deleted!\n");
								break;
								
							}else {
								System.out.println("Invalid selection\n\n");
								}
							}
						}
					
					if(input.equals("2")) {
						
						System.out.println("Enter account number: \n");
						
						input = sc.nextLine();
							
						bankAccount = aH.retrieveAccount(input);
						
						if(bankAccount == null) {
							System.out.println("Account not found \n\n");
							break;
						}
						
						customer = cH.retrieveCustomer(bankAccount.getCustomerId());
						
						System.out.println(customer);
						System.out.println(bankAccount);
						
						System.out.println("Press 1 to approve account\n"
											+ "Press 2 to deny account\n"
											+ "Approving an account will automatically approve the customer as well\n");
						
						input = sc.nextLine();
						
						if(input.equals("1")) {
							
							cH.approveCustomer(customer, e);
							aH.approveAccount(bankAccount, e);
						}
						else if(input.equals("2")) {
							
							aH.denyAccount(bankAccount, e);
							
						}else {
							
							System.out.println("Invalid selcetion\n\n");
						}
					}							
				break;

			case "0":
				userActive = false;
				break;
			default:
				System.out.println("Please select a valid option\n\n");
			}
	
	}
		this.displayWelcomeMenu();
	}
}