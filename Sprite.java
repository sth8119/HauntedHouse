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
	Rectangle collider;
	ArrayList<BufferedImage> character;
	BufferedImage current;

	public Sprite(ArrayList<BufferedImage> character, int x, int y, int width, int height)
	{
		this.character = character;
		current = character.get(0);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collider = new Rectangle(x, y, width, height);
	}
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
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
	public void draw(Graphics g)
	{
		g.drawImage(current, x, y, null);
		((Graphics2D)g).draw(collider);
	}
}


