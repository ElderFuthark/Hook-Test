package com.revature.screens;

import java.io.BufferedReader;

public interface Screen {
	/*
	 * All screens must be initialized when they are called. The screens themselves
	 * only require a start method. Each screen will be receiving input from the
	 * console. Therefore, each will need a BufferedReader.
	 * An interface is ideal for the screens, since each needs a public method that's
	 * abstract. This allows for each screen to define their own initializations.
	 */
	
	public void start(BufferedReader br);
}
