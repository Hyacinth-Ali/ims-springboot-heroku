package com.ali.hyacinth.ims.service;

import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.CustomerDTO;
import com.ali.hyacinth.ims.shared.dto.EmployeeDto;

public interface CustomerService {
	
	CustomerDTO createCustomer(CustomerDTO customerDTO) throws InvalidInputException;
	
	CustomerDTO updateCustomer(String id, CustomerDTO DTO) throws InvalidInputException;
	
	void deleteCustomer(String customerId);

	CustomerDTO getCustomerByUserId(String customerId, CustomerDTO customerDTO);
	
	List<CustomerDTO> getCustomers(int page, int limit);

}
