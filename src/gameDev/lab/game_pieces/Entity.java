package gameDev.lab.game_pieces;

import java.awt.Graphics;

import gameDev.lab.physics.Rectangle;

public interface Entity {
	public String getName();
	public int getY();
	public int getX();
	public Rectangle getRect();
	public void draw2D(Graphics g);

}
