import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class RogueDriver extends JApplet implements KeyListener
{
	//IMPORTANT- Make sure Quinn uses the current Sprite class- it will be in the main branch of the Github repository
	Sprite sprite; //16 Sprites- Name them similar to the file names I made to make it easier to keep track
	BufferedImage brickImage; //16 BufferedImages
	ArrayList<BufferedImage> animations; //This array list will
	ArrayList<Sprite> sprites;
	//Timer t;
	final int delay = 200;
	int imageDisplayed;
	int p1X;
	int p1Y;
	int p2X;
	int p2Y;
	Runner thread;
	ArrayList<String> pressed;
	ArrayList<String> possible;
	ArrayList<String> current;
	public void init()
	{
		setFocusable(true);
		addKeyListener(this);
		possible = new ArrayList<String>();
		pressed = new ArrayList<String>();
		current = new ArrayList<String>();
		possible.add("up");
		possible.add("left");
		possible.add("right");
		possible.add("down");
		possible.add("w");
		possible.add("a");
		possible.add("d");
		possible.add("s");
		animations  = new ArrayList<BufferedImage>();
		sprites = new ArrayList<Sprite>();
		thread = new Runner();
		imageDisplayed = 0;
		thread.start();
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
			sprites.add(new Sprite(animations.get(index), index, index));
		}
	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			sprites.get(imageDisplayed).draw(g);
			//sprites.get(5).draw(g); //Use a timer to change the image ever few milliseconds for now. Once the image index reaches 16, it should go back to index = 0
		}
	}
	public class Runner implements Runnable
	{
		Thread t;
		public void run()
		{
			try
			{
				while(true)
				{
					System.out.println("Ran");
					t.sleep(delay);
					imageDisplayed++;
					if(imageDisplayed == 16)
					{
						imageDisplayed = 0;
					}
					repaint();
					for(int a = 0; a < pressed.size(); a++)
    				{
      					if(pressed.get(a).equalsIgnoreCase("up"))
     					{
       						p1Y = p1Y - 50;
       					}
     				 	else if(pressed.get(a).equalsIgnoreCase("left"))
       					{
       						p1X = p1X - 50;
      					}
       					else if(pressed.get(a).equalsIgnoreCase("down"))
       					{
       				 		p1Y = p1Y + 50;
       					}
       					else if(pressed.get(a).equalsIgnoreCase("right"))
       					{
        					p1X = p1X + 50;
       					}
       					else if(pressed.get(a).equalsIgnoreCase("w"))
       					{
        					p2Y = p2Y - 50;
       					}
       					else if(pressed.get(a).equalsIgnoreCase("a"))
       					{
       	 					p2X = p2X - 50;
       					}
       					else if(pressed.get(a).equalsIgnoreCase("s"))
       					{
        					p2Y = p2Y + 50;
       					}
       					else if(pressed.get(a).equalsIgnoreCase("d"))
       					{
       	 					p2X = p2X + 50;
       					}
					}
					for(int index = 0; index < animations.size(); index++)
					{
						sprites.get(index).setPosition(p1X,p1Y);
					}
				}
			}
			catch(Exception e)
			{

			}
		}
		public void start()
		{
			t = new Thread(this, "MyThread");
			t.start();
		}
	}
	public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				pressed.add("right");
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				pressed.add("left");
			}
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				pressed.add("up");
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				pressed.add("down");
			}
			if(e.getKeyCode() == KeyEvent.VK_W)
			{
				pressed.add("w");
			}
			if(e.getKeyCode() == KeyEvent.VK_A)
			{
				pressed.add("a");
			}
			if(e.getKeyCode() == KeyEvent.VK_D)
			{
				pressed.add("d");
			}
			if(e.getKeyCode() == KeyEvent.VK_S)
			{
				pressed.add("s");
			}
		}
		public void keyReleased(KeyEvent e)
		{

		}
		public void keyTyped(KeyEvent e)
		{

		}

}