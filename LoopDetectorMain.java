public class LoopDetectorMain
{
	public static void main(String[] args)
	{
		int[][] array =	   {{1,1,1,1,1,0,1,1,1,0},
							{1,0,0,0,0,0,0,0,0,1},
							{1,0,0,0,0,1,1,1,1,1},
							{1,0,0,0,1,0,0,0,0,1},
							{1,0,0,0,0,1,0,0,0,0},
							{1,0,1,1,1,0,1,0,0,0},
							{1,1,1,0,1,0,1,0,0,0},
							{0,0,1,0,1,0,1,0,0,0},
							{0,0,0,1,1,1,1,0,0,0},
							{0,0,0,0,0,0,0,0,0,0}};
		LoopDetector t = new LoopDetector(array);
		int[][] tempArray = array;
		int counter = 0;
		boolean isLooped = false;
		for(int x = 0; x < tempArray[0].length; x++)
		{
			for(int y = 0; y < tempArray.length; y++)
			{ //2x for to check for loops at every x,y
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
				if(counter > 4)
				{
					isLooped = true;
					System.out.println("Loop detected");
				}
				counter = 0;
				tempArray = array;
			}
		}
		System.out.println(t);
		System.out.println(isLooped);
		/*if(t.isLooped(0,0))
		{
			for(int x = 0; x < array[0].length; x++)
			{
				for(int y = 0; y < array.length; y++)
				{
					if(array[x][y] == 7)
					{
						counter++;
					}
				}
			}
		}
		if(counter > 4)
		{
			System.out.println("Looped");
		}
		else
		{
			System.out.println("Not looped");
		} */ //For when only checking 1 x,y
	}
}