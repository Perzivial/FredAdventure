package filereading;

import java.awt.image.BufferedImage;

public class Attack {
	String name;
	String type;
	int damage;
	String element;
	String url;

	public Attack(String namein, String typein, int damagein, String elementin, String path) {
		name = namein;
		type = typein;
		damage = damagein;
		element = elementin;
		url = path;
	}
}
