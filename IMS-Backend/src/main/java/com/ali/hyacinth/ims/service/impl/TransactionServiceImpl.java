package com.ali.hyacinth.ims.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	public List<TransactionDTO> getCustomerTransactions(String userName) throws InvalidInputException{
		
		if (userName == null || userName.length() == 0) {
			throw new InvalidInputException("Please select the customer.");
		} 
		
		Customer customer = customerRepository.findByUserName(userName);
		
		if (customer == null) {
			throw new InvalidInputException("This customer does not exist.");
		}
		
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
		String date = ImsBackendApplication.getCurrentDate();
		
		String name = null;
		for (Employee e : ImsBackendApplication.getCurrentEmployees()) {
			if (e.getUserName().equals(employeeUserName)) {
				name = employeeUserName;
			}
		}
		
		if (name == null || name.length() == 0) {
			error = "Employee must be logged in.";
		} else if (customerUserName == null || customerUserName.length() == 0) {
			error = "Please, enter the customer user name.";
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
		
		ImsBackendApplication.addCurrentTransaction(transaction);

	}

	@Override
	public void addTransactionProduct(String productName, int quantity, String transactionId) throws InvalidInputException {
		
		String error = "";
		Product product = productRepository.findByName(productName);
		
		Transaction currentTransaction = null;
		
		
		for (Transaction t : ImsBackendApplication.getCurrentTransactions()) {
			if (t.getTransactionId().equals(transactionId)) {
				currentTransaction = t;
			}
		}
		if (currentTransaction == null) {
			throw new InvalidInputException("The customer user name must be enetered before adding products.");
		}
		
		boolean productExist = false;
		for (ProductTransaction pTransaction : productTransactionRepository.findAllByTransaction(currentTransaction)) {
			if (productRepository.findByProductTransaction(pTransaction).equals(product)) {
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
		
		//currentTransaction.getProductTransactions().add(productTransaction);
		//List<ProductTransaction> productTransactions = productTransactionRepository.findAllByTransaction(currentTransaction);
		
		Set<ProductTransaction> productTransactions = new HashSet<ProductTransaction>();
		productTransactions.add(productTransaction);
		currentTransaction.setProductTransactions(productTransactions);
		setTransactionTotalAmount(currentTransaction.getTransactionId());
		
		transactionRepository.save(currentTransaction);

	}

	@Override
	public void setTransactionTotalAmount(String transactionId) throws InvalidInputException {
		
		Transaction currentTransaction = transactionRepository.findByTransactionId(transactionId);
		
		float totalAmount = 0.0f;
		if (currentTransaction != null) {
			for (ProductTransaction productTransaction : productTransactionRepository.findAllByTransaction(currentTransaction)) {
				double amount = productTransaction.getPrice();
				totalAmount += amount;
			}
		} else {
			throw new InvalidInputException("There transaction does not exist.");
		}
		
		currentTransaction.setTotalAmount(totalAmount);
		
		//transactionRepository.save(currentTransaction);

	}

	/**
	 * Final step to purchase products, generate receipt.
	 * @param transaction of the purchase
	 * @param amountPaid paid for the transaction
	 * @throws InvalidInputException
	 */
	@Override
	public Receipt checkout(String transactionId, float amountPaid) throws InvalidInputException {
		String error = "";
		Transaction transaction = transactionRepository.findByTransactionId(transactionId);
		if (transaction == null) {
			error = "This transaction does not exist!";
		} else if (amountPaid < 0) {
			error = "The amount to pay cannot be negative.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		Receipt receipt = null;
		
		transaction.setAmountPaid(amountPaid);
		receipt = new Receipt();
		receipt.setTotalAmount(transaction.getTotalAmount());
		receipt.setAmoundPaid(transaction.getAmountPaid());
		String date = transaction.getDate();
		receipt.setDate(date);
		try {
			for (ProductTransaction pTransaction : productTransactionRepository.findAllByTransaction(transaction)) {
				
				ProductTransactionDTO pTransactionDTO = new ProductTransactionDTO();
				
				ModelMapper modelMapper = new ModelMapper();
				pTransactionDTO = modelMapper.map(pTransaction, ProductTransactionDTO.class);
				receipt.getpTransactions().add(pTransactionDTO);
			}
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
		return receipt;
	}

	/**
	 * Deletes a transaction
	 * @param id of the transaction
	 * @throws InvalidInputException
	 */
	@Override
	public void deleteTransaction(String id) throws InvalidInputException {
		
		Transaction transaction = transactionRepository.findByTransactionId(id);
		
		if (transaction == null) {
			throw new InvalidInputException("The transaction doesn't exist");
		}
		
		transactionRepository.delete(transaction);

	}

	@Override
	public void clearTransactionProducts(String transactionId) throws InvalidInputException {
		
		Transaction transaction = transactionRepository.findByTransactionId(transactionId);
		if (transaction == null) {
			throw new InvalidInputException("The transaction does not exist");
		}
		
		try {
			for (ProductTransaction productTransaction : productTransactionRepository.findAllByTransaction(transaction)) {
				Product p = productRepository.findByProductTransaction(productTransaction);
				p.setQuantity(p.getQuantity() + productTransaction.getQuantity());
				productRepository.save(p);
			}
			List<ProductTransaction> clearTransactions = productTransactionRepository.findAllByTransaction(transaction);
			productTransactionRepository.deleteAll(clearTransactions);
			setTransactionTotalAmount(transactionId);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	@Override
	public void updateQuantityTransaction(String productName, int newQuantity, String transactionId) throws InvalidInputException {
		
		String error = "";
		Transaction transaction = transactionRepository.findByTransactionId(transactionId);
		Product product = productRepository.findByName(productName);
		
		if (transaction == null) {
			error = "The transaction does not exist.";
		} else if(product == null) {
			error = "The product doesn't exist for this transaction.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		ProductTransaction pTransaction = productTransactionRepository.findByProduct(product);
		int differenceQuantity = newQuantity - pTransaction.getQuantity();
		
		pTransaction.setQuantity(newQuantity);	
		pTransaction.setPrice(newQuantity * product.getItemPrice());
		product.setQuantity(product.getQuantity() - differenceQuantity);
		setTransactionTotalAmount(transactionId);
		transactionRepository.save(transaction);

	}

	@Override
	public void updateAmountPaidTransaction(String id, float newAmount, String transactionId) throws InvalidInputException {
		
		String error = "";
		Transaction transaction = transactionRepository.findByTransactionId(transactionId);
		
		if (transaction == null) {
			error = "The transaction does not exist.";
		} 
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		transaction.setAmountPaid(transaction.getAmountPaid() + newAmount);
		
		transactionRepository.save(transaction);

	}

	@Override
	public void deleteProductTransaction(String productName, String transactionId) throws InvalidInputException {
		
		String error = "";
		Transaction transaction = transactionRepository.findByTransactionId(transactionId);
		Product product = productRepository.findByName(productName);
		
		if (transaction == null) {
			error = "The transaction does not exist.";
		} else if(product == null) {
			error = "The product doesn't exist for this transaction.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		ProductTransaction pTransaction = productTransactionRepository.findByProduct(product);
		int quantity = pTransaction.getQuantity();
		product.setQuantity(product.getQuantity() + quantity);
		productTransactionRepository.delete(pTransaction);
		setTransactionTotalAmount(transactionId);
		transactionRepository.save(transaction);
	}

	}
