public class LoopDetector
{
	int[][] array;
	boolean done;
	int n;
	public LoopDetector(int[][] array)
	{
		this.array = new int[array[0].length][array.length];
		for(int x = 0; x < array[0].length; x++)
		{
			for(int y = 0; y < array.length; y++)
			{
				this.array[x][y] = array[x][y];
			}
		}
		n = 0;
	}
	public void refresh(int[][] array)
	{
		this.array = new int[array[0].length][array.length];
		for(int x = 0; x < array[0].length; x++)
		{
			for(int y = 0; y < array.length; y++)
			{
				this.array[x][y] = array[x][y];
			}
		}
		n = 0;
	}
	public boolean isLooped(int column, int row, int initialColumn, int initialRow)
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
				if(n > 2)
				{
					array[initialColumn][initialRow] = 1;
					//Fix
				}
				done = isLooped(column + 1, row, initialColumn, initialRow);
				if(!done)
				{
					done = isLooped(column, row + 1, initialColumn, initialRow);
				}
				if(!done)
				{
					done = isLooped(column - 1, row, initialColumn, initialRow);
				}
				if(!done)
				{
					done = isLooped(column, row - 1, initialColumn, initialRow);
				}
				///*

				if(!done)
				{
					done = isLooped(column + 1, row + 1, initialColumn, initialRow);
				}
				if(!done)
				{
					done = isLooped(column - 1, row + 1, initialColumn, initialRow);
				}
				if(!done)
				{
					done = isLooped(column + 1, row - 1, initialColumn, initialRow);
				}
				if(!done)
				{
					done = isLooped(column - 1, row - 1, initialColumn, initialRow);
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
				System.out.println("Row = " + row);
				System.out.println("Column = " + column);
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