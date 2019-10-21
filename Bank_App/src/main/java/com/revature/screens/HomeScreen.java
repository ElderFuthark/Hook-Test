package com.revature.screens;
import java.io.BufferedReader;
import java.io.IOException;

import com.revature.BBSDriver;

public class HomeScreen implements Screen{

	private String selection;
	
	public HomeScreen(){}
	
	// Must override the start() method from the Screen interface.
	
	@Override
	public void start(BufferedReader br) {
		System.out.println("+---------------------------------+");
		System.out.println("|            Welcome to           |");
		System.out.println("|           Better Bank!          |");
		System.out.println("|                                 |");
		System.out.println("+---------------------------------+");
		
		System.out.println("1) Login");
		System.out.println("2) Register");
		System.out.println("3) Exit");
		
		try {
			
			System.out.println("> ");
			selection = br.readLine();
			switch(selection) {
			case "1":
				Screen login = new LoginScreen();
				login.start(br);
				break;
			case "2":
				Screen register = new RegistrationScreen();
				register.start(br);
				break;
			case "3":
				BBSDriver.appState = false;
				break;
			default:
				System.out.println("The value you've entered is not recognized.\n"
						+ "Please make a selection from the list by entering a number "
						+ "and pressing enter");
				this.start(br);
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
