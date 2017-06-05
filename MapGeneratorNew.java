import java.util.Random;
import java.util.ArrayList;
public class MapGenerator
{
    int columns = 10;
    int rows = 10;
    int[][] map;
    int[] connectedList;
    Random r;
    public MapGenerator(int columns, int rows)
    {
        r = new Random();
        map = new int[columns][rows];
        connectedList = new int[4];
    }
    public void create()
    {
       //Top = 0, Right = 1, Bottom = 2, Left = 3
        connectedList[0] = r.nextInt(40);
        connectedList[1] = r.nextInt(40);
        connectedList[2] = r.nextInt(40);
        connectedList[3] = r.nextInt(40);
        for(int y = 0; y < map[0].length; y++)
        {
            for(int x = 0; x < map.length; x++)
            {
                map[x][y] = 1;
            }
        }
        //System.out.println(r.nextInt(40));
        connectedList[0] = r.nextInt(40);
        connectedList[1] = r.nextInt(40);
        connectedList[2] = r.nextInt(40);
        connectedList[3] = r.nextInt(40);
        for(int k = 0; k < connectedList.length; k++)
        {
            if(!isValid(connectedList[k]))
            {
                create();
            }
        }
        if(!isValid(connectedList))
        {
            create();
        }
        for(int y = 0; y < map[0].length; y++)
        {
            for(int x = 0; x < map.length; x++)
            {
                map[x][y] = 1;
            }
        }
        System.out.println(connectedList[0]);
        System.out.println(connectedList[1]);
        System.out.println(connectedList[2]);
        System.out.println(connectedList[3]);
        System.out.println("Ran");
        for(int key = 0; key < connectedList.length; key++)
        {
            int length = r.nextInt(7) + 1;
            if(connectedList[key] >= 1 && connectedList[key] <= 8)
            {
                for(int down = 0; down < length; down++)
                {
                    if(map[connectedList[key]][down] == 1 && map[connectedList[key] + 1][down] == 1)
                    {
                        map[connectedList[key]][down] = 2;
                        map[connectedList[key] + 1][down] = 2;
                    }
                }
            }
            if(connectedList[key] >= 11 && connectedList[key] <= 18)
            {
                for(int left = 0; left < length; left++)
                {
                    if(map[map[0].length - 1 - left][getLastDigit(connectedList[key])] == 1 && map[map[0].length - 1 -
                        left][getLastDigit(connectedList[key]) + 1] == 1)
                    {
                        map[map[0].length - 1 - left][getLastDigit(connectedList[key])] = 3; 
                        map[map[0].length - 1 - left][getLastDigit(connectedList[key]) + 1] = 3;
                    }
                }
            }
            if(connectedList[key] >= 21 && connectedList[key] <= 28)
            {
                for(int up = 0; up < length; up++)
                {
                    if(map[map.length - getLastDigit(connectedList[key])][map.length - 1 - up] == 1 && map[(map.length +
                        1) - getLastDigit(connectedList[key])][map.length - 1 - up] == 1)
                    {
                        map[map.length - getLastDigit(connectedList[key])][map.length - 1 - up] = 4;
                        map[(map.length + 1) - getLastDigit(connectedList[key])][map.length - 1 - up] = 4;
                    }
                }
            }
            if(connectedList[key] >= 31 && connectedList[key] <= 38)
            {
                for(int right = 0; right < length; right++)
                {
                    if(map[right][map[0].length - (getLastDigit(connectedList[key]))] == 1 && map[right][(map[0].length + 1)
                        - (getLastDigit(connectedList[key]))] == 1)
                    {
                        map[right][map[0].length - (getLastDigit(connectedList[key]))] = 5;
                        map[right][(map[0].length + 1) - (getLastDigit(connectedList[key]))] = 5;
                    }
                }
            }
        }
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
    public boolean isValid(int n)
    {
        int cp = n;
        while(cp >= 10)
        {
            cp -= 10;
        }
        System.out.println("Last digit = " + cp);
        if(cp == 0 || cp == 1 || cp == 8 || cp == 9)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean isValid(int[] array)
    {
        boolean isValid = true;
        ArrayList<Integer> r0 = new ArrayList<Integer>();
        ArrayList<Integer> r1 = new ArrayList<Integer>();
        ArrayList<Integer> r2 = new ArrayList<Integer>();
        ArrayList<Integer> r3 = new ArrayList<Integer>();
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] % 10 == 0)
            {
                r0.add(array[i]);
            }
            else if(array[i] % 10 == 1)
            {
                r1.add(array[i]);
            }
            else if(array[i] % 10 == 2)
            {
               r2.add(array[i]); 
            }
            else
            {
               r3.add(array[i]);
            }
        }
        list.add(r0);
        list.add(r1);
        list.add(r2);
        list.add(r3);
        for(int listIndex = 0; listIndex < list.size(); listIndex++)
        {
           if(list.get(listIndex).size() > 2)
           {
                isValid = false;
           }
           if(list.get(listIndex).size() == 2)
           {
                if(Math.abs((int)(list.get(listIndex).get(1)) - (int)(list.get(listIndex).get(0))) < 3)
                {
                    isValid = false;
                }
           }
        }
        return isValid;
    }
    public void print()
    {
        for(int y = 0; y < map.length; y++)
        {
            for(int x = 0; x < map.length; x++)
            {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }
}
