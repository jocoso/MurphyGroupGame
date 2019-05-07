package gameDev.lab.input;

public class Action {
	public static final int DETECT_CONTINUOUSLY = 0;
	public static final int DETECT_INITIAL_PRESS_ONLY = 1;
	
	public static final int STATE_RELEASED = 0;
	public static final int STATE_PRESSED = 1;
	public static final int STATE_WAITING_FOR_RELEASE = 2;
	
	private String name;
	private int behaviour;
	private int amount;
	private int state;
	
	public Action(String name) {
		this(name, DETECT_CONTINUOUSLY);
	}
	
	public Action(String name, int behaviour) {
		this.name = name;
		this.behaviour = behaviour;
		reset();
	}
	
	public String getName() {
		return name;
	}
	
	public void reset() {
		state = STATE_RELEASED;
		amount = 0;
	}
	
	public synchronized void tap() {
		press();
		release();
	}
	
	public synchronized void release() {
		state = STATE_RELEASED;
	}
	
	public synchronized void press() {
		press(1);
	}
	
	public synchronized void press(int amount) {
		if(state != STATE_WAITING_FOR_RELEASE) {
			this.amount += amount;
			state = STATE_PRESSED;
		}
	}
	
	public synchronized boolean isPressed() {
		return (getAmount() != 0);
	}
	
	public synchronized int getAmount() {
		int retVal = amount;
		
		if(retVal != 0) {
			if(state == STATE_RELEASED)
				amount = 0;
			else if(behaviour == DETECT_INITIAL_PRESS_ONLY) {
				state = STATE_WAITING_FOR_RELEASE;
				amount = 0;
			}
		}
		return retVal;
	}
	
}
