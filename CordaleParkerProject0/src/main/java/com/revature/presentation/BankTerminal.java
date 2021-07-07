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
import com.revature.service.BankManager;
import com.revature.service.BankManagerImpl;

public class BankTerminal {
	

	BankManagerImpl bM;
	Scanner sc = new Scanner(System.in);
	
	public BankTerminal(BankManagerImpl bankManager) {
		this.bM = bankManager;
	}
	

	public void displayWelcomeMenu() {
		boolean validInput = false;
		String input = null;
		while(validInput == false) {

		
		System.out.println("Welcome to Revature Bank!");
		System.out.println("Press 1 if you are a customer: ");
		System.out.println("Press 2 if you are an employee: ");
		
		input = sc.nextLine();
		
		if(input.equals("1") || input.equals("2")) {
		validInput = true;
			}
		else {System.out.println("Please enter a valid option.");}
		}
		switch(input) {
		case "1":
			this.customerWelcomeMenu();
			break;
		case "2":
			//this.employeeMenu();
			break;
		default:
			System.out.println("Invalid option recieved. Exiting program!");
		}
	}


	
	private void customerWelcomeMenu() {
		
		boolean validInput = false;
		String input = null;
		

		

		
		while(validInput == false) {
		
		System.out.println("Thank you for banking with Revature Bank!\n"
				+ "Press 1 if you are an existing customer.\n"
				+ "Press 2 if you want to create a new account. ");
		
		input = sc.nextLine();
		
		if(input.equals("1") || input.equals("2")) {

		validInput = true;
			}
		else {System.out.println("Please enter a valid option.");
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
		boolean validLogin = false;
		Customer customer;
		

		
		while(validLogin == false) {
		System.out.println("Please input your account name: ");
		
		accountName = sc.nextLine();
		
		System.out.println("Please input your password: ");
		
		accountPassword = sc.nextLine();
		
		if(this.bM.authenticateCustomer(accountName, accountPassword) == true) {
			
			customer = bM.retrieveCustomerInformation(accountName,accountPassword);
			validLogin = true;
			this.customerActionMenu(customer);
			}

		}
	}
	
	private void customerActionMenu(Customer c) {
		System.out.println(c);
		String input;
		String accountType;
		boolean userActive = true;
		boolean validInput = false;
		List<Account> customerAccounts;
		Account bankAccount;
		String transfer;
		
		while(userActive == true) {
		System.out.println("Made it to action menu!");
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
				customerAccounts= bM.retrieveCustomerAccounts(c.getId());
				
				for(Account a:customerAccounts) {
					System.out.println(a);
				}
				break;
				
			case "2":
				System.out.println("Enter account number to view balance:");
				
				input = sc.nextLine();
				
				if(bM.checkNumberValid(input)) {
					
				bankAccount = bM.retrieveCustomerAccount(input , c);
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

				System.out.println("Please enter starting balance\n ");
				input = sc.nextLine();
				if(bM.checkNumberValid(input)) {
					System.out.println("Press 1 for savings\n"
										+ "Press 2 for checkings\n");
					
					accountType = sc.nextLine();
					
					if(accountType.equals("1") || accountType.equals("2")) {
						
						bM.applyForAccount(input,accountType,c.getId());
							}
					
						else {System.out.println("Please enter a valid option.");
							}
					}

				break;
				
			case "4":
					System.out.println("Please enter account number");
					input = sc.nextLine();
					
					if(bM.checkNumberValid(input)) {
						
						bankAccount = bM.retrieveCustomerAccount(input, c);
						
						if(bankAccount == null) {
							System.out.println("Could not select account!");
						}else {
						System.out.println("Please enter deposit amount with numbers only: ");
						
						input = sc.nextLine();
						
						if(bM.checkNumberValid(input)) {
							if(bM.depositCustomerAccount(input,bankAccount)== false) {
								System.out.println("Could not make deposit!");
								}
							}
						}
					}
					
				break;
			case "5":
				System.out.println("Please enter account number");
				input = sc.nextLine();
				
				if(bM.checkNumberValid(input)) {
					
					bankAccount = bM.retrieveCustomerAccount(input,c);
					
					if(bankAccount ==null) {
						System.out.println("Could not access account!");
					}else {
					System.out.println("Please enter withdraw amount with numbers only: ");
					
					input = sc.nextLine();
					
					if(bM.checkNumberValid(input)) {
						if (bM.withdrawCustomerAccount(input,bankAccount) == false) {
							System.out.println("Could not make withdraw!");
							}
						}
					}
				}
				
				break;
				
			case "6":
					System.out.println("Please enter the account number you wish to withdraw from ");
					input = sc.nextLine();
					
					if(bM.checkNumberValid(input)) {
						bankAccount = bM.retrieveCustomerAccount(input,c);
						if(bankAccount ==null) {
							System.out.println("Could not access account!");
							break;
						}else {
							System.out.println("Please enter the account number you wish to deposit to ");
							input = sc.nextLine();
							if(bM.checkNumberValid(input)) {
								System.out.println("Enter amount to transfer:");
								transfer = sc.nextLine();
								if(bM.checkNumberValid(transfer)) {
									if(bM.makeTransfer(bankAccount, input, transfer) == false) {
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
			
		int id;
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
		System.out.println("Please input your account name: ");
		
		accountName = sc.nextLine();
				
		System.out.println("Please input your password: ");
		
		accountPassword = sc.nextLine();
		
		if(bM.authenticateCustomer(accountName, accountPassword)) {
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

		
		if(bM.makeNewCustomerLogin(accountName, accountPassword,firstName, lastName, phoneNumber, email, dateOfBirth, address, apartment, city, state, country, postalCode)== false) {
			System.out.println("Could not create user account!");
		}else {
			userLoginAvailable = true;
			System.out.println("Applied for a customer account!");
		}
		this.displayWelcomeMenu();
		}
		
		//create all the inputs for customer fields
		
		//this.displayWelcomeMenu();
		}
	
	}
}