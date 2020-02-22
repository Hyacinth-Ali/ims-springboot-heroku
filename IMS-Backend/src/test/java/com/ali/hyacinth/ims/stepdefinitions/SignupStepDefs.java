package com.ali.hyacinth.ims.stepdefinitions;

import io.cucumber.java.en.*;

public class SignupStepDefs {
	
	@Given("IMS is running")
	public void ims_is_running() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("The following employees exist")
	public void the_following_employees_exist(io.cucumber.datatable.DataTable dataTable) {
		dataTable.
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Employee wants to sign up with {string}, {string}, {string}, {string}, {string} and {string}")
	public void employee_wants_to_sign_up_with_and(String string, String string2, String string3, String string4, String string5, String string6) {
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
