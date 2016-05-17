package filereading;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * utility class. creates a bufferedimage to save space in game.java
 * @author Perzivial
 *
 */
public class Image{
	BufferedImage img;
	String path;

	public Image(String url) {
		img = loadImage(url);
		path = url;
	}

	public BufferedImage loadImage(String fileName) {
		BufferedImage buff = null;
		try {
			buff = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (Exception e) {
			try {
				buff = ImageIO.read(new File(String.valueOf("src/filereading" + fileName)));

			} catch (Exception e2) {
				e.printStackTrace();
				return null;
			}
		}
		return buff;
	}

}
