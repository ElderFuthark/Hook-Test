package com.revature.services;

public abstract class AccountService implements Service{
	/*
	 *  Start() is declared abstract here for ease of readability.
	 *  It is not requred to be since the class is abstract, and
	 *  therefore, the compiler understands that if the method is not
	 *  implemented here, it will be implemented in a subclass.
	 *  
	 *  Different actions are required when checking the username and
	 *  pasword when logging in versus registering an account. The former
	 *  checks for existance and whether each value matches. The latter
	 *  checks to ensure that the username is not already taken by an
	 *  existing user.
	 */
	
	public abstract boolean validate(String...data);
	
}
