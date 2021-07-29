package com.revature.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.service.CustomerHandler;

public class CustomersTest {

		CustomerHandler testManager;
	
	
	
		@Mock
		CustomerDaoImpl mockCDao;

		
		@Before
		public void setup() {
			Customer c = mock(Customer.class);
			Employee e = mock(Employee.class);

	
			mockCDao = mock(CustomerDaoImpl.class);

			
			when(mockCDao.selectCustomerByLogin("123", "123")).thenReturn(c);
			when(mockCDao.selectCustomerByLogin("Jeremy11", "123Pass")).thenReturn(null);
			when(mockCDao.selectCustomerByLogin(null,null)).thenReturn(null);
			when(mockCDao.selectCustomerByLogin(null,"123")).thenReturn(null);
			when(mockCDao.selectCustomerByLogin("123",null)).thenReturn(null);
			
			when(mockCDao.updateCustomerApproval(true,1,e)).thenReturn(true);
			when(mockCDao.updateCustomerApproval(true,1,null)).thenReturn(false);
	
			when(mockCDao.updateCustomerApproval(false,1,e)).thenReturn(true);
			when(mockCDao.updateCustomerApproval(false,1,null)).thenReturn(false);
			
			

			
	
			
			testManager = new CustomerHandler(mockCDao);
			System.out.println("____________________Test Begin____________________");
		}
		
		@After
		public void output() {
			System.out.println("Test passed!\n");
		}
		
		@Test
		public void testCustomerAuthentication() {
	
			
			assertEquals(true,testManager.authenticateCustomer("123", "123"));
			assertEquals(false,testManager.authenticateCustomer("Jeremy11", "123Pass"));
			assertEquals(false,testManager.authenticateCustomer(null, null));
			assertEquals(false,testManager.authenticateCustomer(null, "123"));
			assertEquals(false,testManager.authenticateCustomer("123", null));
			
			System.out.println("--Customer Authentication Test--");
		}
		
	
		
		@Test
		public void testApproveCustomer() {
			Customer c = mock(Customer.class);
	
			
	
			Employee e = mock(Employee.class);
			assertEquals(true,testManager.approveCustomer(c,e));
			assertEquals(false,testManager.approveCustomer(c,null));
			
			
			System.out.println("--Customer Approval Test--");
		}
		
		@Test
		public void testDenyCustomer() {
			Customer c = mock(Customer.class);
			Employee e = mock(Employee.class);
			
			
			assertEquals(true,testManager.denyCustomer(c,e));
			assertEquals(false,testManager.denyCustomer(c,null));
			
			
			System.out.println("--Customer Denial Test--");
		}
	}


