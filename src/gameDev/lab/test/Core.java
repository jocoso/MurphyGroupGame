package gameDev.lab.test;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import gameDev.lab.tools.GameCore;
import gameDev.lab.tools.UtilityTools;
import gameDev.lab.input.InputManager;
import gameDev.lab.input.Action;
import gameDev.lab.game_pieces.Billboard;
import gameDev.lab.game_pieces.Camera;
import gameDev.lab.game_pieces.Entity;
import gameDev.lab.game_pieces.Floor;
import gameDev.lab.game_pieces.Player;
import gameDev.lab.mechanics.DialogManager;
import gameDev.lab.physics.Rectangle;

public class Core extends GameCore{
	
	
	Player tester;
	Billboard background;
	List<Floor> surfaces;
	
	InputManager a;
	DialogManager dialog;
	
	Action walk_right;
	Action walk_left;
	Action look_up;
	Action look_down;
	Action jump;
	
	
	public static void main(String[] args) {
		new Core().run();
	}

	public Core() {
		super(1000, 700);
	}
	
	public void init() {
		super.init();
		
		// Managers
		createInput();
		dialog = new DialogManager(this);
		background = new Billboard(10, 10, 10, 300, 300, 
				UtilityTools.getAddr() + "\\background.jpg");
		background.resize(200, 200);
		
		// Sprite
		tester = new Player(0, 300, 0);
		Camera.set(0, 0, 0, 0);
		tester.setScript(UtilityTools.getAddr() + "\\playerScript.txt");
		
		Floor floor = new Floor(0, getHeight()-100, 0, 600, 125, UtilityTools.ADDR + "\\floor.png", "Main Floor");
		Floor plat1 = new Floor(700, 300, 0, 600, 125, UtilityTools.ADDR + "\\floor.png", "Plat-1");
		Floor plat2 = new Floor(0, 300, 0, 600, 125, UtilityTools.ADDR + "\\floor.png", "Plat-2");

		
		surfaces = new LinkedList<Floor>();
		surfaces.add(floor);
		surfaces.add(plat1);
		surfaces.add(plat2);
		
		
		
		tester.setFloorY(floor.getY() - tester.getHeight());

		
		
	}
	
	public void createInput() {
		// Action Handler
		walk_right = new Action("Walk-Right");
		walk_left  = new Action("Walk-Left");
		look_up    = new Action("Look-Up", Action.DETECT_INITIAL_PRESS_ONLY);
		look_down  = new Action("Look-Down", Action.DETECT_INITIAL_PRESS_ONLY);
		jump       = new Action("Jump", Action.DETECT_INITIAL_PRESS_ONLY);
		
		// Input Manager		
		a = new InputManager(this);
		a.mapToKey(walk_right, KeyEvent.VK_RIGHT);
		a.mapToKey(walk_left, KeyEvent.VK_LEFT);
		a.mapToKey(look_up, KeyEvent.VK_UP);
		a.mapToKey(look_down, KeyEvent.VK_DOWN);
		a.mapToKey(jump, KeyEvent.VK_SPACE);
	}
	
	
	public void update() {
		
		for(Floor surface: surfaces) {
			Rectangle flrTmp = surface.getRect();
			Rectangle usrTmp = tester.getRect();
			//System.out.println(surface.getName() + ": " + usrTmp.collides(flrTmp));
			if(usrTmp.collides(surface.getRect())) {
				tester.setVelocityY(0);
				tester.setY(surface.getY()-tester.getHeight());
				tester.setState(Player.STATE_NORMAL);
			}else {
				if((usrTmp.x > flrTmp.x + flrTmp.w || usrTmp.x < flrTmp.x) ) {
					tester.setVelocityY(10f);
				}
			}
			

//			try {
//				Thread.sleep(2);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
		
		
		checkGameInput();
		tester.update();
		
		
		
	}
	
	public void checkGameInput() {
		
		if(walk_right.isPressed()) 				                            tester.moveRight(4); //Camera.turnLeft(20);            
		if(walk_left.isPressed())  					                        tester.moveLeft(4);
		if(look_up.isPressed())    					                        tester.moveUp();
		if(look_down.isPressed())  					                        tester.moveDown();
		if(jump.isPressed() && tester.getState() != Player.STATE_JUMPING)   tester.jump();    
		
	}
	
	
	@Override
	public void draw(Graphics e) {
		background.draw(e);
		tester.draw2D(e);
		for(Floor surface: surfaces)
			surface.draw2D(e);
		//dialog.drawConversationBlock(e);
	}
}
