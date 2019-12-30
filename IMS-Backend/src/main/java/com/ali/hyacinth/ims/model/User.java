package com.ali.hyacinth.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
	private String password;

	public void setPassword(String value) {
		this.password = value;
	}

	@Column(nullable = false)
	public String getPassword() {
		return this.password;
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

	private boolean manager;

	public void setManager(boolean value) {
		this.manager = value;
	}

	@Column(nullable = false)
	public boolean isManager() {
		return this.manager;
	}

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

	private String userId;

	public void setUserId(String value) {
		this.userId = value;
	}

	@Column(nullable = false)
	public String getUserId() {
		return this.userId;
	}

	private String email;

	public void setEmail(String value) {
		this.email = value;
	}

	@Column(nullable = false, length = 120, unique = true)
	public String getEmail() {
		return this.email;
	}

	private String userName;

	public void setUserName(String value) {
		this.userName = value;
	}

	@Column(nullable = false, length = 50, unique = true)
	public String getUserName() {
		return this.userName;
	}

	private String encryptedPassword;

	public void setEncryptedPassword(String value) {
		this.encryptedPassword = value;
	}

	@Column(nullable = false)
	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}
}
