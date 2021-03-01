package ball;

import java.awt.Color;
import java.awt.Graphics2D;

import components.Screen;

/**
 * @author Daniel
 * 
 * 
 */

public class Ball {
	private float x, y;
	
	private final byte diameter;
	
	private float velocityY = 2;
	
	private final float gravity;
	
	private int[] hexColors = {0x8b0000, 0xff8c00, 0xCCCC00, 0x306149, 0x4040cb, 0x8E4585};
	private Color color;
	
	public float getX() {
		return x;
	}
	
	public void setX(float xCoord) {
		x = xCoord;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float yCoord) {	
		y = yCoord;
	}
	
	public byte getDiameter() {
		return diameter;
	}
	
	public Ball(byte diameterSize, short xCoord, short yCoord, float gravityConst) {
		diameter = diameterSize;
		
		x = xCoord - diameter / 2;
		y = yCoord - diameter / 2;
		
		color = new Color(hexColors[(int) (Math.random() * hexColors.length)]);
		
		gravity = gravityConst;
	}
	
	public boolean ifMouseHoversOverBall(short xCoord, short yCoord) {
		byte count = 0;
		
		if(x <= xCoord)
			count++;
		
		if(x >= xCoord - diameter)
			count++;
		
		if(y <= yCoord)
			count++;
		
		if(y >= yCoord - diameter)
			count++;
		
		return count == 4;
	}
	
	public void draw(Graphics2D graphics) {
		graphics.setPaint(color);
		graphics.fillOval((int) x, (int) y, diameter, diameter);
	}
	
	public void move() {
		if(isVelocityNearZero(velocityY)) return;
		
		if(Screen.getInstance().collisionDetectionWithBorder(this, false)) {
			velocityY *= gravity;
			
			if(y + velocityY > Screen.getInstance().getAppHeight() - diameter) {
				float temp = y + velocityY - Screen.getInstance().getAppHeight() + diameter;
				
				for(byte i = 0; i < temp; i++)
					y--;
			}
		}
		else velocityY++;
		
		y += velocityY;
	}
	
	private boolean isVelocityNearZero(float velocity) {
		return Math.abs(velocity) <= 1.5 && y >= Screen.getInstance().getAppHeight() - diameter;
	}
}
