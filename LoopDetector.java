public class LoopDetector
{
	int[][] array;
	boolean done;
	int n, initialColumn, initialRow;
	public LoopDetector(int[][] array, int initialColumn, int initialRow)
	{
		this.array = new int[array[0].length][array.length];
		this.initialColumn = initialColumn;
		this.initialRow = initialRow;
		for(int x = 0; x < array[0].length; x++)
		{
			for(int y = 0; y < array.length; y++)
			{
				this.array[x][y] = array[x][y];
			}
		}
		n = 0;
	}
	public boolean isLooped(int column, int row)
	{
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
				if(n > 3) //Sets the initial xy to an opening after 3 loops
				{
					array[initialColumn][initialRow] = 1;
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
				//Diagonal checks

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
	public int[][] getArray()
	{
		return array;
	}
	public boolean canMove(int column, int row)
	{
		boolean result = false;
	//	System.out.println("Col = " + column + " | array[0].length = " + array[0].length);
	//	System.out.println("Row = " + row + " | array.length = " + array.length);
		if(column >= 0 && column < array[0].length && row >= 0 && row < array.length)
		{
			if(array[column][row] == 1)
			{
				//System.out.println("Row = " + row);
				//System.out.println("Column = " + column);
				result = true;
			}
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