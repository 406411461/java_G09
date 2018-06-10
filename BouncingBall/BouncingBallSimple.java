package BouncingBall;

import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

/**
 * One ball bouncing inside a rectangular box. All codes in one file. Poor
 * design!
 */
// Extends JPanel, so as to override the paintComponent() for custom rendering
// codes.
public class BouncingBallSimple extends JPanel {
	// Container box's width and height
	private static final int BOX_WIDTH = 640;
	private static final int BOX_HEIGHT = 480;

	// Ball's properties
	private float ballRadius = 100; // Ball's radius
	private float ballX = ballRadius + 50; // Ball's center (x, y)
	private float ballY = ballRadius + 20;
	private static double ballSpeedX = 10; // Ball's speed for x and y
	private static double ballSpeedY = 10;
   
	private static final int UPDATE_RATE = 10; // Number of refresh per second

	/** Constructor to create the UI components and init game objects. */
	public BouncingBallSimple() {
		this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

		// Start the ball bouncing (in its own thread)
		Thread gameThread = new Thread() {
			public void run() {
				
				while (true) { // Execute one update step>
					// Calculate the ball's new position
					if(ballSpeedX==0&&ballSpeedY==0) {
						break;
					}
					
					if(ballSpeedX>0) {
						System.out.println("true");
						ballSpeedX = ballSpeedX - 0.01;
						if (ballSpeedX<=0) {
							ballSpeedX = 0;
							System.out.println("0");
							
						}
						else if (ballX + ballRadius > BOX_WIDTH) {
							ballSpeedX = -ballSpeedX;
							ballX = BOX_WIDTH - ballRadius;
						}
						
					}else if(ballSpeedX<0) {
						System.out.println("false");
						ballSpeedX = ballSpeedX + 0.01;
						if (ballSpeedX>=0) {
							ballSpeedX = 0;
							System.out.println("0");
							
						}
						else if (ballX - ballRadius < 0) {
							ballSpeedX = -ballSpeedX; // Reflect along normal
							ballX = ballRadius; // Re-position the ball at the edge
						} 
						
					}
					if(ballSpeedY>0) {
						System.out.println("false");
						ballSpeedY = ballSpeedY - 0.01;
						if (ballSpeedY<=0) {
							ballSpeedY = 0;
							System.out.println("0");
							
						}
						else if (ballY + ballRadius > BOX_HEIGHT) {
							ballSpeedY = -ballSpeedX;
							ballY = BOX_HEIGHT - ballRadius;
						}
						
					}else if(ballSpeedY<0){
						System.out.println("false");
						ballSpeedY = ballSpeedY + 0.01;
						if (ballSpeedY>=0) {
							
							ballSpeedY = 0;
							
							System.out.println(1);
							
						}
						else if (ballY - ballRadius <= 0) {
							ballSpeedY = -ballSpeedY; // Reflect along normal
							ballX = ballRadius; // Re-position the ball at the edge
						}
						
						
					}
					
					ballX += ballSpeedX;
					ballY += ballSpeedY;
					repaint(); // Callback paintComponent()
					// Delay for timing control and give other threads a chance
					try {
						Thread.sleep(10 ); // milliseconds
					} catch (InterruptedException ex) {
					}
				}
			}
		};
//		if(ballSpeedX!=0||ballSpeedY!=0) {
			gameThread.start();	
//		s
//		}
//		if(ballSpeedX)	{	
//			System.out.printf("STOP");
//			gameThread.stop();
//		}
		
	}

	/** Custom rendering codes for drawing the JPanel */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Paint background

		// Draw the box
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

		// Draw the ball
		g.setColor(Color.BLUE);
		g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius), (int) (2 * ballRadius),
				(int) (2 * ballRadius));

		// Display the ball's information
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.PLAIN, 12));
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("Ball @(%3.0f,%3.0f) Speed=(%2.0f,%2.0f)", ballX, ballY, ballSpeedX, ballSpeedY);
		g.drawString(sb.toString(), 20, 30);
	}

	/** main program (entry point) */
	public static void main(String[] args) {
		// Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Set up main window (using Swing's Jframe)
				JFrame frame = new JFrame("zz");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BouncingBallSimple());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}