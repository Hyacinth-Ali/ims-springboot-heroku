package com.ali.hyacinth.ims.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ali.hyacinth.ims.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByEmail(String email);
	User findByUserId(String userId);
}
