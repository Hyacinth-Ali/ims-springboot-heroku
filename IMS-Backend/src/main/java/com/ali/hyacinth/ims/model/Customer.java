package com.ali.hyacinth.ims.model;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1642219633693293123L;
	
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

	private Set<Transaction> purchases;

	@OneToMany(mappedBy="buyer")
public Set<Transaction> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Set<Transaction> purchasess) {
		this.purchases = purchasess;
	}

}
