import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.Random;

public class Sprite
{
	int width;
	int height;
	int x;
	int y;
	int range;
	Rectangle collider;
	BufferedImage image;

	public Sprite(BufferedImage image, int x, int y)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.getWidth();
		height = image.getHeight();
		range = 15;
		collider = new Rectangle(x, y, width, height);
	}
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
		collider.setLocation(x, y);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Rectangle getCollider()
	{
		return collider;
	}
	public int getDistance(Sprite sprite)
	{
		return (int)Math.sqrt(Math.pow((x - sprite.getX() + 15), 2) + Math.pow((y - sprite.getY() + 15), 2));
	}
	public boolean collidesGeneral(Sprite sprite)
	{
		if(collider.intersects(sprite.getCollider()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean collidesRight(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(y <= (y2 + height2) && y >= y2)
		{
			if(x2 - (x + width) < 10 && x2 - (x + width) > -10)
			{
				collides = true;
			}
		}
		if(y2 <= (y + height) && y2 >= y)
		{
			if(x2 - (x + width) < 10 && x2 - (x + width) > -10)
			{
				collides = true;
			}
		}
		return collides;
	}
	public boolean collidesLeft(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(y <= (y2 + height2) && y >= y2)
		{
			if(x - (x2 + width2) < 10 && x - (x2 + width2) > -10)
			{
				collides = true;
			}
		}
		if(y2 <= (y + height) && y2 >= y)
		{
			if(x - (x2 + width2) < 10 && x - (x2 + width2) > -10)
			{
				collides = true;
			}
		}
		return collides;
	}
	public boolean collidesDown(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(x <= (x2 + width2) && x >= x2)
		{
			if((y + height) - y2 < 10 && (y + height) - y2 > -10)
			{
				collides = true;
			}
		}
		if(x2 <= (x + width) && x2 >= x)
		{
			if((y + height) - y2 < 10 && (y + height) - y2 > -10)
			{
				collides = true;
			}
		}
		return collides;
	}
	public boolean collidesUp(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(x <= (x2 + width2) && x >= x2)
		{
			if(y - (y2 + height2) < 10 && y - (y2 + height2) > -10)
			{
				collides = true;
			}
		}
		if(x2 <= (x + width) && x2 >= x)
		{
			if(y - (y2 + height2) < 10 && y - (y2 + height2) > -10)
			{
				collides = true;
			}
		}
		return collides;
	}
//Swings
	public boolean swingRight(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(y <= (y2 + height2) && y >= y2)
		{
			if(x2 - (x + width) < range && x2 - (x + width) > -range)
			{
				collides = true;
			}
		}
		if(y2 <= (y + height) && y2 >= y)
		{
			if(x2 - (x + width) < range && x2 - (x + width) > -range)
			{
				collides = true;
			}
		}
		return collides;
	}
	public boolean swingLeft(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(y <= (y2 + height2) && y >= y2)
		{
			if(x - (x2 + width2) < range && x - (x2 + width2) > -range)
			{
				collides = true;
			}
		}
		if(y2 <= (y + height) && y2 >= y)
		{
			if(x - (x2 + width2) < range && x - (x2 + width2) > -range)
			{
				collides = true;
			}
		}
		return collides;
	}
	public boolean swingDown(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(x <= (x2 + width2) && x >= x2)
		{
			if((y + height) - y2 < range && (y + height) - y2 > -range)
			{
				collides = true;
			}
		}
		if(x2 <= (x + width) && x2 >= x)
		{
			if((y + height) - y2 < range && (y + height) - y2 > -range)
			{
				collides = true;
			}
		}
		return collides;
	}
	public boolean swingUp(Sprite sprite)
	{
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(x <= (x2 + width2) && x >= x2)
		{
			if(y - (y2 + height2) < range && y - (y2 + height2) > -range)
			{
				collides = true;
			}
		}
		if(x2 <= (x + width) && x2 >= x)
		{
			if(y - (y2 + height2) < range && y - (y2 + height2) > -range)
			{
				collides = true;
			}
		}
		return collides;
	}
//Swings
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
		((Graphics2D)g).draw(collider);
		//System.out.println("x = " + x + " y = " + y);
	}
}


