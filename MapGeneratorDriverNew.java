public class MapGeneratorDriver
{
    public static void main(String[] args)
    {
        MapGenerator gen = new MapGenerator(10, 10);
        gen.create();
        gen.print();
    }
}
