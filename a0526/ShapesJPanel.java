package a0526;
// Demonstrating some Java 2D shapes.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import javax.swing.JPanel;

public class ShapesJPanel extends JPanel
{
	private int x1, y1, x2, y2;
	private static final int UPDATE_RATE = 30; //FPS
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
    }
    


		
}  

