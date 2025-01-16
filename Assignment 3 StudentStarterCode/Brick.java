import java.awt.Graphics;

// This class extends the Sprite calls and represents the brick object in the game
public class Brick extends Sprite {
	
	private boolean broken = false; // used to the stat of the brick (Broken/not broken)

	// This is the constructor to initialize the bricks position and size
	public Brick(int x, int y) {
		setX(x); //Sets the bricks X coordinates
		setY(y); //Sets the bricks Y coordinates
		setWidth(Settings.BRICK_WIDTH); // Sets the bricks width
		setHeight(Settings.BRICK_HEIGHT); // Sets the bricks height
	}

	// This method is used to check if the brick if broken
	public boolean isBroken() {
		return broken;
	}

	// This methods marks the brick as broken or not broken
	public void setBroken(boolean b) {
		broken = b;
	}

	// This draws the bricks on the screen using the g graphics object for rendering
	public void paint(Graphics g) {
		if(!broken) {
			g.fillRect(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
		}
	}
}
