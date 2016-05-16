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
public class Gameviewer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JFrame splashframe;
	public final static int ONE_SECOND = 1000;
	static Timer timer = null;
	Game thegame = new Game();	
	public Gameviewer(String s) throws Exception {

		super(s);

		/*
		 * JPanel p = new JPanel(); label = new JLabel("Key Listener!");
		 * p.add(label); add(p); addKeyListener(this); setSize(200, 100);
		 * setVisible(true);
		 */
		frame = new JFrame();
		frame.add(thegame);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Game");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thegame.addKeyListener(thegame);
		thegame.setFocusable(true);
		//frame.setVisible(true);
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// frame.setLocation(dim.width/2-this.getSize().width/2,
		// dim.height/2-this.getSize().height/2);
		


	}
/**
 * creates the Game instance
 * @param args
 * @throws Exception
 */
	public static void main(String[] args) throws Exception {
		new Gameviewer("GameViewer");
		//timer.start();

	}

}
