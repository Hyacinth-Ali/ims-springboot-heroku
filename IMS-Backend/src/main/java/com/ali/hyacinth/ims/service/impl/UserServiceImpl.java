package com.ali.hyacinth.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.model.User;
import com.ali.hyacinth.ims.repository.UserRepository;
import com.ali.hyacinth.ims.service.UserService;
import com.ali.hyacinth.ims.shared.GenerateId;
import com.ali.hyacinth.ims.shared.Utils;
import com.ali.hyacinth.ims.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	Utils utils;
	
	@Autowired
	UserRepository userRepository;

	

	/**
	 * Create an object of an employee when the person does not exist.
	 * 
	 * @param UserDto the details of the new employee
	 * @param person     to be associated with the manager.
	 * @throws InvalidInputException
	 */
	@Override
	public UserDto createEmployee(UserDto userDto) {
		
		//TODO:
		//IMS ims = IMSApplication.getIms();
		
		if (userRepository.findByEmail(userDto.getEmail()) != null) {
			throw new RuntimeException("Records already exist");
		}
		
		User user = new User();
		user.setManager(false);
		BeanUtils.copyProperties(userDto, user);
		String publicUserId = utils.generateUserId(30);
		user.setUserId(publicUserId);
		user.setEncryptedPassword("testpassword");
		//TODO: change to muliple values.
		//person.setUserRole(employee);

		User storedEmployee = userRepository.save(user);

		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(storedEmployee, returnValue);

		return returnValue;

		/*
		 * BeanUtils.copyProperties(employeeDto, employee); String publicUserId =
		 * utils.generateUserId(30); employee.setUserId(publicUserId);
		 * employee.setEncryptedPassword("testpassword");
		 * 
		 * Employee storedEmployee = userRepository.save(employee);
		 * 
		 * EmployeeDto returnValue = new EmployeeDto();
		 * 
		 * BeanUtils.copyProperties(storedEmployee, returnValue);
		 * 
		 * return returnValue;
		 */
	}

//	@Override
//	public EmployeeDto getEmployeeByUserName(String userName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		
		User user = userRepository.findByUserId(userId);
		
		if (user == null) {
			throw new InvalidInputException("User doesn't exist");
		}
		
		UserDto returnValue = new UserDto();
		
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		
		User storedUser = userRepository.save(user);
		
		BeanUtils.copyProperties(storedUser, returnValue);
		
		return returnValue;
		
		
	}
	
	@Override
	public UserDto getEmployeeByUserId(String userId, UserDto userDto) {
		
		UserDto returnValue = new UserDto();
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new RuntimeException("Wrong credentials");
		}
		if (!user.getUserName().equals(userDto.getUserName())) {
			throw new RuntimeException("incorrect user name");
		}
		if (!user.getPassword().equals(userDto.getPassword())) {
			throw new RuntimeException("incorrect password");
		}
		//TODO:
		//Person person = employee.getPerson();
		BeanUtils.copyProperties(user, returnValue);
		//BeanUtils.copyProperties(person, returnValue);
		// TODO Auto-generated method stub
		return returnValue;
	}
	
	@Transactional
	@Override
	public void deleteUser(String userId) {
		
		User user = userRepository.findByUserId(userId);
		
		if (user == null) {
			throw new InvalidInputException("User doesn't exist");
		}
		
		userRepository.delete(user);
		
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		
		if (page > 0) {
			page = page - 1;
		}

		List<UserDto> returnValue = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<User> userPage = userRepository.findAll(pageableRequest);
		List<User> users = userPage.getContent();
		
		for (User user : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			returnValue.add(userDto);
		}
		return returnValue;
	}

//	@Override
//	public List<EmployeeDto> getEmployees() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Employee findEmployee(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public Employee findEmployeeByUserId(String userId) {
//		Employee employee = null;
//		//IMS ims = IMSApplication.getIms();
//		for (Employee e : ims.getEmployees()) {
//			if (e.getUserId().equals(userId)) {
//				employee = e;
//			}
//		}
//		
//		return employee;
//		
//	}
//
//	@Override
//	public void createEmployee(String userName, String password, Person person) throws InvalidInputException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean isEmployeeUsernameUnique(String userName) {
//		for (Employee e : IMSApplication.getIms().getEmployees()) {
//			if (userName.equals(e.getUserName())) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public boolean isEmployeeEmailUnique(String email) {
//		for (Employee e : IMSApplication.getIms().getEmployees()) {
//			if (email.equals(e.getEmail())) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public void deleteEmployee(String userName) throws InvalidInputException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void upDateEmployeeUsername(String oldUserName, String newUserName) throws InvalidInputException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void upDateEmployeePassword(String userName, String newPassword) throws InvalidInputException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void upDateEmployeerName(String userName, String newName) throws InvalidInputException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void createEmployee(String userName, String password) throws InvalidInputException {
//		// TODO Auto-generated method stub
//		
//	}

}
