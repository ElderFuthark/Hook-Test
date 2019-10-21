package com.revature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.models.User;
import com.revature.screens.HomeScreen;
import com.revature.screens.Screen;

public class BBSDriver {
	public static String activeUser;
	public static boolean appState;			// True means the app is running; False means the user has exited.
	public static int idIncrementor;			// A dummy int that iterates whenever a new user is added.
	public static List<User> users;			// List containing all users.
	
	public static void main(String[] args) {
		appState = true;
		idIncrementor = 1;
		users = new ArrayList<User>();
		AccountDAO acctAccessor = new AccountDAO();
		acctAccessor.init();				// Populates users list with existing users.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Screen menu = new HomeScreen();
		while(appState) {
			menu.start(br);
		}
		try {
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		/*
		* It's the end of the program. Write the changes
		* to the file.
		*/
		acctAccessor.writeBack();
	}
}
