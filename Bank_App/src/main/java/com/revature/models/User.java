/*
 * POJO Design Pattern
 * 
 *         POJO = Plain Old Java Object
 *             - Synonyms: model, bean
 * 
 *         Characteristics of the design pattern:
 *             - proper encapsulation of instance fields
 *             - external access/manipulation to private fields is done through accessor and mutator methods
 *             - various constructors for flexible class construction
 *             - overridden Object methods: .hashCode(), .equals(Object o), and .toString()
 *         
 *         Note:
 *             - This is a class that only defines the User itself, application behaviors that a User may
 *               perform on our application are not included here (such as login/logout). These are behaviors
 *               which we will write into the flow of our application. This User class is simply a static 
 *               template for which we can create User objects, it is NOT responsible for the logic of dynamically
 *               manipulating the state of the application.
 */

package com.revature.models;

import java.util.Objects;

import com.revature.BBSDriver;

public class User {
	private Integer id;
	private String username;
	private String password;
	private Double balance;
	
	public User() {
		
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.id = BBSDriver.idIncrementor += 1;
		this.balance = 0d;
	}

	public User(Integer id, String username, String password, Double balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;

	}

	// HashCode and equals methods
	
	@Override
	public int hashCode() {
		return Objects.hash(balance, id, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(balance, other.balance) && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	// Setters and getters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	// toString
	@Override
	public String toString() {
		return id + "," + username + "," + password + "," + balance;
	}
	
	
	
}

