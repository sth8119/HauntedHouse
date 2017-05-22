import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class RogueDriver extends JApplet
{
	Toolkit tk;
	Sprite sprite;
	public void init()
	{
		tk = Toolkit.getDefaultToolkit();
		//BufferedImage brickImage = (BufferedImage)tk.getImage("brick.png");
		//BufferedImage brickImage = ImageIO.read(new File()) //Image file goes here
		sprite = new Sprite(brickImage, 50, 50, brickImage.getWidth(), brickImage.getHeight());
	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			sprite.draw(g);
		}
	}
}