package com.ali.hyacinth.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.ManyToMany;

@Entity(name = "customers")
public class Customer {
	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	@Column(nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}

	@Column(nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	private String customerId;

	public void setCustomerId(String value) {
		this.customerId = value;
	}

	@Column(nullable = false)
	public String getCustomerId() {
		return this.customerId;
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

	private String userName;

	public void setUserName(String value) {
		this.userName = value;
	}

	@Column(nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	private float debt;

	public void setDebt(float value) {
		this.debt = value;
	}

	@Column(nullable = false)
	public float getDebt() {
		return this.debt;
	}

	private Set<Transaction> purchases;

	@ManyToMany
	public Set<Transaction> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Set<Transaction> purchasess) {
		this.purchases = purchasess;
	}

}
