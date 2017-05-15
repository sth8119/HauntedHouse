

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;


public class RogueDriver extends JApplet
{
	RogueSprite rog;

 	public void init()
	{
		 rog = new RogueSprite();
  	setContentPane(new DrawingPanel());
  	setLayout(null);


  }

   public class DrawingPanel extends JPanel
   {
       public void paintComponent(Graphics g)
       {
   		 super.paintComponent(g);
    	rog.draw(g);


       }
   }
}