package gameDev.lab.game_pieces;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import gameDev.lab.physics.Rectangle;

import java.awt.Image;

public class Floor implements Entity{
	
	int x;
	int y;
	int z;
	
	int width;
	int height;
	
	String name;
	
	Image image;
	
	Rectangle rect;
	
	public Floor(int x, int y, int z, int w, int h, String addr, String name) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		width = w;
		height = h;
		
		rect = new Rectangle(x, y, w, h);
		this.name = name;
		
		image = getImage(addr);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private Image getImage(String addr) {
		return new ImageIcon(addr).getImage();
	}

	@Override
	public void draw2D(Graphics g) {
		g.drawImage(image, x, y, null);
		g.setColor(Color.RED);
		rect.draw(g);
		
	}

	@Override
	public String getName() {
		return name;
	}

}
