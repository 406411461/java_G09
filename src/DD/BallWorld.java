package DD;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class BallWorld extends JPanel {
   private static final int UPDATE_RATE = 30;  // Frames per second (fps)
   
   private Ball ball; // A single bouncing Ball's instance
   private Ball ball2;  
   private ContainerBox box;  // The container rectangular box
  
   private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
  
   public BallWorld(int width, int height) {
  
      canvasWidth = width;
      canvasHeight = height;
      Color colorBorder = Color.BLACK;
  	  Color colorFilled = Color.GREEN;
      
      // Init the ball at a random location (inside the box) and moveAngle
      Random rand = new Random();
      int radius = 10;
      int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
      int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
      int speed = 5;
      int radius2 = 10;
      int x2 = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
      int y2 = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
      int speed2 = 10;
      int angleInDegree = rand.nextInt(360);
      ball = new Ball(x, y, radius, speed, angleInDegree, Color.BLUE);
      ball2 = new Ball(x2, y2, radius2, speed2, angleInDegree, Color.RED);
	
	// Init the Container Box to fill the screen
      box = new ContainerBox(0,0,canvasWidth, canvasHeight, colorFilled, colorBorder);
     
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      this.add(canvas, BorderLayout.SOUTH);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            canvasWidth = 350;
            canvasHeight = 700;
            // Adjust the bounds of the container to fill the window
            box.set(x,y,350,700);
         }
      });
  
      // Start the ball bouncing
      gameStart();
   }
   
   
   
   /** Start the ball bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
      Thread gameThread = new Thread() {
         public void run() {
            while (true) {
               // Execute one time-step for the game 
               gameUpdate();
               // Refresh the display
               repaint();
               // Delay and give other thread a chance
               try {
                  Thread.sleep(1000 / UPDATE_RATE);
               } catch (InterruptedException ex) {}
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
   }
   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
      ball.moveOneStepWithCollisionDetection(box);
      ball2.moveOneStepWithCollisionDetection(box);
   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);    // Paint background
         // Draw the box and the ball
         box.draw(g);
         ball.draw(g);
         ball2.draw(g);
         
      }
      

      
      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }
}