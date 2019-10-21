package com.revature.screens;

import java.io.BufferedReader;
import java.io.IOException;

import com.revature.services.RegistrationService;
import com.revature.services.Service;

public class RegistrationScreen implements Screen {
	private String unvalidatedUsername;
	private String unvalidatedPassword;
	Service registerNewUser;
	
	public RegistrationScreen()
	{
		unvalidatedUsername = "";
		unvalidatedPassword = "";
		registerNewUser = new RegistrationService();
	}
	
	@Override
	public void start(BufferedReader br)
	{
		System.out.println("+---------------------------------+");
		System.out.println("|                                 |");
		System.out.println("|        Registration Page        |");
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
			
			if(registerNewUser.validate(unvalidatedUsername, unvalidatedPassword) == true) 
			{
				System.out.println("Registration was successful!.");
			}
			else {
				System.out.println("A user with that account already exists.");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
