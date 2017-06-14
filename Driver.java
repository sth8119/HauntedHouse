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
	BufferedImage background;
	BufferedImage deathScreen;
	MapGenerator gen;
	ArrayList<BufferedImage> animations1, animations2; //This array list will
	ArrayList<Character> sprites1, sprites2;
	ArrayList<Integer> keys;
	ArrayList<Enemy> ghosts;
	ArrayList<Particle> particleList;
	Sprite[][] map;
	ProjectileMotion pm;
	Label healthbar1, healthbar2;
	Random r;
	int[][] numberMap;
	int attackSpeed1, maxHealth1, range1, attackSpeed2, maxHealth2, range2;
	int xPos1, yPos1, xPos2, yPos2;
	int enemyX, enemyY;
	int mapXPos, mapYPos;
	int characterImageDisplayed1, characterImageDisplayed2;
	int facing1, facing2;
	int speed1, speed2;
	int health1, health2;
	int enemySpeed;
	int attackCooldown1, attackCooldown2;
	int respawnTime;
	int timeDead1, timeDead2;
	int particleN;
	//long timeheld = 0;
	long timeUp1, timeUp2;
	long timeDown1, timeDown2;
	long timeRight1, timeRight2;
	long timeLeft1, timeLeft2;
	long timeSpace;
	long timeAlt;

	final int XSIZE = 90;
	final int YSIZE = 90;
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
	final int SPACE = 32;
	final int ALT = 67;
	final int FACE_RIGHT = 0;
	final int FACE_DOWN = 1;
	final int FACE_LEFT = 2;
	final int FACE_UP = 3;
	boolean canCreate;
	boolean canPlace1, canPlace2;
	boolean isSwinging1, isSwinging2;
	boolean fire;
	boolean gameOver;
	boolean canMoveUp1, canMoveDown1, canMoveLeft1, canMoveRight1, canMoveUp2, canMoveDown2, canMoveLeft2, canMoveRight2;
	Runner thread;
	public void init()
	{
		animations1  = new ArrayList<BufferedImage>();
		animations2  = new ArrayList<BufferedImage>();
		keys = new ArrayList<Integer>();
		ghosts = new ArrayList<Enemy>();
		canPlace1 = true;
		isSwinging1 = false;
		isSwinging2 = false;
		gameOver = false;
		r = new Random();
		//80 + r.nextInt(500);
		timeRight1 = 0;
		timeLeft1 = 0;
		timeUp1 = 0;
		timeDown1 = 0;
		timeSpace = 0;
		attackCooldown1 = 0;
		facing1 = 0;
		speed1 = 5;
		timeRight2 = 0;
		timeLeft2 = 0;
		timeUp2 = 0;
		timeDown2 = 0;
		timeAlt = 0;
		attackCooldown2 = 0;
		facing2 = 0;
		speed2 = 5;
		enemySpeed = 1;
		respawnTime = 500;
		timeDead1 = 0;
		timeDead2 = 0;
		fire = false;
		particleList = new ArrayList<Particle>();
		particleN = 25;
		xPos1 = 70;
		yPos1 = 70;
		xPos2 = 70;
		yPos2 = 70;
		enemyX = 300;
		enemyY = 300;
		attackSpeed1 = 15;
		attackSpeed2 = 15;
		maxHealth1 = 100;
		maxHealth2 = 100;
		range1 = 1;
		range2 = 1;
		healthbar1 = new Label();
		healthbar2 = new Label();
		healthbar1.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		healthbar2.setFont(new Font("Comic Sans", Font.PLAIN, 20));
		healthbar1.setBounds(1250, 100, 250, 100);
		healthbar2.setBounds(1250, 250, 250, 100);
		pm = new ProjectileMotion(enemySpeed);
		thread = new Runner();
		setLayout(null);
		setFocusable(true);
		setSize(2000, 1200);
		add(healthbar1);
		addKeyListener(this);
		setContentPane(new DrawingPanel());
		try
		{
			brickImage = ImageIO.read(new File("brick.jpg"));
			ghostImage = ImageIO.read(new File("enemy.gif"));
			background = ImageIO.read(new File("background.jpg"));
			deathScreen = ImageIO.read(new File("deathScreen.jpg"));
			animations1.add(ImageIO.read(new File("Player1_Down.png")));
			animations1.add(ImageIO.read(new File("Player1_DownRun.png")));
			animations1.add(ImageIO.read(new File("Player1_Down2.png")));
			animations1.add(ImageIO.read(new File("Player1_DownRun2.png")));
			animations1.add(ImageIO.read(new File("Player1_Up.png")));
			animations1.add(ImageIO.read(new File("Player1_UpRun.png")));
			animations1.add(ImageIO.read(new File("Player1_Up2.png")));
			animations1.add(ImageIO.read(new File("Player1_UpRun2.png")));
			animations1.add(ImageIO.read(new File("Player1_Left.png")));
			animations1.add(ImageIO.read(new File("Player1_LeftRun.png")));
			animations1.add(ImageIO.read(new File("Player1_Left2.png")));
			animations1.add(ImageIO.read(new File("Player1_LeftRun2.png")));
			animations1.add(ImageIO.read(new File("Player1_Right.png")));
			animations1.add(ImageIO.read(new File("Player1_RightRun.png")));
			animations1.add(ImageIO.read(new File("Player1_Right2.png")));
			animations1.add(ImageIO.read(new File("Player1_RightRun2.png")));
			animations1.add(ImageIO.read(new File("P1right_02_new.png")));
			animations1.add(ImageIO.read(new File("P1left_02_new.png")));
			animations1.add(ImageIO.read(new File("P1up_02_new.png")));
			animations1.add(ImageIO.read(new File("P1down_02_new.png")));

			animations2.add(ImageIO.read(new File("Player2_Down.png")));
			animations2.add(ImageIO.read(new File("Player2_DownRun.png")));
			animations2.add(ImageIO.read(new File("Player2_Down2.png")));
			animations2.add(ImageIO.read(new File("Player2_DownRun2.png")));
			animations2.add(ImageIO.read(new File("Player2_Up.png")));
			animations2.add(ImageIO.read(new File("Player2_UpRun.png")));
			animations2.add(ImageIO.read(new File("Player2_Up2.png")));
			animations2.add(ImageIO.read(new File("Player2_UpRun2.png")));
			animations2.add(ImageIO.read(new File("Player2_Left.png")));
			animations2.add(ImageIO.read(new File("Player2_LeftRun.png")));
			animations2.add(ImageIO.read(new File("Player2_Left2.png")));
			animations2.add(ImageIO.read(new File("Player2_LeftRun2.png")));
			animations2.add(ImageIO.read(new File("Player2_Right.png")));
			animations2.add(ImageIO.read(new File("Player2_RightRun.png")));
			animations2.add(ImageIO.read(new File("Player2_Right2.png")));
			animations2.add(ImageIO.read(new File("Player2_RightRun2.png")));
			animations2.add(ImageIO.read(new File("P2right_02.png")));
			animations2.add(ImageIO.read(new File("P2left_02.png")));
			animations2.add(ImageIO.read(new File("P2up_02.png")));
			animations2.add(ImageIO.read(new File("P2down_02.png")));
		}
		catch(Exception e)
		{
			System.out.println("File Not Found");
		}
		//ghost = new Sprite(ghostImage, enemyX, enemyY);
		for(int i = 0; i < 4; i++)
		{
			ghosts.add(new Enemy(ghostImage, 0, 0, r.nextInt(2) + 1));
			ghosts.get(i).setPosition(r.nextInt(1000) + 50, r.nextInt(1000) + 50);
		}
		generateMap();
		spawn1();
		spawn2();
		gen.print();
		thread.start();
	}
	public void generateMap()
	{
		mapXPos = 20;
		mapYPos = 20;
		map = new Sprite[XROWS][YROWS];
		numberMap = new int[XROWS][YROWS];
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
	}
	public void spawn1()
	{
		do
		{
			canPlace1 = true;
			sprites1 = new ArrayList<Character>();
			xPos1 = r.nextInt(500) + 80;
			yPos1 = r.nextInt(500) + 80;
			for(int index = 0; index < animations1.size(); index++)
			{
				sprites1.add(new Character(animations1.get(index), xPos1, yPos1, attackSpeed1, maxHealth1, range1));
			}
			for(int y = 0; y < map[0].length; y++)
			{
				for(int x = 0; x < map.length; x++)
				{
					if(sprites1.get(0).collidesGeneral(map[x][y]) && numberMap[x][y] != 0)
					{
						canPlace1 = false;
					}
				}
			}
		}while(!canPlace1);
	}
	public void spawn2()
	{
		do
		{
			canPlace2 = true;
			sprites2 = new ArrayList<Character>();
			xPos2 = r.nextInt(500) + 80;
			yPos2 = r.nextInt(500) + 80;
			for(int index = 0; index < animations2.size(); index++)
			{
				sprites2.add(new Character(animations2.get(index), xPos2, yPos2, attackSpeed2, maxHealth2, range2));
			}
			for(int y = 0; y < map[0].length; y++)
			{
				for(int x = 0; x < map.length; x++)
				{
					if(sprites2.get(0).collidesGeneral(map[x][y]) && numberMap[x][y] != 0)
					{
						canPlace2 = false;
					}
				}
			}
		}while(!canPlace2);
	}
	public void keyPressed(KeyEvent e) //Multi-Key Listener
	{
		boolean isRegistered = false;
		int keyPressedValue = e.getKeyCode();
		System.out.println(keyPressedValue);
		if(keyPressedValue == UP)
		{
			timeUp1++;
		}
		if(keyPressedValue == DOWN)
		{
			timeDown1++;
		}
		if(keyPressedValue == LEFT)
		{
			timeLeft1++;
		}
		if(keyPressedValue == RIGHT)
		{
			timeRight1++;
		}
		if(keyPressedValue == SPACE)
		{
			timeSpace++;
		}

		if(keyPressedValue == W)
		{
			timeUp2++;
		}
		if(keyPressedValue == S)
		{
			timeDown2++;
		}
		if(keyPressedValue == A)
		{
			timeLeft2++;
		}
		if(keyPressedValue == D)
		{
			timeRight2++;
		}
		if(keyPressedValue == ALT)
		{
			timeSpace++;
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
			timeUp1 = 0;
		}
		if(keyReleasedValue == DOWN)
		{
			timeDown1 = 0;
		}
		if(keyReleasedValue == LEFT)
		{
			timeLeft1 = 0;
		}
		if(keyReleasedValue == RIGHT)
		{
			timeRight1 = 0;
		}
		if(keyReleasedValue == SPACE)
		{
			timeSpace = 0;
			isSwinging1 = true;
		}

		if(keyReleasedValue == W)
		{
			timeUp2 = 0;
		}
		if(keyReleasedValue == S)
		{
			timeDown2 = 0;
		}
		if(keyReleasedValue == A)
		{
			timeLeft2 = 0;
		}
		if(keyReleasedValue == D)
		{
			timeRight2 = 0;
		}
		if(keyReleasedValue == ALT)
		{
			timeAlt = 0;
			isSwinging2 = true;
		}
	}
	public void keyClicked(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}
	public void fire()
	{
		for(int index = 0; index < particleN; index++)
		{
			particleList.get(index).tick();
		}
	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			add(healthbar1);
			add(healthbar2);
			g.drawImage(background, 0, 0, this);
			if(sprites1.get(0).isAlive())
			{
				sprites1.get(characterImageDisplayed1).draw(g);
			}
			if(sprites2.get(0).isAlive())
			{
				sprites2.get(characterImageDisplayed2).draw(g);
			}
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
			/*if(fire)
			{
				fire();
				for(int i = 0; i < particleN; i++)
				{
					particleList.get(i).draw(g);
					System.out.println("Particle drawn");
				}
			}*/
			if(gameOver)
			{
				g.drawImage(deathScreen, 0, 0, this);
			}
			//sprites.get(5).draw(g); //Use a timer to change the image ever few milliseconds for now. Once the image index reaches 16, it should go back to index = 0
		}
	}
	public class Runner implements Runnable
	{
		Thread t;
		public Runner()
		{
			characterImageDisplayed1 = 0;
			characterImageDisplayed2 = 0;
		}
		public void run()
		{
			try
			{
				while(true)
				{
					canMoveUp1 = true;
					canMoveDown1 = true;
					canMoveRight1 = true;
					canMoveLeft1 = true;
					canMoveUp2 = true;
					canMoveDown2 = true;
					canMoveRight2 = true;
					canMoveLeft2 = true;
					for(int y = 0; y < map[0].length; y++)
					{
						for(int x = 0; x < map.length; x++)
						{
							if(numberMap[x][y] == 1)
							{
								if(sprites1.get(characterImageDisplayed1).collidesUp(map[x][y]))
								{
									canMoveUp1 = false;
								}
								if(sprites1.get(characterImageDisplayed1).collidesDown(map[x][y]))
								{
									canMoveDown1 = false;
								}
								if(sprites1.get(characterImageDisplayed1).collidesRight(map[x][y]))
								{
									canMoveRight1 = false;
								}
								if(sprites1.get(characterImageDisplayed1).collidesLeft(map[x][y]))
								{
									canMoveLeft1 = false;
								}

								if(sprites2.get(characterImageDisplayed2).collidesUp(map[x][y]))
								{
									canMoveUp2 = false;
								}
								if(sprites2.get(characterImageDisplayed2).collidesDown(map[x][y]))
								{
									canMoveDown2 = false;
								}
								if(sprites2.get(characterImageDisplayed2).collidesRight(map[x][y]))
								{
									canMoveRight2 = false;
								}
								if(sprites2.get(characterImageDisplayed2).collidesLeft(map[x][y]))
								{
									canMoveLeft2 = false;
								}
							}
						}
					}
//////////////////////////////////////
					if(keys.contains(SPACE) && !isSwinging1)
					{
						//0 = Right, 1 = Left, 2 = Up, 3 = Down
						isSwinging1 = true;
						attackCooldown1 = 0;
						if(facing1 == 0)
						{
							characterImageDisplayed1 = 16;
						}
						else if(facing1 == 1)
						{
							characterImageDisplayed1 = 19;
						}
						else if(facing1 == 2)
						{
							characterImageDisplayed1 = 17;
						}
						else if(facing1 == 3)
						{
							characterImageDisplayed1 = 18;
						}
						for(int currentGhost = 0; currentGhost < ghosts.size(); currentGhost++)
						{
							for(int currentSprite = 16; currentSprite < sprites1.size(); currentSprite++)
							{
								if(sprites1.get(currentSprite).swingRight(ghosts.get(currentGhost)) == true && facing1 == FACE_RIGHT)
								{
									ghosts.remove(currentGhost);
									break;
								}
								if(sprites1.get(currentSprite).swingLeft(ghosts.get(currentGhost)) == true && facing1 == FACE_LEFT)
								{
									ghosts.remove(currentGhost);
									break;
								}
								if(sprites1.get(currentSprite).swingUp(ghosts.get(currentGhost)) == true && facing1 == FACE_UP)
								{
									ghosts.remove(currentGhost);
									break;
								}
								if(sprites1.get(currentSprite).swingDown(ghosts.get(currentGhost)) == true && facing1 == FACE_DOWN)
								{
									ghosts.remove(currentGhost);
									break;
								}

							}
						}
					}

					else if((keys.contains(RIGHT) || keys.contains(UP) || keys.contains(DOWN) || keys.contains(LEFT)) && (attackCooldown1 == 0 || attackCooldown1 > 5))
					{
						if((keys.contains(RIGHT) && !keys.contains(UP) && !keys.contains(DOWN)) && canMoveRight1)
						{

							xPos1 += speed1;
							facing1 = FACE_RIGHT;
							if(timeRight1 < 1)
							{
								characterImageDisplayed1 = 12;
							}
							else
							{
								if(characterImageDisplayed1 < 12 || characterImageDisplayed1 > 16)
								{
									characterImageDisplayed1 = 12;
								}
								characterImageDisplayed1++;
								if(characterImageDisplayed1 == 16)
								{
									characterImageDisplayed1 = 12;
								}
							}
						}
						if((keys.contains(LEFT) && !keys.contains(UP) && !keys.contains(DOWN)) && canMoveLeft1)
						{
							xPos1 -= speed1;
							facing1 = FACE_LEFT;
							if(timeLeft1 < 1)
							{
								characterImageDisplayed1 = 8;
							}
							else
							{
								if(characterImageDisplayed1 < 8 || characterImageDisplayed1 > 12)
								{
									characterImageDisplayed1 = 8;
								}
								characterImageDisplayed1++;
								if(characterImageDisplayed1 >= 12)
								{
									characterImageDisplayed1 = 8;
								}
							}
						}
						if((keys.contains(UP) || (keys.contains(UP) && keys.contains(RIGHT)) || (keys.contains(UP) && keys.contains(LEFT))) && canMoveUp1)
						{
							yPos1 -= speed1;
							facing1 = FACE_UP;
							if(timeUp1 < 1)
							{
								characterImageDisplayed1 = 4;
							}
							else
							{
								if(characterImageDisplayed1 < 4 || characterImageDisplayed1 > 8)
								{
									characterImageDisplayed1 = 4;
								}
								characterImageDisplayed1++;
								if(characterImageDisplayed1 >= 8)
								{
									characterImageDisplayed1 = 4;
								}
							}
						}
						if((keys.contains(DOWN) || (keys.contains(DOWN) && keys.contains(RIGHT)) || (keys.contains(DOWN) && keys.contains(LEFT))) && canMoveDown1)
						{
							yPos1 += speed1;
							facing1 = FACE_DOWN;
							if(timeDown1 < 1)
							{
								characterImageDisplayed1 = 0;
							}
							else
							{
								if(characterImageDisplayed1 < 0 || characterImageDisplayed1 > 4)
								{
									characterImageDisplayed1 = 0;
								}
								characterImageDisplayed1++;
								if(characterImageDisplayed1 >= 4)
								{
									characterImageDisplayed1 = 0;
								}
							}
						}
					}
					else
					{
						if(attackCooldown1 == 0 || attackCooldown1 > 5)
						{
							if(facing1 == FACE_DOWN)
							{
								characterImageDisplayed1 = 0;
							}
							else if(facing1 == FACE_UP)
							{
								characterImageDisplayed1 = 4;
							}
							else if(facing1 == FACE_RIGHT)
							{
								characterImageDisplayed1 = 12;
							}
							else
							{
								characterImageDisplayed1 = 8;
							}
						}
					}
					for(int spriteIndex = 0; spriteIndex < sprites1.size(); spriteIndex++)
					{
						sprites1.get(spriteIndex).setPosition(xPos1, yPos1);
					}


///////////////////////////////////////////////////////////////////////////////////
					if(keys.contains(ALT) && !isSwinging2)
					{
						//0 = Right, 1 = Left, 2 = Up, 3 = Down
						isSwinging2 = true;
						attackCooldown2 = 0;
						if(facing2 == 0)
						{
							characterImageDisplayed2 = 16;
						}
						else if(facing2 == 1)
						{
							characterImageDisplayed2 = 19;
						}
						else if(facing2 == 2)
						{
							characterImageDisplayed2 = 17;
						}
						else if(facing2 == 3)
						{
							characterImageDisplayed2 = 18;
						}
						for(int currentGhost = 0; currentGhost < ghosts.size(); currentGhost++)
						{
							for(int currentSprite = 16; currentSprite < sprites2.size(); currentSprite++)
							{
								if(sprites2.get(currentSprite).swingRight(ghosts.get(currentGhost)) == true && facing2 == FACE_RIGHT)
								{
									for(int i = 0; i < particleN; i++)
									{
										particleList.add(new Particle(ghosts.get(currentGhost).getX() + 15, ghosts.get(currentGhost).getY() + 15));
									}
									fire = true;
									fire();
									ghosts.remove(currentGhost);
									break;
								}
								if(sprites2.get(currentSprite).swingLeft(ghosts.get(currentGhost)) == true && facing2 == FACE_LEFT)
								{
									for(int i = 0; i < particleN; i++)
									{
										particleList.add(new Particle(ghosts.get(currentGhost).getX() + 15, ghosts.get(currentGhost).getY() + 15));
									}
									fire = true;
									fire();
									ghosts.remove(currentGhost);
									break;
								}
								if(sprites2.get(currentSprite).swingUp(ghosts.get(currentGhost)) == true && facing2 == FACE_UP)
								{
									for(int i = 0; i < particleN; i++)
									{
										particleList.add(new Particle(ghosts.get(currentGhost).getX() + 15, ghosts.get(currentGhost).getY() + 15));
									}
									fire = true;
									fire();
									ghosts.remove(currentGhost);
									break;
								}
								if(sprites2.get(currentSprite).swingDown(ghosts.get(currentGhost)) == true && facing2 == FACE_DOWN)
								{
									for(int i = 0; i < particleN; i++)
									{
										particleList.add(new Particle(ghosts.get(currentGhost).getX() + 15, ghosts.get(currentGhost).getY() + 15));
									}
									fire = true;
									fire();
									ghosts.remove(currentGhost);
									break;
								}

							}
						}
					}

					else if((keys.contains(D) || keys.contains(W) || keys.contains(S) || keys.contains(A)) && (attackCooldown2 == 0 || attackCooldown2 > 5))
					{
						if((keys.contains(D) && !keys.contains(W) && !keys.contains(S)) && canMoveRight2)
						{

							xPos2 += speed2;
							facing2 = FACE_RIGHT;
							if(timeRight2 < 1)
							{
								characterImageDisplayed2 = 12;
							}
							else
							{
								if(characterImageDisplayed2 < 12 || characterImageDisplayed2 > 16)
								{
									characterImageDisplayed2 = 12;
								}
								characterImageDisplayed2++;
								if(characterImageDisplayed2 == 16)
								{
									characterImageDisplayed2 = 12;
								}
							}
						}
						if((keys.contains(A) && !keys.contains(W) && !keys.contains(S)) && canMoveLeft2)
						{
							xPos2 -= speed2;
							facing2 = FACE_LEFT;
							if(timeLeft2 < 1)
							{
								characterImageDisplayed2 = 8;
							}
							else
							{
								if(characterImageDisplayed2 < 8 || characterImageDisplayed2 > 12)
								{
									characterImageDisplayed2 = 8;
								}
								characterImageDisplayed2++;
								if(characterImageDisplayed2 >= 12)
								{
									characterImageDisplayed2 = 8;
								}
							}
						}
						if((keys.contains(W) || (keys.contains(W) && keys.contains(D)) || (keys.contains(W) && keys.contains(A))) && canMoveUp2)
						{
							yPos2 -= speed2;
							facing2 = FACE_UP;
							if(timeUp2 < 1)
							{
								characterImageDisplayed2 = 4;
							}
							else
							{
								if(characterImageDisplayed2 < 4 || characterImageDisplayed2 > 8)
								{
									characterImageDisplayed2 = 4;
								}
								characterImageDisplayed2++;
								if(characterImageDisplayed2 >= 8)
								{
									characterImageDisplayed2 = 4;
								}
							}
						}
						if((keys.contains(S) || (keys.contains(S) && keys.contains(D)) || (keys.contains(S) && keys.contains(A))) && canMoveDown2)
						{
							yPos2 += speed2;
							facing2 = FACE_DOWN;
							if(timeDown2 < 1)
							{
								characterImageDisplayed2 = 0;
							}
							else
							{
								if(characterImageDisplayed2 < 0 || characterImageDisplayed2 > 4)
								{
									characterImageDisplayed2 = 0;
								}
								characterImageDisplayed2++;
								if(characterImageDisplayed2 >= 4)
								{
									characterImageDisplayed2 = 0;
								}
							}
						}
					}
					else
					{
						if(attackCooldown2 == 0 || attackCooldown2 > 5)
						{
							if(facing2 == FACE_DOWN)
							{
								characterImageDisplayed2 = 0;
							}
							else if(facing2 == FACE_UP)
							{
								characterImageDisplayed2 = 4;
							}
							else if(facing2 == FACE_RIGHT)
							{
								characterImageDisplayed2 = 12;
							}
							else
							{
								characterImageDisplayed2 = 8;
							}
						}
					}
					for(int spriteIndex = 0; spriteIndex < sprites2.size(); spriteIndex++)
					{
						sprites2.get(spriteIndex).setPosition(xPos2, yPos2);
					}

/////////////////////////////////////////////////////////////////////////////////////////////////
					//Ghost movement and attacks
					for(int i = 0; i < ghosts.size(); i++)
					{
						if(ghosts.get(i).getDistance(sprites1.get(0)) < 70)
						{
							if(ghosts.get(i).getX() != xPos1 && ghosts.get(i).getY() != yPos1)
							{
								pm.calculate(ghosts.get(i).getX(), ghosts.get(i).getY(), xPos1, yPos1);
								ghosts.get(i).setPosition(pm.getEnemyX(), pm.getEnemyY());
							}
						}
						else if(ghosts.get(i).getDistance(sprites2.get(0)) < 70)
						{
							if(ghosts.get(i).getX() != xPos2 && ghosts.get(i).getY() != yPos2)
							{
								pm.calculate(ghosts.get(i).getX(), ghosts.get(i).getY(), xPos2, yPos2);
								ghosts.get(i).setPosition(pm.getEnemyX(), pm.getEnemyY());
							}
						}
						else
						{
							if(ghosts.get(i).getPlayer() == 1)
							{
								if(ghosts.get(i).getX() != xPos1 && ghosts.get(i).getY() != yPos1)
								{
									pm.calculate(ghosts.get(i).getX(), ghosts.get(i).getY(), xPos1, yPos1);
									ghosts.get(i).setPosition(pm.getEnemyX(), pm.getEnemyY());
								}
							}
							else
							{
								if(ghosts.get(i).getX() != xPos2 && ghosts.get(i).getY() != yPos2)
								{
									pm.calculate(ghosts.get(i).getX(), ghosts.get(i).getY(), xPos2, yPos2);
									ghosts.get(i).setPosition(pm.getEnemyX(), pm.getEnemyY());
								}
							}
						}
						if(ghosts.get(i).collidesGeneral(sprites1.get(0)))
						{
							for(int index = 0; index < sprites1.size(); index++)
							{
								sprites1.get(index).loseHealth();
							}
							System.out.println(sprites1.get(0).getHealth());
						}
						if(ghosts.get(i).collidesGeneral(sprites2.get(0)))
						{
							for(int index = 0; index < sprites2.size(); index++)
							{
								sprites2.get(index).loseHealth();
							}
							System.out.println(sprites2.get(0).getHealth());
						}

						if(!sprites1.get(0).isAlive())
						{
							timeDead1++;
						}
						if(!sprites2.get(0).isAlive())
						{
							timeDead2++;
						}
						if(timeDead1 > respawnTime)
						{
							System.out.println("Respawn");
							timeDead1 = 0;
							spawn1();
						}
						if(timeDead2 > respawnTime)
						{
							timeDead2 = 0;
							spawn2();
						}


					}
					health1 = sprites1.get(0).getHealth();
					String h1 = Integer.toString(health1);
					if(health1 > 0)
					{
						healthbar1.setText("Player 1 health: " + h1 + "%");
					}
					else
					{
						healthbar1.setText("Player 1 is respawning...");
					}
					health2 = sprites2.get(0).getHealth();
					String h2 = Integer.toString(health2);
					if(health2 > 0)
					{
						healthbar2.setText("Player 2 health: " + h2 + "%");
					}
					else
					{
						healthbar2.setText("Player 2 is respawning");
					}
					//Ghost movement

					repaint();
					if(isSwinging1)
					{
						attackCooldown1++;
						isSwinging1 = true;
					}
					if(attackCooldown1 > attackSpeed1)
					{
						isSwinging1 = false;
					}
					if(isSwinging2)
					{
						attackCooldown2++;
						isSwinging2 = true;
					}
					if(attackCooldown2 > attackSpeed2)
					{
						isSwinging2 = false;
					}
					//Ghosts respawning
					if(ghosts.size() == 0)
					{
						for(int i = 0; i < 4; i++)
						{
							ghosts.add(new Enemy(ghostImage, 0, 0, r.nextInt(2) + 1));
							ghosts.get(i).setPosition(r.nextInt(1000) + 50, r.nextInt(1000) + 50);
							pm.increaseSpeedMultiplier();
						}
						generateMap();
						spawn1();
						spawn2();
					}
					if(!sprites1.get(0).isAlive() && !sprites2.get(0).isAlive())
					{
						gameOver = true;
						healthbar1.setBounds(100000, 100000, 0, 0);
						healthbar2.setBounds(100000, 100000, 0, 0);
						t.stop();
					}
					//
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
