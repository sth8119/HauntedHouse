public class RogueCharacter extends RogueSprite
{
	int Speed;
	int Health;
	int ASpeed;
	int MaxHealth;
	public RogueCharacter(int startSpeed, int startASpeed, int startHealth)
	{
		Speed = startSpeed;
		ASpeed = startASpeed;
		Health = startHealth;
	}
	public void setSpeed(int newSpeed)
	{
		Speed = newSpeed;
	}
	public void setHealth(int newHealth)
	{
		Health = newHealth;
	}
	public void setASpeed(int newASpeed)
	{
		ASpeed = newASpeed;
	}
	public void setMaxHealth(int newMaxHealth)
	{
		MaxHealth = newMaxHealth;
	}
	public int getMaxHealth()
	{
		return MaxHealth;
	}
	public int getSpeed()
	{
		return Speed;
	}
	public int getHealth()
	{
		return Health;
	}
	public int getASpeed()
	{
		return ASpeed;
	}
	public boolean isAlive()
	{
		boolean isAlive = true;
		if(Health <= 0)
		{
			isAlive = false;
		}
		return isAlive;
	}
}