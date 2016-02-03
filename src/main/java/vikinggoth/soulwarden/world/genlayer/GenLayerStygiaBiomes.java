package vikinggoth.soulwarden.world.genlayer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import vikinggoth.soulwarden.world.biome.BiomeGenStygia;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class GenLayerStygiaBiomes extends GenLayer
{
    //TODO temperatures
    protected BiomeGenBase[] allowedBiomes = {
            BiomeGenStygia.biomeStygianSea
            };

    public GenLayerStygiaBiomes(long seed) {
        super(seed);
    }

    public GenLayerStygiaBiomes(long seed, GenLayer genLayer) {
        super(seed);
        this.parent = genLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] dest = IntCache.getIntCache(areaWidth * areaHeight);
        for(int dz = 0; dz < areaHeight; ++dz)
        {
            for(int dx = 0; dx < areaWidth; ++dx)
            {
                this.initChunkSeed(dx + areaX, dz + areaY);
                dest[(dx + dz * areaWidth)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
            }
        }
        return dest;
    }
}
