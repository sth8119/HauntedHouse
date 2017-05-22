import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.applet.*;
import java.util.Random;
public class ParticleTest extends JApplet
{
	ArrayList<Particle> particleList;
	boolean fire;

	public void init()
	{
		fire = false;
		particleList = new ArrayList<Particle>(1);
		fire();
		setLayout(null);
		setContentPane(new DrawingPanel());
	}
	public void fire()
	{
		particleList.add(new Particle(10, 10));
		fire = true;
		repaint();
	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(fire)
			{
				particleList.get(0).tick();
			}
			particleList.get(0).draw(g);
		}
	}
}