package com.example.ali.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ali.share.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	UserDto getUser(String email);
}
