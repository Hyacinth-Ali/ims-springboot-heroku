package com.ali.hyacinth.ims.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ali.hyacinth.ims.model.ProductTransaction;
import com.ali.hyacinth.ims.model.Transaction;

@Repository
public interface ProductTransactionRepository extends CrudRepository<ProductTransaction, Long> {
	List<ProductTransaction> findAllByTransaction(Transaction transaction);
}
