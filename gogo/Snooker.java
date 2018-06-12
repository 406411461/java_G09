package gogo;

//==================================================================
//               Snooker.java            MRH 26-Jun-2000/3-Jul-2000
//==================================================================
//
// Snooker game.
//
//==================================================================

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

//==================================================================

public class Snooker extends Applet implements MouseListener, MouseMotionListener, KeyListener { //start of Snooker

    // ======================== Params ===========================

    int nBalls = 9; // Total number of balls (9)

    double velScale =3.0; // Scales drag to velocity. 50.0 for appl, 3.0 for applet
    double pocketSize2 = 4.0; // Capture radius for pockets

    double transferRatio = 0.9; // Maybe this should increase with velocity...
    double def_friction = 0.98; // Velocity reduced this amount each time.//0.999 for appl, 0.97 for applet
    double minVel = 0.01; // Stops threads below this velocity // 0.01 always

    int refresh = 20;  // ms between recalculation // 3 for appl, 10 for applet
    int paintFreq = 1; // number of recalcs before repaint // 10 for appl, 1 for applet

    // ======================== Variables =======================

    int ipointerX, ipointerY, drag_flag, points;
    int iheight, iwidth, ix, iy;

    double xmin, xmax, ymin, ymax, radius, rad2, vX, vY;
    double startX, startY, x, y, pointerX, pointerY, friction;
    double[][] pockets = new double[6][2];
    double ballPos[][] = new double[20][2];

    String tmptype, scoreString="", msgString= "";

    boolean newPoints = true, scratchFlag = false;
    boolean frictionFlag = true, traceFlag = false, doubleBuffered = false;

    Ball ball[] = new Ball[20];	

    Color ballColor[] = new Color[20];

    Image screenBuffer;

    //========================== Executable =========================

    public void init() { // Applet only
    }

    public void start() { // Applet or Application

       	this.addMouseListener(this);
	this.addMouseMotionListener(this);
      	this.addKeyListener(this);

	this.requestFocus();  // Doesn't work

	friction = def_friction;

	xmin = 0.0;
	xmax = 100.0;
	ymin = 0.0;
	ymax = 50.0;

	radius = 1.1*(xmax-xmin)/67.0; // Ball radius
	rad2=radius*radius*4.0;

	points = 0;
	scoreString="Points : 0 ";

	startX = 3*(xmax+xmin)/4;
	startY = (ymax+ymin)/2;

	// Pocket positions
	pockets[0][0]=xmin;
	pockets[1][0]=xmin;
	pockets[2][0]=xmax;
	pockets[3][0]=xmax;
	pockets[4][0]=(xmax-xmin)/2;
	pockets[5][0]=(xmax-xmin)/2;

	pockets[0][1]=ymin;
	pockets[1][1]=ymax;
	pockets[2][1]=ymin;
	pockets[3][1]=ymax;
	pockets[4][1]=ymin;
	pockets[5][1]=ymax;

	// Ball colours
	ballColor[0] = new Color(255,255,255);
	ballColor[1] = new Color(255,0,0);
	ballColor[2] = new Color(255,230,0);
	ballColor[3] = new Color(0,255,0);
	ballColor[4] = new Color(205,125,0);
	ballColor[5] = new Color(0,0,255);
	ballColor[6] = new Color(255,100,180);
	ballColor[7] = new Color(100,100,100);
	ballColor[8] = new Color(0,255,255);

	ballColor[9] = new Color(155,255,155);
	ballColor[10] = new Color(155,0,0);
	ballColor[11] = new Color(155,230,0);
	ballColor[12] = new Color(0,155,0);
	ballColor[13] = new Color(105,125,0);
	ballColor[14] = new Color(0,0,255);
	ballColor[15] = new Color(155,100,180);
	ballColor[16] = new Color(100,000,100);
	ballColor[17] = new Color(0,255,255);

	// Ball positions
	ballPos[0][0] = startX;
	ballPos[0][1] = startY;
	ballPos[1][0] = 1*(xmax+xmin)/3;
	ballPos[1][1] = (ymax+ymin)/2;
	ballPos[7][0] = ballPos[1][0]-2.2*radius;
	ballPos[7][1] = ballPos[1][1];
	ballPos[3][0] = ballPos[1][0]-2.2*radius;
	ballPos[3][1] = ballPos[1][1]-2.2*radius;
	ballPos[4][0] = ballPos[1][0]-2.2*radius;
	ballPos[4][1] = ballPos[1][1]+2.2*radius;
	ballPos[5][0] = ballPos[1][0]-4.4*radius;
	ballPos[5][1] = ballPos[1][1]-1.2*radius;
	ballPos[6][0] = ballPos[1][0]-4.4*radius;
	ballPos[6][1] = ballPos[1][1]-3.6*radius;
	ballPos[2][0] = ballPos[1][0]-4.4*radius;
	ballPos[2][1] = ballPos[1][1]+1.2*radius;
	ballPos[8][0] = ballPos[1][0]-4.4*radius;
	ballPos[8][1] = ballPos[1][1]+3.6*radius;

	ballPos[9][0] = ballPos[1][0]-6.2*radius;
	ballPos[9][1] = ballPos[1][1]+5.4*radius;
	ballPos[10][0] = ballPos[1][0]-6.2*radius;
	ballPos[10][1] = ballPos[1][1]+2.8*radius;
	ballPos[11][0] = ballPos[1][0]-6.2*radius;
	ballPos[11][1] = ballPos[1][1];
	ballPos[12][0] = ballPos[1][0]-6.2*radius;
	ballPos[12][1] = ballPos[1][1]-2.8*radius;
	ballPos[13][0] = ballPos[1][0]-6.2*radius;
	ballPos[13][1] = ballPos[1][1]-5.4*radius;

	ball[0] = new Ball(x,y,0);	

	for (int i = 0; i<nBalls;i++) {
	    ball[i] = new Ball(ballPos[i][0],ballPos[i][1],ballColor[i],i);	
	}

	repaint();
    }
    
    public void stop() { // Tidy up (Applet only ?)

	for (int i = 0; i<nBalls;i++) {
	    ball[i].stop();	
	}
	repaint();

       	this.removeMouseListener(this);
	this.removeMouseMotionListener(this);
      	this.removeKeyListener(this);

    }
    
    // ===================== update ==================
    
    public void update (Graphics g) {  // Gets called by repaint(); Rename for single buffering.

	if (doubleBuffered) {
	 
	    iheight = getSize().height-30;
	    iwidth = getSize().width;
	    
	    if (screenBuffer == null) screenBuffer = createImage(iwidth,iheight);
	    Graphics bg = screenBuffer.getGraphics();
	    
	    g.drawImage(screenBuffer,0,0,this);

	    paint(bg);

	    //bg.dispose();

	} else {
	
	    paint(g);
	}
    }
	
    // ===================== paint ==================

    public void paint(Graphics g) {
	
	iheight = getSize().height-30;
	iwidth = getSize().width;

	g.setClip(0,0,iwidth,iheight);

	setBackground(new Color(0,160,0));

	if (!traceFlag) g.clearRect(0,0,iwidth,iheight);

	g.setColor(Color.black);

	// Draw pockets
	for (int i = 0; i<6;i++) {
	    drawCircleReal(g,pockets[i][0],pockets[i][1],radius*1.5);
	}

	// Draw border
	g.drawRect(0,0,iwidth-1,iheight-1);

	// Draw the balls
	for (int i = 0; i<nBalls;i++) {
	    Color tmpCol = ball[i].getColor();
	    g.setColor(tmpCol.darker().darker());
	    drawCircleReal(g,ball[i].getX(),ball[i].getY(),radius);	
	    g.setColor(tmpCol.darker());
	    drawCircleReal(g,ball[i].getX(),ball[i].getY(),3*radius/4);	
	    g.setColor(tmpCol);
	    drawCircleReal(g,ball[i].getX(),ball[i].getY(),radius/2);	
	}

	// Draw cue
	if (drag_flag > 0) {
	    //BasicStroke bs = new BasicStroke(12.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	    //g.setStroke(bs);
	    g.setColor(Color.lightGray);
	    drawLineReal(g, ball[0].getX(),ball[0].getY(),pointerX,pointerY);
	}
	
	// Write score
	if (newPoints) {
	    g.setClip(0,iheight,iwidth,iheight-30);
	    g.setColor(Color.gray);
	    g.fillRect(0,iheight,iwidth,iheight-30);
	    g.setColor(Color.white);

	    g.drawString(scoreString,20,iheight+20);
	    //g.drawString(msgString,100,iheight+20);

	    //newPoints = false;
	}
    }
    
    // =================== Balls ==================

    class Ball {
	double velX, velY;
	double x, y;
	double inc;
	Color color;
	int thisBall;

	int count;
	boolean running = false;
	boolean stop = false;

	public Ball(double x, double y, int ib) {
	    this.x=x;
	    this.y=y;
	    this.thisBall = ib;
	}

	public Ball(double x, double y, Color color, int ib) {
	    this.x=x;
	    this.y=y;
	    this.color=color;
	    this.thisBall = ib;
	}

	public void setXY(double x, double y) {
	    this.x=x;
	    this.y=y;
	    return;
	}
	public void addXY(double x, double y) {
	    this.x+=x;
	    this.y+=y;
	    return;
	}

	public void addVel(double velX, double velY) {
	    this.velX+=velX;
	    this.velY+=velY;
	    return;
	}

	public void setVel(double velX, double velY) {
	    this.velX=velX;
	    this.velY=velY;
	    return;
	}

	public void setColor(Color color) {
	    this.color=color;
	    return;
	}

	public double getX() {return x;}
	public double getY() {return y;}
	public double getVelX() {return x;}
	public double getVelY() {return y;}
	public Color getColor() {return color;}
	public boolean getRunning() {return running;}

	Thread myThread;

	public void roll(double vX, double vY) {

	    velX = vX;
	    velY = vY;

	    myThread = new Thread (new Angel());
	    myThread.start();

	}

	public void stop() {

	    //x = startX;
	    //y = startY;
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
			
loop:		while (!stop) { // Start of infinite loop
		    try {
			Thread.sleep(refresh);
		    } 
		    catch (InterruptedException e) {}

		    count++;

		    // Apply friction
		    velX *= friction;
		    velY *= friction;

		    // Stop calculating if running too slowly
		    if (  ((velX > -minVel) && (velX < minVel)) && ((velY > -minVel) &&  (velY < minVel)) ) {
			stop = true;
			running = false;
		    }

		    // Move the ball
		    x += velX;
		    y += velY;
		    
		    // Check for downing
		    
pockets:
		    for (int i = 0; i<6;i++) {
			if (scratchFlag && (thisBall == 0)) continue;
			if ((x-pockets[i][0])*(x-pockets[i][0]) < pocketSize2) {
			    if ((y-pockets[i][1])*(y-pockets[i][1]) < pocketSize2) {
				x = pockets[i][0];
				y = pockets[i][1];
				points += thisBall%9;
				if (thisBall == 0) {
				    points=0;
				    System.out.println("Scratch. Total points : "+points);
				    scoreString = "Points : "+points+" Scratch";
				    //scoreString = "Points : "+points+" Scratch "+x+" "+y;
				} else {
				    System.out.println("Down : "+thisBall+" points. Total : "+points);
				    scoreString = "Points : "+points;
				}
				stop = true;
				newPoints = true;
				repaint();
				break pockets;
			    }
			}
			
                               }
		    
		    if (stop) continue;
		    
		    // Check for collision with other balls
		    
		    synchronized(ball) {  // Thread lock
			for (int i = 0; i<nBalls;i++) {
			    
			    double dx = x-ball[i].getX();
			    double dy = y-ball[i].getY();
			    double dx2 = dx*dx;
			    double dy2 = dy*dy;

			    if (i == thisBall) continue; // Same ball

			    if (dx2 > rad2) continue; // No collision
			    if (dx2+dy2 > rad2) continue; // No collision

			    // Remove sign from separation vectors
			    double adx = Math.abs(dx);
			    double ady = Math.abs(dy);

			    double norm = adx+ady;

			    // Normalise separation vectors
			    double cx = adx/norm;
			    double cy = ady/norm;

			    // And multiply incoming velocity vectors by them
			    double velAddX = cx*velX*transferRatio;
			    double velAddY = cy*velY*transferRatio;

			    /*
			      System.out.println("Run: "+ball[i].running+" Norm: "+(double)norm+" Count: "+count);
			    System.out.println("Ball : "+i+" adx,y "+(double)adx+" "+(double)ady);
			    System.out.println("Ball : "+i+"   cx,y   "+(double)cx+" "+(double)cy);
			    System.out.println("Velocity in : "+(double)velX+" "+(double)velY);
			    System.out.println("Velocity add "+(double)velAddX+" "+(double)velAddY);
			    System.out.println(" ");
			    */

			    //Push the ball away to avoid another hit
			    //ball[i].addXY(-dx/2.0,-dy/2.0);
			    ball[i].addXY(-dx/4.0,-dy/4.0);

			    // Add transfer vector to new ball if already rolling
			    if (ball[i].running) {
				ball[i].addVel(velAddX,velAddY);

			    // Or apply the vector if it's not moving yet
			    } else {
				ball[i].roll(velAddX,velAddY);
			    }

			    // And subtract the rest from the current ball
			    velX -=velAddX;
			    velY -=velAddY;

			} // End loop over balls
			
		    } // End thread lock

		    // Check for wall bounce
		    
		    if (x < (xmin+radius) ) {
			velX =  -velX;
			x += radius;
		    }
		    if (x > (xmax-radius) ) {
			velX =  -velX;
			x -= radius;
		    }
		    
		    if (y < (ymin+radius) ) {
			velY = -velY;
			y += radius;
		    }
		    if (y > (ymax-radius) ) {
			velY = -velY;
			y -= radius;
		    }
		    
		    
		    // repaint sometimes
		    if (count >= paintFreq) {
			count = 0;
			repaint();			
		    }

		} // End infinite loop

		velX = 0.0;
		velY = 0.0;
	    }
	}
    }

    // =================== drawStuff ==================
    
    public void drawCircle(Graphics cg, int xCen, int yCen, int rad) {
	cg.fillOval(xCen-rad, yCen-rad, 2*rad, 2*rad);
    }
    
    public void drawCircleReal(Graphics cg, double xCen, double yCen, double rad) {
	int ix = cartToPix(xCen);
	int iy = cartToPiy(yCen);
	int irad = cartToPix(rad);
	cg.fillOval(ix-irad, iy-irad, 2*irad, 2*irad);
    }
    
    public void drawLineReal(Graphics cg, double x1, double y1, double x2, double y2) {
	int ix1 = cartToPix(x1);
	int iy1 = cartToPiy(y1);
	int ix2 = cartToPix(x2);
	int iy2 = cartToPiy(y2);
	cg.drawLine(ix1, iy1, ix2, iy2);
    }
    
    // ===================== mouse events ==================

    // Button clicking

    public void mousePressed(MouseEvent e) {
	drag_flag = 1;
    }

    public void mouseReleased(MouseEvent e) {
	drag_flag = 0;
	vX=(ball[0].getX()-pointerX)/velScale;
	vY=(ball[0].getY()-pointerY)/velScale;
	ball[0].roll(vX,vY);
	repaint();
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    // Mouse motion

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

	if (drag_flag > 0) { 
	    ipointerX = e.getX();
	    ipointerY = e.getY();
	    pointerX = pixToCart(ipointerX);
	    pointerY = piyToCart(ipointerY);
	    repaint();
	}
    }

    // Key listener

    public void keyTyped(KeyEvent e) {
	char key_char = e.getKeyChar();
	tmptype = String.valueOf(key_char).toUpperCase();

	if (tmptype.equals("S") || tmptype.equals("R") ) { // Reset
	    for (int i=0;i<nBalls;i++) {ball[i].stop();}
	    stop();
	    start();

	} else if (tmptype.equals("F") ) { // Friction toggle
	    if (frictionFlag == true) {
		frictionFlag =false;
		friction = 1.0;
	    } else {
		frictionFlag =true;
		friction = def_friction;
	    }
	    System.out.println("Friction enabled : "+frictionFlag);
	    msgString = "Friction enabled : "+frictionFlag;

	} else if  (tmptype.equals("L")) { // Littler balls
	    radius*=0.8; 
	    rad2 = radius*radius*4.0;

	} else if  (tmptype.equals("B")) { // Bigger balls
	    radius*=1.2; 
	    rad2 = radius*radius*4.0;

	} else if  (tmptype.equals("U")) { //Update paint
	    repaint();

	} else if  (tmptype.equals("T")) { //Trace
	    traceFlag = !traceFlag;
	    System.out.println("Trace enabled : "+traceFlag);
	    msgString="Trace enabled : "+traceFlag;

	} else if  (tmptype.equals("D")) { //Double_buffered
	    doubleBuffered = !doubleBuffered;
	    System.out.println("Double buffering : "+doubleBuffered);
	    msgString="doubleBuffering : "+doubleBuffered;

	} else if  (tmptype.equals("P")) { //No scratch
	    scratchFlag = !scratchFlag;
	    System.out.println("Scratch disabled : "+scratchFlag);
	    msgString="Scratch disabled : "+scratchFlag;

	} else if  (tmptype.equals("H")) { //Help
	    System.out.println("Reset, Bigger, Littler, Trace, Prevent_scratch, Double_buffer_off, Quit");
	    msgString="Reset, Bigger, Littler, Trace, Prevent_scratch, Double_buffer_off, Quit";
	    
	} else if  (tmptype.equals("Q") || tmptype.equals("E") ) { // Exit
	    stop();
	    System.exit(0); 
	}
	repaint();
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    private int cartToPix(double x) {
	int ix = (int)(((x-xmin)/(xmax-xmin))*(double)iwidth);
	return ix;
    }

    private int cartToPiy(double y) {
	int iy = iheight-(int)(((y-ymin)/(ymax-ymin))*(double)iheight);
	return iy;
    }

    private double pixToCart(int ix) {
	double x = (double) (ix*(xmax-xmin)/iwidth)+xmin;
	return x;
    }

    private double piyToCart(int iy) {
	double y = (double) ((iheight-iy)*(ymax-ymin)/iheight)+ymin;
	return y;
    }

    // =======================================================

	// Convert to application

    static Frame myWindow;

    public static void main(String[] args) {

	// Frame to put the applet in.
        myWindow = new Frame("Snooker"); 
        myWindow.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent evt) {

		    // stop when the close box is clicked.
		    System.exit(0); 
	        }
	    });

	// Set size to proper (width, height)
        myWindow.setSize(600, 300); 
	
	// Create a new object of this class.  Note (2)
        Snooker myApplet = new Snooker();

        // Add applet to the window.
        myWindow.add(myApplet, "Center"); 

        myApplet.init();   // initialize the applet.
        myApplet.start();  // start it running.  Note (3)

        myWindow.show();   // Make the window visible.

    } //endmethod main()



}