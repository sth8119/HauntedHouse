import java.awt.*;
import java.awt.image.BufferedImage;
public class Character extends Sprite
{
	int speed;
	int health;
	int attackSpeed;
	int maxHealth;
	int range;
	public Character(BufferedImage image, int x, int y, int speed, int attackSpeed, int maxHealth, int range)
	{
		super(image, x, y);
		this.speed = speed;
		this.attackSpeed = attackSpeed;
		this.maxHealth = maxHealth;
		this.range = range;
		health = maxHealth;
	}
	public boolean attackCollision(Sprite sprite, int direction)
	{
		//0 = Right, 1 = Left, 2 = Up, 3 = Down
		boolean collides = false;
		int x2 = (int)sprite.getCollider().getX();
		int y2 = (int)sprite.getCollider().getY();
		int width2 = (int)sprite.getCollider().getWidth();
		int height2 = (int)sprite.getCollider().getHeight();
		if(direction == 0)
		{
			if(y + (height / 2) > y2 && y + (height / 2) < (y2 + height2))
			{
				if(x2 - (x + width) < range)
				{
					collides = true;
				}
			}
		}
		if(direction == 1)
		{
			if(y + (height / 2) > y2 && y + (height / 2) < (y2 + height2))
			{
				if(x - (x2 + width2) < range)
				{
					collides = true;
				}
			}
		}
		if(direction == 2)
		{
			if(x + (width / 2) > x2 && x + (width / 2) < (x2 + width2))
			{
				if(y - (y2 + height2) < range)
				{
					collides = true;
				}
			}
		}
		if(direction == 3)
		{
			if(x + (width / 2) > x2 && x + (width / 2) < (x2 + width2))
			{
				if(y2 - (y + height) < range)
				{
					collides = true;
				}
			}
		}
		return collides;
	}
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	public void setHealth(int health)
	{
		this.health = health;
	}
	public void setAttackSpeed(int attackSpeed)
	{
		this.attackSpeed = attackSpeed;
	}
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}
	public int getMaxHealth()
	{
		return maxHealth;
	}
	public int getSpeed()
	{
		return speed;
	}
	public int getHealth()
	{
		return health;
	}
	public int getASpeed()
	{
		return attackSpeed;
	}
	public boolean isAlive()
	{
		boolean isAlive = true;
		if(health <= 0)
		{
			isAlive = false;
		}
		return isAlive;
	}
}