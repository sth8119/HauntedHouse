import java.util.Random;
public class MapGenerator
{
	Random r;
	int[][] map, tempArray;
	int counter;
	boolean isLooped;
	LoopDetector t;
	public MapGenerator()
	{
		r = new Random(); //Make 10x10 array, then check looped, then make 12x12 array with walls
		counter = 0;
		isLooped = false;
		map = new int[10][10];
		tempArray = new int[10][10];
	}
	public void create()
	{
		for(int a = 0; a < map.length; a++)
		{
			map[a][0] = 1;
		}
		for(int b = 0; b < map.length; b++)
		{
			map[0][b] = 1;
		}
		for(int c = 0; c < map.length; c++)
		{
			map[c][map.length - 1] = 1;
		}
		for(int d = 0; d < map.length; d++)
		{
			map[map.length - 1][d] = 1;
		}
		for(int y = 1; y < map.length - 1; y++)
		{
			for(int x = 1; x < map.length - 1; x++)
			{
				map[x][y] = r.nextInt(2);
			}
		}
		for(int y = 0; y < map.length; y++)
		{
			for(int x = 0; x < map.length; x++)
			{
				System.out.print(map[x][y] + " ");
			}
			System.out.println();
		}
		for(int x = 0; x < map[0].length; x++)
		{
			for(int y = 0; y < map.length; y++)
			{ //2x for to check for loops at every x,y
				counter = 0;
				t = new LoopDetector(map, x, y);
				tempArray = t.getArray();
				if(t.isLooped(x, y)) //Checks to see if 7s are greater than tolerance
				{
					for(int a = 0; a < tempArray[0].length; a++)
					{
						for(int b = 0; b < tempArray.length; b++)
						{
							if(tempArray[a][b] == 7)
							{
								counter++;
							}
						}
					}
				}
				if(counter > 3)
				{
					isLooped = true;
					System.out.println("Loop detected");
					System.out.println(t);
				}
			}
		}
		if(isLooped)
		{
			create();
		}
	}
	public int[][] getMap()
	{
		return map;
	}
}