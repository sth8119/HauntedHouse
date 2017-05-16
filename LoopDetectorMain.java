public class LoopDetectorMain
{
	public static void main(String[] args)
	{
	/*	int[][] array =	   {{1,1,1,1,1,0,1,1,1,0},
							{1,0,0,0,0,0,0,0,0,1},
							{1,0,0,0,0,1,1,1,1,1},
							{1,0,0,0,1,0,0,0,0,1},
							{1,0,0,0,0,1,0,0,0,0},
							{1,0,1,1,1,0,1,0,0,0},
							{1,1,1,0,1,0,1,0,0,0},
							{0,0,1,0,1,0,1,0,0,0},
							{0,0,0,1,1,1,1,0,0,0},
							{0,0,0,0,0,0,0,0,0,0}};*/
		int[][] array =	   		   {{1,1,1,0,0,0,0,0,0,0},
									{1,0,1,0,0,0,0,0,0,0},
									{1,1,1,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,1,1,1,0,0},
									{0,0,0,0,0,1,0,1,0,0},
									{0,0,0,0,0,1,1,1,0,0},
									{0,0,0,0,0,0,0,0,0,0}};
		LoopDetector t = new LoopDetector(array);
		int counter = 0;
		boolean isLooped = false;
		System.out.println(t.isLooped(0,0));
		System.out.println(t);
		/*for(int x = 0; x < array[0].length; x++)
		{
			for(int y = 0; y < array.length; y++)
			{ //2x for to check for loops at every x,y
				counter = 0;
				//System.arraycopy(array, 0, tempArray, 0, 10);
				t.refresh(array);
				if(t.isLooped(x, y)) //Checks to see if 7s are greater than tolerance
				{
					for(int a = 0; a < array[0].length; a++)
					{
						for(int b = 0; b < array.length; b++)
						{
							if(array[a][b] == 7)
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
				System.out.println(t);
			}
		}
		System.out.println(isLooped);*/
	}
}