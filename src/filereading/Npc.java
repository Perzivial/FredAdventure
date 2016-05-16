package filereading;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Npc {
	int x;
	int y;
	int width;
	int height;
	ArrayList<Textbox> textboxes = new ArrayList<>();
	BufferedImage img;
	Rectangle rect;
	int currentimg = 0;
	int velx;
	int vely;
	boolean shouldopenwindow = false;
	int windowindex;
	public Npc(int xpos, int ypos, int w, int h, String url) {
		x = xpos;
		y = ypos;
		width = w;
		height = h;
		img = new Image(url).img;
	}
/**
 * does the basic drawing (and movement if nessesary)
 * @param g
 */
	public void draw(Graphics g) {
		if (x < 900 && x > (-100 - width))
			x += velx;
		if (y < 700 && y > (-100 - height))
			y += vely;
		g.drawImage(img, x, y, width, height, null);
		rect = new Rectangle(x, y, width, height);
	}
}
