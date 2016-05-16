package filereading;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
	String name;
	int maxhealth;
	int health;
	int attack;
	int defence;
	int x = 400;
	int y = 300;
	int velx = 0;
	int vely = 0;
	boolean isinbattle;
	int exp;
	int expbefore;
	int level = 1;
	int total;
	double percent;
	int levelbefore;
	int tempattack;
	int oldx;
	int oldy;
	BufferedImage img = new Image("pics/fred.png").img;
	BufferedImage img2 = new Image("pics/fredanimation2.png").img;
	BufferedImage img3 = new Image("pics/fredanimation3.png").img;
	BufferedImage attackimg = new Image("pics/fredattack1.png").img;
	BufferedImage attackimg2 = new Image("pics/fredattack2.png").img;
	BufferedImage attackimg3 = new Image("pics/fredattack3.png").img;
	BufferedImage flipfred1 = new Image("pics/fredbig1.png").img;
	BufferedImage flipfred2 = new Image("pics/fredbig2.png").img;
	BufferedImage flipfred3 = new Image("pics/fredbig3.png").img;
	BufferedImage imgded = new Image("pics/fred2.png").img;
	BufferedImage fredwalk = new Image("pics/fredup.png").img;
	BufferedImage fredup = new Image("pics/fredup.png").img;
	BufferedImage freddown = new Image("pics/freddown.png").img;
	BufferedImage fredleft = new Image("pics/fredleft.png").img;
	BufferedImage fredright = new Image("pics/fredright.png").img;
	BufferedImage fredupleft = new Image("pics/fredupleft.png").img;
	BufferedImage fredupright = new Image("pics/fredupright.png").img;
	BufferedImage freddownleft = new Image("pics/freddownleft.png").img;
	BufferedImage freddownright = new Image("pics/freddownright.png").img;
	ArrayList<Attack> attacks = new ArrayList<>();
	int currentattack = 0;
	ArrayList<BufferedImage> battleanimation = new ArrayList<>();
	ArrayList<BufferedImage> attackanimation = new ArrayList<>();
	ArrayList<BufferedImage> flipsideanimation = new ArrayList<>();
	Rectangle rect;
	Rectangle rectbattle;
	boolean shouldmoveback2 = false;
	int expdifference;
	private boolean shouldmoveback;
	int cellnum = 0;
	int cellnumchangetimer = 2;
	boolean isattacking;
	Sound ding = new Sound("sounds/ding.wav");
	boolean hasflipped = false;
	boolean isgoingforward = false;
	int forward = 0;
	boolean isinteracting;
	Rectangle rectinteract;
/**
 * name, health, attack, defence, x position, y position, level, experience
 * @param n
 * @param h
 * @param a
 * @param d
 * @param xpos
 * @param ypos
 * @param lvl
 * @param ex
 */
	public Player(String n, int h, int a, int d, int xpos, int ypos, int lvl, int ex) {
		name = n;
		health = h;
		attack = a;
		defence = d;
		maxhealth = 20;
		x = xpos;
		y = ypos;
		level = lvl;
		exp = ex;
		battleanimation.add(img);
		battleanimation.add(img2);
		battleanimation.add(img3);
		attackanimation.add(attackimg);
		attackanimation.add(attackimg2);
		attackanimation.add(attackimg3);
		flipsideanimation.add(flipfred1);
		flipsideanimation.add(flipfred2);
		flipsideanimation.add(flipfred3);

	}
	public void applydamage(int amount) {
		if (amount > (defence / 2))
			health -= amount - (defence / 2);
		else {
			health--;
		}
		if (health < 0)
			health = 0;
	}

	public void heal(int amount) {
		health += amount;
	}
/**
 * draws the player with approriate image, hitboxes, etc
 * @param g
 */
	public void draw(Graphics g) {
		// TODO the draw method
		placeinteractrects(g);
		putbackinframe();
		if (isinbattle) {
			rectbattle = new Rectangle(40 + forward, 210, 100, 200);
			maxhealth = 20 + (5 * (level - 1));
			if (health > 0) {
				if (!hasflipped) {
					if (isattacking)
						g.drawImage(attackanimation.get(cellnum), 40 + forward, 210, 100, 200, null);
					else
						g.drawImage(battleanimation.get(cellnum), 40 + forward, 210, 100, 200, null);
				} else {
					g.drawImage(flipsideanimation.get(cellnum), 20, 10, 200, 400, null);
				}
			} else
				g.drawImage(imgded, 40, 350, 200, 100, null);
			drawlifebar(g);
			cellnumchangetimer--;
			animate();
			moveinbattle();
		} else{
			g.drawImage(fredwalk, x, y, 100, 100, null);
			expbefore = exp;
		}
		if (vely == -5)
			fredwalk = fredup;
		if (vely == 5)
			fredwalk = freddown;
		if (velx == -5)
			fredwalk = fredleft;
		if (velx == 5)
			fredwalk = fredright;
		if (vely == -5 && velx == -5)
			fredwalk = fredupleft;
		if (vely == -5 && velx == 5)
			fredwalk = fredupright;
		if (vely == 5 && velx == -5)
			fredwalk = freddownleft;
		if (vely == 5 && velx == 5)
			fredwalk = freddownright;
		levelup(g);
		drawcurrentattack(g);
	}
public void move(){
	if(!isinbattle)
	{
		shouldmoveback2 = false;
		oldx = x;
		oldy = y;
		tempattack = 0;
		x += velx;
		y += vely;
	}
}
public Boolean checkcollision(Rectangle rect1, Rectangle rect2) {
	if (rect1.intersects(rect2))
		return true;
	return false;
}
/**
 * in order to prevent the player from glitching in and out of the walls, this method checks if the player would collide with the walls if it would move, the rest is handled in Game.java
 * @param walls
 * @param rect1
 * @return
 */
public boolean checkifwouldcollidewithwalls(ArrayList<Wall> walls, Rectangle rect1){
	for(Wall current: walls){
	if(checkcollision(current.rect, rect1))
		return true;
	}
	return false;
}
	public void drawlifebar(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (health > maxhealth)
			health = maxhealth;
		// makes the health bar total size stay constant no matter what the max
		// is
		// g.fillRect(100, 450, health * 50, 20);
		Double healthdouble = (double) health;
		Double healthtotal = (double) maxhealth;
		double percentleft = (healthdouble / healthtotal) * 300;
		g.setColor(Color.blue);
		g.fillRect(100, 450, 300, 20);
		g.setColor(Color.cyan);
		if (percentleft < 50)
			g.setColor(Color.yellow);
		if (percentleft < 25)
			g.setColor(Color.red);
		g.fillRect(100, 450, (int) percentleft, 20);
		g.setColor(Color.black);
		g2.drawString("HP: " + Integer.toString(health) + "/" + Integer.toString(maxhealth), 30, 465);
		g.drawString("Fred, level " + level + " bipedal rabbit", 30, 430);
	}

	public int attack(int target) {
		return target - (attack + tempattack);
	}
/**
 * checks if the player should level up, then does so
 * This method also draws the experience bar
 * @param g
 */
	public void levelup(Graphics g) {
		total = level * 5;
		if (exp >= (total)) {
			level++;
			exp = 0;
			attack += 3;
			maxhealth += 5;
			defence += 2;
		}
		if (isinbattle == true) {
			Graphics2D g2 = (Graphics2D) g;
			double expdouble = (double) exp;
			double totaldouble = (double) total;
			g.setColor(Color.blue);
			g.fillRect(100, 500, 100, 10);
			g.setColor(Color.cyan);
			/*
			 * Instead of drawing a bar that the x value is equal to the experience, it draws the percentage of the exp over the max exp
			 */
			percent = (expdouble / totaldouble) * 100;
			g.fillRect(100, 500, (int) percent, 10);
			g.setColor(Color.black);
			g2.drawString("EXP: " + Integer.toString((int) percent) + "%", 40, 509);

		}
	}

	public void makeplayerrect() {
		rect = new Rectangle(x, y, 100, 100);
	}

	public void tempraiseattack(int amount) {
		tempattack += amount;
	}
/**
 * makes sure that the player does not walk too far outside of the bounds of the screen
 */
	public void putbackinframe() {
		if (x < -152) {
			x = -100;
		}
		if (y < -152) {
			y = -100;
		}
		if (x > 852) {
			x = 800;
		}
		if (y > 652) {
			y = 600;
		}
	}

	public void shouldmoveback() {
		x = oldx;
		y = oldy;
		velx = 0;
		vely = 0;
	}
/**
 * cycles through the arraylist of images to be drawn in battle
 */
	public void animate() {
		if (cellnumchangetimer <= 0) {
			cellnum++;
			cellnumchangetimer = 8;
		}
		if (cellnum >= battleanimation.size()) {
			cellnum = 0;
		}

	}
	public void drawcurrentattack(Graphics g) {
		if (isinbattle && !hasflipped) {
			g.drawString("Selected Attack:" + attacks.get(currentattack).name, 20, 50);
			g.drawString("Type:" + attacks.get(currentattack).element, 20, 70);
		}
	}

	public void goforward() {
		isgoingforward = true;
	}
/**
 * moves the player forward if nessesary 
 */
	public void moveinbattle() {
		if (isgoingforward == true) {
			forward += 30;
		} else {
			if (forward > 0) {
				forward -= 30;
			}
		}
		if (forward < 0)
			forward = 0;
	}
/**
 * places appropriate interacting hitboxes depending on the direction that the player is moving
 * @param g
 */
	public void placeinteractrects(Graphics g) {
		if (isinteracting) {
			g.setColor(Color.BLACK);
			if (fredwalk.equals(fredright)) {
				// g.fillRect(x + 100, y, 50, 100);
				rectinteract = new Rectangle(x + 100, y, 50, 100);
			}
			if (fredwalk.equals(fredleft)) {
				// g.fillRect(x - 50, y, 50, 100);
				rectinteract = new Rectangle(x - 50, y, 50, 100);
			}
			if (fredwalk.equals(fredup)) {
				// g.fillRect(x, y - 50, 100, 50);
				rectinteract = new Rectangle(x, y - 50, 100, 50);
			}
			if (fredwalk.equals(freddown)) {
				// g.fillRect(x, y + 100, 100, 50);
				rectinteract = new Rectangle(x, y + 100, 100, 50);
			}
			if (fredwalk.equals(freddownleft)) {
				// g.fillRect(x - 40, y + 50, 80, 80);
				rectinteract = new Rectangle(x - 40, y + 50, 80, 80);
			}
			if (fredwalk.equals(freddownright)) {
				// g.fillRect(x + 50, y + 50, 80, 80);
				rectinteract = new Rectangle(x + 50, y + 50, 80, 80);
			}
			if (fredwalk.equals(fredupleft)) {
				// g.fillRect(x - 40, y - 50, 80, 80);
				rectinteract = new Rectangle(x - 40, y - 50, 80, 80);
			}
			if (fredwalk.equals(fredupright)) {
				// g.fillRect(x + 50, y - 50, 80, 80);
				rectinteract = new Rectangle(x + 50, y - 50, 80, 80);
			}
		}
	}
}
