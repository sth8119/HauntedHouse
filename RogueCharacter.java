public class RogueCharacter extends RogueSprite
{
	int speedMultiplier;
	int SpeedX;
	int SpeedY;
	int Health;
	int ASpeed;
	int MaxHealth;
	public RogueCharacter(int startHealth)
	{
		Health = startHealth;
	}
	public void updatePos()
	{
		setPosition(getPositionX()+SpeedX,getPositionY()+SpeedY);
	}
	public void setSpeed(int newSpeedX, int newSpeedY)
	{
		SpeedX = newSpeedX;
		SpeedY = newSpeedY;
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
	public int getSpeedX()
	{
		return SpeedX;
	}
	public int getSpeedY()
	{
		return SpeedY;
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