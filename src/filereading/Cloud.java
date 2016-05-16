package filereading;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cloud {
	BufferedImage img = new Image("pics/cloud.png").img;
	int x;
	int y;
	int size;
	public Cloud(int xpos, int ypos ,int sz) {
		x = xpos;
		y = ypos;
		size = sz;
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, 87 * size, 50 * size, null);
		x ++;
	}

}
