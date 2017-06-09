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
	//DOWN ON MAP GENERATOR OVERLAP BROKEN
	BufferedImage brickImage; //16 BufferedImages
	BufferedImage ghostImage;
	MapGenerator gen;
	ArrayList<BufferedImage> animations; //This array list will
	//ArrayList<Sprite> sprites;
	ArrayList<Character> player1Sprites, player2Sprites;
	ArrayList<Integer> keys;
	//Sprite ghost;
	//ArrayList<Sprite> ghosts;
	ArrayList<Enemy> ghosts;
	Sprite[][] map;
	ProjectileMotion pm;
	Random r;
	int[][] numberMap;
	int attackSpeed, maxHealth, range;
	int xPosP1, yPosP1, xPosP2, yPosP2;
	int enemyX, enemyY;
	int mapXPos, mapYPos;
	int characterImageDisplayedP1, characterImageDisplayedP2;
	int facingP1 = 0;
	int facingP2 = 0;
	int speed = 5;
	//long timeheld = 0;
	long timeUpP1, timeDownP1, timeRightP1, timeLeftP1, timeUpP2, timeDownP2, timeRightP2, timeLeftP2;
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
	boolean canPlaceP1, canPlaceP2;
	Runner thread;
	public void init()
	{
		animations  = new ArrayList<BufferedImage>();
		keys = new ArrayList<Integer>();
		ghosts = new ArrayList<Enemy>();
		map = new Sprite[XROWS][YROWS];
		numberMap = new int[XROWS][YROWS];
		canPlaceP1 = true;
		canPlaceP2 = true;
		r = new Random();
		//80 + r.nextInt(500);
		timeRightP1 = 0;
		timeLeftP1 = 0;
		timeUpP1 = 0;
		timeDownP1 = 0;
		timeRightP2 = 0;
		timeLeftP2 = 0;
		timeUpP2 = 0;
		timeDownP2 = 0;
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
			ghostImage = ImageIO.read(new File("enemy.gif"));
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
			ghosts.add(new Enemy(ghostImage, 0, 0));
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
			}
			mapXPos = XINIT;
			mapYPos += YSIZE;
		}
		do
		{
			canPlaceP1 = true;
			player1Sprites = new ArrayList<Character>();
			xPosP1 = r.nextInt(500) + 80;
			yPosP1 = r.nextInt(500) + 80;
			for(int index = 0; index < animations.size(); index++)
			{
				player1Sprites.add(new Character(animations.get(index), xPosP1, yPosP1, attackSpeed, maxHealth, range));
			}
			for(int y = 0; y < map[0].length; y++)
			{
				for(int x = 0; x < map.length; x++)
				{
					if(player1Sprites.get(0).collidesGeneral(map[x][y]) && numberMap[x][y] != 0)
					{
						canPlaceP1 = false;
					}
				}
			}
		}while(!canPlaceP1);
		{
			canPlaceP2 = true;
			player2Sprites = new ArrayList<Character>();
			xPosP2 = r.nextInt(500) + 80;
			yPosP2 = r.nextInt(500) + 80;
			for(int index = 0; index < animations.size(); index++)
			{
				player2Sprites.add(new Character(animations.get(index), xPosP2, yPosP2, attackSpeed, maxHealth, range));
			}
			for(int y = 0; y < map[0].length; y++)
			{
				for(int x = 0; x < map.length; x++)
				{
					if(player2Sprites.get(0).collidesGeneral(map[x][y]) && numberMap[x][y] != 0)
					{
						canPlaceP2 = false;
					}
				}
			}
		}while(!canPlaceP2);
		gen.print();
		thread.start();
	}
	public void keyPressed(KeyEvent e) //Multi-Key Listener
	{
		boolean isRegistered = false;
		int keyPressedValue = e.getKeyCode();
		if(keyPressedValue == UP)
		{
			timeUpP1++;
		}
		if(keyPressedValue == DOWN)
		{
			timeDownP1++;
		}
		if(keyPressedValue == LEFT)
		{
			timeLeftP1++;
		}
		if(keyPressedValue == RIGHT)
		{
			timeRightP1++;
		}
		if(keyPressedValue == W)
		{
			timeUpP2++;
		}
		if(keyPressedValue == S)
		{
			timeDownP2++;
		}
		if(keyPressedValue == A)
		{
			timeLeftP2++;
		}
		if(keyPressedValue == D)
		{
			timeRightP2++;
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
			timeUpP1 = 0;
		}
		if(keyReleasedValue == DOWN)
		{
			timeDownP1 = 0;
		}
		if(keyReleasedValue == LEFT)
		{
			timeLeftP1 = 0;
		}
		if(keyReleasedValue == RIGHT)
		{
			timeRightP1++;
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
			player1Sprites.get(characterImageDisplayedP1).draw(g);
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
			characterImageDisplayedP1 = 0;
		}
		public void run()
		{
			try
			{
				while(true)
				{
					if(keys.contains(RIGHT) && !keys.contains(UP) && !keys.contains(DOWN))
					{
						xPosP1 += speed;
						facingP1 = FACE_RIGHT;
						if(timeRightP1 < 1)
						{
							characterImageDisplayedP1 = 12;
						}
						else
						{
							if(characterImageDisplayedP1 < 12 || characterImageDisplayedP1 > 16)
							{
								characterImageDisplayedP1 = 12;
							}
							characterImageDisplayedP1++;
							if(characterImageDisplayedP1 == 16)
							{
								characterImageDisplayedP1 = 12;
							}
						}
					}
					if(keys.contains(LEFT) && !keys.contains(UP) && !keys.contains(DOWN))
					{
						xPosP1 -= speed;
						facingP1 = FACE_LEFT;
						if(timeLeftP1 < 1)
						{
							characterImageDisplayedP1 = 8;
						}
						else
						{
							if(characterImageDisplayedP1 < 8 || characterImageDisplayedP1 > 12)
							{
								characterImageDisplayedP1 = 8;
							}
							characterImageDisplayedP1++;
							if(characterImageDisplayedP1 >= 12)
							{
								characterImageDisplayedP1 = 8;
							}
						}
					}
					if(keys.contains(UP) || (keys.contains(UP) && keys.contains(RIGHT)) || (keys.contains(UP) && keys.contains(LEFT)))
					{
						yPosP1 -= speed;
						facingP1 = FACE_UP;
						facingP1 = FACE_LEFT;
						if(timeUpP1 < 1)
						{
							characterImageDisplayedP1 = 4;
						}
						else
						{
							if(characterImageDisplayedP1 < 4 || characterImageDisplayedP1 > 8)
							{
								characterImageDisplayedP1 = 4;
							}
							characterImageDisplayedP1++;
							if(characterImageDisplayedP1 >= 8)
							{
								characterImageDisplayedP1 = 4;
							}
						}
					}
					if(keys.contains(DOWN) || (keys.contains(DOWN) && keys.contains(RIGHT)) || (keys.contains(DOWN) && keys.contains(LEFT)))
					{
						yPosP1 += speed;
						facingP1 = FACE_DOWN;
						facingP1 = FACE_LEFT;
						if(timeDownP1 < 1)
						{
							characterImageDisplayedP1 = 0;
						}
						else
						{
							if(characterImageDisplayedP1 < 0 || characterImageDisplayedP1 > 4)
							{
								characterImageDisplayedP1 = 0;
							}
							characterImageDisplayedP1++;
							if(characterImageDisplayedP1 >= 4)
							{
								characterImageDisplayedP1 = 0;
							}
						}
					}
					for(int spriteIndex = 0; spriteIndex < player1Sprites.size(); spriteIndex++)
					{
						player1Sprites.get(spriteIndex).setPosition(xPosP1, yPosP1);
					}
					//Ghost movement
					for(int i = 0; i < ghosts.size(); i++)
					{
						if(Math.sqrt(Math.pow(xPosP1 - ghosts.get(i).getX(), 2) + Math.pow(yPosP1 - ghosts.get(i).getY(), 2)) < 150)
						{
							pm.calculate(ghosts.get(i).getX(), ghosts.get(i).getY(), xPosP1, yPosP1);
						}
						else
						{
							pm.calculate(ghosts.get(i).getX(), ghosts.get(i).getY(), xPosP1 + ghosts.get(i).getRandomX(), yPosP1 + ghosts.get(i).getRandomY());
						}
						System.out.println(ghosts.get(i).getRandomX());
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
