package com.ali.hyacinth.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali.hyacinth.ims.model.Address;
import com.ali.hyacinth.ims.model.User;
import com.ali.hyacinth.ims.repository.AddressRepository;
import com.ali.hyacinth.ims.repository.UserRepository;
import com.ali.hyacinth.ims.service.AddressService;
import com.ali.hyacinth.ims.shared.dto.AddressDTO;
import com.ali.hyacinth.ims.ui.response.AddressRest;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public List<AddressDTO> getAddresses(String userId) {
		// TODO Auto-generated method stub
		
		List<AddressDTO> returnValue = new ArrayList<>();
		
		User user = userRepository.findByUserId(userId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		Iterable<Address> addresses = addressRepository.findAllByUser(user);
		
		for (Address address : addresses) {
			returnValue.add(modelMapper.map(address, AddressDTO.class));
		}

		return returnValue;
		
	}

	@Override
	public AddressDTO getAddress(String addressId) {
		
		AddressDTO returnValue = null;
		
		Address address = addressRepository.findByAddressId(addressId);
		if (address != null) {
			ModelMapper modelMapper = new ModelMapper();
			
			returnValue = modelMapper.map(address, AddressDTO.class);
		}
		return returnValue;
	}

}
