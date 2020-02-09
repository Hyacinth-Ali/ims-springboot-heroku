package com.ali.hyacinth.ims.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import com.ali.hyacinth.ims.service.AddressService;
import com.ali.hyacinth.ims.service.EmployeeService;
import com.ali.hyacinth.ims.shared.dto.AddressDTO;
import com.ali.hyacinth.ims.shared.dto.EmployeeDTO;
import com.ali.hyacinth.ims.ui.request.UserDetailsRequest;
import com.ali.hyacinth.ims.ui.request.UserLoginRequest;
import com.ali.hyacinth.ims.ui.response.AddressRest;
import com.ali.hyacinth.ims.ui.response.ErrorMessages;
import com.ali.hyacinth.ims.ui.response.OperationStatusModel;
import com.ali.hyacinth.ims.ui.response.RequestOperationName;
import com.ali.hyacinth.ims.ui.response.RequestOperationStatus;
import com.ali.hyacinth.ims.ui.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	@Autowired
	EmployeeService userService;
	
	@Autowired
	AddressService addressService;
	 
	@GetMapping(path="/{id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {  MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public UserRest getUser(@PathVariable String id, @RequestBody UserLoginRequest loginDetails) {
		
		UserRest returnValue = new UserRest();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(loginDetails, employeeDTO);
		EmployeeDTO returnUserDto = userService.getEmployeeByUserId(id, employeeDTO);
		
		BeanUtils.copyProperties(returnUserDto, returnValue);
		
		return returnValue;
	}
	
	@GetMapping(path="/{id}/addresses",
			produces = {  MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public List<AddressRest> getUserAddresses(@PathVariable String id) {
		
		List<AddressRest> returnValue = new ArrayList<>();
		
		List<AddressDTO> addressDTO = addressService.getAddresses(id);
		
		if (addressDTO != null && !addressDTO.isEmpty()) {
			Type listType = new TypeToken<List<AddressRest>>() {}.getType();
			returnValue = new ModelMapper().map(addressDTO, listType);
		}
		
		return returnValue;
	}
	
	@GetMapping(path="/{id}/addresses/{addressId}",
			produces = {  MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public AddressRest getUserAddress(@PathVariable String addressId) {
		
		//List<AddressRest> returnValue = new ArrayList<>();
		
		AddressDTO addressDTO = addressService.getAddress(addressId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		AddressRest returnValue = modelMapper.map(addressDTO, AddressRest.class);
		
		return returnValue;
	}
	
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
		
		ModelMapper modelMapper = new ModelMapper();
		EmployeeDTO employeeDTO = modelMapper.map(userDetails, EmployeeDTO.class);
		
		EmployeeDTO createEmployee = userService.createEmployee(employeeDTO);

		returnValue = modelMapper.map(createEmployee, UserRest.class);
		
		return returnValue;
	}
	
	@PutMapping(path="/{id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
			)
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequest userDetails) {
	
		UserRest returnValue = new UserRest();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(userDetails, employeeDTO);
		
		EmployeeDTO updateUser = userService.updateUser(id, employeeDTO);
		BeanUtils.copyProperties(updateUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
	)
	public OperationStatusModel deleteUser(@PathVariable String id) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		userService.deleteEmployee(id);
		
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="1") int limit) {
		
		List<UserRest> returnValue = new ArrayList<>();
		
		List<EmployeeDTO> users = userService.getEmployees(page, limit);
		
		for (EmployeeDTO employeeDTO : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(employeeDTO, userModel);
			returnValue.add(userModel);
		}
		return returnValue;
	}

}
