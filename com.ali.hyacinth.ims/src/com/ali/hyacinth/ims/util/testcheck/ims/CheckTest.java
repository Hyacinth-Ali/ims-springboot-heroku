package com.ali.hyacinth.ims.util.testcheck.ims;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ali.hyacinth.ims.model.Employee;
import com.ali.hyacinth.ims.repository.EmployeeRepository;
import com.ali.hyacinth.ims.service.EmployeeService;
import com.ali.hyacinth.ims.service.impl.EmployeeServiceImpl;
import com.ali.hyacinth.ims.shared.Utils;
import com.ali.hyacinth.ims.shared.dto.EmployeeDTO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

class CheckTest {
	
	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	Utils utils;

	Employee existingEmployee;
	EmployeeDTO employeeDTO;
	String loginUserName;
	String loginPassword;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(EmployeeServiceImpl.class);
	}
	
	@Given("IMS has the following employees")
	public void ims_has_the_following_employees(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    //throw new io.cucumber.java.PendingException();
		
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		String user_name = list.get(0).get("user_name");
		String password = list.get(0).get("password");
		
		existingEmployee = new Employee();
		existingEmployee.setPassword(password);
		existingEmployee.setManager(true);
		existingEmployee.setFirstName("Jason");
		existingEmployee.setLastName("Chijioke");
		existingEmployee.setEmail("jason@gmail.com");
		existingEmployee.setUserName(user_name);
		existingEmployee.setEmployeeId(utils.generateEmployeeId(30));
		existingEmployee.setEmployeeId("gtytuui");
	}

	@Given("Employee wants to log in with {string} and {string}")
	public void i_want_to_log_in_with_and(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		loginUserName = string;
		loginPassword = string2;
	}

	@When("Employee initiates log in process")
	public void employee_initiates_log_in_process() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		when(employeeRepository.findByUserName( anyString())).thenReturn(existingEmployee);
		employeeDTO = employeeService.getEmployeeByUserId(loginUserName, loginPassword);
	}

	@Then("The log in status is {string}")
	public void the_log_in_status_is(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		if (string.equals("valid")) {
			assertNotNull(employeeDTO);
			assertEquals("Jason", employeeDTO.getFirstName());
		}
	}


}
