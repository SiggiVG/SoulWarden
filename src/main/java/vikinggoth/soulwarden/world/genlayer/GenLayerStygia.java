package vikinggoth.soulwarden.world.genlayer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class GenLayerStygia extends GenLayer
{


    public GenLayerStygia(long seed)
    {
        super(seed);
    }

    public static GenLayer[] genWorld(long seed, WorldType type)
    {
        GenLayer biomes = new GenLayerStygiaBiomes(1L); //the seed is set to 1??/
        biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerZoom(1001L, biomes);
        biomes = new GenLayerZoom(1002L, biomes);
        biomes = new GenLayerZoom(1003L, biomes);
        biomes = new GenLayerZoom(1004L, biomes);
        biomes = new GenLayerZoom(1005L, biomes);
        GenLayer genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, biomes);
        biomes.initWorldGenSeed(seed);
        genLayerVoronoiZoom.initWorldGenSeed(seed);
        return new GenLayer[] {biomes, genLayerVoronoiZoom};
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        return null;
    }
}
