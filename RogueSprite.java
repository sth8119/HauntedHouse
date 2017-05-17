import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;


public class RogueSprite
{
 	Image Brick;
 	Image LBrick;
 	Image Back;

 	Rectangle r1;

 	double d1;
 	double d2;

 	int x, y, w, h;

 public RogueSprite(int x,int y ,int w, int h)
 {
	this.x = x;
	this.y = y;
	this.h = h;
	this.w = w;

	Toolkit tk = Toolkit.getDefaultToolkit();

	Back = tk.getImage("back5.png");
	LBrick = tk.getImage("LongWall.png");
    Brick = tk.getImage("brick.png");

    r1 = new Rectangle(x,y,w,h);
  }

  public Rectangle getRectangle()
  {
	  return r1;
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

  public void setPosition(int x, int y)
  {
	  this.x = x;
	  this.y = y;
  }
  public int getPositionX()
  {
	  return x;
  }
  public int getPositionY()
  {
	  return y;
  }
  public void draw (Graphics g)
  {
	  g.drawImage(Brick,x,y,null);
	  g.setColor(Color.BLUE);
	  r1 = new Rectangle(x,y,w,h);
	  ((Graphics2D)g).draw(r1);
   }

}