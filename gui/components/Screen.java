package components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import ball.Ball;

/**
 * @author Daniel
 * 
 * This JPanel subclass is what the balls will be drawn on.
 */

public class Screen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static Screen screen;
	
	private static ArrayList<Ball> balls;
	
	private final short appWidth, appHeight;
	
	// Standard singleton pattern.
	private Screen() {
		appWidth = 1280;
		appHeight = 720;
		
		balls = new ArrayList<Ball>();
		setPreferredSize(new Dimension(appWidth, appHeight));
	}
	
	public static Screen getInstance() {
		if(screen == null)
			screen = new Screen();
		
		return screen;
	}
	
	public static ArrayList<Ball> getBalls() {
		return balls;
	}
	
	public short getAppWidth() {
		return appWidth;
	}
	
	public short getAppHeight() {
		return appHeight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
		// For better drawing.
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paintComponent(graphics);
		
		// Draws all the balls in the balls array.
		balls.forEach(ball -> {
			ball.move();
			ball.draw(graphics);
		});
		
		graphics.dispose();
		g.dispose();
	}
	
	// If editable is true, the method moves the ball to the inside of the screen.
	// Otherwise, the method just returns whether the ball collides with the sides of the screen.
	public boolean collisionDetectionWithBorder(Ball ball, boolean editable) {
		byte count = 0;
		
		if(ball.getX() < 0) {
			if(editable) ball.setX(0);
			count++;
		}
		
		if(ball.getX() + ball.getDiameter() > appWidth) {
			if(editable) ball.setX(appWidth - ball.getDiameter());
			count++;
		}
		
		if(ball.getY() < 0) {
			if(editable) ball.setY(0);
			count++;
		}
		
		if(ball.getY() + ball.getDiameter() > appHeight) {
			if(editable) ball.setY(appHeight - ball.getDiameter());
			count++;
		}
		
		return count > 0;
	}
}
