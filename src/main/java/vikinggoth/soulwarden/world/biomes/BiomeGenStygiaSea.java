package vikinggoth.soulwarden.world.biomes;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class BiomeGenStygiaSea extends BiomeGenStygia
{
    public BiomeGenStygiaSea(int id)
    {
        super(id);
        this.spawnableCaveCreatureList.clear();
    }

    public BiomeGenBase.TempCategory getTempCategory()
    {
        return BiomeGenBase.TempCategory.OCEAN;
    }

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double num)
    {
        super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, num);
    }
}
