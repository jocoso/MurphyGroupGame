package gameDev.lab.game_pieces;
import gameDev.lab.physics.Rectangle;
import gameDev.lab.tools.UtilityTools;

public class Player extends Sprite {
	
	public static String[] poses = {"front", "back", "right", "left"};
	static String addr = UtilityTools.getAddr();
	public static float SPEED   = 3f;
	public static float GRAVITY = .50f;
	
	public static int STATE_NORMAL  = 0;
	public static int STATE_JUMPING = 1;
	
	private int state;
	
	public Player(int x, int y, int z) {
		super(x, y, z, 80, 120, "boy", addr, poses, 4, 10);
		state = STATE_NORMAL;
	}
	
	
	 public void setY(int y) {
		 this.y = y;
		 rect.y = y;
	 }
	 
	 public void setX(int x) {
		 this.x = x;
		 rect.x = x;
	 }
	
	public void setFloorY(int floorY) {
		this.floorY = floorY;
		setY(floorY);
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void jump() {
		//Decreases the Y-Coordinates of the Sprite by the jump strength
		setVelocityY(-10f);
		state  = STATE_JUMPING;
	}
	
	
	public void update() {
		// set vertical velocity (gravity effect)
		if(getState() == STATE_JUMPING)
			setVelocityY(getVelocityY() + GRAVITY);
		
		
		// move Player
		super.update();
		
		
		
//		// check if player landed on floor
//		if(getState() == STATE_JUMPING && getY() >= floorY) {
//			setVelocityY(0);
//			setY(floorY);
//			setState(STATE_NORMAL);
//		}
	
	}
	

}
