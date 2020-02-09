package com.ali.hyacinth.ims.service;

import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.EmployeeDTO;

public interface EmployeeService {

	void createEmployee(EmployeeDTO employeeDTO) throws InvalidInputException;
	
	void updateEmployee(String id, EmployeeDTO employeeDTO) throws InvalidInputException;
	
	void deleteEmployee(String employeeId);

	EmployeeDTO getEmployeeByUserId(String userId, EmployeeDTO employeeDTO);
	
	List<EmployeeDTO> getEmployees();
	
	List<EmployeeDTO> getEmployees(int page, int limit);
	
	List<EmployeeDTO> getManagers();
	

}
