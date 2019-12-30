package com.ali.hyacinth.ims.service;

import java.util.List;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.UserDto;

public interface UserService {

	/**
	 * Create an object of an employee when the person does not exist.
	 * 
	 * @param personName of the manager
	 * @param password   of the manager
	 * @param person     to be associated with the manager.
	 * @throws InvalidInputException
	 */
	UserDto createEmployee(UserDto employeeDto) throws InvalidInputException;
	
	/**
	 * update user details
	 * 
	 */
	UserDto updateUser(String id, UserDto userDto) throws InvalidInputException;
	
	/**
	 * Delete user
	 */
	void deleteUser(String userId);

//	/**
//	 * Retrieve a given employee
//	 * 
//	 * @param userName user name of the employee
//	 * @return the employee
//	 */
//	EmployeeDto getEmployeeByUserName(String userName);

	/**
	 * Retrieve a given employee with his public id
	 * @param userDto TODO
	 * @param userName user name of the employee
	 * 
	 * @return the employee
	 */
	UserDto getEmployeeByUserId(String userId, UserDto userDto);
	
	List<UserDto> getUsers(int page, int limit);

//	/**
//	 * Query method for retrieving employees
//	 * 
//	 * @return list of employees
//	 */
//	List<EmployeeDto> getEmployees();
//
//	/**
//	 * Helper method for finding an employee.
//	 * 
//	 * @param personName of the manager
//	 * @return the manager
//	 */
//	Employee findEmployee(String email);
//
//	/**
//	 * create an employee and then add it to an existing person
//	 * TODO: Add Person in parameter
//	 * @param customerID of the customer
//	 * @param person     associated with customer role.
//	 * @throws InvalidInputException
//	 */
//	void createEmployee(String userName, String password) throws InvalidInputException;
//
//	/**
//	 * Determine the uniqueness of a employee username.
//	 * 
//	 * @param userName
//	 * @return
//	 */
//	boolean isEmployeeUsernameUnique(String userName);
//
//	/**
//	 * Determine the uniqueness of a employee username.
//	 * 
//	 * @param userName
//	 * @return
//	 */
//	boolean isEmployeeEmailUnique(String userName);
//

//
//	/**
//	 * Updates the name of a employee
//	 * 
//	 * @param oldPersonName of the employee
//	 * @param newPersonName of the employee
//	 * @throws InvalidInputException
//	 */
//	void upDateEmployeeUsername(String oldUserName, String newUserName) throws InvalidInputException;
//
//	/**
//	 * Updates the password of a employee
//	 * 
//	 * @param oldPersonName of the employee
//	 * @param newPassword   of the employee
//	 * @throws InvalidInputException
//	 */
//	void upDateEmployeePassword(String userName, String newPassword) throws InvalidInputException;
//
//	/**
//	 * Updates the name of an employee.
//	 * 
//	 * @param oldPersonName of the employee
//	 * @param newName       of the employee
//	 * @throws InvalidInputException
//	 */
//	void upDateEmployeerName(String userName, String newName) throws InvalidInputException;

}
