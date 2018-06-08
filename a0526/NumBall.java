package a0526;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class NumBall {
	private int x ,y;
	NumBall(int x,int y){
		this.x = x;
		this.y = y;
		
	}
	public void paintComponent( Graphics g )  
    {

       Graphics2D g2d = ( Graphics2D ) g;
  	   g2d.setColor(Color.red);	
 	   g2d.fillOval(150,600, 20, 20);	
 	   g2d.setColor(Color.pink);
 	   g2d.fillOval(150,400, 20, 20);
    }
}
