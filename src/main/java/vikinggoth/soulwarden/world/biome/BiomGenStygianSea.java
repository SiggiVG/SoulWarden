package vikinggoth.soulwarden.world.biome;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

/**
 * Created by Friedrich on 1/28/2016.
 */
public class BiomGenStygianSea extends BiomeGenStygia
{

    public BiomGenStygianSea(int biomeId)
    {
        super(biomeId);
        this.spawnableCreatureList.clear();
    }

    @Override
    public BiomeGenBase.TempCategory getTempCategory()
    {
        return BiomeGenBase.TempCategory.OCEAN;
    }

    @Override
    public void genTerrainBlocks(World worldObj, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double num)
    {
        super.genTerrainBlocks(worldObj, rand, primer, chunkX, chunkZ, num);
    }
}
