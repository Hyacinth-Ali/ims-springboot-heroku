package com.ali.hyacinth.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali.hyacinth.ims.ImsBackendApplication;
import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.model.Customer;
import com.ali.hyacinth.ims.model.Employee;
import com.ali.hyacinth.ims.model.Product;
import com.ali.hyacinth.ims.model.ProductTransaction;
import com.ali.hyacinth.ims.model.Transaction;
import com.ali.hyacinth.ims.repository.CustomerRepository;
import com.ali.hyacinth.ims.repository.EmployeeRepository;
import com.ali.hyacinth.ims.repository.ProductRepository;
import com.ali.hyacinth.ims.repository.ProductTransactionRepository;
import com.ali.hyacinth.ims.repository.TransactionRepository;
import com.ali.hyacinth.ims.service.TransactionService;
import com.ali.hyacinth.ims.shared.dto.ProductTransactionDTO;
import com.ali.hyacinth.ims.shared.dto.Receipt;
import com.ali.hyacinth.ims.shared.dto.TransactionDTO;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductTransactionRepository productTransactionRepository;

	/**
	 * Retrieves all transactions linked to a customer
	 */
	@Override
	public List<TransactionDTO> getCustomerTransactions(String userName) {
		
		Customer customer = customerRepository.findByUserName(userName);
		
		List<Transaction> transactions = transactionRepository.findAllByBuyer(customer);
				
		ArrayList<TransactionDTO> returnValuie = new ArrayList<TransactionDTO>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		for (Transaction transaction : transactions) {
			returnValuie.add(modelMapper.map(transaction, TransactionDTO.class));
		}
		return returnValuie;
	}

	/**
	 * Creates instance of a {@link Transaction}
	 * @param date of the transaction
	 * @param customerUserName of the buyer
	 * @param employeeUserName of the seller
	 * @throws InvalidInputException
	 */
	@Override
	public void createTransaction(String customerUserName, String employeeUserName) throws InvalidInputException {
		String error = "";
		//get the current date
		Date date = ImsBackendApplication.getCurrentDate();
		
		if (customerUserName == null || customerUserName.length() == 0) {
			error = "Please, enter the customer user name.";
		} else if (employeeUserName == null || employeeUserName.length() == 0) {
			error = "Select employee first.";
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		Customer c = customerRepository.findByUserName(customerUserName);
		Employee e = employeeRepository.findByUserName(employeeUserName);
		
		
		if (c == null) {
			error = "The customer does not exist, register first.";
		} else if (e == null || e.isManager()) {
			error = "You need a manager to create a transaction.";
		} 
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setBuyer(c);
		transaction.setSeller(e);
		
		transactionRepository.save(transaction);

	}

	@Override
	public void addTransactionProduct(String productName, int quantity, long id) throws InvalidInputException {
		
		String error = "";
		Product product = productRepository.findByName(productName);
		Transaction currentTransaction = null;
		
		boolean productExist = false;
		for (Transaction t : ImsBackendApplication.getCurrentTransactions()) {
			if (t.getId() == id) {
				currentTransaction = t;
			}
		}
		if (currentTransaction == null) {
			throw new InvalidInputException("The customer user name must be enetered before adding products.");
		}
		
		for (ProductTransaction pTransactions : productTransactionRepository.findAllByTransaction(currentTransaction)) {
			if (productRepository.findByProductTransaction(pTransactions).equals(product)) {
				productExist = true;
				break;
			}
		}
		
		if(productExist) {
			error = "The product is already added, you can edit it.";
		} else if (quantity <= 0) {
			error = "Quantity of items must be greater than zero.";
		} else if (product == null) {
			error = "The product does not exist.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		if(quantity > product.getQuantity()) {
			error = "Sorry! we do not have enough product in store.";
		} 
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		ProductTransaction productTransaction = new ProductTransaction();
		productTransaction.setProduct(product);
		productTransaction.setQuantity(quantity);
		productTransaction.setPrice(quantity * product.getItemPrice());
		
		product.setQuantity(product.getQuantity() - quantity);
		product.setProductTransaction(productTransaction);
		
		currentTransaction.getProductTransactions().add(productTransaction);
		
		setTransactionTotalAmount();

	}

	@Override
	public void setTransactionTotalAmount() throws InvalidInputException {
		// TODO Auto-generated method stub

	}

	@Override
	public Date cleanDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Receipt purchase(String customerID, float amountPaid) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Receipt generateReceipt(String customerID) throws InvalidInputException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductTransactionDTO> getTOProductTransaction(String transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTransaction(String id) throws InvalidInputException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearTransactionProducts() throws InvalidInputException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQuantityTransaction(String productName, int quantity) throws InvalidInputException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAmountPaidTransaction(String id, float newAmount) throws InvalidInputException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProductTransaction(String productName) throws InvalidInputException {
		// TODO Auto-generated method stub

	}

}
