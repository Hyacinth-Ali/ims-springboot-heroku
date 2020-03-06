package com.ali.hyacinth.ims.service;

import java.util.Date;
import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.ProductTransactionDTO;
import com.ali.hyacinth.ims.shared.dto.Receipt;
import com.ali.hyacinth.ims.shared.dto.TransactionDTO;

public interface TransactionService {
	
	List<TransactionDTO> getCustomerTransactions(String userName);
	
	void createTransaction(String customerUserName, String employeeUserName) throws InvalidInputException;
	
	void addTransactionProduct(String productName, int quantity, String transactionId) throws InvalidInputException;
	
	void setTransactionTotalAmount(String transactionId) throws InvalidInputException;
	
	//Final step to purchase products, generate receipt.
	Receipt checkout(String transactionId, float amountPaid) throws InvalidInputException;
	
	//Receipt generateReceipt(String customerID) throws InvalidInputException;
	
	//List of products for a given transaction 
	//List<ProductTransactionDTO> getTOProductTransaction(String transactionId);
	
	void deleteTransaction(String id) throws InvalidInputException;
	
	void clearTransactionProducts(String transactionId) throws InvalidInputException;
	
	void updateQuantityTransaction(String productName, int newQuantity, String transactionId) throws InvalidInputException;
	
	void updateAmountPaidTransaction(String id, float newAmount, String transactionId) throws InvalidInputException;
	
	void deleteProductTransaction(String productName, String transactionId) throws InvalidInputException;

}









