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
	//IMPORTANT- Make sure Quinn uses the current Sprite class- it will be in the main branch of the Github repository
	Sprite sprite; //16 Sprites- Name them similar to the file names I made to make it easier to keep track
	BufferedImage brickImage; //16 BufferedImages
	//Sprite[] animations = new Sprite[16]; //This array list will
	public void init()
	{
		setLayout(null);
		setContentPane(new DrawingPanel());
		try
		{
			brickImage = ImageIO.read(new File("brick.png")); //Do this once for each character model - There should be 16 total
		}
		catch(Exception e)
		{
			System.out.println("File Not Found");
		}
		sprite = new Sprite(brickImage, 50, 50, brickImage.getWidth(), brickImage.getHeight()); //50, 50 is just the initial x and y for the image
	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			sprite.draw(g); //Use a timer to change the image ever few milliseconds for now. Once the image index reaches 16, it should go back to index = 0
		}
	}
}