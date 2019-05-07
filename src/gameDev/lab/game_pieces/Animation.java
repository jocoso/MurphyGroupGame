package gameDev.lab.game_pieces;
import java.awt.*;

public class Animation {
	private Image[] image;
	private int current = 0, duration, delay;
	
	public Animation(String name, String addr, int count, int duration) {
		image = new Image[count];
		
		
		
		for(int i = 0; i < count; i++) 
			image[i] = Toolkit.getDefaultToolkit().getImage(addr + "\\" + name + "-" + i + ".png");
			
		this.duration = duration;
		delay = duration;
	}
	
	public Image getCurrentImage() {
		if(delay == 0) {
			current++;
			if(current == image.length) current = 1;
			delay = duration;
		}
		
		delay--;
		return image[current];
	}
	
	public Image getStillImage() {
		return image[0];
	}
}
