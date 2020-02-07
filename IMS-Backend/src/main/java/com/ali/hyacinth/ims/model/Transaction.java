package com.ali.hyacinth.ims.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity(name = "transactions")
public class Transaction {
	private float totalAmount;

	public void setTotalAmount(float value) {
		this.totalAmount = value;
	}

	public float getTotalAmount() {
		return this.totalAmount;
	}

	private float amountPaid;

	public void setAmountPaid(float value) {
		this.amountPaid = value;
	}

	public float getAmountPaid() {
		return this.amountPaid;
	}

	private String id;

	public void setId(String value) {
		this.id = value;
	}

	@Id
	@GeneratedValue
	public String getId() {
		return this.id;
	}

	private Employee seller;

	@ManyToOne(optional = false)
	public Employee getSeller() {
		return this.seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	private CEO cEO;

	@ManyToOne(optional = false)
	public CEO getCEO() {
		return this.cEO;
	}

	public void setCEO(CEO cEO) {
		this.cEO = cEO;
	}

	private Set<Customer> buyer;

	@ManyToMany(mappedBy = "purchases")
	public Set<Customer> getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Set<Customer> buyers) {
		this.buyer = buyers;
	}

	private Set<ProductTransaction> productTransactions;

	@OneToMany(mappedBy = "transaction", cascade = { CascadeType.ALL })
	public Set<ProductTransaction> getProductTransactions() {
		return this.productTransactions;
	}

	public void setProductTransactions(Set<ProductTransaction> productTransactionss) {
		this.productTransactions = productTransactionss;
	}

}
