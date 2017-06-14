import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.awt.Toolkit;

public class HomeScreen extends JApplet implements ActionListener
{
	Image bats, startpic, background, restartpic, backdrop, backHome;
	JButton start, restart, homescreen;
	JPanel hs, ds;
	Toolkit tk;

	public void init()
	{
		hs = new drawingPanelHome();
		ds = new drawingPanelDeath();

		setContentPane(hs);
		tk = Toolkit.getDefaultToolkit();

		setSize(1265, 910);

		setLayout(null);

		//HOMESCREEN
		startpic = tk.getImage("startButton.png");
		background = tk.getImage("Homescreen.png");
		bats = tk.getImage("bats.GIF");

		start = new JButton(new ImageIcon(startpic));
		start.setBounds(494,277,245,400);

		hs.add(start);

		start.addActionListener(this);

		//DEATHSCREEN
		backdrop = tk.getImage("deathPaneltrial.png");
		restartpic = tk.getImage("restartButton.png");
		backHome = tk.getImage("homescreenButton.png");

		restart = new JButton(new ImageIcon(restartpic));
		restart.setBounds(400,430,400,100);

		homescreen = new JButton(new ImageIcon(backHome));
		homescreen.setBounds(400,590,400,100);

		ds.add(restart);
		ds.add(homescreen);

		restart.addActionListener(this);
		homescreen.addActionListener(this);
	}
	public class drawingPanelHome extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(background, 0, 0, this);
			g.drawImage(bats, 0, 120, this);
		}
	}
	public class drawingPanelDeath extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(backdrop,0, 160, this);
		}
	}
	public void actionPerformed (ActionEvent e)
	{
		if(e.getSource() == start)
		{
			hs.setVisible(false);
			ds.setVisible(false);
		}
		if(e.getSource() == restart)
		{

			hs.setVisible(false);
			ds.setVisible(false);
		}
		if(e.getSource() == homescreen)
		{
			add(ds);
			hs.setVisible(true);
			ds.setVisible(false);
		}

	}
}