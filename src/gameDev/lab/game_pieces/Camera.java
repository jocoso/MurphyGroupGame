package gameDev.lab.game_pieces;

import gameDev.lab.tools.Lookup;

public class Camera {
	static int x = 0;
	static int y = 0;
	static int z = 0;
	
	static int angle = 0;
	
	public static void set(int x, int y, int z, int angle) {
		Camera.x = x;
		Camera.y = y;
		Camera.z = z;
		
		Camera.angle = angle;
	}
	
	public static void moveForward(int dist) {
		x += dist * Lookup.sin[angle];
		z += dist * Lookup.cos[angle];
	}
	
	public static void moveBackward(int dist) {
		x -= dist * Lookup.sin[angle];
		z -= dist * Lookup.cos[angle];
	}
	
	public static void turnLeft(int dangle) {
		angle -= dangle;
		if(angle < 0) angle += 360;
	}
	
	public static void turnRight(int dangle) {
		angle += dangle;
		if(angle > 359) angle -= 360;
	}
}
