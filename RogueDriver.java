

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.ArrayList;
import java.util.Random;


public class RogueDriver extends JApplet
{
	Random r;
	RogueSprite rog;
	ArrayList<RogueSprite> Bricks;
	int x,y ,w,h;


 	public void init()
	{
		r = new Random();
		Bricks = new ArrayList<RogueSprite>();
		setContentPane(new DrawingPanel());
		for(int k = 0; k <50; k++)
		{
			x = r.nextInt(1000);
			y = r.nextInt(1000);
			w = 100;
			h = 26;
			Bricks.add(new RogueSprite(x,y,w,h));
		}

			setLayout(null);
  }

   public class DrawingPanel extends JPanel
   {
       public void paintComponent(Graphics g)
       {
   		 super.paintComponent(g);

   		 for(int k = 0; k <50; k++)
		 {
			 Bricks.get(k).draw(g);
		 }
       }
   }
}