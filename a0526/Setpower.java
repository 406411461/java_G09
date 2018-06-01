package a0526;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//
//import painter.Shape;



public class Setpower  extends Share{
	
	public Setpower(int x1, int y1, int x2, int y2) {
		super(x1,y1,x2,y2);
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5.0f));
		g2d.setColor(Color.DARK_GRAY);
		g2d.drawLine(x1,y1,x2,y2);
	}
	@Override
	public String toString() {
		return "Setpower [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
