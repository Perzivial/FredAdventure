package filereading;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JComponent;

public class Splash extends JComponent{
BufferedImage img = new Image("pics/loading.png").img;
@Override
public void paintComponent(Graphics g){
	g.drawImage(img, 0, 0, 800,500,this);
}
}
