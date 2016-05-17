package filereading;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JComponent;
/**
 * splash screen, held in startgame.java
 * @author Perzivial
 *
 */
public class Splash extends JComponent{
BufferedImage img = new Image("pics/loading.png").img;
/**
 * just draws the image, nothing to extravagant
 */
@Override
public void paintComponent(Graphics g){
	g.drawImage(img, 0, 0, 800,500,this);
}
}
