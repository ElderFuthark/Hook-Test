package com.revature.services;

/*
 *  Services take input and validate it. The Account Service 
 *  validates user input by checking to ensure the user has not
 *  entered a negative number when depositing into their account
 *  or overdrafting when withdrawing from their account.
 *  
 *  User Services are responsible for handling the login and 
 *  registration process. Because both processes must check the 
 *  username and password, it only makes sense that an abstract class
 *  provide the declaration of those methods. Two subclasses, named
 *  Registration Service and Login Service, define their own implementations
 *  for them.
 *  
 */
public interface Service {
	public boolean validate(String...data);
}
