import java.util.Random;
public class MapGenerator
{
	Random r;
	int[][] map;
	int counter;
	public MapGenerator(int cols, int rows)
	{
		r = new Random(); //Make 10x10 array, then check looped, then make 12x12 array with walls
		counter = 0;
		map = new int[rows][cols];
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
		System.out.println("Created");
	}
	public int[][] getMap()
	{
		return map;
	}
}