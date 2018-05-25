package bigggg;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

public class RunTable extends JPanel implements MouseListener,MouseMotionListener{
	private int number =15;
	private int x1,x2,y1,y2;
	private Color color;
	private Ball balls[];
	private Boolean exist;
	private int ballnum;
	private Ball shape;
	private int[] a={240,240,210,250};
	private int[] b={900,600,640,640};
	public RunTable() {
		super();
		balls=new Ball[number];
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	DrawBall d=new DrawBall();
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<ballnum;i++) {
		    balls[i].draw(g);
		}
	}
	public void b16(int n) {
		n=4;
		for(int i=0;i<n;i++) {
			shape=new DrawBall(a[i],b[i],color,exist);
		}
	}
	public void go() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
