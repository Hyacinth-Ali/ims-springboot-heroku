package com.ali.hyacinth.ims.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.model.Product;
import com.ali.hyacinth.ims.repository.ProductRepository;
import com.ali.hyacinth.ims.service.ProductService;
import com.ali.hyacinth.ims.shared.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public void callCreateProduct() throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method creates new instance of {@link Product}
	 * @param productDTO is transfer object object which contains name, price, and quantity of the product
	 */
	@Override
	public void createProduct(ProductDTO productDTO) throws InvalidInputException {
		
		String error = "";
		if (productDTO.getName() == null || productDTO.getName().equals("")) {
			error = "The name of a product cannot be empty.";
		}
		if (productDTO.getItemPrice() < 0) {
			error = "The price of a product cannot be negative.";
		}
		if (productDTO.getItemPrice() == 0) {
			error = "The price of a product cannot be zero";
		}
		if (productDTO.getQuantity() <= 0) {
			error = "Quantity of a product cannot be less than one.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		
		Product newProduct = modelMapper.map(productDTO, Product.class);
		
		productRepository.save(newProduct);
		
		
	}

	/**
	 * Deletes an instance of a product
	 */
	@Transactional
	@Override
	public void deleteProduct(String name) throws InvalidInputException {
		Product product = productRepository.findByName(name);
		if (product != null) {
			productRepository.delete(product);
		}
		
	}

	/**
	 * updates the details of a given instance of a {@link Product}
	 */
	@Override
	public void updateProduct(String oldName, ProductDTO productDTO) throws InvalidInputException {
		
		String error = "";
		if (productDTO.getName() == null || productDTO.getName().equals("")) {
			error = "The name of a product cannot be empty.";
		}
		if (productDTO.getItemPrice() < 0) {
			error = "The price of a product cannot be negative.";
		}
		if (productDTO.getItemPrice() == 0) {
			error = "The price of a product cannot be zero";
		}
		if (productDTO.getQuantity() <= 0) {
			error = "Quantity of a product cannot be less than one.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		Product product = productRepository.findByName(oldName);
		
		if (product != null) {
			product.setName(productDTO.getName());
			product.setItemPrice(productDTO.getItemPrice());
			product.setQuantity(productDTO.getQuantity());
		}
		
		productRepository.save(product);
		
	}

	/**
	 * Adds item(s) to an existing product
	 * @param name of the product
	 * @param quantity of the product;
	 */
	@Override
	public void addProductItem(String name, int newQuantity) throws InvalidInputException {
		
		String error = "";
		if (name == null || name.length() == 0) {
			error = "The name of a product cannot be empty.";
		}
		if (newQuantity < 0) {
			error = "Quantity of a product cannot be less than zero.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Product product = productRepository.findByName(name);
		if (product != null) {
			product.setQuantity(product.getQuantity() + newQuantity);
		}
		
		productRepository.save(product);	
	}

}
