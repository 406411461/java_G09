package gogo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class qq extends Applet implements MouseListener, MouseMotionListener {
	int Ballcount = 2;
	double pocketSize2 = 4.0;
	double minv = 0.01;
	int redo = 20;
	int repainted = 1;
	int mouseX, mouseY, drag_flag;
	double xmin, xmax, ymax, ymin, radius, rad2, vx, vy;
	double x, y, i, j;

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
		radius = 12;
		rad2 = radius * radius * 4.0;
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

		ballColor[0] = new Color(255, 255, 255);
		ballColor[1] = new Color(255, 255, 0);

		x = 600;
		y = 200;
		ballposition[0][0] = x;
		ballposition[0][1] = y;
		i = 266;
		j = 200;
		ballposition[1][0] = i;
		ballposition[1][1] = j;

		ball[0] = new Ball(x, y);
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
			drawcir(g, pockets[i][0], pockets[i][1], radius * 2);
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
		vx = (ball[0].getX() - mouseX) / 3;
		vy = (ball[0].getY() - mouseY) / 3;
		ball[0].move(vx, vy);
		repaint();
	}

	class Ball {
		double momvx, momvy, nonvx, nonvy;
		double x, y;
		Color color;
		int thisBall;

		int count;
		boolean running = false;
		boolean stop = false;

		public Ball(double x, double y, Color color, int ib) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.thisBall = ib;
		}

		public Ball(double x2, double y2) {
			this.x = x2;
			this.y = y2;

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
			this.momvx += momvx;
			this.momvy += momvy;
			return;
		}

		public void setVel(double velX, double velY) {
			this.momvx = velX;
			this.momvy = velY;
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

		public void move(double vX, double vY) {

			momvx = vx;
			momvy = vy;
			myThread = new Thread(new gg());
			myThread.start();

		}

		class gg implements Runnable {

			public void run() {
				running = true;
				while (running) { // Start of infinite loop
					try {
						Thread.sleep(redo);
					} catch (InterruptedException e) {
					}

					count++;

					// Apply friction
					momvx *= 0.98;
					momvy *= 0.98;
					nonvy *= 0.98;
					nonvx *= 0.98;
					// Stop calculating if running too slowly
					if (((momvx > -minv) && (momvx < minv)) && ((momvy > -minv) && (momvy < minv))) {
						running = false;
					}

					// Move the ball
					x += momvx;
					y += momvy;
					i += nonvx;
					j += nonvy;
					if (x < 12) {
						momvx = -momvx;
						x += radius;
					}
					if (x > 788) {
						momvx = -momvx;
						x -= radius;
					}

					if (y < 12) {
						momvy = -momvy;
						y += radius;
					}
					if (y > 388) {
						momvy = -momvy;
						y -= radius;
					}

					if (Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2)) < 24) {
						System.out.print("0");
						double degree = Math.atan((x - i) / (y - j));
						if (x > i) {
							vx = vx * Math.cos(degree);
							vy = vy * Math.sin(degree);
							System.out.print("1");
							nonvx = nonvx * -Math.cos(degree);
							nonvy = nonvy * -Math.sin(degree);
						} else if (x < i) {
							vx = vx * -Math.cos(degree);
							vy = vy * -Math.sin(degree);

							nonvx = nonvx * Math.cos(degree);
							nonvy = nonvy * Math.sin(degree);
						}

					}
					if (count >= repainted) {
						count = 0;
						repaint();
					}

				} // End infinite loop
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
		JFrame app = new JFrame("撞球");
		app.setSize(900, 500);
		qq a = new qq();
		app.add(a);
		a.start();
		app.setVisible(true);
	}
}
