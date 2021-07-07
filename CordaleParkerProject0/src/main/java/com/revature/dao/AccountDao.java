package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {
	
	//CRUD
	
	//Create
	boolean insertAccount(Account a);
	
	//Read
	List<Account> selectAllAccounts();
	List<Account> selectAllApproved();
	List<Account> selectAllUnapproved();
	List<Account> selectByAccountType(String type);
	List<Account> selectAccountByCustomerId(int id);
	Account selectAccountByAccountNumber(String accountNumber);
	
	//Update
	boolean updateAccountApproval(boolean b,int accountNumber);
	boolean updateAccountBalance(double amount, String accountNumber);
	
	//Delete
	boolean deleteAccount(Account a);
}
