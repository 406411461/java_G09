package a0526;

import javax.swing.JFrame;

public class Shapes
{
   
   public static void main( String[] args )
   {
      
      JFrame frame = new JFrame( "Drawing 2D shapes" );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

      
      ShapesJPanel shapesJPanel = new ShapesJPanel();
      frame.add( shapesJPanel ); 
      frame.setSize( 350, 700 ); 
      frame.setVisible( true ); 
   } 
} 

