package filereading;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Take a look in the JRE (Java Runtime Environment) Library classes.jar for javax.swing in Eclipse IDE
// You will see all the .class files for each swing class, like JFrame, JComponent etc. (compiled Java bytecode)
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Class that contains the main method for the program and creates the frame
 * containing the component. 
 */
@SuppressWarnings("deprecation")
public class Startgame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame splashframe;
	Splash thesplash = new Splash();
	public final static int ONE_SECOND = 1000;
	static Timer timer = null;
	static Gameviewer game = null;

	public Startgame(String s) throws Exception {

		super(s);

		/*
		 * JPanel p = new JPanel(); label = new JLabel("Key Listener!");
		 * p.add(label); add(p); addKeyListener(this); setSize(200, 100);
		 * setVisible(true);
		 */
		splashframe = new JFrame();
		splashframe.add(thesplash);
		splashframe.setSize(800, 500);
		splashframe.setLocationRelativeTo(null);
		splashframe.setTitle("Loading");
		splashframe.setResizable(false);
		splashframe.setUndecorated(true);
		splashframe.setVisible(true);
		timer = new Timer(ONE_SECOND / 60, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(game != null)
				game.thegame.repaint();
			}
		});
	}
/**
 * creates splashscreen and then loads the gameviewer, then hiding the splash screen
 * @param args
 * @throws Exception
 */
	public static void main(String[] args) throws Exception {
		Startgame load = new Startgame("Load");
		
		game = new Gameviewer("Game");
		load.splashframe.setVisible(false);
		load.splashframe.dispose();
		game.frame.setVisible(true);
		
		timer.start();
		
	}

}
