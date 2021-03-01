package mouseEvents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ball.Ball;
import components.Screen;

/**
 * @author Daniel
 * 
 * This class checks for the user's mouse clicks.
 */

public class UserMouseListener extends MouseAdapter {
	// Standard singleton pattern.
	private static UserMouseListener listener;
	
	private UserMouseListener() {}
	
	public static UserMouseListener getInstance() {
		if(listener == null)
			listener = new UserMouseListener();
		
		return listener;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		// If the left mouse button is clicked, a new ball is created.
		// The ball object will have a diameter of 50 and the position of the mouse pointer.
		// The ball object will have a gravity of -0.95.
		if(SwingUtilities.isLeftMouseButton(event)) {
			Ball ball = new Ball((byte) 50, (short) event.getX(), (short) event.getY(), -0.95f);
			
			Screen.getInstance().collisionDetectionWithBorder(ball, true);
			
			Screen.getBalls().add(ball);
		}
		
		// If the right mouse button is clicked, any balls underneath the mouse pointer will get deleted.
		if(SwingUtilities.isRightMouseButton(event))
			Screen.getBalls().removeIf(ball -> ball.ifMouseHoversOverBall((short) event.getX(), (short) event.getY()));
	}
}
