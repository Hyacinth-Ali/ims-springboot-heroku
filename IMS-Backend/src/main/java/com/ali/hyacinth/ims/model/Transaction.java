package com.ali.hyacinth.ims.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Date;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity(name = "transactions")
public class Transaction {
	
	private double totalAmount;

	public void setTotalAmount(double value) {
		this.totalAmount = value;
	}

	@Column(nullable = false, unique = true)
	public double getTotalAmount() {
		return this.totalAmount;
	}

	private double amountPaid;

	public void setAmountPaid(double value) {
		this.amountPaid = value;
	}

	@Column(nullable = false)
	public double getAmountPaid() {
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
	
	private double amountUnpaid;
	
	public double getAmountUnpaid() {
		amountUnpaid = this.totalAmount - this.amountPaid;
		return amountUnpaid;
	}
	
	private Date date;

	@Column(nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
