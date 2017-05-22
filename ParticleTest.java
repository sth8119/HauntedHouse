import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.applet.*;
import java.util.Random;
import java.awt.event.MouseEvent;
public class ParticleTest extends JApplet implements MouseListener
{
	ArrayList<Particle> particleList;
	boolean fire;
	int particleN;

	public void init()
	{
		fire = false;
		particleList = new ArrayList<Particle>();
		particleN = 200;
		initializeParticles();
		setLayout(null);
		addMouseListener(this);
		setContentPane(new DrawingPanel());
	}
	public void mousePressed(MouseEvent e)
	{
		for(int i = 0; i < particleN; i++)
		{
			particleList.add(new Particle(e.getX(), e.getY()));
		}
		fire = true;
		fire();
	}
	public void initializeParticles()
	{

	}
	public void fire()
	{
		for(int index = 0; index < particleN; index++)
		{
			particleList.get(index).tick();
		}
		repaint();
	}
	public void mouseClicked(MouseEvent e)
	{

	}
	public void mouseEntered(MouseEvent e)
	{

	}
	public void mouseReleased(MouseEvent e)
	{

	}
	public void mouseExited(MouseEvent e)
	{

	}
	public class DrawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(fire)
			{
				fire();
				for(int i = 0; i < particleN; i++)
				{
					particleList.get(i).draw(g);
				}
			}
		}
	}
}