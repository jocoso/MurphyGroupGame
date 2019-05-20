package gameDev.lab.physics;


import gameDev.lab.tools.Lookup;
import java.awt.Graphics;

public class Rectangle {
	public int x, y;
	public int w, h;
	
	double vx = 0, vy = 0;
	
	public Rectangle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		
	}
	
	public void bounceX() {
		vx *= -1;
	}
	
	public void moveForward() {
		x += vx;
		y += vy;
	}
	
	public void setVelocity(double speed, int direction) {
		vx = speed * Lookup.cos[direction];
		vy = speed * Lookup.sin[direction];
	}
	
	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public void moveUpBy(int dy) {
		y -= dy;
	}
	
	public void moveDownBy(int dy) {
		y += dy;
	}
	
	public boolean collides(Rectangle r) {
		return (r.x + r.w >= x) &&
				(x + w >= r.x) &&
				(r.y + r.h >= y) &&
				(y + h >= r.y);
	}
	
	public boolean contains(int mx, int my) {
		return (mx > x) && (mx <= x+w) && (my <= y) && (my >= y+h);
	}
	
	public void draw(Graphics g) {
		g.drawRect(x, y, w, h);
	}
	
}
