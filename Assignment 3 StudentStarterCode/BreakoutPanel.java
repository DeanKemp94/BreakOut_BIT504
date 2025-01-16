import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

// Panel for the Breakout game, this handles the gameplay logic and rendering
public class BreakoutPanel extends JPanel implements ActionListener, KeyListener {
	
	static final long serialVersionUID = 2L;

	private boolean gameRunning = true; //track the game play running state
	private int livesLeft = 3; // tracks the players remaining lives
	private String screenMessage = ""; // Empty string is used as variable to handle any messages displayed on screen
	private Ball ball; // Ball object
	private Paddle paddle; // Paddle object
	private Brick bricks[]; //Array that holds the bricks for the game
	
	public BreakoutPanel(Breakout game) {
		
		addKeyListener(this); // adds key listeners for keys pressed
		setFocusable(true); // allows keyboard focus
		
		Timer timer = new Timer(5, this); // creates timer for updates
		timer.start();

		ball = new Ball(); // initializes the ball object

		paddle = new Paddle(); // initializes the paddle object

		bricks = new Brick[Settings.TOTAL_BRICKS]; // Initializes the brick array

		createBricks(); //creates bricks
	}
	
	private void createBricks() {
		int counter = 0; // tracking current index in bricks arrray
		int x_space = 0; // spacing between the bricks horizontally
		int y_space = 0; // spacing between the bricks vertically

		// The outer loop for the row of bricks (There are 4 rows)
		for(int x = 0; x < 4; x++) {
			// the inner loop for the colums of the bricks ( there are 5 colums per row)
			for(int y = 0; y < 5; y++) {
				// creates a new brick at each position
				bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
				counter++; // moving through the bricks array
				y_space++; // adding vertical space between the bricks
			}
			x_space++; // adding horizontal space between the bricks
			y_space = 0; // this is reseting the spacing for the next row
		}
	}
	
	private void paintBricks(Graphics g) {
		// draws the unbroken bricks
		for(Brick brick : bricks) {
			if(!brick.isBroken()){
				brick.paint(g);
			}
		}
	}
	
	private void update() {
		if(gameRunning) {
			ball.update(); // update sthe balls position
			paddle.update(); // updates the paddles position
			collisions(); // Checks if there are any collisions
			repaint(); // Refreshes teh screen
		}
	}

	// This methods purpose is to reset the game when the game state changes to win/loose
	private void resetGame() {
		gameRunning = true;
		livesLeft = 3;
		screenMessage = "";
		ball.resetPosition();
		paddle.resetPosition();
		for(Brick brick : bricks){
			brick.setBroken(false); // resets all the bricks
		}
	}
	
	private void gameOver() {
		screenMessage = "Game Over! Press R to restart"; // uses empty variable to insert game over message and restart
		stopGame();
	}
	
	private void gameWon() {
		screenMessage = "You Won! Press R to restart."; // uses empty variable to insert game won and restart
		stopGame();
	}
	
	private void stopGame() {
		gameRunning = false; // stops any game updates
	}
	
	private void collisions() {
		// Check for loss
		if(ball.y > 450) { // this does a check to see if the ball has fallen below the paddle and if it is, deducts a life and reset the ball position
			// Game over
			livesLeft--;
			if(livesLeft <= 0) {
				gameOver();
				return;
			} else {
				ball.resetPosition();
				ball.setYVelocity(-1);
			}
		}
		
		// Check for win
		boolean bricksLeft = false;
        for (Brick value : bricks) {
            // Check if there are any bricks left
            if (!value.isBroken()) {
                // Brick was found, close loop
                bricksLeft = true;
                break;
            }
        }
		if(!bricksLeft) {
			gameWon();
			return;
		}
		
		// Check collisions
		if(ball.getRectangle().intersects(paddle.getRectangle())) { // ball hits the paddle
			ball.setYVelocity(-1); // this reverses the balls Y position if the ball hits the paddle
		}


        for (Brick brick : bricks) { // check if the ball collides with any of the current bricks
            if (ball.getRectangle().intersects(brick.getRectangle())) { // getting the ball position and size
                int ballLeft = (int) ball.getRectangle().getMinX();
                int ballHeight = (int) ball.getRectangle().getHeight();
                int ballWidth = (int) ball.getRectangle().getWidth();
                int ballTop = (int) ball.getRectangle().getMinY();

				// Defines the points slightlu outside of the balls rectagle to check for colition
                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

				// this only proceeds if the brick is not already broken
                if (!brick.isBroken()) {
					//check if the ball hit the right side of the brick
                    if (brick.getRectangle().contains(pointRight)) {
                        ball.setXVelocity(-1); // reverses the balls x direction left
					// checks if the ball hit the left side of the brick
                    } else if (brick.getRectangle().contains(pointLeft)) {
                        ball.setXVelocity(1); // reverses the balls x direction right
                    }

					// checking if the ball hit the top of the brick
                    if (brick.getRectangle().contains(pointTop)) {
                        ball.setYVelocity(1); // reverses the balls y direction down
					// checks if the ball hit the bottom of the brick
                    } else if (brick.getRectangle().contains(pointBottom)) {
                        ball.setYVelocity(-1); // // reverses the balls y direction upwards
                    }
                    brick.setBroken(true); // marks the brick as broken if collition is made
                }
            }
        }
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ball.paint(g); // draws the ball
        paddle.paint(g); // draws the paddle
        paintBricks(g); // Draws the bricks
        
        // Draw lives left
		g.setFont(new Font("Arial", Font.PLAIN,14)); // set the font and size text
		g.drawString("Lives: " + livesLeft, Settings.LIVES_POSITION_X, Settings.LIVES_POSITION_Y); // draws the lives and positions it by references the setting class
        
        // Draw screen message
        if(!screenMessage.isEmpty()) {
        	g.setFont(new Font("Arial", Font.BOLD, 18)); // sets the font and size of text
        	int messageWidth = g.getFontMetrics().stringWidth(screenMessage);
        	g.drawString(screenMessage, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION); // draws the message and positions it by references the setting class
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) { // set the keyboard keys used to move paddle left
			paddle.setXVelocity(-5); // moves paddle left and sets the speed it does that
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // set the keyboard keys used to move paddle right
			paddle.setXVelocity(5); // moves paddle right  and sets the speed it does that
		}
		if(e.getKeyCode() == KeyEvent.VK_R){ // sets the key used to restart the game
			resetGame(); // invokes the restart game method when R is pressed
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT){
			paddle.setXVelocity(0); // stops paddle movement
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//not used
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update(); // performs game update method
	}

}
