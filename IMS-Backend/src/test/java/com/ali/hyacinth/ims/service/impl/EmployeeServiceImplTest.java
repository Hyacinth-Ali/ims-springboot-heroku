package com.ali.hyacinth.ims.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.model.Employee;
import com.ali.hyacinth.ims.repository.EmployeeRepository;
import com.ali.hyacinth.ims.shared.Utils;
import com.ali.hyacinth.ims.shared.dto.EmployeeDTO;

class EmployeeServiceImplTest {
	
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock 
	Utils utils;
	
	Employee existingEmployee;
	String userName = "jason";
	String password = "ali123";
	String email = "jason@gmail.com";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		existingEmployee = new Employee();
		existingEmployee.setPassword("ali123");
		existingEmployee.setId(1L);
		existingEmployee.setManager(true);
		existingEmployee.setFirstName("Jason");
		existingEmployee.setLastName("Chijioke");
		existingEmployee.setEmail("jason@gmail.com");
		existingEmployee.setUserName("jason");
		existingEmployee.setEmployeeId(utils.generateEmployeeId(30));
	}
	
	/** log in  ****/

	@Test
	void testGetEmployee() {
		
		//EmployeeServiceImpl employee = new EmployeeServiceImpl();
		when ( employeeRepository.findByUserName( userName)). thenReturn( existingEmployee );
		
		EmployeeDTO employeeDTO = employeeService.getEmployeeByUserName(userName, password);
		
		assertNotNull(employeeDTO);
		assertEquals("Jason", employeeDTO.getFirstName());
		assertEquals("ali123", employeeDTO.getPassword());
		assertTrue(employeeDTO.isManager());
		assertEquals("Chijioke", employeeDTO.getLastName());
		assertEquals("jason@gmail.com", employeeDTO.getEmail());
	}
	
	@Test
	void testGetEmployeeEmptyUserName() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByUserName( userName)). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByUserName("", password);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The user name is incorrect, try again!", error);
		assertNull(employeeDTO);
	}
	
	@Test
	void testGetEmployeeIncorrectUserName() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByUserName( userName)). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByUserName(userName + "rte", password);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The user name is incorrect, try again!", error);
		assertNull(employeeDTO);
	}
	
	@Test
	void testGetEmployeeEmptyPassword() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByUserName( userName )). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByUserName(userName, "");
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Incorrect password, try again!", error);
		assertNull(employeeDTO);
	}
	
	@Test
	void testGetEmployeeIncorrectPassword() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByUserName( userName )). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByUserName(userName, password + "rt");
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Incorrect password, try again!", error);
		assertNull(employeeDTO);
	}
	
	@Test
	void testGetEmployeeNullEmail() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByEmail( email)). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByEmail(null, password);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The email is incorrect, try again!", error);
		assertNull(employeeDTO);
	}
	
	@Test
	void testGetEmployeeNullUserName() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByUserName( userName)). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByUserName(null, password);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The user name is incorrect, try again!", error);
		assertNull(employeeDTO);
	}
	
	@Test
	void testGetEmployeeEmptyEmail() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByEmail( email )). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByEmail("", password);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The email is incorrect, try again!", error);
		assertNull(employeeDTO);
	}
	
	@Test
	void testGetEmployeeIncorrectEmail() {
		String error = "";
		
		EmployeeDTO employeeDTO = null;
		when ( employeeRepository.findByEmail( email )). thenReturn( existingEmployee );
		try {
			employeeDTO = employeeService.getEmployeeByEmail(email + "try", password);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("The email is incorrect, try again!", error);
		assertNull(employeeDTO);
	}

}
