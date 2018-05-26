package a0526;
// Demonstrating some Java 2D shapes.

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class ShapesJPanel extends JPanel 
{
   // draw shapes with Java 2D API
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g ); 
      Graphics2D g2d = ( Graphics2D ) g; 
      g2d.fillArc(-25, -25, 50, 50, 270, 90);
      g2d.fillArc(310, -25, 50, 50, 180, 90);
      g2d.fillArc(-25, 350, 50, 50, 270, 180);
      g2d.fillArc(310, 350, 50, 50, 90, 180);
      g2d.fillArc(-25, 635, 50, 50, 360, 90);
      g2d.fillArc(310, 635, 50, 50, 90, 90);
   } 
} 

