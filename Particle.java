import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.applet.*;
import java.util.Random;
public class Particle
{
	//Resolution = 1280x1024
	final int WIDTH, HEIGHT;
	int x, y, randomx, randomy, xpos, ypos;
	double angle, xdelta, ydelta, xvel, yvel, xraw, yraw;
	Random r;
	public Particle(int x, int y)
	{
		WIDTH = 10;
		HEIGHT = 10;
		this.x = x;
		this.y = y;
		this.xraw = x;
		this.yraw = y;
		r = new Random();
		randomx = r.nextInt(1280);
		randomy = r.nextInt(1024);
		xdelta = x - randomx;
		ydelta = y - randomy;
		angle = Math.atan(ydelta / xdelta);
		if(xdelta % 2 == 0)
		{
			xvel = Math.cos(angle);
			yvel = Math.sin(angle);
		}
		else
		{
			xvel = -1 * Math.cos(angle);
			yvel = -1 * Math.sin(angle);
		}
		//xvel *= 2;
		//yvel *= 2;
		xpos = x;
		ypos = y;
	}
	public void tick()
	{
		xraw += xvel;
		yraw += yvel;
		xpos = (int)Math.round(xraw);
		ypos = (int)Math.round(yraw);
	}
	public void draw(Graphics g)
	{
		g.fillOval(xpos, ypos, WIDTH, HEIGHT);
	}
}