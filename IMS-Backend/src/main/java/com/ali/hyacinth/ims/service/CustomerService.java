package com.ali.hyacinth.ims.service;

import java.util.Date;
import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.CustomerDTO;
import com.ali.hyacinth.ims.shared.dto.EmployeeDTO;

public interface CustomerService {
	
	void createCustomer(CustomerDTO customerDTO) throws InvalidInputException;
	
	void updateCustomer(String newUserName, CustomerDTO customerDTO) throws InvalidInputException;
	
	void deleteCustomer(String userName);
	
	List<CustomerDTO> getCustomers(int page, int limit);
	
	double getCustomerDebt(String userName) throws InvalidInputException;
	
	double getCustomerTransactionDebt(String customerId, Date date) throws InvalidInputException;
	
	List<CustomerDTO> getCustomers();

}
