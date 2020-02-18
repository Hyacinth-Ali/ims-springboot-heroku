package com.ali.hyacinth.ims.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.ali.hyacinth.ims.repository.EmployeeRepository;

class EmployeeServiceImplTest {
	
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() {
		
		EmployeeServiceImpl employee = new EmployeeServiceImpl();
		when ( employeeRepository.findByEmail( anyString() )). thenReturn(null);
	}

}
