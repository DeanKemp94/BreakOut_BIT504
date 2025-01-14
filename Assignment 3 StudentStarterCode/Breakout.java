import java.awt.*;

import javax.swing.JFrame;

public class Breakout extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	private BreakoutPanel panel;

	public Breakout() {
		setTitle(Settings.WINDOW_NAME);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new BreakoutPanel(this);
		panel.setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
		add(panel);

		pack();

		int extraWidth = getInsets().left + getInsets().right;
		int extraHeight = getInsets().top + getInsets().bottom;
		setSize(Settings.WINDOW_WIDTH + extraWidth, Settings.WINDOW_HEIGHT + extraHeight);

		setLocationRelativeTo(null);

		setVisible(true);

		System.out.println("Window Size: " + getSize());
		System.out.println("Content Pane Size: " + getContentPane().getSize());
		System.out.println("Insets: " + getInsets());
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
				 new Breakout();
	         }
		});

	}
}
