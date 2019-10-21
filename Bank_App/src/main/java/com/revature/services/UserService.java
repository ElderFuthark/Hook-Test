package com.revature.services;

import com.revature.dao.UserDAO;


public class UserService implements Service{
	
	Double amount;			// Amount to withdraw or deposit.
	String message;			// A message denoting the user's action.
	String username;
	UserDAO userDAO;
	public UserService()
	{
		message = "";
		amount = 0d;
		userDAO = new UserDAO();
	}
	
	/*
	 * 	Notice that no username parameter is passed in. That is
	 * because the active user is indicated by Bank_System_v1.activeUser.
	 * If this variable is not set or is empty (should be impossible if all
	 * other checks work) then account info such as the current balance cannot
	 * be retrieved. This extends to all other logic checks when checking a user's
	 * account.
	 */
	
	@Override
	public boolean validate(String ... data) {
		message = data[0];					// The message will either be CHECK, DEPOSIT, or WITHDRAW
		amount = Double.parseDouble(data[1]);
		username = data[2];
		
		/*
		 *  The message acts as a means for the class to differentiate between a
		 *  checking the balance, making a deposit, or withdrawing. Depending on this
		 *  value, the action of the account service may be tailored.
		 */
		switch(message) {
		
		case "DEPOSIT":
			// check if a deposit is possible.
			if(amount < 0) {
				System.out.println("Can't deposit negative money.");
				return false;
			}
			break;
		case "WITHDRAW":
			// check if withdraw is possible.
			if(amount < 0 || amount > userDAO.readBalance()) {
				System.out.println("Can't withdraw more money than you own.");
				return false;
			}
			break;
		default:
			System.out.println("Invalid input");
			return false;
		}
		return true;
	}
}
