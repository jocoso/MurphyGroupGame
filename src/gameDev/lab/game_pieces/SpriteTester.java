package gameDev.lab.game_pieces;
import java.awt.event.*;

import javax.swing.ImageIcon;

import gameDev.lab.tools.UtilityTools;

import java.awt.*;
import java.applet.Applet;

public class SpriteTester extends Applet implements Runnable, KeyListener{
//	public static void main(String[] args) {
//		new SpriteTester();
//	}
	Thread t;
	Player sprite;
	Animation anim;
	Billboard background;
	
	boolean up_bttn = false;
	boolean dw_bttn = false;
	boolean lt_bttn = false;
	boolean rt_bttn = false;
	boolean A_bttn = false;
	
	public void init() {
		
		sprite = new Player(0, 200, 0);
		//setVisible(true);
		Image bg = getImage(UtilityTools.getAddr() + "//background.jpg");
		background = new Billboard(0,0,0,bg.getWidth(null), bg.getHeight(null),
				UtilityTools.getAddr() + "//background.jpg");
		sprite.setFloorY(300);
		
		requestFocus();
		addKeyListener(this);
		t = new Thread(this);
		t.start();
	}
	
	public synchronized void run() {
		while(true) {
			//System.out.println(rt_bttn);
			if(rt_bttn) sprite.moveRight(3);
			if(lt_bttn) sprite.moveLeft(3);
			if(A_bttn && sprite.getState() != Player.STATE_JUMPING) sprite.jump();
			if(up_bttn) sprite.moveUp();
			if(dw_bttn) sprite.moveDown();
			
			// If the character is 
			//if()
			
			repaint();
			
			try
			{
			   t.sleep(16);
			}
			catch(Exception x) {};
		}
	}
	
	public void update()
	{
		sprite.update();
		repaint();
	}
	
	public Image getImage(String filename) {
		return new ImageIcon(filename).getImage();
	}
	
	public void paint(Graphics g) {
		background.draw(g);
		sprite.draw2D(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) up_bttn = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) dw_bttn = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) lt_bttn = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) rt_bttn = true;
		if(e.getKeyCode() == KeyEvent.VK_A) A_bttn = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) up_bttn = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) dw_bttn = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) lt_bttn = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) rt_bttn = false;
		if(e.getKeyCode() == KeyEvent.VK_A) A_bttn = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
