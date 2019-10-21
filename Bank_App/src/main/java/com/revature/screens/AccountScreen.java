package com.revature.screens;

import java.io.BufferedReader;
import java.io.IOException;

import com.revature.BBSDriver;
import com.revature.dao.UserDAO;
import com.revature.services.AccountService;
import com.revature.services.UserService;

public class AccountScreen implements Screen{
	public static boolean loggedIn = true;
	UserDAO userDAO;
	AccountService acctService;
	UserService userService;
	String amount;
	
	AccountScreen() {
		userDAO = new UserDAO();
		userService = new UserService();
	}

	@Override
	public void start(BufferedReader br) {
		while (loggedIn) {
			System.out.println("+---------------------------------+");
			System.out.println("|                                 |");
			System.out.println("|           Account Page          |");
			System.out.println("|                                 |");
			System.out.println("+---------------------------------+");
			
			System.out.println("1) Deposit");
			System.out.println("2) Withdraw");
			System.out.println("3) Check");
			System.out.println("4) Logout");
				
				try {
					String selection;
					System.out.println("> ");
					selection = br.readLine();
					switch(selection) {
					case "1":
						System.out.println("How much do you want to deposit?");
						System.out.println("> ");
						amount = br.readLine();
						
						if(userService.validate("DEPOSIT", amount, BBSDriver.activeUser) == true)
						{
							Double newBalance = userDAO.readBalance() + Double.parseDouble(amount);
							userDAO.writeBalance(newBalance);
							System.out.println("Deposit successful");
						}
						break;
					case "2":
						System.out.println("How much do you want to withdraw?");
						System.out.println("> ");
						amount = br.readLine();
						if(userService.validate("WITHDRAW", amount, BBSDriver.activeUser) == true)
						{
							Double newBalance = userDAO.readBalance() - Double.parseDouble(amount);
							userDAO.writeBalance(newBalance);
							System.out.println("Withdraw successful");
						}
						break;
					case "3":
						System.out.println(userDAO.readBalance());
						break;
					case "4":
						System.out.println("Please wait while you're logged out.");
						BBSDriver.activeUser = "";
						loggedIn = false;
						break;
					default:
						System.out.println("The value you've entered is not recognized.\n"
								+ "Please make a selection from the list by entering a number "
								+ "and pressing enter");
						this.start(br);
					}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
