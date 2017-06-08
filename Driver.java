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
	Sprite sprite; //16 Sprites- Name them similar to the file names I made to make it easier to keep track
	BufferedImage brickImage; //16 BufferedImages
	BufferedImage ghostImage;
	MapGenerator gen;
	ArrayList<BufferedImage> animations; //This array list will
	//ArrayList<Sprite> sprites;
	ArrayList<Character> player1Sprites;
	ArrayList<Integer> keys;
	//Sprite ghost;
	ArrayList<Sprite> ghosts;
	Sprite[][] map;
	ProjectileMotion pm;
	Random r;
	int[][] numberMap;
	int attackSpeed, maxHealth, range;
	int xPos, yPos;
	int enemyX, enemyY;
	int mapXPos, mapYPos;
	int characterImageDisplayed;
	int facing = 0;
	int speed = 5;
	//long timeheld = 0;
	long timeUp;
	long timeDown;
	long timeRight;
	long timeLeft;
	final int XSIZE = 50;
	final int YSIZE = 50;
	final int XINIT = 20;
	final int YINIT = 20;
	final int ENEMYXSIZE = 30;
	final int ENEMYYSIZE = 30;
	final int XROWS = 12;
	final int YROWS = 12;
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
	boolean canCreate;
	boolean canPlace;
	Runner thread;
	public void init()
	{
		animations  = new ArrayList<BufferedImage>();
		keys = new ArrayList<Integer>();
		ghosts = new ArrayList<Sprite>();
		map = new Sprite[XROWS][YROWS];
		numberMap = new int[XROWS][YROWS];
		canPlace = true;
		r = new Random();
		//80 + r.nextInt(500);
		timeRight = 0;
		timeLeft = 0;
		timeUp = 0;
		timeDown = 0;
		xPos = 70;
		yPos = 70;
		mapXPos = 20;
		mapYPos = 20;
		enemyX = 300;
		enemyY = 300;
		attackSpeed = 1;
		maxHealth = 1;
		range = 1;
		pm = new ProjectileMotion(2);
		thread = new Runner();
		setLayout(null);
		setFocusable(true);
		setSize(2000, 1200);
		addKeyListener(this);
		setContentPane(new DrawingPanel());
		try
		{
			brickImage = ImageIO.read(new File("greenWall.jpg"));
			ghostImage = ImageIO.read(new File("ghost.png"));
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

		}
		catch(Exception e)
		{
			System.out.println("File Not Found");
		}
		//ghost = new Sprite(ghostImage, enemyX, enemyY);
		for(int i = 0; i < 4; i++)
		{
			ghosts.add(new Sprite(ghostImage, 0, 0));
			ghosts.get(i).setPosition(r.nextInt(500) + 50, r.nextInt(500) + 50);
		}
		do
		{
			gen = new MapGenerator(10, 10);
			canCreate = gen.create();
		} while(!canCreate);
		gen.buildFinalMap();
		numberMap = gen.getMap();
		for(int y1 = 0; y1 < map[0].length; y1++)
		{
			for(int x1 = 0; x1 < map.length; x1++)
			{
				//System.out.println("X = " + x1 + " | Mapxpos = " + mapXPos);
				map[x1][y1] = new Sprite(brickImage, mapXPos, mapYPos);
				mapXPos += XSIZE;
				System.out.println((mapXPos + XSIZE));
			}
			System.out.println();
			mapXPos = XINIT;
			mapYPos += YSIZE;
		}
		do
		{
			canPlace = true;
			player1Sprites = new ArrayList<Character>();
			xPos = r.nextInt(500) + 80;
			yPos = r.nextInt(500) + 80;
			for(int index = 0; index < animations.size(); index++)
			{
				player1Sprites.add(new Character(animations.get(index), xPos, yPos, attackSpeed, maxHealth, range));
			}
			for(int y = 0; y < map[0].length; y++)
			{
				for(int x = 0; x < map.length; x++)
				{
					if(player1Sprites.get(0).collidesGeneral(map[x][y]) && numberMap[x][y] != 0)
					{
						canPlace = false;
					}
				}
			}
		}while(!canPlace);
		gen.print();
		thread.start();
	}
	public void keyPressed(KeyEvent e) //Multi-Key Listener
	{
		boolean isRegistered = false;
		int keyPressedValue = e.getKeyCode();
		if(keyPressedValue == UP)
		{
			timeUp++;
		}
		if(keyPressedValue == DOWN)
		{
			timeDown++;
		}
		if(keyPressedValue == LEFT)
		{
			timeLeft++;
		}
		if(keyPressedValue == RIGHT)
		{
			timeRight++;
		}
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
		if(keyReleasedValue == UP)
		{
			timeUp = 0;
		}
		if(keyReleasedValue == DOWN)
		{
			timeDown = 0;
		}
		if(keyReleasedValue == LEFT)
		{
			timeLeft = 0;
		}
		if(keyReleasedValue == RIGHT)
		{
			timeRight++;
		}
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
			player1Sprites.get(characterImageDisplayed).draw(g);
			for(int i = 0; i < ghosts.size(); i++)
			{
				ghosts.get(i).draw(g);
			}
			for(int y1 = 0; y1 < map[0].length; y1++)
			{
				for(int x1 = 0; x1 < map.length; x1++)
				{
					if(numberMap[x1][y1] != 0)
					{
						map[x1][y1].draw(g);
					}
					//System.out.println("Ran");
				}
			}
			//sprites.get(5).draw(g); //Use a timer to change the image ever few milliseconds for now. Once the image index reaches 16, it should go back to index = 0
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
					if(keys.contains(RIGHT) && !keys.contains(UP) && !keys.contains(DOWN))
					{
						xPos += speed;
						facing = FACE_RIGHT;
						if(timeRight < 1)
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
					if(keys.contains(LEFT) && !keys.contains(UP) && !keys.contains(DOWN))
					{
						xPos -= speed;
						facing = FACE_LEFT;
						if(timeLeft < 1)
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
					if(keys.contains(UP) || (keys.contains(UP) && keys.contains(RIGHT)) || (keys.contains(UP) && keys.contains(LEFT)))
					{
						yPos -= speed;
						facing = FACE_UP;
						facing = FACE_LEFT;
						if(timeUp < 1)
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
					if(keys.contains(DOWN) || (keys.contains(DOWN) && keys.contains(RIGHT)) || (keys.contains(DOWN) && keys.contains(LEFT)))
					{
						yPos += speed;
						facing = FACE_DOWN;
						facing = FACE_LEFT;
						if(timeDown < 1)
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
					for(int spriteIndex = 0; spriteIndex < player1Sprites.size(); spriteIndex++)
					{
						player1Sprites.get(spriteIndex).setPosition(xPos, yPos);
					}
					//Ghost movement
					for(int i = 0; i < ghosts.size(); i++)
					{
						pm.calculate(ghosts.get(i).getX(), ghosts.get(i).getY(), xPos, yPos);
						ghosts.get(i).setPosition(pm.getEnemyX(), pm.getEnemyY());
					}
					//Ghost movement

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
