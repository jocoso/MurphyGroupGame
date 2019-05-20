package gameDev.lab.tools;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;



public abstract class GameCore extends JFrame{
	protected static final int FONT_SIZE = 24;
	private boolean isRunning;
	
	public GameCore(int width, int height) {
		super("A window!");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public void run() {
		setVisible(true);
		init();
		gameLoop();
	}
	
	public void init() {
		setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
		setBackground(Color.blue);
		setForeground(Color.white);
		isRunning = true;
	}
	
	public Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public void gameLoop() {
		while(isRunning) {
			update();
			Graphics g = getGraphics();
			draw(g);
			try {
				Thread.sleep(20);
			}catch(InterruptedException ex) {}
		}
		lazilyExit();
	}
	
	public void update() {
		
	}
	
	public void lazilyExit() {
		Thread thread = new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				}catch(InterruptedException ex) {}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}
	
	public abstract void draw(Graphics e);
}
