package com.ali.hyacinth.ims.model;

import javax.persistence.ManyToOne;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name = "addresses")
public class Address implements Serializable {
	private long id;

	public void setId(long value) {
		this.id = value;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return this.id;
	}

	private String addressId;

	public void setAddressId(String value) {
		this.addressId = value;
	}

	@Column(length = 30, nullable = false)
	public String getAddressId() {
		return this.addressId;
	}

	private String city;

	public void setCity(String value) {
		this.city = value;
	}

	@Column(length = 30, nullable = false)
	public String getCity() {
		return this.city;
	}

	private String country;

	public void setCountry(String value) {
		this.country = value;
	}

	@Column(length = 30, nullable = false)
	public String getCountry() {
		return this.country;
	}

	private String streetName;

	public void setStreetName(String value) {
		this.streetName = value;
	}

	@Column(length = 100, nullable = false)
	public String getStreetName() {
		return this.streetName;
	}

	private String postalCode;

	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	@Column(length = 7, nullable = false)
	public String getPostalCode() {
		return this.postalCode;
	}

	private String type;

	public void setType(String value) {
		this.type = value;
	}

	@Column(length = 10, nullable = false)
	public String getType() {
		return this.type;
	}

	private Employee employee;

	@ManyToOne(optional = false)
	@JoinColumn(name = "users_id")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee user) {
		this.employee = user;
	}

	private static final long serialVersionUID = -5606520411628590064L;

}
