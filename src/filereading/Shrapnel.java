package filereading;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;
/**
 * particles that use gravity
 * @author Perzivial
 *
 */
public class Shrapnel {
	int x;
	int y;
	int velx;
	int vely;
	int amountrotate = 0;
	int size;
	Color color;
	boolean shoulddraw = false;
	int lifetime = 200;

	public Shrapnel(int xpos, int ypos, int xvel, int yvel, int sz, Color clr) {
		x = xpos;
		y = ypos;
		velx = xvel;
		vely = yvel;
		size = sz;
		color = clr;
	}
/**
 * brings the lifetime down and moves the shrapnel down (gravity), also,drawing
 * @param g
 */
	public void draw(Graphics g) {
		lifetime--;
		y += vely;
		x += velx;
		vely++;
		g.setColor(color);
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
