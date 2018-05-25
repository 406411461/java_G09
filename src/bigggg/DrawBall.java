package bigggg;

import java.awt.Color;
import java.awt.Graphics;

public class DrawBall extends Ball{
	private int dx,dy;
	public DrawBall(int x1,int y1,int x2,int y2 ,Color color ,boolean exist) {
		super(x1,y1,x2,y2,color,exist);
	}
	public void draw(Graphics g) {
		g.setColor(color);
		
		int sx=Math.min(x1,x2);
		int sy=Math.max
	}
}
