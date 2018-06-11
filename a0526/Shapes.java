package a0526;

import javax.swing.JFrame;

public class Shapes
{
   
   public static void main( String[] args )
   {
      setBALL ball = new setBALL(); 
      JFrame frame = new JFrame( "���y" );


      
  
      
      frame.add( ball ); 
      frame.setSize( 350, 700 ); 

      frame.setLocationByPlatform(true);  //将框架的定位交给系统实现  
      frame.setVisible(true);         //设置框架可见 
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   } 
} 

