public class LoopDetectorMain
{
	public static void main(String[] args)
	{
		int[][] array =	   		   {{0,0,1,0,0,0,0,0,0,0},
									{1,0,1,0,0,0,0,0,0,0},
									{0,1,0,0,0,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,1,0,1,0,0},
									{0,0,0,0,0,1,1,1,0,0},
									{0,0,0,0,0,0,0,0,0,0}};
		LoopDetector t;
		int counter;
		boolean isLooped = false;
		int[][] tempArray;
		for(int x = 0; x < array[0].length; x++)
		{
			for(int y = 0; y < array.length; y++)
			{ //2x for to check for loops at every x,y
				counter = 0;
				t = new LoopDetector(array, x, y);
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
		System.out.println(isLooped);
	}
}