package DD;

import java.awt.*;
/**
 * A rectangular container box, containing the bouncing ball.  
 */
public class ContainerBox {
   int minX, maxX, minY, maxY;  // Box's bounds (package access)
   private   Color colorFilled = Color.GREEN;
   private   Color colorBorder = Color.GREEN;
   
   /** Constructors */
   public ContainerBox(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
      minX = 0;
      minY = 0;
      maxX = 350;
      maxY = 700;
      this.colorFilled = colorFilled;
      this.colorBorder = colorBorder;
   }
   
   /** Set or reset the boundaries of the box. */
   public void set(int x, int y, int width, int height) {
      minX = 0;
      minY = 0;
      maxX = 350;
      maxY = 700;
   }
   
   public void set(int width, int height) {
	     height = 700;
	     width = 350;
	   }

   /** Draw itself using the given graphic context. */
   public void draw(Graphics g) {
      g.setColor(colorFilled);
      g.fillRect(0, 0, 350, 700);
      g.setColor(colorBorder);
      g.drawRect(0, 0, 350, 700);
   }
}