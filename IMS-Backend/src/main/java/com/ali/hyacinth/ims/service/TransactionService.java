package com.ali.hyacinth.ims.service;

import java.util.Date;
import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.ProductTransactionDTO;
import com.ali.hyacinth.ims.shared.dto.Receipt;
import com.ali.hyacinth.ims.shared.dto.TransactionDTO;

public interface TransactionService {
	
	List<TransactionDTO> getCustomerTransactions(String customerID);
	
	void createTransaction(String customerID, String managerUserName) throws InvalidInputException;
	
	void addTransactionProduct(String productName, int quantity) throws InvalidInputException;
	
	void setTransactionTotalAmount() throws InvalidInputException;
	
	Date cleanDate(Date date);
	
	//Final step to purchase products, generate receipt.
	Receipt purchase(String customerID, float amountPaid) throws InvalidInputException;
	
	Receipt generateReceipt(String customerID) throws InvalidInputException;
	
	//List of products for a given transaction 
	List<ProductTransactionDTO> getTOProductTransaction(String transactionId);
	
	void deleteTransaction(String id) throws InvalidInputException;
	
	void clearTransactionProducts() throws InvalidInputException;
	
	void updateQuantityTransaction(String productName, int quantity) throws InvalidInputException;
	
	void updateAmountPaidTransaction(String id, float newAmount) throws InvalidInputException;
	
	void deleteProductTransaction(String productName) throws InvalidInputException;

}









