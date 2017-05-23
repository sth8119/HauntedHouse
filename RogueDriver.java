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
	ArrayList<BufferedImage> animations; //This array list will
	ArrayList<Sprite> sprites;
	public void init()
	{
		animations  = new ArrayList<BufferedImage>();
		sprites = new ArrayList<Sprite>();
		setLayout(null);
		setContentPane(new DrawingPanel());
		try
		{
			brickImage = ImageIO.read(new File("brick.png"));
			animations.add(ImageIO.read(new File("Player1_Down.png")));
			animations.add(ImageIO.read(new File("Player1_DownRun.png")));
			animations.add(ImageIO.read(new File("Player1_Down2.png")));
			animations.add(ImageIO.read(new File("Player1_DownRun2.png")));
			animations.add(ImageIO.read(new File("Player1_Up.png")));
			animations.add(ImageIO.read(new File("Player1_UpRun.png")));
			animations.add(ImageIO.read(new File("Player1_Up2.png")));
			animations.add(ImageIO.read(new File("Player1_UpRun2.png")));
			animations.add(ImageIO.read(new File("Player1_Left.png")));
			animations.add(ImageIO.read(new File("Player1_LeftRun.png")));
			animations.add(ImageIO.read(new File("Player1_Left2.png")));
			animations.add(ImageIO.read(new File("Player1_LeftRun2.png")));
			animations.add(ImageIO.read(new File("Player1_Right.png")));
			animations.add(ImageIO.read(new File("Player1_RightRun.png")));
			animations.add(ImageIO.read(new File("Player1_Right2.png")));
			animations.add(ImageIO.read(new File("Player1_RightRun2.png")));

			//Do this once for each character model - There should be 16 total
		}
		catch(Exception e)
		{
			System.out.println("File Not Found");
		}
		for(int index = 0; index < animations.size(); index++)
		{
			sprites.add(new Sprite(animations.get(index), index*50 + 5, index*50 + 5));
		}
	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			for(int index = 0; index < sprites.size(); index++)
			{
				sprites.get(index).draw(g);
			}
			//sprites.get(5).draw(g); //Use a timer to change the image ever few milliseconds for now. Once the image index reaches 16, it should go back to index = 0
		}
	}
}