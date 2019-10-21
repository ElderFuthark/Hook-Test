package com.revature.services;

import com.revature.BBSDriver;
import com.revature.dao.AccountDAO;
import com.revature.models.User;

public class RegistrationService extends AccountService{
	String username;
	String password;
	AccountDAO acctDAO;
	
	public RegistrationService() 
	{
		username = "";
		password = "";
		acctDAO = new AccountDAO();
	}
	
	@Override
	public boolean validate(String... data) {
		username = data[0];
		password = data[1];
		
		/*
		 * In order to register a user, the username must first be
		 * checked against existing users to ensure one with that username
		 * does not already exist.
		 */
		for(User u : BBSDriver.users) {
			if(u.getUsername().equals(username)) {
				return false;
			}
		}
		
		/*
		 * If the program reaches here, no matching users have been found.
		 * It's safe to register a new user.
		 */
		acctDAO.registerUser(username, password);
		return true;			// User was not found, but it has been registered.
	}
}
