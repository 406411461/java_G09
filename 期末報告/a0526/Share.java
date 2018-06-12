package a0526;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Share {
	protected int x1, y1, x2, y2;
	public Share(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

	}
	public abstract void draw(Graphics g);
}
