package com.revature.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import com.revature.dao.AccountDaoImpl;
import com.revature.models.Account;
import com.revature.models.Employee;
import com.revature.service.AccountHandler;

public class AccountsTest {

		AccountHandler testManager;
	
	
	
		@Mock

		AccountDaoImpl mockADao;

		
		@Before
		public void setup() {
			Employee e = mock(Employee.class);
			Account a = mock(Account.class);
			Account b = new Account();
			b.setAccountApproved(true);
			b.setAccountBalance(0);
			b.setAccountNumber(123);
	

			mockADao = mock(AccountDaoImpl.class);
			

			



			
			when(mockADao.selectAccountByAccountNumber("1232321")).thenReturn(a);
			when(mockADao.selectAccountByAccountNumber("aaaaasd")).thenReturn(null);
			when(mockADao.selectAccountByAccountNumber(null)).thenReturn(null);
			
			when(mockADao.updateAccountApproval(false,"1",e)).thenReturn(true);
			when(mockADao.updateAccountApproval(false,"1",null)).thenReturn(false);
			when(mockADao.updateAccountApproval(false,null,null)).thenReturn(false);
			
			when(mockADao.updateAccountApproval(true,"1",e)).thenReturn(true);
			when(mockADao.updateAccountApproval(true,"1",null)).thenReturn(false);
			when(mockADao.updateAccountApproval(true,null,null)).thenReturn(false);
			
			when(mockADao.updateAccountBalance(10, "123")).thenReturn(true);
			when(mockADao.updateAccountBalance(0,"321")).thenReturn(true);
			when(mockADao.selectAccountByAccountNumber("123")).thenReturn(b);
			
			
	
			
			testManager = new AccountHandler(mockADao);
			System.out.println("____________________Test Begin____________________");
		}
		
		@After
		public void output() {
			System.out.println("Test passed!\n");
		}
		
	
	
		@Test
		public void testAccountRetrieval() {
			
			assertTrue(testManager.retrieveAccount("1232321") instanceof Account);
			assertEquals(null,testManager.retrieveAccount("aaaaasd"));
			assertEquals(null,testManager.retrieveAccount(null));
			
			System.out.println("--Account Retrieval Test--");
		}
		

		
		@Test
		public void testDenyAccount() {
			Account a = mock(Account.class);
	
			
	
			Employee e = mock(Employee.class);
			assertEquals(true,testManager.denyAccount(a,e));
			assertEquals(false,testManager.denyAccount(a,null));
			assertEquals(false,testManager.denyAccount(null,null));
			
			
			System.out.println("--Account Denial Test--");
		}
		
		@Test
		public void testApproveAccount() {
			Account a = mock(Account.class);
			
	
			Employee e = mock(Employee.class);
			assertEquals(true,testManager.approveAccount(a,e));
			assertEquals(false,testManager.approveAccount(a,null));
			assertEquals(false,testManager.approveAccount(null,null));
			
			
			System.out.println("--Account Approval Test--");
		}
		
	
	}


