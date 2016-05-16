package filereading;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Explosion {
	int x;
	int y;
	int velx;
	int vely;
	int amountrotate = 0;
	int size;
	Color color;
	boolean shoulddraw = false;
	int lifetime = 200;

	public Explosion(int xpos, int ypos, int xvel, int yvel, int sz, Color clr) {
		x = xpos;
		y = ypos;
		velx = xvel;
		vely = yvel;
		size = sz;
		color = clr;
	}
	public Explosion(int xpos, int ypos, int xvel, int yvel, int sz, Color clr, int life) {
		x = xpos;
		y = ypos;
		velx = xvel;
		vely = yvel;
		size = sz;
		color = clr;
		lifetime = life;
	}
	public void draw(Graphics g) {
		lifetime --;
		Color myColour = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255);
		y += vely;
		x += velx;
		g.setColor(myColour);
		if (shoulddraw == true)
			g.fillRect(x, y, size, size);
		shoulddraw = true;
	}

	public static int randInt(int min, int max) {
		Random random = new Random();
		int randomNum = random.nextInt(max - min + 1) + min;
		return randomNum;
	}
}
