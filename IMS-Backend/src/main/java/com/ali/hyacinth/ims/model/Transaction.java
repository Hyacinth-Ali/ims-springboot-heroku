package com.ali.hyacinth.ims.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {
	private String transactionId;

	public void setTransactionId(String value) {
		this.transactionId = value;
	}

	@Column(nullable = false, unique = true)
	public String getTransactionId() {
		return this.transactionId;
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

	private static final long serialVersionUID = -8943418603226944904L;

	private double totalAmount;

	public void setTotalAmount(double value) {
		this.totalAmount = value;
	}

	@Column(nullable = false)
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

	private Employee seller;

	@ManyToOne(optional = false)
	public Employee getSeller() {
		return this.seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	private Customer buyer;

	@ManyToOne(optional = false)
	// @JoinColumn(name = "customers_id")
	public Customer getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	private Set<ProductTransaction> productTransactions;

	@OneToMany(mappedBy = "transaction", cascade = { CascadeType.ALL })
	public Set<ProductTransaction> getProductTransactions() {
		return this.productTransactions;
	}

	public void setProductTransactions(Set<ProductTransaction> productTransactions) {
		this.productTransactions = productTransactions;
	}

	private double amountUnpaid;

	public double getAmountUnpaid() {
		amountUnpaid = this.totalAmount - this.amountPaid;
		return amountUnpaid;
	}

	public void setAmountUnpaid(double amountUnpaid) {
		this.amountUnpaid = amountUnpaid;
	}

	private String date;

	@Column(nullable = false)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
