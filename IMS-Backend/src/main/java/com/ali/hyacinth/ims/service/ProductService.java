package com.ali.hyacinth.ims.service;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.shared.dto.ProductDTO;

public interface ProductService {
	
	void callCreateProduct() throws InvalidInputException;
	
	void createProduct(ProductDTO productDTO) throws InvalidInputException;
	
	void deleteProduct(String name) throws InvalidInputException;
	
	void updateProduct(String oldName, ProductDTO productDTO) throws InvalidInputException;
	
	void addProductItem(String name, int newQuantity) throws InvalidInputException;

}
