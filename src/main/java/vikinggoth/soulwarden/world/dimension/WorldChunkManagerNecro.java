package vikinggoth.soulwarden.world.dimension;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import vikinggoth.soulwarden.world.genlayer.GenLayerNecro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class WorldChunkManagerNecro extends net.minecraft.world.biome.WorldChunkManager
{
    private GenLayer genBiomes;
    /**A GenLayer containing the indices into BiomGenBase.biomeList[]*/
    private GenLayer biomeIndexLayer;
    /**The BiomeCache object for the world*/
    private BiomeCache biomeCache;
    /**A List of biome that the player can spaen in */
    private List<BiomeGenBase> biomesToSpawnIn;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public WorldChunkManagerNecro(List<BiomeGenBase> biomes)
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        this.biomesToSpawnIn.addAll(biomes); //TODO change this
    }

    public WorldChunkManagerNecro(long seed, WorldType worldType, List<BiomeGenBase> biomes)
    {
        this(biomes);
        GenLayer[] aGenLayer = GenLayerNecro.genWorld(seed, worldType);
        aGenLayer = getModdedBiomeGenerators(worldType, seed, aGenLayer);
        this.genBiomes = aGenLayer[0];
        this.biomeIndexLayer = aGenLayer[1];
    }

    public WorldChunkManagerNecro(World world, List<BiomeGenBase> biomes)
    {
        this(world.getSeed(), world.getWorldInfo().getTerrainType(), biomes);
    }

    /**
     * Gets the list of valid biome for the player to spawn in.
     */
    @Override
    public List<BiomeGenBase> getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    /**
     * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
     */
    @Override
    public float[] getRainfall(float[] listToReuse, int x, int z, int width, int length)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new float[width * length];
        }

        int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

        for (int i1 = 0; i1 < width * length; ++i1)
        {
            try
            {
                float f = (float)BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;

                if (f > 1.0F)
                {
                    f = 1.0F;
                }

                listToReuse[i1] = f;
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("DownfallBlock");
                crashreportcategory.addCrashSection("biome id", Integer.valueOf(i1));
                crashreportcategory.addCrashSection("downfalls[] size", Integer.valueOf(listToReuse.length));
                crashreportcategory.addCrashSection("x", Integer.valueOf(x));
                crashreportcategory.addCrashSection("z", Integer.valueOf(z));
                crashreportcategory.addCrashSection("w", Integer.valueOf(width));
                crashreportcategory.addCrashSection("h", Integer.valueOf(length));
                throw new ReportedException(crashreport);
            }
        }

        return listToReuse;
    }


    /**
     * Return a list of biome for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
     * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
     */
    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int y, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new BiomeGenBase[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (y & 15) == 0)
        {
            BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(x, y);
            System.arraycopy(abiomegenbase1, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        else
        {
            int[] aint = this.biomeIndexLayer.getInts(x, y, width, length);

            for (int i = 0; i < width * length; ++i)
            {
                listToReuse[i] = BiomeGenBase.getBiome(aint[i]);
            }
            return listToReuse;
        }
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean areBiomesViable(int x, int y, int z, List par4List)
    {
        IntCache.resetIntCache();
        int l = x - z >> 2;
        int i1 = y - z >> 2;
        int j1 = x + z >> 2;
        int k1 = y + z >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);

        try
        {
            for (int j2 = 0; j2 < l1 * i2; ++j2)
            {
                BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[j2]);

                if (!par4List.contains(biomegenbase))
                {
                    return false;
                }
            }

            return true;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(y));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(z));
            crashreportcategory.addCrashSection("allowed", par4List);
            throw new ReportedException(crashreport);
        }
    }

    /**
     * Finds a valid position within a range, that is in one of the listed
     * biome. Searches {par1,par2} +-par3 blocks. Strongly favors positive y
     * positions.
     */
    @Override
    @SuppressWarnings("rawtypes")
    public BlockPos findBiomePosition(int x, int y, int z, List par4List, Random random)
    {
        IntCache.resetIntCache();
        int l = x - z >> 2;
        int i1 = y - z >> 2;
        int j1 = x + z >> 2;
        int k1 = y + z >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
        BlockPos blockPos = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2)
        {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

            if (par4List.contains(biomegenbase) && (blockPos == null || random.nextInt(j2 + 1) == 0))
            {
                blockPos = new BlockPos(l2, 0, i3);
                ++j2;
            }
        }

        return blockPos;
    }


    /**
     * Calls the WorldChunkManager's biomeCache.cleanupCache()
     */
    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }


}
