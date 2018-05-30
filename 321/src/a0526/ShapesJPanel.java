package a0526;
// Demonstrating some Java 2D shapes.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JPanel;

public class ShapesJPanel extends JPanel implements KeyListener, ActionListener
{
	private int x1, y1, x2, y2;
   // draw shapes with Java 2D API
	final int PLAYER_NUM = 2;
	// 更新球位置的計時器
	Timer ballTimer;
	// 玩家分數
	int[] playerScore = new int[PLAYER_NUM];
	
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
    }

    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	} 
} 

