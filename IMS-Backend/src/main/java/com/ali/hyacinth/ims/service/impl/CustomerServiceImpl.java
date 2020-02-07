package com.ali.hyacinth.ims.service.impl;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.service.CustomerService;
import com.ali.hyacinth.ims.shared.dto.CustomerDTO;
import com.ali.hyacinth.ims.shared.dto.EmployeeDto;

public class CustomerServiceImpl implements CustomerService {

	/**
	 * Create an object of an employee.
	 * 
	 * @throws InvalidInputException
	 */
	@Override
	public EmployeeDto createCustomer(CustomerDTO employeeDto) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrieve debt of a customer.
	 * @param id of the customer
	 * @return the amount of the debt
	 * @throws InvalidInputException
	 */
	@Override
	public float getCustomerDebt(String userName) throws InvalidInputException {
		// TODO Auto-generated method stub
		return 0;
	}

}
