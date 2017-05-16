import java.applet.Applet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.ArrayList;

public class TesterClass extends JApplet implements KeyListener, ActionListener
{
	int facing;
	Timer t;
	ArrayList<String> pressed;
	ArrayList<String> possible;
	ArrayList<String> current;

	public void init()
	{
		setFocusable(true);
		addKeyListener(this);
		int delay = 16;
		t = new Timer(delay,this);
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
	}
	public String FacingDirection()
	{
		int d = pressed.size();
		String direction = pressed.get(d);
		return direction;
	}
	public void actionPerformed(ActionEvent e)
	{
		//adds the direction to an arraylist and outputs it
		if(e.getSource() == t)
		{
			for(int i = 0; i < pressed.size(); i++)
			{
				for(int x = 0; x < possible.size(); x++)
				{
					if(pressed.get(i).equalsIgnoreCase(possible.get(x)))
					{
						System.out.println(pressed.get(i));
					}
				}
			}
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


	}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}

}