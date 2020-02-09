package com.ali.hyacinth.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.model.Employee;
import com.ali.hyacinth.ims.repository.EmployeeRepository;
import com.ali.hyacinth.ims.service.EmployeeService;
import com.ali.hyacinth.ims.shared.Utils;
import com.ali.hyacinth.ims.shared.dto.EmployeeDTO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	Utils utils;
	
	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * Create an object of an employee when the person does not exist.
	 * 
	 * @param EmployeeDTO the details of the new employee
	 * @param person     to be associated with the manager.
	 * @throws InvalidInputException
	 */
	@Override
	public void createEmployee(EmployeeDTO employeeDTO) {
		
		String error = "";
		
		if (employeeRepository.findByEmail(employeeDTO.getEmail()) != null) {
			error = "Email already exist";
		} else if (employeeDTO.getFirstName() == null || employeeDTO.getFirstName().length() == 0) {
			error = "The first name cannot be empty.";
		} else if (employeeDTO.getLastName() == null || employeeDTO.getLastName().length() == 0) {
			error = "The first name cannot be empty.";
		} else if (employeeDTO.getPassword() == null || employeeDTO.getPassword() == "") {
			error = "The password cannot be empty password.";
		} else if (employeeDTO.getUserName() == null || employeeDTO.getUserName().length() == 0) {
			error = "The user name cannot be empty.";
		} else if (employeeDTO.getEmail() == null || employeeDTO.getEmail().length() == 0) {
			error = "The email cannot be empty.";
		} 
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
//		for (int i = 0; i < employeeDTO.getAddresses().size(); i++) {
//			AddressDTO address = employeeDTO.getAddresses().get(i);
//			address.setUserDetails(employeeDTO);
//			address.setAddressId(utils.generateAddressId(30));
//			employeeDTO.getAddresses().set(i, address);
//		}

		ModelMapper modelMapper = new ModelMapper();
		
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		
		//employee.setManager(false);
		String publicUserId = utils.generateUserId(30);
		employee.setEmployeeId(publicUserId);
		employee.setEncryptedPassword("testpassword");
		
		employeeRepository.save(employee);

	}

	
	/**
	 * Retrieve a given employee with his public id
	 * @param employeeDTO 
	 * @param userName user name of the employee
	 * 
	 * @return the employee
	 */
	@Override
	public EmployeeDTO getEmployeeByUserId(String userId, EmployeeDTO employeeDTO) {
		
		EmployeeDTO returnValue = new EmployeeDTO();
		Employee employee = employeeRepository.findByEmployeeId(userId);
		if (employee == null) {
			throw new RuntimeException("Wrong credentials");
		}
		if (!employee.getUserName().equals(employeeDTO.getUserName())) {
			throw new RuntimeException("incorrect user name");
		}
		if (!employee.getPassword().equals(employeeDTO.getPassword())) {
			throw new RuntimeException("incorrect password");
		}
		
		BeanUtils.copyProperties(employee, returnValue);
		
		return returnValue;
	}
	
	@Transactional
	@Override
	public void deleteEmployee(String employeeId) {
		
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		
		if (employee == null) {
			throw new InvalidInputException("The employee doesn't exist");
		}
		
		employeeRepository.delete(employee);
		
	}

	/**
	 * Query method for retrieving managers
	 */
	@Override
	public void updateEmployee(String id, EmployeeDTO employeeDTO) throws InvalidInputException {
		String error = "";
		
		if (employeeRepository.findByEmail(employeeDTO.getEmail()) != null) {
			error = "Email already exist";
		} else if (employeeDTO.getFirstName() == null || employeeDTO.getFirstName().length() == 0) {
			error = "The first name cannot be empty.";
		} else if (employeeDTO.getLastName() == null || employeeDTO.getLastName().length() == 0) {
			error = "The first name cannot be empty.";
		} else if (employeeDTO.getPassword() == null || employeeDTO.getPassword() == "") {
			error = "The password cannot be empty password.";
		} else if (employeeDTO.getUserName() == null || employeeDTO.getUserName().length() == 0) {
			error = "The user name cannot be empty.";
		} else if (employeeDTO.getEmail() == null || employeeDTO.getEmail().length() == 0) {
			error = "The email cannot be empty.";
		} 
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		Employee employee = employeeRepository.findByEmployeeId(employeeDTO.getUserName());
		
		if (employee == null) {
			throw new InvalidInputException("The employee doesn't exist");
		}
		
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setPassword(employeeDTO.getPassword());
		employee.setUserName(employeeDTO.getUserName());
		
		employeeRepository.save(employee);
		
	}

	/**
	 * Retrieves all the employees from the database.
	 */
	@Override
	public List<EmployeeDTO> getEmployees() {
		ArrayList<EmployeeDTO> returnValue = new ArrayList<EmployeeDTO>();
		ModelMapper modelMapper = new ModelMapper();
		
		for (Employee employee : employeeRepository.findAll()) {
			returnValue.add(modelMapper.map(employee, EmployeeDTO.class));
			
		}
		return returnValue;
	}
	
	@Override
	public List<EmployeeDTO> getEmployees(int page, int limit) {
		
		if (page > 0) {
			page = page - 1;
		}
		if (limit <= 0) {
			throw new InvalidInputException("The size cannot be zero or less");
		}

		List<EmployeeDTO> returnValue = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<Employee> employeePage = employeeRepository.findAll(pageableRequest);
		List<Employee> employees = employeePage.getContent();
		
		for (Employee employee : employees) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			returnValue.add(employeeDTO);
		}
		return returnValue;
	}


	@Override
	public List<EmployeeDTO> getManagers() {
		ArrayList<EmployeeDTO> returnValue = new ArrayList<EmployeeDTO>();
		ModelMapper modelMapper = new ModelMapper();
		
		for (Employee employee : employeeRepository.findAll()) {
			if (employee.isManager()) {
				returnValue.add(modelMapper.map(employee, EmployeeDTO.class));
			}
		}
		return returnValue;
	}



}
