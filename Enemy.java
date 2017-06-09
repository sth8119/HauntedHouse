import java.awt.image.BufferedImage;
import java.util.Random;
public class Enemy extends Sprite
{
	int randomX, randomY;
	double randomAngle;
	Random r;
	public Enemy(BufferedImage image, int x, int y)
	{
		super(image, x, y);
		r = new Random();
		randomAngle = Math.atan(r.nextInt(500) / r.nextInt(500));
		if(r.nextInt(2) == 1)
		{
			randomX = (int)(Math.cos(randomAngle) * 150);
		}
		else
		{
			randomX = (int)(-1 * Math.cos(randomAngle) * 150);
		}
		if(r.nextInt(2) == 1)
		{
			randomY = (int)(Math.sin(randomAngle) * 150);
		}
		else
		{
			randomY = (int)(-1 * Math.cos(randomAngle) * 150);
		}
		System.out.println("Randomx = " + randomX);
	}
	public int getRandomX()
	{
		return randomX;
	}
	public int getRandomY()
	{
		return randomY;
	}
}