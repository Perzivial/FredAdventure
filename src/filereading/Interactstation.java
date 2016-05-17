package filereading;

import java.awt.Rectangle;
/**
 * two kinds of station, saving and healing
 * @author Perzivial
 *
 */
public class Interactstation {
	int x;
	int y;
	String type;
	Rectangle rect;
	/**
	 * the actual code that handles the interact stations is all in Game.java, this is done for ease of access of the variables
	 * @param xpos
	 * @param ypos
	 * @param tp
	 */
	public Interactstation(int xpos, int ypos, String tp) {
		x = xpos;
		y = ypos;
		type = tp;
	}
}
