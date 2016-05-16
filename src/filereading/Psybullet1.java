package filereading;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Psybullet1 {
	int x;
	int velx = 15;
	double y;
	int strength;
	int targetx;
	int targety;
	BufferedImage img;
	Rectangle rect;
	int damage;

	public Psybullet1(int xpos, int ypos, int lvl, int dmg, String url) {
		x = xpos;
		y = ypos;
		strength = lvl;
		damage = dmg;
		img = new Image(url).img;
	}
/**
 * draws and rotates the image of the fireball to save space on different images
 * @param g
 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int yint = (int) y;
		x += velx;
		g2.translate(x - 10, y - 10);
		g2.rotate(Math.toRadians(x));
		g2.drawImage(img, 0 - 20, 0 - 20, strength, strength, null);
		rect = new Rectangle(x, (int) y, strength, strength);
		g2.rotate(Math.toRadians(-x));
		g2.translate(-x + 10, -y + 10);
		// g.fillRect(x, yint, strength, strength);
	}
}
