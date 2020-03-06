package com.ali.hyacinth.ims.service;

import java.util.Date;
import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.CustomerDTO;

public interface CustomerService {
	
	void createCustomer(CustomerDTO customerDTO, String employeeId) throws InvalidInputException;
	
	void updateCustomer(String newUserName, CustomerDTO customerDTO, String employeeId) throws InvalidInputException;
	
	void deleteCustomer(String userName, String employeeId);
	
	List<CustomerDTO> getCustomers(int page, int limit);
	
	double getCustomerDebt(String userName, String employeeId) throws InvalidInputException;
	
	double getCustomerTransactionDebt(String customerId, Date date) throws InvalidInputException;
	
	List<CustomerDTO> getCustomers();

}
