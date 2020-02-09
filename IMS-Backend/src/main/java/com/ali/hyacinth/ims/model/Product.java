package com.ali.hyacinth.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "products")
public class Product {
	private double itemPrice;

	public void setItemPrice(double value) {
		this.itemPrice = value;
	}

	@Column(nullable = false)
	public double getItemPrice() {
		return this.itemPrice;
	}

	private int quantity;

	public void setQuantity(int value) {
		this.quantity = value;
	}

	@Column(nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	private long id;

	public void setId(long value) {
		this.id = value;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return this.id;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	@Column(nullable = false, unique = true)
	public String getName() {
		return this.name;
	}

	private ProductTransaction productTransaction;

	@OneToOne(mappedBy = "product")
	public ProductTransaction getProductTransaction() {
		return this.productTransaction;
	}

	public void setProductTransaction(ProductTransaction productTransaction) {
		this.productTransaction = productTransaction;
	}

}
