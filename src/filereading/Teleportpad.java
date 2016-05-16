package filereading;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Teleportpad {
	int x;
	int y;
	int width;
	int height;
	int newroom;
	int newxlocation;
	int newylocation;
	Rectangle rect;
	Player theplayer;
	boolean isactivated = false;
	int lifetime;
/**
 * The "Teleportpad" is what triggers the movement to a new room. 
 * @param xpos
 * @param ypos
 * @param w
 * @param h
 * @param roomnum
 * @param newlocx
 * @param newlocy
 * @param plyr
 */
	public Teleportpad(int xpos, int ypos, int w, int h, int roomnum, int newlocx, int newlocy, Player plyr) {
		x = xpos;
		y = ypos;
		width = w;
		height = h;
		newroom = roomnum;
		theplayer = plyr;
		newxlocation = newlocx;
		newylocation = newlocy;

	}

	public Teleportpad(int where, int roomnum) {
		if (where == 1) {
			x = -200;
			y = 0;
			width = 110;
			height = 800;
			newxlocation = 740;
			newylocation = 270;
		}
		if (where == 2) {
			x = 900;
			y = 0;
			width = 110;
			height = 800;
			newxlocation = -50;
			newylocation = 270;

		}
		if (where == 3) {
			x = 0;
			y = -200;
			width = 800;
			height = 110;
			newxlocation = 300;
			newylocation = 700;

		}
		if (where == 4) {
			x = 0;
			y = 700;
			width = 800;
			height = 110;
			newxlocation = 300;
			newylocation = -100;

		}
		newroom = roomnum;
		// theplayer = plyr;
		rect = new Rectangle(x, y, width, height);
	}
/**
 * when the player collides with this Teleportpad, it turns isactivated to true and teleports the player to the specified location
 * @param g
 * @param player
 */
	public void dostuff(Graphics g, Player player) {
		theplayer = player;
		if (lifetime < 62)
			lifetime++;
		g.fillRect(x, y, width, height);
		if (theplayer.rect != null) {
			if (checkcollision(rect, theplayer.rect) && lifetime > 60) {

				isactivated = true;
				theplayer.x = newxlocation;
				theplayer.y = newylocation;
			}
		} else {
			System.out.print("Dammit");
		}

	}

	public Boolean checkcollision(Rectangle rect1, Rectangle rect2) {
		if (rect1.intersects(rect2))
			return true;
		return false;
	}
}
