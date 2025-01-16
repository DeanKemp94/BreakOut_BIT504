import java.awt.*;

import javax.swing.JFrame;

// main class for Breakout game, this is extending JFram to create the game window
public class Breakout extends JFrame{
	
	static final long serialVersionUID = 1L; // this ensures compatibility
	
	private BreakoutPanel panel; // This is the game panel for rendering and gameplay

	public Breakout() {
		setTitle(Settings.WINDOW_NAME); // Sets the window title/game name
		setResizable(false); //prevents the user from resizing the window
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Allows the game to be closed

		panel = new BreakoutPanel(this);
		panel.setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
		add(panel); // adding the game panel to the window

		pack(); // adjusts window size to fit panel

		// Accounts for the window borders
		int extraWidth = getInsets().left + getInsets().right;
		int extraHeight = getInsets().top + getInsets().bottom;
		setSize(Settings.WINDOW_WIDTH + extraWidth, Settings.WINDOW_HEIGHT + extraHeight);

		setLocationRelativeTo(null); // centers the window for convenience

		setVisible(true); //Makes the window visible

		// This is debug info for issue with Jfame not sizing the window correctly (See Githib patch 1.1 for more info on issue)
		System.out.println("Window Size: " + getSize());
		System.out.println("Content Pane Size: " + getContentPane().getSize());
		System.out.println("Insets: " + getInsets());
	}

	// Main method to start teh game
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
				 new Breakout(); // Launches the game
	         }
		});

	}
}
