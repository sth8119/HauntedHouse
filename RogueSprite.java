import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;
public class RogueSprite
{
	Image brick;
	Image lBrick;
	Image back;
	Rectangle rectangle;
	double d1;
	double d2;
	int x, y, width, height;
	public RogueSprite(int x,int y ,int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Toolkit tk = Toolkit.getDefaultToolkit();
		back = tk.getImage("back5.png");
		lBrick = tk.getImage("LongWall.png");
		brick = tk.getImage("brick.png");
		rectangle = new Rectangle(x,y,width,height);
	}
	public Rectangle getRectangle()
	{
		return rectangle;
	}
	public boolean collide(Rectangle r)
	{
		if (rectangle.intersects(r))
		{
			return true;
		}
		else
		{
			return false;
		}
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
		g.drawImage(brick,x,y,null);
		g.setColor(Color.BLUE);
		//rectangle = new Rectangle(x,y,w,h);
		((Graphics2D)g).draw(rectangle);
	}
}