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

	public void init()
	{
		setFocusable(true);
		addKeyListener(this);
		int delay = 16;
		t = new Timer(delay,this);
		t.start();
		possible = new ArrayList<String>();
		pressed = new ArrayList<String>();
		possible.add("up");
		possible.add("left");
		possible.add("right");


	}
	public void FacingDirection()
	{

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == t)
		{
			for(int i = 0; i < pressed.size(); i++)
			{
				for(int x = 0; x < possible.size(); x++)
				{
					if(pressed.getIndex(i).equalsIgnoreCase(possible.getIndex(x)))
					{
						System.out.println(pressed.getIndex(i));
					}
				}
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

	}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}
}