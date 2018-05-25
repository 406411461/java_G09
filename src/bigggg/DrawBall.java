package bigggg;

import java.awt.Color;
import java.awt.Graphics;

public class DrawBall extends Ball{
	private int dx=20,dy=20;
	public DrawBall(int x1,int y1,Color color ,boolean exist) {
		super(x1,y1,color,exist);
	}
	public DrawBall() {
		super();
	}
	public void draw(Graphics g) {
		g.setColor(color);
		
		int sx=Math.min(x1,x2);
		int sy=Math.min(y1,y2);
		g.fillOval(sx,sy,dx,dy);
	}
}
