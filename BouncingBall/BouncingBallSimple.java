package BouncingBall;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class BouncingBallSimple extends JPanel {

	private static final int BOX_WIDTH = 350;
	private static final int BOX_HEIGHT = 700;


	private float ballRadius = 70;

	private float ballX[] = {ballRadius + 50,ballRadius + 110}; 
	private float ballY[] = {ballRadius + 20,ballRadius + 260};
	private static double ballSpeedX[] = {10,-20}; 
	private static double ballSpeedY[] = {10,-20};


	public BouncingBallSimple() {
		this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

		Thread gameThread = new Thread() {
			public void run() {
				
				while (true) { 
					if(((ballX[0]-ballX[1])*(ballX[0]-ballX[1])+(ballY[0]-ballY[1])*(ballY[0]-ballY[1]))<=(ballRadius)*(ballRadius)) {
						double degree = Math.atan((ballY[0]-ballY[1]) / (ballX[0]-ballX[1]));
						if(ballX[0]>ballX[1]||ballY[0]>ballY[1]) {
							
							ballSpeedX[0] = -ballSpeedX[0]*0.9;
							ballSpeedY[0] = -ballSpeedY[0]*0.9;

							ballSpeedX[1] = -ballSpeedX[1]*0.9;
							ballSpeedY[1] = -ballSpeedY[1]*0.9;

						}
						else if(ballX[0]>ballX[1]||ballY[0]<ballY[1]) {
							ballSpeedX[0] = -ballSpeedX[0]*0.9;
							ballSpeedY[0] = -ballSpeedY[0]*0.9;

							ballSpeedX[1] = -ballSpeedX[1]*0.9;
							ballSpeedY[1] = -ballSpeedY[1]*0.9;
						}
						else if(ballX[0]<ballX[1]||ballY[0]>ballY[1]) {
							ballSpeedX[0] = -ballSpeedX[0]*0.9;
							ballSpeedY[0] = -ballSpeedY[0]*0.9;

							ballSpeedX[1] = -ballSpeedX[1]*0.9;
							ballSpeedY[1] = -ballSpeedY[1]*0.9;
						}
						else {
							ballSpeedX[0] = -ballSpeedX[0]*0.9;
							ballSpeedY[0] = -ballSpeedY[0]*0.9;

							ballSpeedX[1] = -ballSpeedX[1]*0.9;
							ballSpeedY[1] = -ballSpeedY[1]*0.9;
						}
					}
					
					
					for(int i=0;i<2;i++) {
					
						
					if(ballSpeedX[i]>0) {
						
						ballSpeedX[i] = ballSpeedX[i] - 0.05;
						if (ballSpeedX[i]<=0) {
							ballSpeedX[i] = 0;
							
						}
						else if (ballX[i] + ballRadius/100 >= BOX_WIDTH) {
							ballSpeedX[i] = -ballSpeedX[i];
							ballX[i] = BOX_WIDTH-ballRadius/100;
						}
						
					}else if(ballSpeedX[i]<0) {
						ballSpeedX[i] = ballSpeedX[i] + 0.05;
						if (ballSpeedX[i]>=0) {
							ballSpeedX[i] = 0;
							
						}
						else if (ballX[i] - ballRadius < 0) {
							ballSpeedX[i] = -ballSpeedX[i];
							ballX[i] = ballRadius;
						} 
						
					}
					if(ballSpeedY[i]>0) {

						ballSpeedY[i] = ballSpeedY[i] - 0.05;
						if (ballSpeedY[i]<=0) {
							ballSpeedY[i] = 0;
							
						}
						else if (ballY[i] + ballRadius/1000 >= BOX_HEIGHT) {
							ballSpeedY[i] = -ballSpeedY[i];
							ballY[i] = BOX_HEIGHT-ballRadius/100;
						}
						
					}else if(ballSpeedY[i]<0){

						ballSpeedY[i] = ballSpeedY[i] + 0.05;
						if (ballSpeedY[i]>=0) {
							
							ballSpeedY[i] = 0;
							
							
						}
						else if (ballY[i] - ballRadius <= 0) {
							ballSpeedY[i] = -ballSpeedY[i]; 
							ballY[i] = ballRadius;
						}
						
						
					}
					
					ballX[i] += ballSpeedX[i];
					ballY[i] += ballSpeedY[i];
					
					repaint(); // 回傳 paintComponent()
					if(ballSpeedY[0]==0&&ballSpeedY[0]==0&&ballSpeedY[1]==0&&ballSpeedY[1]==0) {
						break;
					}
					}
					
					try {
						Thread.sleep(10 );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

			gameThread.start();	
		
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
     	g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     	g2d.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2d.setColor(Color.BLUE);
		g2d.fillOval((int) (ballX[0] - ballRadius), (int) (ballY[0] - ballRadius), (int) (ballRadius),(int) ( ballRadius));	
		g2d.setColor(Color.red);
		g2d.fillOval((int) (ballX[1] - ballRadius), (int) (ballY[1] - ballRadius), (int) (ballRadius),(int) ( ballRadius));	
	}


	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("減速");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BouncingBallSimple());				
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}