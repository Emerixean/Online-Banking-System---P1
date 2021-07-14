package com.revature.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;
import com.revature.service.EmployeeHandler;

public class EmployeesTest {
	
	EmployeeHandler testManager;

		@Mock
		EmployeeDaoImpl mockEDao;
		
		@Before
		public void setup() {

			Employee e = mock(Employee.class);

	
			mockEDao = mock(EmployeeDaoImpl.class);

			
			when(mockEDao.selectEmployeeByLogin("123", "123")).thenReturn(e);
			when(mockEDao.selectEmployeeByLogin("Jeremy11", "123Pass")).thenReturn(null);
			when(mockEDao.selectEmployeeByLogin(null,null)).thenReturn(null);
			when(mockEDao.selectEmployeeByLogin(null,"123")).thenReturn(null);
			when(mockEDao.selectEmployeeByLogin("123",null)).thenReturn(null);
			

			
			testManager = new EmployeeHandler(mockEDao);
			System.out.println("____________________Test Begin____________________");
		}
		
		
		@After
		public void output() {
			System.out.println("Test passed!\n");
		}
		

		
	
		
		@Test
		public void testEmployeeAuthentication() {
			
			assertEquals(true,testManager.authenticateEmployee("123", "123"));
			assertEquals(false,testManager.authenticateEmployee("Jeremy11", "123Pass"));
			assertEquals(false,testManager.authenticateEmployee(null, null));
			assertEquals(false,testManager.authenticateEmployee(null, "123"));
			assertEquals(false,testManager.authenticateEmployee("123", null));
			
			System.out.println("--Employee Authentication Test--");
		}
		

	
}

