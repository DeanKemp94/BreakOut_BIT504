import java.awt.Graphics;

// This class extends the Sprite class and represents the paddle object in the game
public class Paddle extends Sprite {

	private int xVelocity; // this is used for the speed and direction of the paddle
	
	public Paddle() {
		setWidth(Settings.PADDLE_WIDTH); // setting the paddle width referencing the setting class
		setHeight(Settings.PADDLE_HEIGHT); // setting the paddle height referencing the setting class
		resetPosition(); // setting the paddle starting position
	}
	
	public void resetPosition() {
		setX(Settings.INITIAL_PADDLE_X); // setting the paddles initial x postion
		setY(Settings.INITIAL_PADDLE_Y); // setting the paddles initial t position
	}
	
	public void update() {
		x += xVelocity; // This moves the paddle horizontally
		// This is stopping the ball from leaving the left of the screen
		if (x < 0){
			x = 0;
		}

		// This is stopping the ball from leaving the right of the screen
		if(x > Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH){
			x = Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH; // This stops the paddle at the right edge of the screen
		}
	}

	// This draws the paddle on the screen using the g graphics object for rendering
	public void paint(Graphics g) {
		g.fillRect(x, y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
	}

	// This is setting the paddles horizontal velocity
	public void setXVelocity(int vel) {
		xVelocity = vel;
	}
}
