package com.revature.dao;

import com.revature.BBSDriver;
import com.revature.models.User;

/*
 * The userDAO object reads and writes to the user_info file.
 * It will also check the active user's balance and write the new
 * balance if it is changed.
 * 
 * NOTE: It is not the responsibility of the UserDAO to register and login
 * users. That responsibility falls to the AccountDAO
 */

public class UserDAO {
	private String username;
	
	public UserDAO() {
		this.username = "";
	}
	
	public Double readBalance() {
		/*
		 *  The username will be used to identify the user with the account
		 *  Since the user is logged in, the username is the activeUser.
		 * 
		 */
		username = BBSDriver.activeUser;
		
		for(User u : BBSDriver.users) {
			if(u.getUsername().equals(username)) {
				return u.getBalance();
			}
		}
		return null;		// This should never trigger. Even if it does, the user will be brought back to the account screen.
	}
	public boolean writeBalance(Double newBalance) {
		/*
		 * To write the user's balance, it is necessary that the user be located in
		 * the ArrayList. Then it's simply a matter of writing the new balance using
		 * the setter for the balance variable.
		 * 
		 */
		
		for(User u : BBSDriver.users) {
			if(u.getUsername().equals(username)) {
				if(u.getUsername().equals(username)) {
					u.setBalance(newBalance);
					return true;			// Successfully set new balance.
				}
			}
		}
		return false;			// Something failed.
	}
}
