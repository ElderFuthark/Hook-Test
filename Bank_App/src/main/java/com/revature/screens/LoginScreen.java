/*
 * Presents the login screen to the user. Validation of login credentials is handled separately.
 */

package com.revature.screens;
import java.io.BufferedReader;
import java.io.IOException;

import com.revature.services.LoginService;
import com.revature.services.Service;

public class LoginScreen implements Screen{
	private String unvalidatedUsername;
	private String unvalidatedPassword;
	private AccountScreen acctScreen;
	
	public LoginScreen()
	{
		unvalidatedUsername = "";
		unvalidatedPassword = "";
		acctScreen = new AccountScreen();
	}
	
/*
 *  Much like the home screen, this screen takes in two inputs, an user's username and password. Prior to passing these inputs
 *  to the validator, it eliminates a few blanket cases.
 *  
 *  Present the user with prompts for the username and password.
 */
	@Override
	public void start(BufferedReader br) {
		System.out.println("+---------------------------------+");
		System.out.println("|                                 |");
		System.out.println("|           Login Page            |");
		System.out.println("|                                 |");
		System.out.println("+---------------------------------+");
		
		try {
			System.out.println("Username and password guidelines:\n"
					+ "A username must start with a letter,\n but can\n"
					+ "contain a mixture of letters and numbers after it.\n"
					+ "Usernames must be at least 6 characters long.\n"
					+ "A password can be any mixture of letters, numbers\n"
					+ "and symbols (e.g. $, ^, *, @) so long at it is at least 8 characters long.\n\n");
			System.out.println("Enter your username:\n");
			System.out.println("> ");
			unvalidatedUsername = br.readLine();
			
			System.out.println("Enter your password:\n");
			System.out.println("> ");
			unvalidatedPassword = br.readLine();
			/*
			 * Check a few blanket cases where the username would be wrong. These include
			 * \w -- word (A through Z, underscore, and 0 through 9)
			 * &&[^] is the excludes what's inside the brackets from being evaluated.
			 */
			if(!unvalidatedUsername.matches("^[\\w&&[^_0-9]]\\w*") || unvalidatedUsername.isEmpty() || unvalidatedUsername.length() < 6) {
				System.out.println("The username or password is invalid.\n");
				this.start(br);
			}
			if(unvalidatedPassword.length() < 8) {
				System.out.println("The password must be longer than 8 characters.");
				this.start(br);
			}
			/*
			 * Pass the File, username, and password to the login service.
			 */
			Service logMeIn = new LoginService();
			if(logMeIn.validate(unvalidatedUsername, unvalidatedPassword) == true) 
			{
				AccountScreen.loggedIn = true;
				acctScreen.start(br);
			}
			else {
				System.out.println("Please register an account first.");
				Screen register = new RegistrationScreen();
				register.start(br);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
