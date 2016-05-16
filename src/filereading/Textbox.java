package filereading;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Textbox {
	int y = 520;
	BufferedImage img;
	Color col1 = new Color(46, 48, 146);
	Color col2 = new Color(236, 0, 139);
	ArrayList<String> lines;
	int index;

	public Textbox(ArrayList<String> lns, BufferedImage image, int yloc, int indx) {
		lines = lns;
		y = yloc;
		img = image;
		index = indx;
	}

	public Textbox(ArrayList<String> lns, BufferedImage image, Color color1, Color color2) {
		lines = lns;
		img = image;
		col1 = color1;
		col2 = color2;
	}
	public Textbox(ArrayList<String> lns, BufferedImage image) {
		lines = lns;
		img = image;
	}

	public void draw(Graphics g) {
		g.setColor(col2);
		g.fillRect(0, 460, 800, 140);
		g.setColor(col1);
		g.fillRect(10, 470, 780, 100);
		g.drawImage(img, 10, 475, 90, 90, null);
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(g.getFont().getStyle(), 20));
		for (int i = 0; i < lines.size(); i++) {
			g.drawString(lines.get(i), 110, y + (20 * i));

		}
	}
}
