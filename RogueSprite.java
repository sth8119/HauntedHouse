

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;


public class RogueSprite
{
  Random r;
 Image Brick;
 Image LBrick;
 Image Back;

 Rectangle r1;

 double d1;
 double d2;

 int x, y, w, h;


  public RogueSprite()
  {
r = new Random();

   Toolkit tk = Toolkit.getDefaultToolkit();
   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

   Back = tk.getImage("back5.png");
   LBrick = tk.getImage("LongWall.png");
    Brick = tk.getImage("brick.png");

    r1 = new Rectangle(x,y,w,h);

  }

  public boolean collide(Rectangle r2)
  {
	  if (r1.intersects(r2))
	  {
		  return true;
	  }
	  else
	  return false;

  }


   public void draw (Graphics g)
   {
	   int k = 0;
	   while(k <50)
	   {
		   x = r.nextInt(1000);
		   y = r.nextInt(1000);


		   g.drawImage(Brick,x,y,null);
		   k++;

	   }

 }


}