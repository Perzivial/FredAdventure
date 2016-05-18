package filereading;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.Line;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
/**
 * The largest file. Manages everything in the larger game, held inside Gameviewer.java
 * @author Perzivial
 *
 */
public class Game extends JComponent implements KeyListener {

	private static final long serialVersionUID = 1L;
	Player theplayer;
	BufferedImage carlneutral = new Image("pics/carlneutral.png").img;
	BufferedImage paulneutral = new Image("pics/paulneutral.png").img;
	BufferedImage civilianneutral = new Image("pics/civilianneutral.png").img;
	BufferedImage startscreen1 = new Image("pics/startscreen1.png").img;
	BufferedImage startscreen2 = new Image("pics/startscreen2.png").img;
	BufferedImage sunnybackground = new Image("pics/sunback.png").img;
	BufferedImage fredonrock = new Image("pics/fredonrock.png").img;
	BufferedImage stanneutral = new Image("pics/textboxstan.png").img;
	BufferedImage stanhappy = new Image("pics/stanhappy.png").img;
	BufferedImage worshipperneutral = new Image("pics/worshipperneutral.png").img;
	BufferedImage worshippersmile = new Image("pics/worshippersmile.png").img;
	BufferedImage worshippercrazy = new Image("pics/worshippercrazy.png").img;
	BufferedImage imgbackbasic = new Image("pics/backgroundbasic.png").img;
	BufferedImage imgbackbasictop = new Image("pics/backgroundbasictop.png").img;
	BufferedImage imgmessage = new Image("pics/battlewin.png").img;
	BufferedImage imgbattletips = new Image("pics/battletips.png").img;
	BufferedImage imgbattletipsinverted = new Image("pics/battletipsinverted.png").img;
	BufferedImage imgbattletipsclosed = new Image("pics/battletipsclosed.png").img;
	BufferedImage imgbattletipsclosedinverted = new Image("pics/battletipsclosedinverted.png").img;
	BufferedImage imgpowerup = new Image("pics/powerup.png").img;
	BufferedImage imgdamagetable = new Image("pics/damagetable.png").img;
	BufferedImage flipsideicon = new Image("pics/fredicon.png").img;
	BufferedImage flipsideicon2 = new Image("pics/frediconinverted.png").img;
	BufferedImage gameover = new Image("pics/gameover.png").img;
	BufferedImage darkstar = new Image("pics/darkstar.png").img;
	BufferedImage demoend = new Image("pics/demoend.png").img;
	BufferedImage statsbackground = new Image("pics/statsbackground.png").img;
	BufferedImage supereffective = new Image("pics/supereffective.png").img;
	BufferedImage criticalhit = new Image("pics/criticalhit.png").img;
	ArrayList<Psybullet1> psybullets = new ArrayList<>();
	ArrayList<Shrapnel> shrapnellist = new ArrayList<>();
	ArrayList<Teleportpad> tppads = new ArrayList<>();
	ArrayList<Wall> wallslist = new ArrayList<>();
	Enemy currentenemy;
	HashSet<Integer> keyspressed = new HashSet<>();
	ArrayList<Enemy> enemies = new ArrayList<>();
	int attacktimer, currentroom = 0, displaytimer = 0;
	boolean isdisplayingmessagebox = false;
	int amountattackpowerups = 0;
	boolean shouldshowhelp;
	boolean canattack = true;
	Sound battlemusic = new Sound("sounds/battle.wav");
	Sound startareamusic = new Sound("sounds/beginnings.wav");
	Sound pew = new Sound("sounds/pew.wav");
	Sound hit = new Sound("sounds/hit.wav");
	Sound zer = new Sound("sounds/zer.wav");
	Sound defaultzer = zer;
	Sound epic = new Sound("sounds/epic.wav");
	Sound boom = new Sound("sounds/boom.wav");
	Sound gameoversound = new Sound("sounds/gameover.wav");
	Sound whack = new Sound("sounds/whack.wav");
	Sound hum = new Sound("sounds/hum.wav");
	Sound menumusic = new Sound("sounds/menumusic.wav");
	Sound soundmenu = new Sound("sounds/soundmenu.wav");
	Sound entergame = new Sound("sounds/entergame.wav");
	Sound victorysound = new Sound("sounds/victorysound.wav");
	Sound city1 = new Sound("sounds/city1.wav");
	Sound city2 = new Sound("sounds/city2.wav");
	Sound bestfriend = new Sound("sounds/bestfriend.wav");
	Sound bossbattlemusic = new Sound("sounds/bosstheme.wav");
	Sound normalbattlemusic = battlemusic;
	Sound wintry = new Sound("sounds/winter.wav");
	Sound ding = new Sound("sounds/textbox.wav");
	Sound roar = new Sound("sounds/roar.wav");
	int theplayersx;
	int theplayersy;
	int attackpowermodifier;
	int damagemodifierlevel;
	int addtoythickness = 0;
	ArrayList<Psybullet2> bigbullets = new ArrayList<>();
	ArrayList<Explosion> explosionparticles = new ArrayList<>();
	ArrayList<Npc> npcs = new ArrayList<>();
	int flipsidemeter = 50;
	int exitbattletimer;
	Line[] lines1;
	boolean isinbossbattle = false;
	boolean isbossleadup;
	BufferedImage bossimg;
	int bossleaduptimer;
	int prevx;
	int prevy;
	AffineTransform oldXForm;
	boolean hassetbasexform;
	boolean rumble = false;
	int rumbletimer = 0;
	Npc currentnpc;
	int textboxtimer;
	Wall walltop = new Wall(0, 0, 800, 100);
	Wall wallbottom = new Wall(0, 500, 800, 100);
	Wall wallright = new Wall(700, 0, 100, 600);
	Wall wallleft = new Wall(0, 0, 100, 800);
	Wall halflefttop = new Wall(0, 0, 100, 250);
	Wall halfleftbottom = new Wall(0, 400, 100, 400);
	Wall halfrighttop = new Wall(700, 0, 100, 250);
	Wall halfrightbottom = new Wall(700, 400, 100, 400);
	Color col = new Color(0, 166, 81);
	ArrayList<Interactstation> stations = new ArrayList<>();
	BufferedImage saveimg = new Image("pics/saveimg.png").img;
	BufferedImage healimg = new Image("pics/healimg.png").img;
	BufferedImage interacthint = new Image("pics/interacthint.png").img;
	int displayloadtimer = 0;
	int displaysavetimer = 0;
	int displayhealtimer = 0;
	boolean hasgamestarted = false;
	int up = 0;
	int back = 0;
	int startscreentoggletimer = 7;
	boolean isfirststartscreenimg = true;
	boolean isshowingchoosesound = false;
	BufferedImage soundschoosescreen = new Image("pics/soundchoosescreen.png").img;
	int startgametimer = 0;
	boolean hasstartedgamestarttimer;
	int showmenutimer = 3;
	boolean shouldshowmenu;
	boolean ischooseingnewattack;
	final int TPPAD_LEFT = 1;
	final int TPPAD_RIGHT = 2;
	final int TPPAD_UP = 3;
	final int TPPAD_DOWN = 4;
	Attack newattack;
	boolean haschangedattack = false;
	boolean isinventoryopen = false;
	int rotatetimer;
	boolean rotate = false;
	boolean ispaused = false;
	int dogspawntimer = 2;
	boolean isready = false;
	boolean islargesize = false;
	int basewidth = 800;
	int baseheight = 600;
	int scrollamount = 0;
	int supereffectivetimer = 31;
	int criticalhittimer = 31;
	ArrayList<Explosion> firelist = new ArrayList<Explosion>();
	ArrayList<Explosion> firelist2 = new ArrayList<Explosion>();
	int spawncloudtimer = 100;
	ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	int rumbleamount = 5;
	/**
	 * Constructor. Creates a save file and a new folder for it is nessesary.
	 * This is the file saved to by save() and load()
	 * 
	 * @throws IOException
	 */
	public Game() throws IOException {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("saves/player.txt"));
		} catch (IOException e) {
			System.out.println("Did not find any player.txt file in /saves, creating one");
			File dir = new File("saves");
			@SuppressWarnings("unused")
			boolean successful = dir.mkdir();
			PrintWriter writer;
			writer = new PrintWriter("saves/player.txt");
			writer.println("luke");
			writer.println(20);
			writer.println(3);
			writer.println(7);
			writer.println(0);
			writer.println(300);
			writer.println(300);
			writer.println(1);
			writer.println(0);
			writer.println(0);
			writer.println(1);
			writer.println(2);
			writer.println(3);
			writer.println(4);
			writer.close();
			lines = Files.readAllLines(Paths.get("saves/player.txt"));
		}
		theplayer = new Player(lines.get(0), Integer.parseInt(lines.get(1)), Integer.parseInt(lines.get(2)),
				Integer.parseInt(lines.get(3)), Integer.parseInt(lines.get(5)), Integer.parseInt(lines.get(6)),
				Integer.parseInt(lines.get(7)), Integer.parseInt(lines.get(8)));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(10))));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(11))));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(12))));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(13))));
		flipsidemeter = Integer.parseInt(lines.get(9));
		currentroom = Integer.parseInt(lines.get(4));
		System.out.println(theplayer.health);
		System.out.println(theplayer.attack);
		System.out.println(theplayer.defence);
		// newroom();
		menumusic.loop();
		isready = true;
		for (int i = 0; i < 2; i++)
			clouds.add(new Cloud(randInt(0, 600), randInt(0, 200), randInt(1, 2)));
	}

	/**
	 * Painting method. Run 60 times each second. This method calls everything
	 * else. One of the larger methods
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gscale = (Graphics2D) g;
		if (islargesize) {
			gscale.scale(1.5, 1.5);
		}
		if (!hassetbasexform) {
			Graphics2D g2 = (Graphics2D) g;
			oldXForm = g2.getTransform();
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		setrumble();
		rumble(g);
		rotateandrumble(g);
		if (hasgamestarted && !isshowingchoosesound && !ispaused) {
			if (theplayer != null) {

				// TODO the paint method
				if (!isbossleadup) {

					if (displaytimer < 7) {
						stopbattlemusic();
						keepenemylistsizesincheck();

						if (theplayer.isinbattle) {
							if (theplayer.hasflipped) {
								rumbletimer = 2;
								rumble(g);
							}
							drawbattlebackground(g);

							drawenemies(g);
							enemyattack();

							if (shouldshowhelp) {
								if (!theplayer.hasflipped)
									g.drawImage(imgbattletips, 500, 0, 300, 150, this);
								else
									g.drawImage(imgbattletipsinverted, 500, 0, 300, 150, this);
							} else {
								if (!theplayer.hasflipped)
									g.drawImage(imgbattletipsclosed, 500, 0, 300, 150, this);
								else
									g.drawImage(imgbattletipsclosedinverted, 500, 0, 300, 150, this);
							}
							checkshouldexitbattle();
							drawattackbar(g);
							drawflipsidemeter(g);

							if (theplayer.isinbattle) {
								if (battlemusic.isrunning() && theplayer.hasflipped) {
									battlemusic.stop();
									epic.loop();
								}
								if (!battlemusic.isrunning() && !theplayer.hasflipped && currentenemy.health > 0
										&& theplayer.health > 0) {
									epic.stop();
									battlemusic.continuesound();
								}
							}
						}
						if (!theplayer.isinbattle) {
							if (textboxtimer > 0)
								textboxtimer--;

							if (epic.isrunning())
								epic.stop();
							amountattackpowerups = 0;
							theplayer.makeplayerrect();
							drawbackground(g);
							checkifspecialroom(g);
							drawwalls(g);
							drawenemies(g);
							checkshouldenterbattle(g);
							drawstations(g);
						}
						// theplayer.move();
						checkcollisionwithwalls();
						theplayer.draw(g);
						theplayersx = theplayer.x;
						theplayersy = theplayer.y;
						drawenemies(g);

						if (theplayer.isinbattle) {
							drawpowerups(g);
							drawshrapnel(g);
							drawsupereffective(g);
							drawcriticalhit(g);
							if (theplayer.hasflipped)
								drawfireforeground(g);
						}
						drawbullets(g);
						if (!theplayer.isinbattle) {
							handletppads(g);
							drawNpcs(g);
							drawshrapnel(g);
						}
					}
					// should stop the boss ui from showing up when you die
					checkshouldstopbossleadup();
					if (isdisplayingmessagebox) {
						if (!haschangedattack)
							newattackpool();
						if (theplayer.health > 0 && displaytimer > 5) {
							drawscrollingbackground(g);
							g.drawImage(imgmessage, 0, 0, 800, 600, null);
							g.setFont(new Font("MyriadPro", Font.PLAIN, 41));
							g.setColor(Color.black);
							g.drawString(Integer.toString(theplayer.expdifference), 255, 290);
							g.drawString(Integer.toString(theplayer.level - theplayer.levelbefore), 300, 410);
						}
						if (theplayer.health <= 0) {
							g.drawImage(gameover, 0, 0, 800, 600, this);

						}
						if (!ischooseingnewattack)
							displaytimer--;
						if (ischooseingnewattack) {
							Color col = new Color(0, 173, 239);
							Color col2 = new Color(0, 153 + randInt(-10, 10), 239);
							g.setColor(col);
							g.fillRect(0, 0, 800, 600);

							explosionparticles.add(new Explosion(randInt(0, 800), 600, randInt(-1, 1), randInt(-5, -2),
									randInt(30, 50), col2, 500));
							for (int i = 0; i < explosionparticles.size(); i++) {
								explosionparticles.get(i).draw(g);

							}

							g.setFont(new Font("MyriadPro", Font.PLAIN, 21));
							g.setColor(Color.white);
							g.drawString("A new attack is available, choose which attack you want to", 50, 250);
							g.drawString("replace or press shift to exit", 50, 271);
							g.drawString(theplayer.attacks.get(theplayer.currentattack).name, 300, 300);
							g.drawString("Attack type: " + theplayer.attacks.get(theplayer.currentattack).element, 300,
									321);
							g.drawString("Attack potency: " + theplayer.attacks.get(theplayer.currentattack).damage,
									300, 342);
							g.drawString("New attack name: " + newattack.name, 300, 384);
							g.drawString("New attack type: " + newattack.element, 300, 405);
							g.drawString("New attack potency: " + newattack.damage, 300, 426);

						}

						if (displaytimer == 0) {
							isdisplayingmessagebox = false;
							if (currentroom <= 10)
								startareamusic.loop();
							else if (currentroom <= 20)
								city1.loop();
							if (theplayer.health <= 0) {
								respawn();
							}
							soundmenu.stop();

						}

					}

				}
			}
			if (isbossleadup) {
				// isbossleadup is equal to true
				drawscrollingbackground(g);
				g.drawImage(bossimg, 0, 0, 800, 600, this);
				bossleaduptimer--;

				if (bossleaduptimer <= 0) {
					isbossleadup = false;
				}

			}
			for (int i = 0; i < explosionparticles.size(); i++) {
				if (!ischooseingnewattack)
					explosionparticles.get(i).draw(g);
				if (explosionparticles.get(i).lifetime <= 0)
					explosionparticles.remove(i);
			}
		}
		if (!hasgamestarted) {
			menuscreen(g);
		}
		if (isshowingchoosesound) {
			showchoosesound(g);
		}
	}

	/**
	 * Implementation of the interface KeyListener, is activated in the press of
	 * any key, e is the KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO the keypressed method
		if (hasgamestarted && !isshowingchoosesound && !isdisplayingmessagebox) {

			keyspressed.add(e.getKeyCode());
			if (!theplayer.isinbattle) {

				if (e.getKeyCode() == KeyEvent.VK_S) {
					// save();
					// startareamusic.rsound();
				}
				if (e.getKeyCode() == KeyEvent.VK_L) {
					load();
				}
				if (keyspressed.contains(KeyEvent.VK_UP)) {
					theplayer.vely = -5;
				}
				if (keyspressed.contains(KeyEvent.VK_DOWN)) {
					theplayer.vely = 5;
				}
				if (keyspressed.contains(KeyEvent.VK_LEFT)) {
					theplayer.velx = -5;
				}
				if (keyspressed.contains(KeyEvent.VK_RIGHT)) {
					theplayer.velx = 5;
				}
				if (e.getKeyCode() == KeyEvent.VK_Z) {
					if (!theplayer.isinteracting) {
						theplayer.isinteracting = true;
						textboxtimer = 15;
						ding.play();
					}
				}

				// checks for when in battle
			} else {
				if (e.getKeyCode() == KeyEvent.VK_Z && attacktimer == 0 && canattack && enemies.get(0).health > 0 && !theplayer.hasflipped) {
					flipsidemeter += 25;
					attacktimer = 50;
					canattack = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT && canattack) {
					if (theplayer.currentattack != 0) {
						theplayer.currentattack--;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && canattack) {
					if (theplayer.currentattack != theplayer.attacks.size() - 1) {
						theplayer.currentattack++;
					}
				}
				if (keyspressed.contains(KeyEvent.VK_F)) {
					if (flipsidemeter == 100) {
						roar.play();
						theplayer.hasflipped = !theplayer.hasflipped;
						for (int i = 0; i < enemies.size(); i++) {
							enemies.get(i).isinverted = !enemies.get(i).isinverted;
						}
						flipsidemeter = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_H)
					shouldshowhelp = !shouldshowhelp;

			}

		}
		if (isdisplayingmessagebox) {
			if (ischooseingnewattack) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (theplayer.currentattack > 0) {
						theplayer.currentattack--;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (theplayer.currentattack < theplayer.attacks.size() - 1) {
						theplayer.currentattack++;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_Z) {
					haschangedattack = true;
					ischooseingnewattack = false;
					explosionparticles.clear();
					newattack = null;
					scrollamount = 0;
					displaytimer = 80;
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (newattack != null) {
						theplayer.attacks.set(theplayer.currentattack, newattack);
						haschangedattack = true;
						ischooseingnewattack = false;
						explosionparticles.clear();
						newattack = null;
						displaytimer = 80;
						scrollamount = 0;
						displaytimer = 80;
					}
				}
			}
		}
		if (!hasgamestarted) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				// hasgamestarted = true;
				// menumusic.stop();
				// newroom();
				hasgamestarted = true;
				isshowingchoosesound = true;
				menumusic.stop();
				soundmenu.loop();
			}
		}
		if (isshowingchoosesound) {
			if (e.getKeyCode() == KeyEvent.VK_Z) {
				hasstartedgamestarttimer = true;

				entergame.play();
			}
			if (e.getKeyCode() == KeyEvent.VK_1) {
				// sets all sounds so that they will play : shows up on the
				// sound choose screen
				normalbattlemusic.isdisabled = false;
				bossbattlemusic.isdisabled = false;
				battlemusic.isdisabled = false;
				startareamusic.isdisabled = false;
				pew.isdisabled = false;
				hit.isdisabled = false;
				zer.isdisabled = false;
				epic.isdisabled = false;
				boom.isdisabled = false;
				gameoversound.isdisabled = false;
				whack.isdisabled = false;
				hum.isdisabled = false;
				menumusic.isdisabled = false;
				soundmenu.isdisabled = false;
				victorysound.isdisabled = false;
				entergame.isdisabled = false;
				city1.isdisabled = false;
				city2.isdisabled = false;
				bestfriend.isdisabled = false;
				if (!soundmenu.isrunning()) {
					soundmenu.loop();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_2) {
				// sets all sounds so that they won't play : shows up on the
				// sound choose screen
				normalbattlemusic.isdisabled = true;
				bossbattlemusic.isdisabled = true;
				battlemusic.isdisabled = true;
				startareamusic.isdisabled = true;
				pew.isdisabled = true;
				hit.isdisabled = true;
				zer.isdisabled = true;
				epic.isdisabled = true;
				boom.isdisabled = true;
				gameoversound.isdisabled = true;
				whack.isdisabled = true;
				hum.isdisabled = true;
				menumusic.isdisabled = true;
				soundmenu.isdisabled = true;
				victorysound.isdisabled = true;
				entergame.isdisabled = true;
				city1.isdisabled = true;
				city2.isdisabled = true;
				bestfriend.isdisabled = true;
				soundmenu.stop();
			}
		}

	}

	/**
	 * Implementation of the interface KeyListener, is activated in the release
	 * of any key, e is the KeyEvent
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Keyreleased method
		if (e.getKeyCode() == KeyEvent.VK_Q) {

			islargesize = !islargesize;
			if (islargesize) {
				SwingUtilities.getRoot(this).setSize((int) (basewidth * 1.5), (int) (baseheight * 1.5));

			} else
				SwingUtilities.getRoot(this).setSize(800, 600);
		}
		if (hasgamestarted && !isshowingchoosesound) {
			keyspressed.remove(e.getKeyCode());
			if (theplayer.isinbattle) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE && attacktimer == 0 && canattack && currentenemy.forward == 0
						&& !theplayer.hasflipped) {
					if (theplayer.attacks.get(theplayer.currentattack).type.equals("Special")) {
						pew.play();
						psybullets.add(new Psybullet1(120, 340, 60,
								theplayer.attack + theplayer.attacks.get(theplayer.currentattack).damage,
								theplayer.attacks.get(theplayer.currentattack).url));
						attacktimer = 50;
						canattack = false;
					}
					if (theplayer.attacks.get(theplayer.currentattack).type.equals("Physical")) {
						theplayer.goforward();
						attacktimer = 50;
						canattack = false;
					}
					if (theplayer.attacks.get(theplayer.currentattack).type.equals("Heal")) {
						theplayer.health += theplayer.attacks.get(theplayer.currentattack).damage * attackpowermodifier;
						attacktimer = 50;
						canattack = false;
					}
				}
			} else {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					theplayer.vely = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					theplayer.vely = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					theplayer.velx = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					theplayer.velx = 0;
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_Z) {
				if (theplayer.isinteracting && textboxtimer <= 0) {
					for (int i = 0; i < npcs.size(); i++) {
						if (checkcollision(theplayer.rectinteract, npcs.get(i).rect)) {
							if (npcs.get(i).currenttextbox < npcs.get(i).textboxes.size() - 1) {
								ding.play();
								npcs.get(i).currenttextbox++;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Implementation of the interface KeyListener, unused in this program
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		//

	}

	/**
	 * saves the player stats into the file created in the constructor
	 */
	public void save() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("saves/player.txt");
			writer.println(theplayer.name);
			writer.println(theplayer.health);
			writer.println(theplayer.attack);
			writer.println(theplayer.defence);
			writer.println(currentroom);
			writer.println(theplayer.x);
			writer.println(theplayer.y);
			writer.println(theplayer.level);
			writer.println(theplayer.exp);
			writer.println(flipsidemeter);
			writer.println(getattackindex(theplayer.attacks.get(0)));
			writer.println(getattackindex(theplayer.attacks.get(1)));
			writer.println(getattackindex(theplayer.attacks.get(2)));
			writer.println(getattackindex(theplayer.attacks.get(3)));
			writer.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		for (int i = 0; i < 200; i++) {
			dropshrapnel(randInt(0, 800), randInt(-40, 600), 10, 1f);
		}
	}

	/**
	 * sets the player's stats to the stats in the save file. may be used in the
	 * contructor
	 */
	public void load() {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("saves/player.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		theplayer = new Player(lines.get(0), Integer.parseInt(lines.get(1)), Integer.parseInt(lines.get(2)),
				Integer.parseInt(lines.get(3)), Integer.parseInt(lines.get(5)), Integer.parseInt(lines.get(6)),
				Integer.parseInt(lines.get(7)), Integer.parseInt(lines.get(8)));
		currentroom = Integer.parseInt(lines.get(4));
		flipsidemeter = Integer.parseInt(lines.get(9));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(10))));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(11))));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(12))));
		theplayer.attacks.add(loadattack(Integer.parseInt(lines.get(13))));
		battlemusic.stop();
		newroom();
		for (int i = 0; i < 200; i++) {
			dropshrapnel(randInt(0, 800), randInt(-40, 600), 10, 1f);
		}
	}

	/**
	 * draws the sky and ground during battle
	 * 
	 * @param g
	 *            (graphics)
	 */
	public void drawbattlebackground(Graphics g) {
		// Color col = new Color(25, 95, 219);
		Color col = new Color(0, 166, 81);
		Color sky = new Color(156, 220, 244);
		// Color sky = new Color(25, 95, 219);
		if (currentroom > 10) {
			col = new Color(78, 81, 122);
			// sky = new Color(4, 27, 165);
			sky = new Color(25, 95, 219);
		}
		if (theplayer.hasflipped) {
			col = invertcolor(col);
			sky = invertcolor(sky);
		}
		if (currentroom == 32)
			col = new Color(0, 166, 81);
		g.setColor(sky);
		g.fillRect(0, 0, 800, 600);
		drawclouds(g);
		if (theplayer.hasflipped)
			drawfire(g);
		g.setColor(col);
		g.fillRect(0, 350, 800, 250);
	}

	public void drawbackground(Graphics g) {

		g.setColor(col);
		g.fillRect(0, 0, 800, 600);
	}

	/**
	 * draws the enemy, as well as check for pysical attacks
	 * 
	 * @param g
	 */
	public void drawenemies(Graphics g) {
		if (enemies.size() > 0) {
			currentenemy = enemies.get(0);
			currentenemy.draw(g);
			currentenemy.isinbattle = theplayer.isinbattle;
			if (theplayer.isinbattle)

				if (checkcollision(theplayer.rectbattle, currentenemy.rect) && theplayer.isgoingforward) {
					// initial damage
					currentenemy.health -= (theplayer.attacks.get(theplayer.currentattack).damage
							+ (theplayer.attack) * damagemodifierlevel);
					// check if it should crit
					if (getshouldcrit()) {
						currentenemy.health -= ((theplayer.attacks.get(theplayer.currentattack).damage
								+ (theplayer.attack)) * 2 * damagemodifierlevel);
						criticalhittimer = 0;
					}
					// checks if it is super effective
					if (theplayer.attacks.get(theplayer.currentattack).element.equals(currentenemy.weakness)) {
						currentenemy.health -= ((theplayer.attacks.get(theplayer.currentattack).damage
								+ (theplayer.attack)) * 2 * damagemodifierlevel);
						supereffectivetimer = 0;

					}
					theplayer.isgoingforward = false;
					if (currentenemy.health <= 0)
						shootexplosion(550, currentenemy.battleheight, 30, 1f);
					else
						dropshrapnel(550 + currentenemy.width2 / 2, currentenemy.battleheight + currentenemy.bobheight,
								10, .9f);
					// shootexplosion(550, currentenemy.battleheight, 20, .9f);

					if (theplayer.currentattack == 3) {
						theplayer.applydamage(
								(theplayer.attacks.get(theplayer.currentattack).damage + (theplayer.attack)) / 2);
						if(theplayer.health <= 0)
							theplayer.health = 1;
					}
					rumbletimer = 15;

					whack.play();

				}

		}
	}

	/**
	 * If this returns true, attacks will do extra damage
	 */
	public boolean getshouldcrit() {
		if (randInt(0, 5) == 3)
			return true;
		return false;
	}

	public void drawwalls(Graphics g) {
		if (currentroom <= 40)
			g.setColor(new Color(220, 220, 220));
		if (currentroom <= 30)
			g.setColor(new Color(91, 68, 30));
		if (currentroom <= 20)
			g.setColor(new Color(31, 26, 84));
		if (currentroom <= 10)
			g.setColor(Color.gray);

		for (int i = 0; i < wallslist.size(); i++) {
			wallslist.get(i).draw(g);
			if (checkcollision(wallslist.get(i).rect, theplayer.rect)) {
				// theplayer.velx *= -1;
				// theplayer.vely *= -1;
				// theplayer.shouldmoveback();
			}
		}
	}

	public void checkcollisionwithwalls() {
		/*
		 * for (int i = 0; i < wallslist.size(); i++) { if
		 * (checkcollision(wallslist.get(i).rect, theplayer.rect)) { //
		 * theplayer.velx *= -1; // theplayer.vely *= -1;fad
		 * theplayer.shouldmoveback(); } }
		 */
		if (!theplayer.checkifwouldcollidewithwalls(wallslist,
				new Rectangle(theplayer.x + theplayer.velx, theplayer.y + theplayer.vely, 100, 100))) {
			theplayer.move();
		}
	}

	public void checkshouldenterbattle(Graphics g) {
		theplayer.rect = new Rectangle(theplayer.x, theplayer.y, 100, 100);
		for (int i = 0; i < enemies.size(); i++) {
			if (!theplayer.isinbattle && checkcollision(theplayer.rect, currentenemy.rect) && enemies.size() > 0) {
				if (currentroom <= 10)
					startareamusic.stop();
				if (currentroom > 10 && currentroom <= 20)
					city1.stop();

				explosionparticles.clear();
				shrapnellist.clear();
				theplayer.velx = 0;
				theplayer.vely = 0;
				canattack = true;
				if (currentenemy.health > 0 && !bestfriend.isrunning())
					battlemusic.loop();

				theplayer.isinbattle = true;
				currentenemy.isinbattle = true;
				theplayer.levelbefore = theplayer.level;
				theplayer.expbefore = theplayer.exp;
				haschangedattack = false;
				if (currentroom == 4) {
					isbossleadup = true;
					bossleaduptimer = 120;
					scrollamount = 0;
				}
			}
		}
		if (bestfriend.isrunning())
			battlemusic.stop();
	}

	public void checkshouldexitbattle() {
		if (enemies.size() > 0) {
			if (currentenemy.health <= 0) {
				exitbattletimer = 60;
				battlemusic.stop();
				enemies.remove(0);
				theplayer.hasflipped = false;
				victorysound.play();
			}
		}
		if (theplayer.health <= 0 && exitbattletimer == 0) {
			exitbattletimer = 60;
		}
		if (exitbattletimer == 1) {
			// battlemusic.stop();
			theplayer.exp += currentenemy.expamount;

			attacktimer = 0;
			theplayer.isinbattle = false;
			scrollamount = 0;
			isdisplayingmessagebox = true;
			displaytimer = 80;
			attackpowermodifier = 0;
			flipsidemeter = 0;
			theplayer.expdifference = Math.abs(theplayer.exp - theplayer.expbefore);
			explosionparticles.clear();
			shrapnellist.clear();
			epic.rewind();
			firelist.clear();
			firelist2.clear();
			for (int i = 0; i < tppads.size(); i++) {
				tppads.get(i).lifetime = 61;
			}
		}

		if (exitbattletimer > 0)
			exitbattletimer--;
	}

	public Boolean checkcollision(Rectangle rect1, Rectangle rect2) {
		if (rect1.intersects(rect2))
			return true;
		return false;
	}

	/**
	 * Run when new room should be loaded First, all lists are cleared, to have
	 * a clean slate for the new room There is an api to make a new room New
	 * rooms don't need these, but without them, there will be nothing in the
	 * room A new room first needs to be created. This is done simply with an if
	 * statement to be fired if the newroom variable is equal to the index of
	 * the new room --- Teleportpads need to be added to the tppads arrayList,
	 * declared like this "new Teleportpad(TPPAD_UP, 5)" First argument is the
	 * part of the screen that the Teleportpad is on, the second argument is the
	 * destination room --- Npcs should be added to the npcs arrayList, declared
	 * with (x location, y location, width, height, image) Npcs come with a
	 * textbox arrayList. To add new Textboxes to the Npc, "Npc name here"
	 * .textboxes.add(new Textbox(makeArrayList("first line of textbox",
	 * "second line of same textbox"), "image")); --- example enemy constuctor:
	 * enemies.add(new Enemy("Rocky", 100, 400, 120, 100, 120, 100, 40, 5, 5,
	 * "pics/rocktop.png", "pics/rock.png", 310, 2, "Psychic")); ("Name",
	 * overworld x location, overworld y location, overworld width, overworld
	 * height, max health, attack, experience drop, "overworld image",
	 * "battle image", y offset in battle, bob amount in battle, "weakness") ---
	 */
	public void newroom() {
		// TODO the place where I make all of my rooms
		if (zer != defaultzer)
			zer = defaultzer;
		/*
		 * if (battlemusic != defaultbattlemusic) battlemusic =
		 * defaultbattlemusic;
		 */
		shrapnellist.clear();
		explosionparticles.clear();
		stations.clear();
		npcs.clear();
		enemies.clear();
		wallslist.clear();
		tppads.clear();
		if (currentroom <= 10 && displaytimer == 0) {
			startareamusic.loop();
			col = new Color(0, 166, 81);
		} else if (currentroom <= 20 && displaytimer == 0) {
			startareamusic.stop();
			city1.loop();
			city2.stop();
			col = new Color(78, 81, 122);
		} else if (currentroom <= 30 && displaytimer == 0) {
			city1.stop();
			wintry.stop();
			city2.loop();
			col = new Color(194, 180, 154);

		} else if (currentroom <= 40 && displaytimer == 0) {
			city2.stop();
			wintry.loop();
			col = new Color(240, 240, 240);

		}
		battlemusic = normalbattlemusic;
		if (currentroom == 0) {
			// teleportpad syntax : x, y , width, height, destination room, new
			// x location, new y location, theplayer
			// or, use one of the presets TPPAD_UP, TPPAD_DOWN , TPPAD_LEFT,
			// TPPAD_RIGHT, followed by the destination room
			Npc stan = new Npc(600, 200, 100, 100, "pics/stan.png");
			stan.textboxes
					.add(new Textbox(makeArrayList("Stan: Gee, I wish a hero would arrive..."), stanneutral, 520, 0));
			stan.textboxes
					.add(new Textbox(makeArrayList("Stan: I'd tell them to enter battles by walking into enemies"),
							stanneutral, 520, 1));
			stan.textboxes.add(new Textbox(makeArrayList("Stan: Then I'd remind them of their ability to change",
					"attacks while in battle using <-->"), stanneutral, 520, 2));
			stan.textboxes
					.add(new Textbox(makeArrayList("Stan: I'd finish by asking them to press space to use the attack!"),
							stanneutral, 520, 3));
			stan.textboxes
					.add(new Textbox(makeArrayList("Stan: I wonder when a hero will arrive..."), stanneutral, 520, 4));
			npcs.add(stan);

			wallslist.add(new Wall(0, 0, 300, 100));
			wallslist.add(new Wall(0, 500, 800, 100));
			wallslist.add(wallbottom);
			wallslist.add(wallright);

			// tppads.add(new Teleportpad(299, -200, 300, 110, 1, 350, 580,
			// theplayer));
			tppads.add(new Teleportpad(TPPAD_UP, 1));
			// tppads.add(new Teleportpad(-200, 0, 110, 800, 3, 800, 300,
			// theplayer));
			tppads.add(new Teleportpad(TPPAD_LEFT, 3));

			stations.add(new Interactstation(100, 100, "Save"));
		}
		if (currentroom == 1) {
			// rocklist.add(new Rockenemy(300, 200));
			Npc stan = new Npc(600, 400, 100, 100, "pics/stanup.png");
			stan.textboxes.add(new Textbox(
					makeArrayList("Stan: When the hero gets here I'll teach them how to", "maximize damage output"),
					stanneutral, 520, 0));
			stan.textboxes.add(
					new Textbox(makeArrayList("Stan: First I'd explain charging up attacks"), stanneutral, 520, 1));
			stan.textboxes.add(new Textbox(makeArrayList("\"Try to hold space until the bar overlaps the red area\""),
					stanneutral, 520, 2));
			stan.textboxes
					.add(new Textbox(
							makeArrayList("\"It'll do more damage that way.",
									"Make sure not to overshoot the red though, that would do less!\""),
							stanneutral, 520, 3));
			stan.textboxes.add(new Textbox(makeArrayList("Stan: Yeah, that's what I'd say..."), stanneutral, 520, 4));
			npcs.add(stan);

			enemies.add(new Enemy("Rocky", 300, 200, 120, 100, 120, 100, 40, 5, 5, "pics/rocktop.png", "pics/rock.png",
					310, 2, "Psychic"));
			wallslist.add(new Wall(0, 500, 300, 100));
			wallslist.add(new Wall(500, 500, 800, 100));
			wallslist.add(new Wall(0, 0, 800, 100));

			wallslist.add(new Wall(700, 0, 800, 600));
			tppads.add(new Teleportpad(TPPAD_DOWN, 0));
			// tppads.add(new Teleportpad(0, 690, 800, 110, 0, 350, -110,
			// theplayer));
			// tppads.add(new Teleportpad(-200, 0, 110, 800, 666, 800, 300,
			// theplayer));
			tppads.add(new Teleportpad(TPPAD_LEFT, 666));
		}
		if (currentroom == 3) {
			Npc stan = new Npc(600, 400, 100, 100, "pics/stanup.png");

			stan.textboxes.add(new Textbox(makeArrayList("Stan: Hey, pal, the hero will come soon enough, but there is",
					"a strong enemy ahead that I need to warn you about"), stanneutral));
			stan.textboxes.add(new Textbox(
					makeArrayList("Stan: Every enemy has a type of attack that they are weak to"), stanneutral));
			stan.textboxes
					.add(new Textbox(makeArrayList("Stan: For example, the rocks you see around here have very small",
							"brains, and so are weak to psychic attack!"), stanneutral));
			stan.textboxes.add(new Textbox(
					makeArrayList("Stan: And the big orange...thing in the next room is weak", "to brawn attacks"),
					stanneutral));
			stan.textboxes
					.add(new Textbox(makeArrayList("Stan: Use the right attack, and it will deal much more damage than",
							"another one might"), stanneutral));

			stan.textboxes.add(new Textbox(makeArrayList("Stan: Best of luck to you"), stanhappy));

			npcs.add(stan);

			enemies.add(new Enemy("Rocky", 100, 400, 120, 100, 120, 100, 40, 5, 5, "pics/rocktop.png", "pics/rock.png",
					310, 2, "Psychic"));
			wallslist.add(new Wall(0, 0, 800, 100));
			wallslist.add(new Wall(0, 500, 800, 100));
			// tppads.add(new Teleportpad(800, 0, 110, 800, 0, -100, 300,
			// theplayer));
			tppads.add(new Teleportpad(TPPAD_RIGHT, 0));
			tppads.add(new Teleportpad(TPPAD_LEFT, 4));
			// tppads.add(new Teleportpad(-200, 0, 110, 800, 4, 800, 300,
			// theplayer));
			stations.add(new Interactstation(100, 100, "Heal"));
		}
		if (currentroom == 4) {
			city1.stop();
			Npc stan = new Npc(500, 400, 100, 100, "pics/stanup.png");
			stan.textboxes.add(new Textbox(
					makeArrayList("Stan: Yo!", "to the left of here is the entrance to the city"), stanneutral));
			stan.textboxes.add(
					new Textbox(makeArrayList("Stan: I've put the basics of what I'll tell to the hero on a help page",
							"so you can tell him what to do when you see him"), stanneutral));
			stan.textboxes.add(new Textbox(
					makeArrayList("Stan: You can access it by pressing the H button while in battle"), stanneutral));
			stan.textboxes.add(new Textbox(makeArrayList("Stan: Come visit me at my home, wintry town"), stanhappy));
			npcs.add(stan);

			enemies.add(new Enemy("Derpy", 200, 240, 200, 200, 200, 247, 100, 10, 15, "pics/derpytop.png",
					"pics/derpy.png", 160, 5, "Brawn"));
			battlemusic = bossbattlemusic;
			isinbossbattle = true;
			bossimg = new Image("pics/bossderpy.png").img;
			// tppads.add(new Teleportpad(800, 0, 110, 800, 3, -100, 300,
			// theplayer));
			wallslist.add(walltop);
			wallslist.add(new Wall(0, 500, 800, 100));
			tppads.add(new Teleportpad(TPPAD_RIGHT, 3));
			tppads.add(new Teleportpad(TPPAD_LEFT, 11));
		}
		if (currentroom == 11) {
			// TODO first room of city1

			Npc civilian = new Npc(100, 110, 100, 100, "pics/civilian.png");
			civilian.textboxes
					.add(new Textbox(makeArrayList("Civilian: Hey dude, you don't look like you are from around here"),
							civilianneutral));
			civilian.textboxes.add(
					new Textbox(makeArrayList("Civilian: You should check out the flipside academy"), civilianneutral));
			civilian.textboxes.add(
					new Textbox(makeArrayList("Civilian: They'll teach you to use your abilities to do cool stuff...",
							"and things..."), civilianneutral));
			npcs.add(civilian);
			wallslist.add(new Wall(0, 0, 800, 100));
			wallslist.add(wallleft);
			stations.add(new Interactstation(300, 100, "Save"));
			stations.add(new Interactstation(400, 100, "Heal"));
			tppads.add(new Teleportpad(TPPAD_DOWN, 12));
			tppads.add(new Teleportpad(TPPAD_RIGHT, 4));
		}
		if (currentroom == 12) {
			wallslist.add(wallleft);
			wallslist.add(wallright);
			enemies.add(new Enemy("Firey", 200, 240, 200, 200, 200, 150, 100, 10, 25, "pics/fireytop.png",
					"pics/firey.png", 250, 5, "Water"));
			tppads.add(new Teleportpad(TPPAD_UP, 11));
			tppads.add(new Teleportpad(TPPAD_DOWN, 13));
		}
		if (currentroom == 13) {
			wallslist.add(wallleft);
			wallslist.add(wallright);
			enemies.add(new Enemy("Firey", 300, 240, 200, 200, 200, 150, 100, 10, 25, "pics/fireytopdown.png",
					"pics/firey.png", 250, 5, "Water"));
			tppads.add(new Teleportpad(TPPAD_UP, 12));
			tppads.add(new Teleportpad(TPPAD_DOWN, 21));
		}
		if (currentroom == 21) {
			wallslist.add(halflefttop);
			wallslist.add(halfleftbottom);
			wallslist.add(halfrighttop);
			wallslist.add(halfrightbottom);
			tppads.add(new Teleportpad(TPPAD_UP, 13));
			tppads.add(new Teleportpad(TPPAD_LEFT, 22));
			tppads.add(new Teleportpad(TPPAD_RIGHT, 23));
			tppads.add(new Teleportpad(TPPAD_DOWN, 24));
		}
		if (currentroom == 22) {
			Npc carl = new Npc(220, 150, 100, 200, "pics/carl.png");
			Npc paul = new Npc(120, 150, 100, 200, "pics/paul.png");
			Npc corpse = new Npc(120, 400, 200, 100, "pics/corpse.png");
			carl.textboxes.add(new Textbox(makeArrayList("Paul: CARLLLLLLLL"), paulneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Carl: Yes?"), carlneutral));
			carl.textboxes.add(
					new Textbox(makeArrayList("Paul: There is a corpse in our living room CARLLLLL"), paulneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Carl: Oh hey, how did that get here?"), carlneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Paul: Why did you kill this person CARLLLLL"), paulneutral));
			carl.textboxes.add(new Textbox(
					makeArrayList("Carl: I do not kill people, that is my LEAST favourite thing to do"), carlneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Paul: Explain yourself Carl"), paulneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Carl: Ok, so I was upstairs...", "then this guy walks in"),
					carlneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Paul: Go on"), paulneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Carl: So I walked up to him..."), carlneutral));
			carl.textboxes
					.add(new Textbox(makeArrayList("Carl: And then I stabbed him 37 times in the chest"), carlneutral));
			carl.textboxes.add(new Textbox(makeArrayList("Paul: CARRLLLLLLL that kills people!!!"), paulneutral));

			npcs.add(carl);
			npcs.add(paul);
			npcs.add(corpse);
			wallslist.add(wallleft);
			wallslist.add(walltop);
			wallslist.add(wallbottom);
			tppads.add(new Teleportpad(TPPAD_RIGHT, 21));
		}
		if (currentroom == 23) {
			wallslist.add(halflefttop);
			wallslist.add(halfleftbottom);
			wallslist.add(walltop);
			wallslist.add(wallbottom);
			wallslist.add(wallright);
			tppads.add(new Teleportpad(TPPAD_LEFT, 21));
		}
		if (currentroom == 24) {
			stations.add(new Interactstation(100, 100, "Save"));
			stations.add(new Interactstation(100, 200, "Heal"));
			wallslist.add(wallleft);
			wallslist.add(wallbottom);
			tppads.add(new Teleportpad(TPPAD_RIGHT, 31));
			tppads.add(new Teleportpad(TPPAD_UP, 21));
		}
		if (currentroom == 25) {
			battlemusic = bossbattlemusic;
			wallslist.add(walltop);
			wallslist.add(wallbottom);
			wallslist.add(wallright);
			tppads.add(new Teleportpad(TPPAD_LEFT, 23));
		}
		if (currentroom == 31) {
			wallslist.add(wallbottom);
			wallslist.add(walltop);
			tppads.add(new Teleportpad(TPPAD_LEFT, 24));
			tppads.add(new Teleportpad(TPPAD_RIGHT, 32));
		}
		if (currentroom == 32) {
			wallslist.add(wallright);
			wallslist.add(walltop);
			wallslist.add(halfleftbottom);
			Npc stan = new Npc(400, 200, 100, 100, "pics/stan.png");
			stan.textboxes.add(new Textbox(makeArrayList("Stan: Dude! You made it"), stanhappy, 520, 0));
			stan.textboxes.add(new Textbox(
					makeArrayList("Stan: Hey, so yeah...", "There's actually a slight problem right now..."),
					stanneutral, 520, 1));
			stan.textboxes
					.add(new Textbox(makeArrayList("Stan: I'll tell you more in the next room"), stanneutral, 520, 1));
			npcs.add(stan);
			tppads.add(new Teleportpad(TPPAD_LEFT, 31));
			tppads.add(new Teleportpad(TPPAD_DOWN, 33));
		}
		if (currentroom == 33) {
			wallslist.add(wallleft);
			wallslist.add(wallbottom);
			wallslist.add(halflefttop);
			wallslist.add(halfrighttop);
			Npc stan = new Npc(200, 300, 100, 100, "pics/stanup.png");
			stan.textboxes.add(new Textbox(makeArrayList("Stan: I heard that you were pretty strong..."), stanneutral));
			stan.textboxes.add(new Textbox(makeArrayList("Stan: So there's an extremely strong enemy in the next room",
					"make sure that you're ready"), stanhappy));
			npcs.add(stan);
			tppads.add(new Teleportpad(TPPAD_UP, 32));
			tppads.add(new Teleportpad(TPPAD_RIGHT, 34));
		}
		if (currentroom == 666) {
			startareamusic.stop();
			hum.loop();

			Npc worshipper = new Npc(300, 200, 100, 100, "pics/worshipper.png");
			worshipper.textboxes.add(new Textbox(
					makeArrayList("There exists a place called the flipside",
							"It's similar to this world, but only in the sense that it is an opposite"),
					worshipperneutral, Color.black, Color.white));
			worshipper.textboxes.add(
					new Textbox(makeArrayList("Many strong creatures are made weaker", "Some things are made powerful"),
							worshippersmile, Color.black, Color.white));
			worshipper.textboxes.add(new Textbox(
					makeArrayList("In order to enter the flipside, you need to first fill up the flipside meter",
							"which is to the right of the exp bar"),
					worshipperneutral, Color.black, Color.white));
			worshipper.textboxes.add(new Textbox(
					makeArrayList("Receiving damage fills the bar somewhat. Note that the amount that",
							" the bar fills up does not relate to the amount of damage in any way"),
					worshipperneutral, Color.black, Color.white));
			worshipper.textboxes.add(new Textbox(
					makeArrayList("Also, pressing Z will fill the meter quite a lot, but it leaves you open"),
					worshipperneutral, Color.black, Color.white));
			worshipper.textboxes.add(new Textbox(makeArrayList("Once you fill the meter..."), worshippersmile,
					Color.black, Color.white));
			worshipper.textboxes.add(
					new Textbox(makeArrayList("PRESS F TO RELEASE YOUR TRUE POTENTIAL!!!", "THEN PRESS G TO ATTACK!!!"),
							worshippercrazy, Color.black, Color.white));
			worshipper.textboxes
					.add(new Textbox(makeArrayList("Why G?", "The programmer ran out of keys that make sense"),
							worshipperneutral, Color.black, Color.white));
			npcs.add(worshipper);
			col = new Color(0, 0, 0);
			// tppads.add(new Teleportpad(800, 0, 110, 800, 1, -100, 300,
			// theplayer));
			tppads.add(new Teleportpad(TPPAD_RIGHT, 1));
		}
		if (bestfriend.isrunning()) {
			city1.stop();
			city2.stop();
		}
	}

	/**
	 * checks if there needs to be a special picture drawn on the background
	 * example, the pentagram in the satan room
	 * 
	 * @param g
	 *            (graphics)
	 */
	public void checkifspecialroom(Graphics g) {
		if (currentroom == 666) {
			g.drawImage(darkstar, 0, 0, 800, 600, this);
		}
		if (currentroom == 0) {
			g.drawImage(interacthint, 0, 0, 800, 600, this);
		}
		if (currentroom == 34) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			wintry.stop();
			bestfriend.loop();
			g.drawImage(demoend, 0, 0, 800, 600, this);
		}
	}

	/**
	 * In theory should never be called, will make sure no more than 1 in any of
	 * these arraylists, they will be deleted
	 */
	public void keepenemylistsizesincheck() {
		if (psybullets.size() > 1) {
			for (int i = 1; i < psybullets.size(); i++) {
				psybullets.remove(i);
			}
		}
		if (bigbullets.size() > 1) {
			for (int i = 1; i < bigbullets.size(); i++) {
				bigbullets.remove(i);
			}
		}
		if (enemies.size() > 1) {
			for (int i = 1; i < enemies.size(); i++) {
				enemies.remove(i);
			}
		}
		if (!theplayer.isinbattle)
			bigbullets.clear();
	}

	public void stopbattlemusic() {
		if (bestfriend.isrunning()) {
			battlemusic.isdisabled = true;
			battlemusic.stop();
		}
	}

	/**
	 * moves the enemy forward, and checks if the gameoversound should play
	 */
	public void enemyattack() {

		if (currentenemy.health <= 0)
			attacktimer = 0;
		if (attacktimer > 0) {
			attacktimer--;
		}
		if (attacktimer == 4) {

			zer.play();
			currentenemy.moveforward();
			theplayer.applydamage(currentenemy.attack);
			attackpowermodifier = 0;
			damagemodifierlevel = 0;
			canattack = true;
			flipsidemeter += 5;
			if (theplayer.health <= 0) {
				if (currentroom <= 10)
					startareamusic.stop();
				battlemusic.stop();
				gameoversound.play();

			}
		}
	}

	/**
	 * draws bullets, as well as applies appropriate damage to the enemy
	 * 
	 * @param g
	 */
	public void drawbullets(Graphics g) {
		// TODO a lot of projectile stuff is done here, makes super effective
		// moves do more damage
		if (psybullets.size() > 0)
			theplayer.isattacking = true;
		else
			theplayer.isattacking = false;
		for (int i = 0; i < psybullets.size(); i++) {
			psybullets.get(0).draw(g);
			if (checkcollision(currentenemy.rect, psybullets.get(i).rect)) {
				hit.play();
				// initial damage
				currentenemy.health -= (theplayer.attacks.get(theplayer.currentattack).damage
						+ (theplayer.attack) * damagemodifierlevel);
				// check if should crit, does more damage if it does
				if (getshouldcrit()) {
					currentenemy.health -= (theplayer.attacks.get(theplayer.currentattack).damage
							+ (theplayer.attack) * damagemodifierlevel);
					criticalhittimer = 0;
				}
				// check if the enemy is weak to the current attack
				if (theplayer.attacks.get(theplayer.currentattack).element.equals(currentenemy.weakness)) {
					currentenemy.health -= ((theplayer.attacks.get(theplayer.currentattack).damage + (theplayer.attack))
							* 2 * damagemodifierlevel);
					supereffectivetimer = 0;
				}
				if (currentenemy.health <= 0)
					shootexplosion(550, currentenemy.battleheight, 30, 1f);
				else
					dropshrapnel(550 + currentenemy.width2 / 2, currentenemy.battleheight + currentenemy.bobheight, 10,
							.9f);
				psybullets.remove(i);

			}
		}
		for (int i = 0; i < bigbullets.size(); i++) {
			boom.play();
			bigbullets.get(i).draw(g);
			dropshrapnel(bigbullets.get(i).x, (int) bigbullets.get(i).y, 10, 0f);
			if (checkcollision(currentenemy.rect, bigbullets.get(i).rect)) {
				currentenemy.health -= theplayer.attack * 10;
				shootexplosion(550, 370, 30, 0f);
				bigbullets.remove(i);
				rumbletimer = 30;
				if (enemies.get(0).health <= 0) {
					epic.stop();
				}
			}

		}

	}

	/**
	 * makes sure the boss aproaching screen does not show up when you die
	 */
	public void checkshouldstopbossleadup() {
		if (theplayer.health <= 0) {
			bossleaduptimer = 0;
		}
	}

	/**
	 * run when health is low, resets variables and runs load & newroom
	 */
	public void respawn() {
		if (theplayer.health <= 0) {
			isbossleadup = false;
			bossleaduptimer = 0;
			theplayer.isinbattle = false;
			currentenemy.isinbattle = false;
			battlemusic.stop();
			startareamusic.stop();
			city1.stop();
			city2.stop();
			enemies.get(0).health = 1000;
			isdisplayingmessagebox = false;
			displaytimer = 0;
			exitbattletimer = 0;
			bossleaduptimer = 0;
			firelist.clear();
			firelist2.clear();
			load();
			newroom();

		}
	}

	/**
	 * utility method
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(int min, int max) {
		Random random = new Random();
		int randomNum = random.nextInt(max - min + 1) + min;
		return randomNum;
	}

	/**
	 * drops rainbow colored particles
	 * 
	 * @param x
	 * @param y
	 * @param amount
	 * @param lightness
	 */
	public void dropshrapnel(int x, int y, int amount, float lightness) {
		// shrapnellist.add(new Shrapnel(400, 300, randInt(-5, 5),
		// randInt(-5, 5), randInt(30, 60)));
		for (int i = 0; i < amount; i++) {
			Random rand = new Random();
			/*
			 * 
			 * float r = rand.nextFloat() / 2f + 0.5f; float g =
			 * rand.nextFloat() / 2f + 0.5f; float b = rand.nextFloat() / 2f +
			 * 0.5f; Color randomColor = new Color(r, g, b);
			 */
			final float hue = rand.nextFloat();
			// Saturation between 0.1 and 0.3
			final float saturation = (rand.nextInt(2000) + 1000) / 10000f;
			final Color randomColor = Color.getHSBColor(hue, saturation, lightness);
			shrapnellist.add(new Shrapnel(x, y, randInt(-5, 10), randInt(-5, 5), randInt(15, 30), randomColor));
		}
	}

	/**
	 * same as dropshrapnel, but explosionparticles are not affected by gravity
	 * 
	 * @param x
	 * @param y
	 * @param amount
	 * @param lightness
	 */
	public void shootexplosion(int x, int y, int amount, float lightness) {
		// shrapnellist.add(new Shrapnel(400, 300, randInt(-5, 5),
		// randInt(-5, 5), randInt(30, 60)));
		for (int i = 0; i < amount; i++) {
			Random rand = new Random();
			final float hue = rand.nextFloat();
			// Saturation between 0.1 and 0.3
			final float saturation = (rand.nextInt(2000) + 1000) / 10000f;
			final Color randomColor = Color.getHSBColor(hue, saturation, lightness);
			explosionparticles.add(new Explosion(x, y, randInt(-5, 10), randInt(-5, 5), randInt(15, 30), randomColor));
		}
	}

	/**
	 * spawns explosion particles of a set color
	 * 
	 * @param x
	 * @param y
	 * @param amount
	 * @param color
	 */
	public void shootexplosionsetcolor(int x, int y, int amount, Color color) {
		for (int i = 0; i < amount; i++) {

			explosionparticles.add(new Explosion(x, y, randInt(-5, 10), randInt(-5, 5), randInt(15, 30), color));
		}
	}

	public void handletppads(Graphics g) {
		for (int i = 0; i < tppads.size(); i++) {
			tppads.get(i).dostuff(g, theplayer);
			if (checkcollision(tppads.get(i).rect, theplayer.rect)) {
				if (tppads.get(i).isactivated) {
					currentroom = tppads.get(i).newroom;
					newroom();
				}
			}
		}
	}

	public void drawpowerups(Graphics g) {
		for (int i = 0; i < amountattackpowerups; i++) {
			g.drawImage(imgpowerup, 50 + (60 * i), 150, 50, 50, this);

		}
	}

	/**
	 * draws the attack meter. the closer you get to the red, the more damage
	 * you do
	 * 
	 * @param g
	 */
	public void drawattackbar(Graphics g) {
		if (keyspressed.contains(KeyEvent.VK_SPACE) && canattack && currentenemy.forward == 0
				&& !theplayer.hasflipped) {
			if (attackpowermodifier < 52)
				attackpowermodifier += 2;
			g.drawImage(imgdamagetable, 50, 20, 240, 176, this);
			g.setColor(Color.black);
			damagemodifierlevel = 1;
			if (attackpowermodifier > 25) {
				damagemodifierlevel = 2;
			}
			if (attackpowermodifier > 38) {
				damagemodifierlevel = 3;
			}
			if (attackpowermodifier > 48) {
				damagemodifierlevel = 1;
			}
			g.fillRect(30 + (attackpowermodifier * 5), 20, 5, 175);

		}
		if (keyspressed.contains(KeyEvent.VK_G) && theplayer.hasflipped && bigbullets.size() < 1
				&& enemies.size() > 0) {
			bigbullets.add(new Psybullet2(200, 250, 50));
			flipsidemeter = 0;
		}

	}

	public Color invertcolor(Color col) {
		return new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue());
	}

	/**
	 * draws the ui for the flipside meter
	 * 
	 * @param g
	 */
	public void drawflipsidemeter(Graphics g) {
		if (flipsidemeter > 100)
			flipsidemeter = 100;
		g.setColor(Color.BLUE);
		g.fillRect(265, 500, 100, 10);
		g.setColor(Color.CYAN);
		g.fillRect(265, 500, flipsidemeter, 10);
		g.setColor(Color.black);
		g.drawString(flipsidemeter + "/100", 207, 509);
		if (flipsidemeter == 100)
			g.drawImage(flipsideicon2, 350, 480, 30, 30, this);
		else
			g.drawImage(flipsideicon, 350, 480, 30, 30, this);
	}

	/**
	 * when saving, attacks are saved at ints to save time on fileIO
	 * 
	 * @param atk
	 * @return
	 */
	public int getattackindex(Attack atk) {
		if (atk.name.equals("Psybullet"))
			return 1;
		if (atk.name.equals("Fireball"))
			return 2;
		if (atk.name.equals("Punch"))
			return 3;
		if (atk.name.equals("Reckless attack"))
			return 4;
		if (atk.name.equals("Water blast"))
			return 5;
		if (atk.name.equals("Heal"))
			return 6;
		return 0;
	}

	/**
	 * when loading them back in, they take the int from getattackindex and turn
	 * it back into the attack in full
	 * 
	 * @param index
	 * @return
	 */
	public Attack loadattack(int index) {
		if (index == 1)
			return (new Attack("Psybullet", "Special", 5, "Psychic", "pics/magic.png"));
		if (index == 2)
			return (new Attack("Fireball", "Special", 5, "Fire", "pics/fireball.png"));
		if (index == 3)
			return (new Attack("Punch", "Physical", 5, "Brawn", "pics/fireball.png"));
		if (index == 4)
			return (new Attack("Reckless attack", "Physical", 10, "Brawn", "pics/fireball.png"));
		if (index == 5)
			return new Attack("Water blast", "Special", 8, "Water", "pics/waterblast.png");
		if (index == 6)
			return new Attack("Heal", "Heal", 50, "Light", "pics/magic.png");
		return null;
	}

	/**
	 * shakes the screen
	 * 
	 * @param g
	 */
	public void rumble(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (rumbletimer > 0) {
			rumble = true;
			rumbletimer--;
		} else {
			rumble = false;
		}

		if (rumble) {
			g.translate(prevx + randInt(-rumbleamount, rumbleamount), prevy + randInt(-rumbleamount, rumbleamount));
		} else
			g2.setTransform(oldXForm);
	}

	public void rotateandrumble(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (rotatetimer > 0) {
			rotate = true;
			rotatetimer -= 5;
		} else {
			rotate = false;
		}

		if (rotate) {
			g2.setTransform(oldXForm);
			g.translate(400, 300);
			g2.scale(rotatetimer * .001, rotatetimer * .001);
			g2.rotate(Math.toRadians(rotatetimer * 3));

		} else
			g2.setTransform(oldXForm);

	}

	/**
	 * draws and handles the npcs
	 * 
	 * @param g
	 */
	public void drawNpcs(Graphics g) {
		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).draw(g);
			int ax = theplayer.x + 50;
			int ay = theplayer.y + 50;
			int bx = npcs.get(i).x + (npcs.get(i).width / 2);
			int by = npcs.get(i).y + (npcs.get(i).height / 2);
			int r1 = 50;
			int r2 = 40;
			if (checkcirclecollision(ax, ay, bx, by, r1, r2)) {
				theplayer.x = theplayer.oldx;
				theplayer.y = theplayer.oldy;
			}
		}
		for (int i = 0; i < npcs.size(); i++) {
			if (theplayer.isinteracting) {
				if (theplayer.rectinteract != null)
					if (checkcollision(theplayer.rectinteract, npcs.get(i).rect)) {
						for (int o = 0; o < npcs.get(i).textboxes.size(); o++) {
							if (npcs.get(i).currenttextbox == o) {
								npcs.get(i).textboxes.get(o).draw(g);
								if (npcs.get(i).currenttextbox != npcs.get(i).textboxes.size() - 1) {
									g.setFont(g.getFont().deriveFont(g.getFont().getStyle(), 11));
									g.drawString("Press z to continue", 665, 565);
								}
							}

						}
					}
			}
		}
		if (!keyspressed.contains(KeyEvent.VK_Z)) {
			boolean isinteractingwithany = false;
			for (int i = 0; i < npcs.size(); i++) {
				if (theplayer.rectinteract != null)
					if (checkcollision(theplayer.rectinteract, npcs.get(i).rect)) {
						isinteractingwithany = true;
					}
			}
			if (!isinteractingwithany) {
				theplayer.isinteracting = false;
				for (int i = 0; i < npcs.size(); i++)
					npcs.get(i).currenttextbox = 0;
			}
		}

	}

	public boolean checkcirclecollision(int ax, int ay, int bx, int by, int r1, int r2) {
		if (Math.sqrt((ax - bx) * (ax - bx) + (ay - by) * (ay - by)) <= (r1 + r2)) {
			return true;
		}
		return false;
	}

	/**
	 * takes an infinite amount of arguments, turns them to an arraylist
	 * 
	 * @param args
	 * @return
	 */
	public ArrayList<String> makeArrayList(String... args) {
		ArrayList<String> lines = new ArrayList<>();
		for (String arg : args) {
			lines.add(arg);
		}
		return lines;
	}

	/**
	 * draws save and heal points
	 * 
	 * @param g
	 */
	public void drawstations(Graphics g) {
		for (int i = 0; i < stations.size(); i++) {
			stations.get(i).rect = new Rectangle(stations.get(i).x, stations.get(i).y, 100, 100);
			if (stations.get(i).type.equals("Save")) {
				g.drawImage(saveimg, stations.get(i).x, stations.get(i).y, 100, 100, this);
				if (checkcollision(theplayer.rect, stations.get(i).rect) && theplayer.isinteracting) {
					save();
					theplayer.isinteracting = false;
					theplayer.rectinteract = null;
				}
			}
			if (stations.get(i).type.equals("Heal")) {
				g.drawImage(healimg, stations.get(i).x, stations.get(i).y, 100, 100, this);
				if (checkcollision(theplayer.rect, stations.get(i).rect) && theplayer.isinteracting) {
					theplayer.health = theplayer.maxhealth + (5 * (theplayer.level - 1));
					theplayer.isinteracting = false;
					theplayer.rectinteract = null;
					shootexplosionsetcolor(theplayer.x + 50, theplayer.y + 50, 30, Color.red);
				}
			}
		}
	}

	public boolean isinteractingwith(Rectangle rect) {
		if (theplayer.rectinteract != null)
			if (checkcollision(theplayer.rectinteract, rect))
				return true;
		return false;

	}

	/*
	 * first screen that you see when you open the game, it has multiple moving
	 * elements handled here fades out with a white screen overlaying the whole
	 * screen
	 */
	public void menuscreen(Graphics g) {

		g.drawImage(sunnybackground, 0, 0, 800, 600, this);
		g.drawImage(fredonrock, 830 + back, 500 + up, 280, 400, this);
		if (back >= -300) {
			back -= 2;
			up -= 2;

		} else {
			if (showmenutimer > 0)
				showmenutimer--;
			if (showmenutimer <= 0)
				shouldshowmenu = true;
		}
		if (shouldshowmenu) {

			startscreentoggletimer--;
			if (startscreentoggletimer <= 0) {
				startscreentoggletimer = 25;
				isfirststartscreenimg = !isfirststartscreenimg;
			}
			if (isfirststartscreenimg)
				g.drawImage(startscreen1, 0, -50, 800, 600, this);
			else
				g.drawImage(startscreen2, 0, -50, 800, 600, this);
		}
	}

	/**
	 * This method manages the screen that shows up after the intro screen; This
	 * screen allows you to turn sound on or off
	 */
	public void showchoosesound(Graphics g) {
		Color col = new Color(0, 173, 239);
		Color col2 = new Color(0, 153 + randInt(-10, 10), 239);
		g.setColor(col);
		g.fillRect(0, 0, 800, 600);
		explosionparticles
				.add(new Explosion(randInt(0, 800), 600, randInt(-1, 1), randInt(-5, -2), randInt(30, 50), col2));
		for (int i = 0; i < explosionparticles.size(); i++) {
			if (!ischooseingnewattack)
				explosionparticles.get(i).draw(g);
		}
		g.drawImage(soundschoosescreen, 0, 0, 800, 600, this);
		if (hasstartedgamestarttimer) {
			startgametimer++;
			int alpha = startgametimer * 3;
			if (alpha > 255)
				alpha = 255;
			Color white = new Color(255, 255, 255, alpha);
			g.setColor(white);
			g.fillRect(0, 0, 800, 600);
			if (startgametimer == 100)
				startgame();
		}
	}

	public void drawscrollingbackground(Graphics g) {
		if (scrollamount < 200) {
			g.drawImage(statsbackground, -200 + scrollamount, -200 + scrollamount, 1000, 800, this);
			scrollamount++;
		}
	}

	public void startgame() {
		isshowingchoosesound = false;
		soundmenu.stop();
		newroom();
	}

	/**
	 * in order to add new attacks into the game, add it here,
	 */
	public void newattackpool() {
		if (theplayer.level != theplayer.levelbefore && theplayer.health > 0) {
			if (theplayer.level == 2) {
				ischooseingnewattack = true;
				newattack = new Attack("Water blast", "Special", 8, "Water", "pics/waterblast.png");
				soundmenu.loop();
			}
			if (theplayer.level == 6) {
				ischooseingnewattack = true;
				newattack = new Attack("Heal", "Heal", 50, "Light", "pics/waterblast.png");
				soundmenu.loop();
			}
		} else
			newattack = null;

	}

	public void drawshrapnel(Graphics g) {
		for (int i = 0; i < shrapnellist.size(); i++) {
			shrapnellist.get(i).draw(g);
			if (shrapnellist.get(i).lifetime <= 0)
				shrapnellist.remove(i);
		}
	}

	/**
	 * during battle, the clouds spawn on a timer and move across the screen,
	 * and move across a cloud can be spawned
	 * 
	 * @param g
	 */
	public void drawclouds(Graphics g) {

		spawncloudtimer--;
		if (spawncloudtimer <= 0) {
			clouds.add(new Cloud(-200, randInt(0, 200), randInt(1, 2)));
			spawncloudtimer = 300;
		}
		for (int i = 0; i < clouds.size(); i++) {
			Cloud current = clouds.get(i);
			current.draw(g);
			if (current.x > 1000)
				clouds.remove(clouds.get(i));
		}
	}

	public void drawfire(Graphics g) {
		firelist.add(new Explosion(randInt(0, 800), randInt(350, 400), randInt(-5, 5), randInt(-7, -5), randInt(40, 42),
				Color.cyan.darker(), 60));
		firelist.add(new Explosion(randInt(0, 800), randInt(350, 400), randInt(-5, 5), randInt(-7, -5), randInt(40, 42),
				Color.cyan, 60));
		for (Explosion current : firelist) {
			current.draw(g);
			current.size -= 1;
		}
	}

	public void drawfireforeground(Graphics g) {
		firelist2.add(new Explosion(randInt(0, 800), randInt(600, 650), randInt(-5, 5), randInt(-7, -5),
				randInt(40, 42), Color.cyan.darker(), 60));
		firelist2.add(new Explosion(randInt(0, 800), randInt(600, 650), randInt(-5, 5), randInt(-7, -5),
				randInt(40, 42), Color.cyan, 60));
		for (Explosion current : firelist2) {
			current.draw(g);
			current.size -= 1;
		}
	}

	public void drawsupereffective(Graphics g) {

		if (supereffectivetimer < 30) {
			g.drawImage(supereffective, 400 + (supereffectivetimer / 2), 200 + (supereffectivetimer / 2),
					300 - supereffectivetimer, 87 - (supereffectivetimer / 2), this);
			supereffectivetimer++;
		}
	}

	public void drawcriticalhit(Graphics g) {

		if (criticalhittimer < 30) {
			if (supereffectivetimer < 30)
				g.drawImage(criticalhit, 400 + (criticalhittimer / 2), 63 + (criticalhittimer / 2),
						300 - criticalhittimer, 87 - (criticalhittimer / 2), this);
			else
				g.drawImage(criticalhit, 400 + +(criticalhittimer / 2), 200 + (criticalhittimer / 2),
						300 - criticalhittimer, 87 - (criticalhittimer / 2), this);
			criticalhittimer++;
		}
	}
	public void setrumble(){
		if(roar.isrunning())
			rumbleamount = 15;
		else
			rumbleamount = 5;
	}
}
