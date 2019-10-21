package com.revature.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.revature.BBSDriver;
import com.revature.models.User;

/*
 * The AccountDAO object is responsible loading users into memory at the start of the program.
 * It also writes all accounts back to the file at the end of the program, or should any premature
 * failure occur.
 */

public class AccountDAO {
	
	/*
	 * The sole purpose of this method is to initialize the exisiting
	 * users by reading from the text file and creating users out of them.
	 * 
	 * First check if the file is exists. If not, it should be created. Then
	 * use a buffered reader to read the lines and parse them accordingly,
	 * creating users from each.
	 */
	public void init() {
		
		String line;
		String[] parsedLine;
		File userFile = new File("src/resources/user_info.txt");
		try {
			userFile.createNewFile();
		} catch (IOException e) {
			System.out.println("An error occured while reading the users");
			System.out.println("Exiing application.");
			BBSDriver.appState = false;
			e.printStackTrace();
			return;
		}
		/*
		 * File exists or was successfully created.
		 * Now iterate through the lines and add them to the user list.
		 */
		try(BufferedReader br = new BufferedReader(new FileReader(userFile)))
		{
			line = br.readLine();
			while(line != null) {
				parsedLine = line.split(",");
				User u = new User(
						Integer.parseInt(parsedLine[0]),
						parsedLine[1],
						parsedLine[2],
						Double.parseDouble(parsedLine[3])
						);
				BBSDriver.users.add(u);
				line = br.readLine();
			}
		} catch(IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * The user now wishes to exit the application or for some other reason write to the file.
	 * Take the information from the users ArrayList and write it to the text file.
	 */
	
	public void writeBack()
	{
		File userFile = new File("src/resources/user_info.txt");
		String content;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, false)))
		{
			for(User u : BBSDriver.users) {
				content = u.toString() + "\n";
				bw.write(content);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean registerUser(String username, String password) {
		User newUser = new User(username, password);
		BBSDriver.users.add(newUser);
		return true;
	}
}
