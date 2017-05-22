import java.applet.Applet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.applet.*;

public class TesterClass extends JApplet implements KeyListener, ActionListener
{
 String facing;
 Timer t;
 ArrayList<String> pressed;
 ArrayList<String> possible;
 ArrayList<String> current;
 Toolkit tk;
 Image blank1;
 Image blank2;
 Image blank3;
 Image blank4;
 int p1X = 500;
 int p1Y = 500;
 int p2X = 700;
 int p2Y = 700;
 int w1X = 20;
 int w1Y = 4000;
 int w2X = 5000;
 int w2Y = 5000;


 public void init()
 {
  setFocusable(true);
  addKeyListener(this);
  int delay = 16;
  t = new Timer(delay,this);
  tk = Toolkit.getDefaultToolkit();
  t.start();
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
  blank1 = tk.getImage("maro.png");
  blank2 = tk.getImage("loogi.png");
  blank3 = tk.getImage("fireball.jpg");
  blank4 = tk.getImage("fireball.jpg");

 }

 public void paint(Graphics g)
 {
	 super.paint(g);
  g.drawImage(blank1,p1X,p1Y,null);
  g.drawImage(blank2,p2X,p2Y,null);
  g.drawImage(blank3,w1X,w1Y,null);
  g.drawImage(blank4,w2X,w2Y,null);
 }
 public String FacingDirection()
 {
  return facing;
 }
 public void actionPerformed(ActionEvent e)
 {
  //adds the direction to an arraylist and outputs it
  if(e.getSource() == t)
  {
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
       else if(pressed.get(a).equalsIgnoreCase("space"))
       {
			if(facing.equalsIgnoreCase("up"))
			{
				w1Y = p1Y;
				w1Y =- 20;
				w1X = p1X;
			}
			else if(facing.equalsIgnoreCase("left"))
			{
			}
			else if(facing.equalsIgnoreCase("left"))
			{
			}
			else if(facing.equalsIgnoreCase("left"))
			{
			}
	   }
	   else if(pressed.get(a).equalsIgnoreCase("shift"))
	   {
	   }
 		facing = pressed.get(pressed.size()-1);
      }

    repaint();
   //removes the current action so that the player doesn't keep moving in that direction forever
   for(int a = 0; a < pressed.size();)
   {
    pressed.remove(a);
   }
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
  if(e.getKeyCode() == KeyEvent.VK_SPACE)
  {
	  pressed.add("space");
  }
  if(e.getKeyCode() == KeyEvent.VK_SHIFT)
  {
	  pressed.add("shift");
  }


 }
 public void keyReleased(KeyEvent e)
 {

 }
 public void keyTyped(KeyEvent e)
 {

 }

}