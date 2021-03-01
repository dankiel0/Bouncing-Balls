package main;

import components.Screen;
import gui.AppGUI;

/**
 * @author Daniel
 * 
 * This is the main class.
 * It initializes the GUI and the thread that repaints the screen.
 */

public class BallsApp {
	// Stop anyone from initializing this class.
	private BallsApp() {}
	
	public static void main(String[] args) {
		AppGUI.getInstance();
		
		// This thread repaints the screen every 15 milliseconds.
		new Thread(() -> {
			while(true) {
				Screen.getInstance().repaint();
				
				long timeElapsed;
				final long startTime = System.nanoTime();
				
				do {
					timeElapsed = System.nanoTime() - startTime;
				} while(timeElapsed < 11111111);
			}
		}).start();
	}
}
