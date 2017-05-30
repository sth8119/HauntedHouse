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
 final int delay = 50;
 int imageDisplayed;
 int p1X;
 int p1Y;
 int p2X;
 int p2Y;
 int x1 = 500;
 int y1 = 100;
 int facing = 0;
 Runner thread;
 Rectangle r2;
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
  setLayout(null);
  setContentPane(new DrawingPanel());
  r2 = new Rectangle(x1,y1,50,50);

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

   animations.add(ImageIO.read(new File("Player2_Down.png")));
   animations.add(ImageIO.read(new File("Player2_DownRun.png")));
   animations.add(ImageIO.read(new File("Player2_Down2.png")));
   animations.add(ImageIO.read(new File("Player2_DownRun2.png")));
   animations.add(ImageIO.read(new File("Player2_Up.png")));
   animations.add(ImageIO.read(new File("Player2_UpRun.png")));
   animations.add(ImageIO.read(new File("Player2_Up2.png")));
   animations.add(ImageIO.read(new File("Player2_UpRun2.png")));
   animations.add(ImageIO.read(new File("Player2_Left.png")));
   animations.add(ImageIO.read(new File("Player2_LeftRun.png")));
   animations.add(ImageIO.read(new File("Player2_Left2.png")));
   animations.add(ImageIO.read(new File("Player2_LeftRun2.png")));
   animations.add(ImageIO.read(new File("Player2_Right.png")));
   animations.add(ImageIO.read(new File("Player2_RightRun.png")));
   animations.add(ImageIO.read(new File("Player2_Right2.png")));
   animations.add(ImageIO.read(new File("Player2_RightRun2.png")));

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
  thread.start();
 }
 public class DrawingPanel extends JPanel
 {
  public void paintComponent(Graphics g)
  {
   super.paintComponent(g);
   sprites.get(imageDisplayed).draw(g);
   ((Graphics2D)g).draw(r2);
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
     //System.out.println("Ran");
     t.sleep(delay);
     imageDisplayed++;
     //System.out.println(animations.size() + "");
     System.out.println(imageDisplayed + "");
     if(facing == 1)
     {
       if(imageDisplayed == 4)
       {
         imageDisplayed = 0;
       }
     }
     if(facing == 2)
     {
       if(imageDisplayed == 8)
       {
         imageDisplayed = 5;
       }
     }
     if(facing == 3)
     {
       if(imageDisplayed == 12)
       {
         imageDisplayed = 9;
       }
     }
     if(facing == 4)
      {
        if(imageDisplayed == 16)
        {
          imageDisplayed = 13;
        }
     }
//the collision wont work unless we know which side they are colliding with also i can't use the collide method until we turn the sprites into Rogue Sprites
     /*for(int x = 0; x < animations.size(); x++)
     {
       if(animations.get(x).collide(r2) == true)
       {
         p1X= p1X-1;
       }
     }*/
     repaint();
     if(imageDisplayed == 16)
     {
      imageDisplayed = 0;
     }
     for(int a = 0; a < pressed.size(); a++)
        {
           if(pressed.get(a).equalsIgnoreCase("up"))
           {
             p1Y = p1Y - 20;
             facing = 2;
             imageDisplayed = 5;
            }
           else if(pressed.get(a).equalsIgnoreCase("left"))
            {
             p1X = p1X - 20;
             facing = 3;
             imageDisplayed = 9;
            }
            else if(pressed.get(a).equalsIgnoreCase("down"))
            {
              p1Y = p1Y + 20;
              facing = 1;
              imageDisplayed = 0;
            }
            else if(pressed.get(a).equalsIgnoreCase("right"))
            {
             p1X = p1X + 20;
             facing = 4;
             imageDisplayed = 13;
            }
            else if(pressed.get(a).equalsIgnoreCase("w"))
            {
             p2Y = p2Y - 20;
            }
            else if(pressed.get(a).equalsIgnoreCase("a"))
            {
              p2X = p2X - 20;
            }
            else if(pressed.get(a).equalsIgnoreCase("s"))
            {
             p2Y = p2Y + 20;
            }
            else if(pressed.get(a).equalsIgnoreCase("d"))
            {
              p2X = p2X + 20;
            }
      }
     for(int a = 0; a < pressed.size();)
   {
    pressed.remove(a);
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