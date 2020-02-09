package com.ali.hyacinth.ims.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTransaction extends PagingAndSortingRepository<ProductTransaction, Long> {

}
