package filereading;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Psybullet2 {
	int x;
	int velx = 7;
	double y;
	int strength;
	int targetx;
	int targety;
	Rectangle rect;

	public Psybullet2(int xpos, int ypos, int lvl) {
		x = xpos;
		y = ypos;
		strength = lvl;
	}
/**
 * used by fred in his transformed state
 * @param g
 */
	public void draw(Graphics g) {
		int yint = (int) y;
		x += velx;
		rect = new Rectangle(x, (int) y, strength, 600);
		g.setColor(Color.black);
		//g.fillOval(x - 20, yint, strength, strength);
		// g.fillRect(x, yint, strength, strength);
	}
}
