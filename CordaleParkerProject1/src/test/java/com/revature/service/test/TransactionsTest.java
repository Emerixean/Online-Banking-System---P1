package com.revature.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.revature.dao.AccountDaoImpl;

import com.revature.models.Account;

import com.revature.service.TransactionHandler;

import io.javalin.http.Context;


public class TransactionsTest {
	TransactionHandler testHandler;




	@Mock
	AccountDaoImpl mockADao;

	
	@Before
	public void setup() {

		Account b = new Account();
		b.setAccountApproved(true);
		b.setAccountBalance(0);
		b.setAccountNumber(123);


		mockADao = mock(AccountDaoImpl.class);
		
		
		when(mockADao.updateAccountBalance(10, "123")).thenReturn(true);
		when(mockADao.updateAccountBalance(0,"321")).thenReturn(true);
		when(mockADao.selectAccountByAccountNumber("123")).thenReturn(b);
		
		when(mockADao.updateAccountBalance(10, "123")).thenReturn(true);
		when(mockADao.updateAccountBalance(-10, null)).thenReturn(false);
		when(mockADao.updateAccountBalance(-10, "123")).thenReturn(false);
		
		when(mockADao.updateAccountBalance(30, "123")).thenReturn(true);
		when(mockADao.updateAccountBalance(-10, "123")).thenReturn(false);
		when(mockADao.updateAccountBalance(-10, null)).thenReturn(false);
	


		
		testHandler = new TransactionHandler(mockADao);

		System.out.println("____________________Test Begin____________________");
	}
	@Test
	public void testTransfer() {
		Account a = new Account();
		a.setAccountApproved(true);
		a.setAccountNumber(321);
		a.setAccountBalance(10);
		

		
		assertEquals(true,testHandler.makeTransfer(a, "123", "10"));
		assertEquals(false,testHandler.makeTransfer(null,"123","10"));
		
		System.out.println("--Transfer Test--");
	}
	
	@Test
	public void testWithdraw() {
		
		Account a = new Account();
		a.setAccountApproved(true);
		a.setAccountNumber(123);
		a.setAccountBalance(20);
		assertEquals(true, testHandler.withdrawCustomerAccount("10", a));
		assertEquals(false, testHandler.withdrawCustomerAccount("30", a));
		assertEquals(false, testHandler.withdrawCustomerAccount("10", null));
		
		System.out.println("--Withdraw Test--");
	}
	
	@Test
	public void testDeposit() {
		Account a = new Account();
		a.setAccountApproved(true);
		a.setAccountNumber(123);
		a.setAccountBalance(20);
		assertEquals(true, testHandler.depositCustomerAccount("10", a));
		assertEquals(true, testHandler.depositCustomerAccount("-30", a));
		assertEquals(false, testHandler.depositCustomerAccount("10", null));
		
		System.out.println("--Deposit Test--");
	}

	
	@After
	public void output() {
		System.out.println("Test passed!\n");
	}
}
