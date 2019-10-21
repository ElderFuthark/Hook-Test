package com.revature.services;

import com.revature.BBSDriver;
import com.revature.dao.AccountDAO;
import com.revature.models.User;

public class LoginService extends AccountService{
	/*
	 * Check if the provided file, userInfoFile exists. If it does not,
	 * it should be created. Furthermore, it is safe to assume that no users exist
	 * Therefore, we can safely append out user to file without checking for name
	 * conflicts.
	 * 
	 * separatedVals includes all parsed strings for the current line.
	 * Note: that all lines follow the same convention.
	 * 		--  ID.username.password
	 * Line is used to store the current line that's read in.
	 * 
	 * The username and password variables store the respective values
	 * that are passwed in via the varargs parameter of the validate method.
	 * 
	 */
	
	String username;
	String password;
	AccountDAO acctDAO = new AccountDAO();
	
	public LoginService()
	{
		username = "";
		password = "";
	}
	
	@Override
	public boolean validate(String ... data) {
		/*
		 * The user cannot login if no users exist. If no users exist,
		 * then the file is empty, and the ArrayList of users is likewise
		 * empty.
		 * 
		 */

		username = data[0];
		password = data[1];
		
		if(BBSDriver.users.size() < 1) {
			return false;
		}
		/*
		 * Iterate through the users in the users ArrayList and see if any have
		 * matching usernames and passwords.
		 * 
		 */
		for(User u : BBSDriver.users) {
			if(u.getUsername().equals(username)) {
				if(u.getPassword().equals(password)) {
					// User exists. Set them to the activeUser
					BBSDriver.activeUser = username;
					return true;
				}
			}
		}
		return false;
	}
}
