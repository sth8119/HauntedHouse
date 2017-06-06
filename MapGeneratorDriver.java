public class MapGeneratorDriver
{
    public static void main(String[] args)
    {
        boolean canCreate;
        MapGenerator gen;
        do
        {
			gen = new MapGenerator(10, 10);
			canCreate = gen.create();
		} while(!canCreate);
		gen.buildFinalMap();
        gen.print();
    }
}
