import java.awt.Graphics;

// This class extends the spirit class and is a represents the ball in teh game
public class Ball extends Sprite {

	private int xVelocity = 1, yVelocity = -1; // the ball movement speed in both x and y direction
	
	// Constructor
	public Ball() {
		setWidth(Settings.BALL_WIDTH); // setting the ball width, referencing the setting class
		setHeight(Settings.BALL_HEIGHT); // setting the ball height, referencing the setting class
		resetPosition(); // resets the balls initial position
	}
	
	/**
	 * Resets the ball to the initial position
	 * Uses Settings.INITIAL_BALL_X/Y to set the position of the ball
	 */
	public void resetPosition() {
		setX(Settings.INITIAL_BALL_X); // setting the balls X coordinates
		setY(Settings.INITIAL_BALL_Y); // setting the balls Y coordinates
	}
	
	public void update() {
		x += xVelocity; //  Moves the ball horizontally
		y += yVelocity; // moves the ball vertically
		
		// Bounce off left side of screen
		if(x <= 0) {
			x = 0;
			xVelocity = Math.abs(xVelocity); // this reverses x direction to move right
			System.out.println("Left bounce: x=" + x + ", xVelocity=" + xVelocity); // is used as a debug for issue with Jframe moving ball off-screen (see Github patch 1.1)
		}
		
		// Bounce off right side of screen
		if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH) {
			x = Settings.WINDOW_WIDTH - Settings.BALL_WIDTH; // keeping ball withing the boarders
			xVelocity = -Math.abs(xVelocity); // this reverses x direction to move left
			System.out.println("Right bounce: x=" + x + ", xVelocity=" + xVelocity); // is used as a debug for issue with Jframe moving ball off-screen (see Github patch 1.1)
		}
		
		// Bounce off top of screen
		if(y <= 0) {
			y = 0; // used to keep ball withing boarders
			yVelocity = Math.abs(yVelocity); // This reverses Y direction to move downward
			System.out.println("Top bounce: y=" + y + ", yVelocity=" + yVelocity); // is used as a debug for issue with Jframe moving ball off-screen (see Github patch 1.1)
		}
	}

	// set the X velocity of the ball
	public void setXVelocity(int x) {
		xVelocity = x;
	}

	// set the Y velocity of the ball
	public void setYVelocity(int y) {
		yVelocity = y;
	}

	// Get the current X velocity of the ball
	public int getXVelocity() {
		return xVelocity;
	}

	// Get the current Y velocity of the ball
	public int getYVelocity() {
		return yVelocity;
	}

	// This draws the ball on the screen using the g graphics object for rendering
	public void paint(Graphics g) {
		g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
	}
}
