package com.ali.hyacinth.ims.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.service.UserService;
import com.ali.hyacinth.ims.shared.dto.UserDto;
import com.ali.hyacinth.ims.ui.request.UserDetailsRequest;
import com.ali.hyacinth.ims.ui.request.UserLoginRequest;
import com.ali.hyacinth.ims.ui.response.ErrorMessages;
import com.ali.hyacinth.ims.ui.response.OperationStatusModel;
import com.ali.hyacinth.ims.ui.response.RequestOperationName;
import com.ali.hyacinth.ims.ui.response.RequestOperationStatus;
import com.ali.hyacinth.ims.ui.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	 
	@GetMapping(path="/{id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {  MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public UserRest getUser(@PathVariable String id, @RequestBody UserLoginRequest loginDetails) {
		
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(loginDetails, userDto);
		UserDto returnUserDto = userService.getEmployeeByUserId(id, userDto);
		
		BeanUtils.copyProperties(returnUserDto, returnValue);
		
		return returnValue;
	}
	
	//TODO: To be fully implemented 
//	@GetMapping(path="/{userName}")
//	public UserRest login(@PathVariable String userName, @PathVariable String password) {
//		
//		UserRest returnValue = new UserRest();
//		UserDto employeeDto = userService.getEmployeeByUserId(userName);
//		
//		BeanUtils.copyProperties(employeeDto, returnValue);
//		
//		return returnValue;
//	}
	
	@PostMapping("/createuser")
	public String createUser() {
		return "A user is created inside Create User.";
	}
	
	/**
	 * RequestBody: to enable this method read json or xml data from a 
	 * web request
	 * @param userDetails is a class to convert the json 
	 * to java object
	 * @return
	 * @throws InvalidInputException 
	 */
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public UserRest createUser(@RequestBody UserDetailsRequest userDetails) throws Exception {
		
		if (userDetails.getFirstName().isEmpty()) {
			//Parameter value can be plain string values
			throw new InvalidInputException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		UserRest returnValue = new UserRest();
		
		//UserDto userDto = new UserDto();
		//BeanUtils.copyProperties(userDetails, userDto);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createEmployee = userService.createEmployee(userDto);
		BeanUtils.copyProperties(createEmployee, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path="/{id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequest userDetails) {
	
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
	)
	public OperationStatusModel deleteUser(@PathVariable String id) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		userService.deleteUser(id);
		
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="1") int limit) {
		
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers(page, limit);
		
		for (UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		return returnValue;
	}

}
