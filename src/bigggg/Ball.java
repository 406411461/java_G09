package bigggg;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Ball {
	
	protected int x1,x2,y1,y2;
	protected Color color;
	protected boolean exist;
	public Ball(int x1, int y1, Color color, boolean exist) {
		this.x1=x1;
		this.y1=y1;
		this.color=color;
		this.exist=exist;
	}
	public Ball() {
		// TODO Auto-generated constructor stub
	}
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}

	public Color getColor() {
		return color;
	}
	public boolean isExist() {
		return exist;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public abstract void draw(Graphics g);
	
}
