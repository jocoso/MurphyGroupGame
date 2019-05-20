package gameDev.lab.game_pieces;
import java.awt.*;
import java.util.LinkedList;

import gameDev.lab.physics.Rectangle;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sprite implements Entity{
	int x, y, z, w, h;
	float dx, dy;
	
	
	Animation[] anim;
	LinkedList<String> script;
	
	String name;
	
	int floorY;
	
	int pose = 0;
	
	boolean moving = false;
	
	
	Rectangle rect;
	
	public Sprite(int x, int y, int z, int w, int h,
				  String name, String addr, String[] poses, int count, int duration) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		
		anim = new Animation[poses.length];
		script = new LinkedList<String>();
		this.name = name;
		rect = new Rectangle(x, y, 80, 120);
		
		
		for(int i = 0; i < poses.length; i++)
			anim[i] = new Animation(name + "_" + poses[i], addr,
					count, duration);
		
		//System.out.println(anim[0].getStillImage().getHeight(null));
	}

	public void moveLeft(int dist) {
		x -= dist;
		pose = 2;
		moving = true;
	}
	
	public void moveRight(int dist) {
		x += dist;
		pose = 3;
		moving = true;
	}
	
	public void moveUp() {
		pose = 1;
	}
	
	public void moveDown() {
		pose = 0;
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHeight() {
		return anim[pose].getCurrentImage().getHeight(null);
	}
	
	public int getWidth() {
		return anim[pose].getCurrentImage().getWidth(null);
	}
	
	
	 public void update() {	 
		 x += dx;
		 y += dy;
		 rect.x = x;
		 rect.y = y;
		 
	 }
	 
	 public float getVelocityX() {
		 return dx;
	 }
	 
	 public float getVelocityY() {
		 return dy;
	 }
	 
	 public void setVelocityX(float dx) {
		 this.dx = dx;
	 }
	 
	 public void setVelocityY(float dy) {
		 this.dy = dy;
	 }
	
	public void draw2D(Graphics g) {
		g.setColor(Color.BLUE);
		rect.draw(g);
		
		if(moving)
			g.drawImage(anim[pose].getCurrentImage(),
			x, y, null);
		else
			g.drawImage(anim[pose].getStillImage(),
			x, y, null);
		moving = false;
		
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void setScript(String document) {
		File scr;
		
		try {
			scr = new File(document);
			
			if(scr.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(scr));
				String tmp = "";
				
				while((tmp = br.readLine()) != null) {
					script.add(tmp);
				}
			}else {
				
				System.out.println("Address " + document + " doesn't exist.");
			}
			
		} catch(FileNotFoundException e) {} 
		  catch (IOException e) {}
		
	}
	
	public String read(int line) {
		return script.get(line);
	}

	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * If collision is found, returns true. 
	 * Otherwise, returns false.
	 */

	
}
