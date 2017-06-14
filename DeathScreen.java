import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
public class DeathScreen extends JPanel
{
	Label l;
	public void init()
	{
		l = new Label("Thank You for playing Haunted House");
		l.setBounds(500, 500, 500, 200);
		System.out.println("DeathScreen");
		add(l);
		setLayout(null);
	}
}