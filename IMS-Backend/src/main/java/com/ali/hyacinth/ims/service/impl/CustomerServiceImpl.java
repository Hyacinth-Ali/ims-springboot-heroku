package com.ali.hyacinth.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.model.Customer;
import com.ali.hyacinth.ims.model.Transaction;
import com.ali.hyacinth.ims.repository.CustomerRepository;
import com.ali.hyacinth.ims.repository.TransactionRepository;
import com.ali.hyacinth.ims.service.CustomerService;
import com.ali.hyacinth.ims.shared.dto.CustomerDTO;
import com.ali.hyacinth.ims.shared.dto.EmployeeDTO;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * Create an object of an employee.
	 * 
	 * @throws InvalidInputException
	 */
	@Override
	public void createCustomer(CustomerDTO customerDto) throws InvalidInputException {
		
		String error = "";
		
		if (customerDto.getUserName() == null || customerDto.getUserName().length() == 0) {
			error = "The user name of a customer cannot be empty";
		}
		if (customerDto.getFirstName() == null || customerDto.getFirstName().length() == 0) {
			error = "The first name of a customer cannot be empty.";
		}
		if (customerDto.getLastName() == null || customerDto.getLastName().length() == 0) {
			error = "The last name of a customer cannot be empty.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		ModelMapper modelMapper = new ModelMapper();
		
		Customer customer = modelMapper.map(customerDto, Customer.class);
		
		customerRepository.save(customer);
	}

	/**
	 * Retrieve debt of a customer.
	 * @param userName of the customer
	 * @return the amount of the debt
	 * @throws InvalidInputException
	 */
	@Override
	public double getCustomerDebt(String userName) throws InvalidInputException {
		
		//Ger t the customer from the database
		Customer customer = customerRepository.findByUserName(userName);
		
		double debt = 0;
		
		if (customer != null) {
			List<Transaction> transactions = transactionRepository.findAllByBuyer(customer);
			for (Transaction transaction : transactions) {
				debt += transaction.getAmountUnpaid();
			}
		} else {
			throw new InvalidInputException("The customer does not exist.");
		}
		
		return debt;
	}
	
	/**
	 * Retrieves specific debt of a customer for a transaction
	 * @param customerId of the customer
	 * @return the amount of the debt
	 * @throws InvalidInputException
	 */
	@Override
	public double getCustomerTransactionDebt(String customerId, Date date) throws InvalidInputException {
		 Customer customer = customerRepository.findByUserName(customerId);
		 double returnValue = 0;
		 if (customer != null) {
			 List<Transaction> transactions = transactionRepository.findAllByBuyer(customer);
				for (Transaction transaction : transactions) {
					if (transaction.getDate().equals(date));
					returnValue = transaction.getAmountUnpaid();
				}
		 }
		 return returnValue;
	}


	@Override
	public void updateCustomer(String newUserName, CustomerDTO customerDTO) throws InvalidInputException {
		String error = "";
		
		Customer customer = customerRepository.findByUserName(customerDTO.getUserName());
		if (customer == null) {
			throw new InvalidInputException("The customer does not exist");
		}
		
		if (customerDTO.getFirstName() == null || customerDTO.getFirstName().length() == 0) {
			error = "The first name of a customer cannot be empty";
		}
		if (customerDTO.getLastName() == null || customerDTO.getLastName().length() == 0) {
			error += "\nThe last of a customer cannot be empty";
		}
		
		if (customerDTO.getUserName() == null || customerDTO.getUserName().length() == 0) {
			error += "\nThe user name of a customer cannot be empty";
		}
		
		ModelMapper modelMapper = new ModelMapper();
		for (Customer c : customerRepository.findAll()) {
			if (c.getUserName().equals(newUserName)) {
				error = "The user name already exist, please try another one";
			}
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		modelMapper.map(customerDTO, Customer.class);
		customerRepository.save(customer);
	}

	/**
	 * Delete an instance of a customer from the database
	 * @param userName of the customer
	 */
	@Transactional
	@Override
	public void deleteCustomer(String userName) {
		Customer customer = customerRepository.findByUserName(userName);
		
		if (customer == null) {
			throw new InvalidInputException("Customer doesn't exist");
		}
		
		customerRepository.delete(customer);
		
	}

	@Override
	public List<CustomerDTO> getCustomers(int page, int limit) {
		
		if (limit <= 0) {
			throw new InvalidInputException("The size must be greater than zero");
		}
		
		if (page > 0) {
			page = page - 1;
		}

		List<CustomerDTO> returnValue = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<Customer> customerPage = customerRepository.findAll(pageableRequest);
		List<Customer> customers = customerPage.getContent();
		
		for (Customer customer : customers) {
			CustomerDTO customerDTO = new CustomerDTO();
			BeanUtils.copyProperties(customer, customerDTO);
			returnValue.add(customerDTO);
		}
		return returnValue;
	}

	

	/**
	 * Retrieves the list of customers from the database.
	 */
	@Override
	public List<CustomerDTO> getCustomers() {
		List<CustomerDTO> returnValue = new ArrayList<>();
		
		Iterable<Customer> customers = customerRepository.findAll();
		
		ModelMapper modelMapper = new ModelMapper();
		
		for (Customer customer : customers) {
			returnValue.add(modelMapper.map(customer, CustomerDTO.class));
		}
		return returnValue;
	}

}
