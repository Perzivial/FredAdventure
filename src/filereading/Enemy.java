package filereading;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class Enemy {
	int x;
	int y;
	int health;
	int maxhealth;
	int attack;
	BufferedImage overworldimg;
	BufferedImage battleimg;
	BufferedImage overworldimginverted;
	BufferedImage battleimginverted;
	boolean isinbattle = false;
	String name;
	int forward = 0;
	int back = 0;
	Rectangle rect;
	private boolean ismovingforward;
	int expamount;
	boolean isinverted = false;
	int width;
	int height;
	int width2;
	int height2;
	int ymodifier = 0;
	boolean isgoingup;
	int changetimer;
	int battleheight;
	int bobheight;
	String weakness;
	BufferedImage Battlehalfway;
	boolean isbossbattle = false;
	ArrayList<Shrapnel> shraps= new ArrayList<>();
	public Enemy(String nm, int xpos, int ypos, int w, int h, int w2, int h2, int maxhp, int attk, int expdrop,
			String urloverworld, String urlbattle, int yinbattle, int bobamount, String weakto) {
		name = nm;
		x = xpos;
		y = ypos;
		maxhealth = maxhp;
		health = maxhealth;
		attack = attk;
		expamount = expdrop;
		overworldimg = new Image(urloverworld).img;
		battleimg = new Image(urlbattle).img;
		battleimginverted = new Image(urlbattle.substring(0, urlbattle.length() - 4) + "inverted" + ".png").img;
		width = w;
		height = h;
		width2 = w2;
		height2 = h2;
		battleheight = yinbattle;
		bobheight = bobamount;
		weakness = weakto;
	}
	public Enemy(String nm, int xpos, int ypos, int w, int h, int w2, int h2, int maxhp, int attk, int expdrop,
			String urloverworld, String urlbattle, int yinbattle, int bobamount, String weakto, BufferedImage battlehalf) {
		name = nm;
		x = xpos;
		y = ypos;
		maxhealth = maxhp;
		health = maxhealth;
		attack = attk;
		expamount = expdrop;
		overworldimg = new Image(urloverworld).img;
		battleimg = new Image(urlbattle).img;
		battleimginverted = new Image(urlbattle.substring(0, urlbattle.length() - 4) + "inverted" + ".png").img;
		width = w;
		height = h;
		width2 = w2;
		height2 = h2;
		battleheight = yinbattle;
		bobheight = bobamount;
		weakness = weakto;
		Battlehalfway = battlehalf;
		isbossbattle = true;
	}
	/**
	 * draws and calls most other methods
	 * @param g
	 */
	public void draw(Graphics g) {

		if (isinbattle) {
			bob();
			if (isinverted)
				g.drawImage(battleimginverted, 550 - forward, battleheight + ymodifier, width2, height2, null);
			else
				g.drawImage(battleimg, 550 - forward, battleheight + ymodifier, width2, height2, null);
			rect = new Rectangle(550 - forward, 330, 120, 100);
			
			drawlifebar(g);
			drawexplosionparts(g);
			moveback();
		}
		if (!isinbattle) {
			g.drawImage(overworldimg, x, y, width, height, null);
			rect = new Rectangle(x, y, width, height);
		}
	
	}

	public void drawlifebar(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		/*
		 * double doublemaxhp = (double) maxhealth; double doublehealth =
		 * (double) health; double percentful = (doublemaxhp / doublehealth) *
		 * 200;
		 */
		Double healthdouble = (double) health;
		Double healthtotal = (double) maxhealth;
		double percentleft = (healthdouble / healthtotal) * 200;
		g.setColor(Color.red);
		g.fillRect(550, 450, 200, 20);
		g.setColor(Color.orange);
		g.fillRect(550, 450, (int) percentleft, 20);
		g.setColor(Color.black);
		g2.drawString(name + ": " + Integer.toString(health) + "/" + Integer.toString(maxhealth), 560, 465);
	}

	public void moveback() {
		if (forward > 0 && ismovingforward == false) {
			forward -= 10;
		}
		if (ismovingforward) {
			forward += 50;
			if (forward > 400)
				ismovingforward = false;
		}

	}

	public void moveforward() {
		ismovingforward = true;
	}
/**
 * moves the enemy up and down
 */
	public void bob() {
		changetimer--;
		if (changetimer <= 0) {
			if (isgoingup)
				ymodifier++;
			else
				ymodifier--;
			if (ymodifier >= bobheight)
				isgoingup = false;
			if (ymodifier <= -bobheight)
				isgoingup = true;
			changetimer = 5;
		}
	}
	public void drawexplosionparts(Graphics g){
		if(isbossbattle)
		shraps.add(new Shrapnel(randInt(0,800), -10, randInt(-1,1), randInt(5,7), randInt(5,10), Color.cyan));
		for(int i = 0; i < shraps.size(); i ++){
			shraps.get(i).draw(g);
		}
	}
	public static int randInt(int min, int max) {
		Random random = new Random();
		int randomNum = random.nextInt(max - min + 1) + min;
		return randomNum;
	}
}
