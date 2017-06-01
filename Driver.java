import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.KeyStroke;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class Driver extends JApplet implements KeyListener
{
	//IMPORTANT- Make sure Quinn uses the current Sprite class- it will be in the main branch of the Github repository
	Sprite sprite; //16 Sprites- Name them similar to the file names I made to make it easier to keep track
	BufferedImage brickImage; //16 BufferedImages
	MapGenerator gen;
	ArrayList<BufferedImage> animations; //This array list will
	ArrayList<Sprite> sprites;
	ArrayList<Integer> keys;
	Sprite testBrick;
	Sprite[][] map;
	int[][] numberMap;
	int xPos, yPos, xBrickPos, yBrickPos;
	int characterImageDisplayed;
	int facing = 0;
	Rectangle rect;
	Random r;
	int Spawnx;
	int Spawny;

	int speed = 5;
	long timeheld = 0;
	final int XSIZE = 155;
	final int YSIZE = 90;
	final int XINIT = 50;
	final int YINIT = 30;
	final int XROWS = 10;
	final int YROWS = 10;
	final int W = 87;
	final int A = 65;
	final int S = 83;
	final int D = 68;
	final int UP = 38;
	final int DOWN = 40;
	final int LEFT = 37;
	final int RIGHT = 39;
	final int FACE_RIGHT = 0;
	final int FACE_DOWN = 1;
	final int FACE_LEFT = 2;
	final int FACE_UP = 3;
	boolean canSpawn;
	Runner thread;
	public void init()
	{
		Random r = new Random();
		animations  = new ArrayList<BufferedImage>();
		sprites = new ArrayList<Sprite>();
		keys = new ArrayList<Integer>();
		gen = new MapGenerator(XROWS, YROWS);
		map = new Sprite[XROWS][YROWS];
		numberMap = new int[XROWS][YROWS];
		xPos = 50;
		yPos = 50;
		xBrickPos = XINIT;
		yBrickPos = YINIT;
		thread = new Runner();
		setLayout(null);
		setFocusable(true);
		setSize(2000, 1200);
		addKeyListener(this);
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
		testBrick = new Sprite(brickImage, 300, 300);

		gen.create();
		numberMap = gen.getMap();
		for(int y1 = 0; y1 < map[0].length; y1++)
		{
			for(int x1 = 0; x1 < map.length; x1++)
			{
				if(numberMap[x1][y1] != 0)
				{
					map[x1][y1] = new Sprite(brickImage, xBrickPos, yBrickPos);
				}
				xBrickPos += XSIZE;
				System.out.println("x1 = " + x1);
			}
			System.out.println("y1 = " + y1);
			xBrickPos = XINIT;
			yBrickPos += YSIZE;
		}
		// Random spawning
		canSpawn = false;
		while(!canSpawn)
		{
			canSpawn = true;
			xPos = r.nextInt(900);
			yPos = r.nextInt(900);
			rect = new Rectangle(xPos, yPos, animations.get(0).getWidth(), animations.get(0).getHeight());
			for(int wallY = 0; wallY < map[0].length; wallY++)
			{
				for(int wallX = 0; wallX < map.length; wallX++)
				{
					if(map[wallX][wallY] != null)
					{
						if(map[wallX][wallY].collidesGeneral(rect))
						{
							canSpawn = false;
						}
					}
				}
			}
		}
		for(int index = 0; index < animations.size(); index++)
		{
			sprites.add(new Sprite(animations.get(index), Spawnx, Spawny));
		}
		thread.start();
	}
	public void keyPressed(KeyEvent e) //Multi-Key Listener
	{
		boolean isRegistered = false;
		int keyPressedValue = e.getKeyCode();
		timeheld++;
		for(int keyIndex = 0; keyIndex < keys.size(); keyIndex++)
		{
			if(keys.get(keyIndex).intValue() == keyPressedValue)
			{
				isRegistered = true;
			}
		}
		if(!isRegistered)
		{
			keys.add((Integer)e.getKeyCode());
		}
		//System.out.println(timeheld);
	}
	public void keyReleased(KeyEvent e)
	{
		//System.out.println(keys.size());
		int keyReleasedValue = e.getKeyCode();
		for(int keyIndex = 0; keyIndex < keys.size(); keyIndex++)
		{
			if(keys.get(keyIndex).intValue() == keyReleasedValue)
			{
				keys.remove(keyIndex);
			}
		}
		timeheld = 0;
	}
	public void keyClicked(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			sprites.get(characterImageDisplayed).draw(g);
			testBrick.draw(g);
			for(int y1 = 0; y1 < map[0].length; y1++)
			{
				for(int x1 = 0; x1 < map.length; x1++)
				{
					if(numberMap[x1][y1] != 0)
					{
						map[x1][y1].draw(g);
					}
				}
			}
		}
	}
	public class Runner implements Runnable
	{
		Thread t;
		public Runner()
		{
			characterImageDisplayed = 0;
		}
		public void run()
		{
			try
			{
				while(true)
				{
					if(keys.contains(RIGHT))
					{
						xPos += speed;
						facing = FACE_RIGHT;
						if(timeheld < 1)
						{
							characterImageDisplayed = 12;
						}
						else
						{
							if(characterImageDisplayed < 12 || characterImageDisplayed > 16)
							{
								characterImageDisplayed = 12;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed == 16)
							{
								characterImageDisplayed = 12;
							}
						}
					}
					if(keys.contains(LEFT) )
					{
						xPos -= speed;
						facing = FACE_LEFT;
						if(timeheld < 1)
						{
							characterImageDisplayed = 8;
						}
						else
						{
							if(characterImageDisplayed < 8 || characterImageDisplayed > 12)
							{
								characterImageDisplayed = 8;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed >= 12)
							{
								characterImageDisplayed = 8;
							}
						}
					}
					if(keys.contains(UP))
					{
						yPos -= speed;
						facing = FACE_UP;

						if(timeheld < 1)
						{
							characterImageDisplayed = 4;
						}
						else
						{
							if(characterImageDisplayed < 4 || characterImageDisplayed > 8)
							{
								characterImageDisplayed = 4;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed >= 8)
							{
								characterImageDisplayed = 4;
							}
						}
					}
					if(keys.contains(DOWN))
					{
						yPos += speed;
						facing = FACE_DOWN;

						if(timeheld < 1)
						{
							characterImageDisplayed = 0;
						}
						else
						{
							if(characterImageDisplayed < 0 || characterImageDisplayed > 4)
							{
								characterImageDisplayed = 0;
							}
							characterImageDisplayed++;
							if(characterImageDisplayed >= 4)
							{
								characterImageDisplayed = 0;
							}
						}
					}
//					imageDisplayed++;
					//if(imageDisplayed
					for(int spriteIndex = 0; spriteIndex < sprites.size(); spriteIndex++)
					{
						sprites.get(spriteIndex).setPosition(xPos, yPos);
					}
					if(sprites.get(1).collidesDown(testBrick))
					{
						System.out.println("Collision");
					}
					repaint();
					t.sleep(50);
				}
			}
			catch(Exception e)
			{
				System.out.println("Game paused");
			}
		}
		public void start()
		{
			if(t == null)
			{
				t = new Thread(this, "GameLoop");
				t.start();
			}
		}
	}
}