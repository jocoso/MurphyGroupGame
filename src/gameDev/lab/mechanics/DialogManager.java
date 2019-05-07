package gameDev.lab.mechanics;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

/**
 * Class managing everything related to Dialog
 * between characters. <p>The DialogManager can be
 * reused as much as needed and its only purpose is
 * to display text.
 */
public class DialogManager {
	public boolean visible;
	
	public int height, width, x, y;
	public Font font;
	public Color backgroundColor;
	
	private static int MARGIN = 20;	
	private int fontSize;
	
	public DialogManager(Component comp) {
		height = 200;
		width = comp.getWidth() - (MARGIN * 2);
		
		x = MARGIN;
		y = width - height;  
		
		visible = false;
		fontSize = 20;
		backgroundColor = new Color(10, 10, 10);
		setFontSize(fontSize);
	}
	
	
	
	public void setFontSize(int size) {
		
		font = new Font("Times Roman", Font.PLAIN, size);
	}
	
	
	/**
	 * Creates a opaque square on the bottom of the screen.
	 * <p>This square will be superpose by text and will
	 * serve to help display dialog.
	 */
	public void drawConversationBlock(Graphics g) {
		
//		Composite alpha = AlphaComposite.getInstance(
//		AlphaComposite.SRC_OVER, .5f);
		
		//g.setComposite(alpha);
		
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x,y, width, height);
		visible = true;
	}
	
	public void write(String say, Graphics g) {
		g.setFont(font);
		g.drawString(say, x + MARGIN, y + MARGIN);
	}
	
	public boolean isVisible() {
		return visible;
	}
	
}
