package gameDev.lab.game_pieces;
import java.awt.*;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sprite {
	int x, y, z, w, h;
	float dx, dy;
	
	
	Animation[] anim;
	LinkedList<String> script;
	
	int pose = 0;
	
	boolean moving = false;
	
	public Sprite(int x, int y, int z, int w, int h,
				  String name, String addr, String[] poses, int count, int duration) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		
		anim = new Animation[poses.length];
		script = new LinkedList<String>();
		
		
		for(int i = 0; i < poses.length; i++)
			anim[i] = new Animation(name + "_" + poses[i], addr,
					count, duration);
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
		if(moving)
			g.drawImage(anim[pose].getCurrentImage(),
			x, y, null);
		else
			g.drawImage(anim[pose].getStillImage(),
			x, y, null);
		moving = false;
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
	
	
}
