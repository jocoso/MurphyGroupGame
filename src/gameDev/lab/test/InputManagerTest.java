package gameDev.lab.test;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Point;

import gameDev.lab.tools.GameCore;
import gameDev.lab.tools.UtilityTools;
import gameDev.lab.input.InputManager;
import gameDev.lab.input.Action;
import gameDev.lab.game_pieces.Player;
import gameDev.lab.mechanics.DialogManager;

public class InputManagerTest extends GameCore{
	InputManager a;
	Player tester;
	
	DialogManager dialog;
	
	Action walk_right;
	Action walk_left;
	Action look_up;
	Action look_down;
	Action jump;
	
	int floor;
	
	public static void main(String[] args) {
		new InputManagerTest().run();
	}

	public InputManagerTest() {
		super();
	}
	
	public void init() {
		super.init();
		
		// Managers
		createInput();
		dialog = new DialogManager(this);
		
		
		// Sprite
		tester = new Player(0, 300, 0);
		floor = getHeight() - tester.getHeight();
		tester.setFloorY(floor);
		tester.setScript(UtilityTools.getAddr() + "\\playerScript.txt");
		
		
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
		checkGameInput();
		tester.update();
		
	}
	
	public void checkGameInput() {
		if(walk_right.isPressed()) 					                     tester.moveRight(4);
		if(walk_left.isPressed())  					                     tester.moveLeft(4);
		if(look_up.isPressed())    					                     tester.moveUp();
		if(look_down.isPressed())  					                     tester.moveDown();
		if(jump.isPressed() && tester.getState() != Player.STATE_JUMPING)   tester.jump();    
		
	}
	
	
	@Override
	public void draw(Graphics e) {
		tester.draw2D(e);
		//dialog.drawConversationBlock(e);
	}
}
