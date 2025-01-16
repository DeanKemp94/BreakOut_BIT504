import java.awt.Rectangle;

// This is the base (super class) class for other objects used in the programme
public class Sprite {
	
	protected int x,y,width,height; // position x/y and dimension width/height of the sprite

	// This sets the X coordinate of the sprite
	public void setX(int x) {
		this.x = x; // updates the x position
	}

	// This sets the Y coordinate of the sprite
	public void setY(int y) {
		this.y = y; // updates the y position
	}

	// This sets the width of the sprite
	public void setWidth(int width) {
		this.width =width; // updates the sprites width
	}

	// This sets the height of the sprite
	public void setHeight(int height) {
		this.height = height; // updates the sprites height
	}

	// Getter for X coordinates of sprite
	public int getX() {
		return x; // returns the current x position
	}

	// Getter for y coordinates of sprite
	public int getY() { 
		return y; // returns the current y position
	}

	// Getter for width of sprite
	public int getWidth() { 
		return width; // Returns the current width
	}

	// Getter for height of sprite
	public int getHeight() { 
		return height; // returns the height of the sprite
	}

	//Creates and returns a rectangle object that represents the sprites bounds
	Rectangle getRectangle() {
		return new Rectangle(x, y, width, height); // Encapsulates the sprites position and size
	}
}
