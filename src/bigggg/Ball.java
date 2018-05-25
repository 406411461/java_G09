package bigggg;

import java.awt.Color;

public abstract class Ball {
	
	protected int x1,x2,y1,y2;
	protected Color color;
	protected boolean exist;
	public Ball(int x1, int y1, int x2, int y2, Color color, boolean exist) {
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.color=color;
		this.exist=exist;
	}
}
