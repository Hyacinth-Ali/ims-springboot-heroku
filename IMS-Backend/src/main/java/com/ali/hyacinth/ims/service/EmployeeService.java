package com.ali.hyacinth.ims.service;

import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto employeeDto) throws InvalidInputException;
	
	EmployeeDto updateEmployee(String id, EmployeeDto employeeDto) throws InvalidInputException;
	
	void deleteEmployee(String employeeId);

	EmployeeDto getEmployeeByUserId(String userId, EmployeeDto employeeDto);
	
	List<EmployeeDto> getEmployees(int page, int limit);

}
