public class ProjectileMotion
{
	int speedMultiplier;
	double angle, xDelta, yDelta, xVel, yVel, xRaw, yRaw;
	public ProjectileMotion(int speedMultiplier)
	{
		this.speedMultiplier = speedMultiplier;
	}
	public void calculate(int enemyX, int enemyY, int playerX, int playerY)
	{
		xRaw = enemyX;
		yRaw = enemyY;
		xDelta = enemyX - playerX;
		yDelta = enemyY - playerY;
		angle = Math.atan(yDelta / xDelta);
		if(xDelta < 0)
		{
			xVel = Math.cos(angle);
			yVel = Math.sin(angle);
		}
		else
		{
			xVel = -1 * Math.cos(angle);
			yVel = -1 * Math.sin(angle);
		}
		xVel *= speedMultiplier;
		yVel *= speedMultiplier;
		xRaw += xVel;
		yRaw += yVel;
	}
	public int getEnemyX()
	{
		return (int)Math.round(xRaw);
	}
	public int getEnemyY()
	{
		return (int)Math.round(yRaw);
	}
	public void setSpeedMultiplier(int speedMultiplier)
	{
		this.speedMultiplier = speedMultiplier;
	}
}