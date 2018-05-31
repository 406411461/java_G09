package a0526;
// Demonstrating some Java 2D shapes.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import javax.swing.JPanel;

public class ShapesJPanel extends JPanel implements MouseListener,MouseMotionListener
{
	private int x1, y1, x2, y2;
	Direction  go = new Direction(x1, y1, x2, y2);
	final int PLAYER_NUM = 2;// draw shapes with Java 2D API
	
	Timer ballTimer;// 更新球位置的計時器
	
	int[] playerScore = new int[PLAYER_NUM];// 玩家分數
	
	private Share currentShape;
	private Shapes shapes[];
	private int shapeCount;
	public ShapesJPanel() {
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
    public void paintComponent( Graphics g )  
    {
       super.paintComponent( g ); 
       Graphics2D g2d = ( Graphics2D ) g; 
       g2d.fillArc(-25, -25, 50, 50, 270, 90);
       g2d.fillArc(310, -25, 50, 50, 180, 90);
       g2d.fillArc(-25, 320, 50, 50, 270, 180);
       g2d.fillArc(310, 320, 50, 50, 90, 180);
       g2d.fillArc(-25, 635, 50, 50, 360, 90);
       g2d.fillArc(310, 635, 50, 50, 90, 90);
		
 	   g2d.setColor(Color.red);	
	   g2d.fillOval(150,600, 20, 20);	
	   g2d.setColor(Color.pink);
	   g2d.fillOval(150,400, 20, 20);
	   g2d.setColor(Color.BLACK);
	   g2d.drawString("Player1:"+ 0,80, 680);
	   g2d.drawString("Player2:"+ 0, 200, 680);
	   
	   if(currentShape != null){
			currentShape.draw(g);
			 
		}
	   
    }
    
	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		x2 = x1;
		y2 = y1;
		go.come1(x1,y1);
		currentShape = new Setpower(x1,y1,x2,y2);
		System.out.println("mouseReleased: "+x1+" "+y1);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentShape.x2 = e.getX();
		currentShape.y2 = e.getY();
		repaint();
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int sum;
		x2 = e.getX();
		y2 = e.getY();
		
		
		currentShape=null;
		repaint();
		System.out.println("mouseReleased: "+x2+" "+y2);
		sum=(x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
		go.come2(x2,y2);
		System.out.println("sum:"+sum);          //sum值可用來呼叫韓式
		
		
	}
	
	
	
	
	
	
	
	
	
	

	@Override
	public void mouseMoved(MouseEvent arg0) {
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



	
} 

