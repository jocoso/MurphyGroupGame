package gameDev.lab.input;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

/**
 * InputManager class maps all different inputs that can 
 * happen during the GamePlay.
 */
public class InputManager implements KeyListener{
	// Creates an invisible cursor
	public static final Cursor INVISIBLE_CURSOR = 
			Toolkit.getDefaultToolkit().createCustomCursor(
					Toolkit.getDefaultToolkit().getImage(""),
					new Point(0, 0),
					"Invisible");
	public static final int NUM_KEY_CODES = 600;
	private Action[] keyActions = new Action[NUM_KEY_CODES];
	private Component comp;
	
	public InputManager(Component comp) {
		this.comp = comp;
		comp.addKeyListener(this);
		comp.setFocusTraversalKeysEnabled(false);
	}
	
	public void mapToKey(Action action, int keycode) {
		keyActions[keycode] = action;
	}
	
	public void clearMap(Action action) {
		for(int i = 0; i < keyActions.length; i++) {
			if(keyActions[i] == action)
				keyActions[i] = null;
		}
		
		action.reset();
	}
	
	public ArrayList getMaps(Action code) {
		ArrayList list = new ArrayList();
		
		for(int i = 0; i < keyActions.length; i++) {
			if(keyActions[i] == code)
				list.add(getKeyName(i));
		}
		
		return list;
	}
	
	public void resetAllGameActions() {
		for(int i = 0; i < keyActions.length; i++) {
			if(keyActions[i] != null)
				keyActions[i].reset();
		}
	}
	
	public static String getKeyName(int keycode) {
		return KeyEvent.getKeyText(keycode);
	}
	
	private Action getKeyAction(KeyEvent e) {
		int keycode = e.getKeyCode();
		if(keycode < keyActions.length)
			return keyActions[keycode];
		return null;
	}
	
	public void keyPressed(KeyEvent e) {
		Action action = getKeyAction(e);
		if(action != null)
			action.press();
		e.consume();
	}
	
	public void keyReleased(KeyEvent e) {
		Action action = getKeyAction(e);
		if(action != null)
			action.release();
		e.consume();
	}
	
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

}
