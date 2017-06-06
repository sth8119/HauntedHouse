import java.util.Random;
import java.util.ArrayList;
public class MapGenerator
{
    int columns = 10;
    int rows = 10;
    int rightx, righty, leftx, lefty, upx, upy, downx, downy;
    int island1X, island1Y, island2X, island2Y;
    int[][] map;
    int[][] finalMap;
    int[] connectedList;
    boolean validNumbers;
    boolean canCreate;
    ArrayList<ArrayList> rightWalls, leftWalls, upWalls, downWalls;
    Random r;
    public MapGenerator(int columns, int rows)
    {
        this.rows = rows;
        this.columns = columns;
        finalMap = new int[columns + 2][rows + 2];
        canCreate = true;
    }
    public boolean create()
    {

       //Connected Walls
		r = new Random();
        map = new int[columns][rows];
        connectedList = new int[4];
        do
        {
			validNumbers = true;
        	connectedList[0] = r.nextInt(40);
        	connectedList[1] = r.nextInt(40);
        	connectedList[2] = r.nextInt(40);
        	connectedList[3] = r.nextInt(40);
			for(int i = 0; i < connectedList.length; i++)
			{
				if(!isValid(connectedList[i]))
				{
					validNumbers = false;
				}
			}
			if(!isValid())
			{
				validNumbers = false;
			}
		} while(!validNumbers);
        for(int y = 0; y < map[0].length; y++)
        {
            for(int x = 0; x < map.length; x++)
            {
                map[x][y] = 0;
            }
        }
        //System.out.println(r.nextInt(40));
     /*   System.out.println(connectedList[0]);
        System.out.println(connectedList[1]);
        System.out.println(connectedList[2]);
        System.out.println(connectedList[3]);
        System.out.println("Ran"); */
        for(int key = 0; key < connectedList.length; key++)
        {
            int length = r.nextInt(7) + 1;
            if(connectedList[key] >= 1 && connectedList[key] <= 8)//Down
            {
                for(int down = 0; down < length; down++)
                {
					downx = getLastDigit(connectedList[key]);
					downy = down;
                    map[downx][downy] += 1;
                    map[downx + 1][downy] += 1;
                }
                if(isOverlapping("down", downx, downy))
                {
					//create();
					canCreate = false;
				}
            }
            if(connectedList[key] >= 11 && connectedList[key] <= 18)//Left
            {
                for(int left = 0; left < length; left++)
                {
					leftx = map[0].length - 1 - left;
					lefty = getLastDigit(connectedList[key]);
                    map[leftx][lefty] += 1;
                    map[leftx][lefty + 1] += 1;
                }
    			if(isOverlapping("left", leftx, lefty))
    			{
					//create();
					canCreate = false;
				}
            }
            if(connectedList[key] >= 21 && connectedList[key] <= 28)//Up
            {
                for(int up = 0; up < length; up++)
                {
					upx = map.length - 1 - getLastDigit(connectedList[key]);
					upy = map.length - 1 - up;
					map[upx][upy] += 1;
                    map[upx + 1][upy] += 1;
                }
                if(isOverlapping("up", upx, upy))
                {
					//create();
					canCreate = false;
				}
            }
            if(connectedList[key] >= 31 && connectedList[key] <= 38)//Right
            {
                for(int right = 0; right < length; right++)
                {
					rightx = right;
					righty = map[0].length - 1 - (getLastDigit(connectedList[key]));
                    map[rightx][righty] += 1;
                    map[rightx][righty + 1] += 1;
                }
                if(isOverlapping("right", rightx, righty))
                {
					//create();
					canCreate = false;
				}
            }
        }
        //Connected Walls

        //Islands
        island1X = r.nextInt(7) + 1;
        island1Y = r.nextInt(7) + 1;
        island2X = r.nextInt(7) + 1;
        island2Y = r.nextInt(7) + 1;
        if(map[island1X][island1Y] != 0 || map[island2X][island2Y] != 0)//Middle
        {
			canCreate = false;
		}
        if(map[island1X + 1][island1Y] != 0 || map[island2X + 1][island2Y] != 0)//Right 1
        {
			canCreate = false;
		}
        if(map[island1X][island1Y + 1] != 0 || map[island2X][island2Y + 1] != 0)//Down 1
        {
			canCreate = false;
		}
        if(map[island1X + 1][island1Y + 1] != 0 || map[island2X + 1][island2Y + 1] != 0)//Right 1 Down 1
        {
			canCreate = false;
		}
        if(map[island1X - 1][island1Y] != 0 || map[island2X - 1][island2Y] != 0) //Left 1
        {
			canCreate = false;
		}
        if(map[island1X + 2][island1Y] != 0 || map[island2X + 2][island2Y] != 0) //Right 2
        {
			canCreate = false;
		}
        if(map[island1X - 1][island1Y + 1] != 0 || map[island2X - 1][island2Y + 1] != 0)//Left 1 Down 1
        {
			canCreate = false;
		}
        if(map[island1X + 2][island1Y + 1] != 0 || map[island2X + 2][island2Y + 1] != 0)//Right 2 Down 1
        {
			canCreate = false;
		}
        if(map[island1X][island1Y - 1] != 0 || map[island2X][island2Y - 1] != 0)//Up 1
        {
			canCreate = false;
		}
        if(map[island1X + 1][island1Y - 1] != 0 || map[island2X + 1][island2Y + 1] != 0)//Right 1 Up 1
        {
			canCreate = false;
		}
        if(map[island1X][island1Y + 2] != 0 || map[island2X][island2Y + 2] != 0)//Down 2
        {
			canCreate = false;
		}
        if(map[island1X + 1][island1Y + 2] != 0 || map[island2X + 1][island2Y + 2] != 0)//Right 1 Down 2
        {
			canCreate = false;
		}
        if(map[island1X - 1][island1Y - 1] != 0 || map[island2X - 1][island2Y - 1] != 0)//Left 1 Up 1
        {
			canCreate = false;
		}
        if(map[island1X + 2][island1Y - 1] != 0 || map[island2X + 2][island2Y - 1] != 0)//Right 2 Up 1
        {
			canCreate = false;
		}
        if(map[island1X - 1][island1Y + 2] != 0 || map[island2X - 1][island2Y + 2] != 0)//Left 1 Down 2
        {
			canCreate = false;
		}
        if(map[island1X + 2][island1Y + 2] != 0 || map[island2X + 2][island2Y + 2] != 0)//Right 2 Down 2
        {
			canCreate = false;
		}
		//Make Islands
		map[island1X][island1Y] += 1;
		map[island1X + 1][island1Y] += 1;
		map[island1X][island1Y + 1] += 1;
		map[island1X + 1][island1Y + 1] += 1;
		map[island2X][island2Y] += 1;
		map[island2X + 1][island2Y] += 1;
		map[island2X][island2Y + 1] += 1;
		map[island2X + 1][island2Y + 1] += 1;
        //Islands
		//Check full overlap
		for(int g = 0; g < map[0].length; g++)
		{
			for(int h = 0; h < map.length; h++)
			{
				if(map[h][g] > 1)
				{
					canCreate = false;
				}
			}
		}
        return canCreate;
    }
    public int getLastDigit(int number)
    {
        int hold = number;
        while(hold > 10)
        {
            hold -= 10;
        }
        return hold;
    }
    //public int[][] getMap()
    //{
     //   return map;
    //}
    public boolean isOverlapping(String direction, int x, int y)
    {
		boolean overlapping = false;
		if(direction.equalsIgnoreCase("right"))
		{
			try
			{
				if(map[x][y - 1] != 0 || map[x][y + 2] != 0)
				{
					overlapping = true;
				}
				if(map[x + 1][y] != 0 || map[x + 1][y + 1] != 0)
				{
					overlapping = true;
				}
				if(map[x + 1][y - 1] != 0 || map[x + 1][y + 2] != 0)
				{
					overlapping = true;
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				//e.printStackTrace();
				overlapping = true;
			}
		}
		if(direction.equalsIgnoreCase("left"))
		{
			try
			{
				if(map[x][y - 1] != 0 || map[x][y + 2] != 0)
				{
					overlapping = true;
				}
				if(map[x - 1][y] != 0 || map[x - 1][y + 1] != 0)
				{
					overlapping = true;
				}
				if(map[x - 1][y - 1] != 0 || map[x - 1][y + 2] != 0)
				{
					overlapping = true;
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
			//	e.printStackTrace();
				overlapping = true;
			}
		}
		if(direction.equalsIgnoreCase("up"))
		{
			try
			{
				if(map[x][y - 1] != 1 || map[x + 1][y - 1] != 0)
				{
					overlapping = true;
				}
				if(map[x - 1][y] != 1 || map[x + 2][y] != 0)
				{
					overlapping = true;
				}
				if(map[x - 1][y - 1] != 0 || map[x + 2][y - 1] != 0)
				{
					overlapping = true;
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
			//	e.printStackTrace();
				overlapping = true;
			}
		}
		if(direction.equalsIgnoreCase("down"))
		{
			try
			{
				if(map[x][y + 1] != 0 || map[x + 1][y + 1] != 0)
				{
					overlapping = true;
				}
				if(map[x - 1][y] != 0 || map[x + 2][y] != 0)
				{
					overlapping = true;
				}
				if(map[x - 1][y + 1] != 0 || map[x + 2][y + 1] != 0)
				{
					overlapping = true;
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
			//	e.printStackTrace();
				overlapping = true;
			}
		}
		for(int b = 0; b < map[0].length; b++)
		{
			for(int a = 0; a < map.length; a++)
			{
				if(map[a][b] > 1)
				{
					overlapping = true;
				}
			}
		}
		return overlapping;
	}
    public boolean isValid(int n)
    {
        if(n % 10 == 0 || n % 10 == 1 || n % 10 == 8 || n % 10 == 9)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean isValid()
    {
		boolean valid = true;
		if(Math.abs(connectedList[0] - connectedList[1]) < 3)
		{
			valid = false;
		}
		if(Math.abs(connectedList[0] - connectedList[2]) < 3)
		{
			valid = false;
		}
		if(Math.abs(connectedList[0] - connectedList[3]) < 3)
		{
			valid = false;
		}
		if(Math.abs(connectedList[1] - connectedList[2]) < 3)
		{
			valid = false;
		}
		if(Math.abs(connectedList[1] - connectedList[3]) < 3)
		{
			valid = false;
		}
		if(Math.abs(connectedList[2] - connectedList[3]) < 3)
		{
			valid = false;
		}
		return valid;
	}
	public void buildFinalMap()//Makes 10x10 array into 12x12 array with borders
	{
		for(int x1 = 0; x1 < finalMap.length; x1++)
		{
			finalMap[x1][0] = 1;
		}
		for(int y1 = 0; y1 < finalMap[0].length; y1++)
		{
			finalMap[0][y1] = 1;
		}
		for(int x2 = 0; x2 < finalMap.length; x2++)
		{
			finalMap[x2][finalMap[0].length - 1] = 1;
		}
		for(int y2 = 0; y2 < finalMap[0].length; y2++)
		{
			finalMap[finalMap.length - 1][y2] = 1;
		}
		for(int y1 = 0; y1 < map[0].length; y1++)
		{
			for(int x1 = 0; x1 < map.length; x1++)
			{
				System.out.println(map[x1][y1]);
				finalMap[x1 + 1][y1 + 1] = map[x1][y1];
			}
		}
	}
    public void print()
    {
        for(int y = 0; y < finalMap[0].length; y++)
        {
            for(int x = 0; x < finalMap.length; x++)
            {
                System.out.print(finalMap[x][y] + " ");
            }
            System.out.println();
        }
    }
}
