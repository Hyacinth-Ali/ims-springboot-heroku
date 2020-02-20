package com.ali.hyacinth.ims.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ali.hyacinth.ims.model.Customer;
import com.ali.hyacinth.ims.model.Transaction;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
	
	List<Transaction> findAllByBuyer(Customer buyer);
	//List<Transaction> findAllByCustomer(Customer customer);
	Transaction findByDate(Date date);

}
