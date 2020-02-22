package com.ali.hyacinth.ims.util.testcheck.ims;

import static org.junit.jupiter.api.Assertions.fail;

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
import com.ali.hyacinth.ims.service.impl.EmployeeServiceImpl;
import com.ali.hyacinth.ims.shared.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignupStepDefs {

	@InjectMocks
	EmployeeServiceImpl employeeService;

	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	Utils utils;

	String userId = "hhty57ehfy";
	String encryptedPassword = "74hghd8474jf";

	Employee employee;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Given("IMS is running")
	public void ims_is_running() {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();
	}

	@Given("The following employees exist")
	public void the_following_employees_exist(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		String firstName = list.get(0).get("firstName");
		String lastName = list.get(0).get("lasstName");
		String email = list.get(0).get("email");
		String userName = list.get(0).get("userName");
		String password = list.get(0).get("password");
		String isManager = list.get(0).get("isManager");
	}

	@Given("Employee wants to sign up with {string}, {string}, {string}, {string}, {string} and {string}")
	public void employee_wants_to_sign_up_with_and(String string, String string2, String string3, String string4,
			String string5, String string6) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("Employee initiates sign up process")
	public void employee_initiates_sign_up_process() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("The sign up status is {string}")
	public void the_sign_up_status_is(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}
