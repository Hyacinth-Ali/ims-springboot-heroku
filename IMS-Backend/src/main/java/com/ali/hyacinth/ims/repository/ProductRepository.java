package com.ali.hyacinth.ims.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ali.hyacinth.ims.model.Product;
import com.ali.hyacinth.ims.model.ProductTransaction;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	
	Product findByName(String name);
	Product findByProductTransaction(ProductTransaction pTransaction);

}
