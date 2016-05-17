package filereading;

import java.awt.*;
/**
 * walls that are drawn and checked if the player collides with
 * @author Perzivial
 *
 */
public class Wall {
	int x;
	int y;
	int width;
	int height;
	Rectangle rect;

	public Wall(int xpos, int ypos, int w, int h) {
		x = xpos;
		y = ypos;
		width = w;
		height = h;

	}

	public void draw(Graphics g) {
		g.fillRect(x, y, width, height);
		rect = new Rectangle(x, y, width, height);
	}

}
