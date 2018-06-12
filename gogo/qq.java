package gogo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class qq extends Applet implements MouseListener, MouseMotionListener {
	int Ballcount = 10;
	double Trans_v = 3.0;
	double pocketSize2 = 4.0;
	double friction = 0.98;
	double minVel = 0.01;
	int redo = 20;
	int repainted = 1;
	// String tmptype, scoreString="", msgString= "";
	int mouseX, mouseY, drag_flag, points;
	double xmin, xmax, ymax, ymin, radius, rad2, vX, vY;
	double startX, startY, x, y, pointerx, pointery;

	double[][] pockets = new double[6][2];
	double ballposition[][] = new double[10][2];
	boolean frictionstop = true;
	Ball ball[] = new Ball[10];
	Color ballColor[] = new Color[10];

	public void start() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		xmin = 0.0;
		ymin = 0.0;
		xmax = 800.0;
		ymax = 400.0;
		radius = 1.1 * (xmax - xmin) / 67.0;

		rad2 = radius * radius * 4.0;
		startX = 0.75 * (xmax + xmin);
		startY = 0.5 * (ymax + ymin);
		// 以下做球袋的位子
		pockets[0][0] = xmin;
		pockets[1][0] = xmin;
		pockets[2][0] = xmax;
		pockets[3][0] = xmax;
		pockets[4][0] = (xmax - xmin) / 2;
		pockets[5][0] = (xmax - xmin) / 2;

		pockets[0][1] = ymin;
		pockets[1][1] = ymax;
		pockets[2][1] = ymin;
		pockets[3][1] = ymax;
		pockets[4][1] = ymin;
		pockets[5][1] = ymax;
		// scoreString="Points : 0";
		ballColor[0] = new Color(255, 255, 255);
		ballColor[1] = new Color(255, 255, 0);
		ballColor[2] = new Color(255, 0, 0);
		ballColor[3] = new Color(0, 0, 0);
		ballColor[4] = new Color(125, 125, 125);
		ballColor[5] = new Color(125, 125, 0);
		ballColor[6] = new Color(125, 0, 0);
		ballColor[7] = new Color(100, 100, 100);
		ballColor[8] = new Color(0, 255, 255);
		ballColor[9] = new Color(0, 255, 255);

		ballposition[0][0] = startX;
		ballposition[0][1] = startY;

		ballposition[1][0] = 1 * (xmax + xmin) / 3;
		ballposition[1][1] = (ymax + ymin) / 2;

		ballposition[2][0] = ballposition[1][0] - 2 * radius;
		ballposition[2][1] = ballposition[1][1] + 1.5 * radius;
		ballposition[3][0] = ballposition[1][0] - 2 * radius;
		ballposition[3][1] = ballposition[1][1] - 1.5 * radius;

		ballposition[4][0] = ballposition[1][0] - 4.0 * radius;
		ballposition[4][1] = ballposition[1][1] + 3.0 * radius;
		ballposition[5][0] = ballposition[1][0] - 4.0 * radius;
		ballposition[5][1] = ballposition[1][1];
		ballposition[6][0] = ballposition[1][0] - 4.0 * radius;
		ballposition[6][1] = ballposition[1][1] - 3.0 * radius;

		ballposition[7][0] = ballposition[1][0] - 6.0 * radius;
		ballposition[7][1] = ballposition[1][1] + 1.5 * radius;
		ballposition[8][0] = ballposition[1][0] - 6.0 * radius;
		ballposition[8][1] = ballposition[1][1] + 1.5 * radius;
		ballposition[9][0] = ballposition[1][0] - 8.0 * radius;

		ballposition[9][1] = ballposition[1][1];

		ball[0] = new Ball(x, y, 0);
		for (int i = 0; i < Ballcount; i++) {
			ball[i] = new Ball(ballposition[i][0], ballposition[i][1], ballColor[i], i);
		}
		repaint();

	}

	public void paint(Graphics g) {

		g.setClip(0, 0, 800, 400);
		setBackground(new Color(0, 100, 0));
		g.setColor(Color.BLACK);

		for (int i = 0; i < 6; i++) {
			drawcir(g, pockets[i][0], pockets[i][1], radius * 1.5);
		}

		g.drawRect(0, 0, 800, 400);

		for (int i = 0; i < Ballcount; i++) {
			Color a = ball[i].getColor();
			g.setColor(a);
			drawcir(g, ball[i].getX(), ball[i].getY(), radius);
		}
		if (drag_flag > 0) {
			g.setColor(Color.lightGray);
			g.drawLine((int) ball[0].getX(), (int) ball[0].getY(), (int) mouseX, (int) mouseY);
		}

	}

	public void drawcir(Graphics g, double x, double y, double r) {
		int ix = (int) x;
		int iy = (int) y;
		int radius = (int) r;
		g.fillOval(ix - radius, iy - radius, 2 * radius, 2 * radius);
	}

	public void mousePressed(MouseEvent e) {
		drag_flag = 1;
	}

	public void mouseReleased(MouseEvent e) {
		drag_flag = 0;
		vX = (ball[0].getX() - mouseX) / Trans_v;
		vY = (ball[0].getY() - mouseY) / Trans_v;
		ball[0].roll(vX, vY);
		repaint();
	}

	class Ball {
		double velX, velY;
		double x, y;
		Color color;
		int thisBall;

		int count;
		boolean running = false;
		boolean stop = false;

		public Ball(double x, double y, int ib) {
			this.x = x;
			this.y = y;
			this.thisBall = ib;
		}

		public Ball(double x, double y, Color color, int ib) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.thisBall = ib;
		}

		public void setXY(double x, double y) {
			this.x = x;
			this.y = y;
			return;
		}

		public void addXY(double x, double y) {
			this.x += x;
			this.y += y;
			return;
		}

		public void addVel(double velX, double velY) {
			this.velX += velX;
			this.velY += velY;
			return;
		}

		public void setVel(double velX, double velY) {
			this.velX = velX;
			this.velY = velY;
			return;
		}

		public void setColor(Color color) {
			this.color = color;
			return;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public double getVelX() {
			return x;
		}

		public double getVelY() {
			return y;
		}

		public Color getColor() {
			return color;
		}

		public boolean getRunning() {
			return running;
		}

		Thread myThread;

		public void roll(double vX, double vY) {

			velX = vX;
			velY = vY;

			myThread = new Thread(new Angel());
			myThread.start();

		}

		public void stop() {

			// x = startX;
			// y = startY;
			velX = 0.0;
			velY = 0.0;

			running = false;
			stop = true;
		}

		class Angel implements Runnable {

			public void run() {

				running = true;
				stop = false;

				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

				while (!stop) { // Start of infinite loop
					try {
						Thread.sleep(redo);
					} catch (InterruptedException e) {
					}

					count++;

					// Apply friction
					velX *= friction;
					velY *= friction;

					// Stop calculating if running too slowly
					if (((velX > -minVel) && (velX < minVel)) && ((velY > -minVel) && (velY < minVel))) {
						stop = true;
						running = false;
					}

					// Move the ball
					x += velX;
					y += velY;

					// Check for downing

					pockets: for (int i = 0; i < 6; i++) {
						if (thisBall == 0)
							continue;
						if ((x - pockets[i][0]) * (x - pockets[i][0]) < pocketSize2) {
							if ((y - pockets[i][1]) * (y - pockets[i][1]) < pocketSize2) {
								x = pockets[i][0];
								y = pockets[i][1];
								points += thisBall % 9;
								if (thisBall == 0) {
									points = 0;
									System.out.println("Scratch. Total points : " + points);
								
							
								}
								stop = true;
								repaint();
								break pockets;
							}
						}

					}

					if (stop)
						continue;

					// Check for collision with other balls

					synchronized (ball) { // Thread lock
						for (int i = 0; i < Ballcount; i++) {

							double dx = x - ball[i].getX();
							double dy = y - ball[i].getY();
							double dx2 = dx * dx;
							double dy2 = dy * dy;

							if (i == thisBall)
								continue; // Same ball

							if (dx2 > rad2)
								continue; // No collision
							if (dx2 + dy2 > rad2)
								continue; // No collision

							// Remove sign from separation vectors
							double adx = Math.abs(dx);
							double ady = Math.abs(dy);

							double norm = adx + ady;

							// Normalise separation vectors
							double cx = adx / norm;
							double cy = ady / norm;

							// And multiply incoming velocity vectors by them
							double velAddX = cx * velX * 0.9;
							double velAddY = cy * velY * 0.9;

							// Push the ball away to avoid another hit
							// ball[i].addXY(-dx/2.0,-dy/2.0);
							ball[i].addXY(-dx / 4.0, -dy / 4.0);

							// Add transfer vector to new ball if already rolling
							if (ball[i].running) {
								ball[i].addVel(velAddX, velAddY);

								// Or apply the vector if it's not moving yet
							} else {
								ball[i].roll(velAddX, velAddY);
							}

							// And subtract the rest from the current ball
							velX -= velAddX;
							velY -= velAddY;

						} // End loop over balls

					} // End thread lock

					// Check for wall bounce

					if (x < (xmin + radius)) {
						velX = -velX;
						x += radius;
					}
					if (x > (xmax - radius)) {
						velX = -velX;
						x -= radius;
					}

					if (y < (ymin + radius)) {
						velY = -velY;
						y += radius;
					}
					if (y > (ymax - radius)) {
						velY = -velY;
						y -= radius;
					}

					// repaint sometimes
					if (count >= repainted) {
						count = 0;
						repaint();
					}

				} // End infinite loop

				velX=0.0;
				velY=0.0;
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		repaint();

	}

	public static void main(String[] args) {
		JFrame app = new JFrame("Snooker");
		app.setSize(900, 500);
		qq a = new qq();
		app.add(a);
		a.start();
		app.setVisible(true);
	}
}
