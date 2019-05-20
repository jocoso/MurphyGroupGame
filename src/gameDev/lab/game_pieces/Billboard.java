package gameDev.lab.game_pieces;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Billboard {
	int x, y, z, w, h;
	
	Image image;
	
	static final int x_origin = 500;
	static final int y_origin = 400;
	
	public Billboard(int x, int y, int z, int w, int h, String filename) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.w = w;
		this.h = h;
		
		image = Toolkit.getDefaultToolkit().getImage(filename);
		resize(w, h);
	}
	
	public void resize(int width, int height) {
		image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	
	public void draw(Graphics g) {
		int d = 1024;
		
		// Convert to View Space - View with respect to the Camera
		int xC = x - (int)Camera.x;
		int yC = y - (int)Camera.y;
		int zC = z - (int)Camera.z;
		
		if(zC > 0) {
			// 3D Perspective Transformation
			int x3D = d * xC / zC;
			int y3D = d * yC / zC;
			
			int w3D = d * w / zC;
			int h3D = d * h / zC;
			
			// Screen Coordinates
			int xS = x3D - w3D/2 + x_origin;
			int yS = y3D - h3D   + y_origin;
			int wS = w3D;
			int hS = h3D;
			
			g.drawImage(image, xS, yS, wS, hS, null);
			
		}
	}
}
