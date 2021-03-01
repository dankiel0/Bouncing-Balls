package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import components.Screen;
import mouseEvents.UserMouseListener;

/**
 * @author Daniel
 * 
 * This class puts together the GUI.
 * It displays a user message and then the frame for the app.
 */

public class AppGUI {
	// Standard singleton pattern.
	private static AppGUI appGUI;
	
	private AppGUI() {
		initGUI();
		showMessage();
	}
	
	public static AppGUI getInstance() {
		if(appGUI == null)
			appGUI = new AppGUI();
		
		return appGUI;
	}
	
	private void initGUI() {
		// Sets the GUI's look and feel to the windows style.
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException event) {
			event.printStackTrace();
		}
		
		JFrame window = new JFrame();
		
		window.getContentPane().addMouseListener(UserMouseListener.getInstance());
		window.getContentPane().add(Screen.getInstance());
		
		window.setTitle("Physics Simulation");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	private void showMessage() {
		JOptionPane.showMessageDialog(null, "Left click to create a new ball. \nRight click on a ball to delete it.", "User Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
