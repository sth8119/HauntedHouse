public class LoopDetector
{
	int[][] array;
	boolean done;
	int n, initialRow, initialColumn;
	public LoopDetector(int[][] array)
	{
		this.array = array;
		n = 0;
	}
	public boolean isLooped(int column, int row)
	{
		if(n == 0)
		{
			initialRow = row;
			initialColumn = column;
		}
		n++;
		if(canMove(column, row))
		{
			array[column][row] = 5;
			if(column == initialRow && row == initialColumn && n > 3)
			{
				done = true;
			}
			else
			{
				if(n > 4)
				{
					array[0][0] = 1;
				}
				done = isLooped(column + 1, row);
				if(!done)
				{
					done = isLooped(column, row + 1);
				}
				if(!done)
				{
					done = isLooped(column - 1, row);
				}
				if(!done)
				{
					done = isLooped(column, row - 1);
				}
				///*

				if(!done)
				{
					done = isLooped(column + 1, row + 1);
				}
				if(!done)
				{
					done = isLooped(column - 1, row + 1);
				}
				if(!done)
				{
					done = isLooped(column + 1, row - 1);
				}
				if(!done)
				{
					done = isLooped(column - 1, row - 1);
				}
			}
			if(done)
			{
				array[column][row] = 7;
			}
		}
		return done;
	}
	public boolean canMove(int column, int row)
	{
		boolean result = false;
	//	System.out.println("Col = " + column + " | array[0].length = " + array[0].length);
	//	System.out.println("Row = " + row + " | array.length = " + array.length);
		if(column >= 0 && column < array[0].length && row >= 0 && row < array.length)
		{
			//System.out.println(array[column][row]);
			if(array[column][row] == 1)
			{
				result = true;
				//System.out.println("Result is true");
			}
		//	if(array[column][row] == 5 && column == 0 && row == 0)
		//	{
		//		result = true;
		//	}
		}
		return result;
	}
	public String toString()
	{
		String result = "\n";
		for(int column = 0; column < array.length; column++)
		{
			for(int row = 0; row < array[column].length; row++)
			{
				result += array[column][row] + " ";
			}
			result += "\n";
		}
		return result;
	}
}