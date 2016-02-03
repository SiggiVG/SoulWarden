package vikinggoth.soulwarden.world.dimension;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.registries.BlockRegister;
import vikinggoth.soulwarden.world.biome.BiomeGenStygia;

import java.util.List;
import java.util.Random;

/**
 *
 */

/**
 * Created by Friedrich on 12/6/2015.
 */
public class ChunkProviderStygia implements IChunkProvider
{

    /** What the Oceans are made of */
    final Block liquidBlock = Blocks.water;
    final int seaLevel = ConfigurationHandler.seaLevelNecro;
    /** What the Land is made of */
    final Block fillBlock = BlockRegister.soulStone;
    final byte fillBlockMeta = 0;
    final Block veinBlock = BlockRegister.soulStone;
    final byte veinBlockMeta = 2;
    final Block dirtBlock = BlockRegister.graveSoil;
    final Block grassBlock = BlockRegister.grassCemetery;

    /** RNG */
    private Random rand;

    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    private NoiseGeneratorOctaves noiseGen5;
    private NoiseGeneratorOctaves noiseGen6;

    private final double[] doubles;
    private final float[] parabolicField;

    private ChunkProviderSettings settings;

    //BiomeDecorator Here

    private final World worldObj;
    private final WorldType worldType;
    public final boolean mapFeaturesEnabled;

    private double[] stoneNoise;

    //private MapGenCaves caveGenerator;

    //MapGenVillage Here

    //MapGenDungeon Here

    private BiomeGenBase[] biomesForGeneration = {BiomeGenStygia.biomeStygianSea};

    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;

    private static final int MID_HEIGHT = 128;
    private static final int CHUNK_SIZE_X = 16;
    private static final int CHUNK_SIZE_Y = 256;
    private static final int CHUNK_SIZE_Z = 16;


    public ChunkProviderStygia(World worldObj, long seed, boolean mapFeaturesEnabled, String str)
    {
        /*this.caveGenerator = new MapGenCaves();
        {
            //Cave
            caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
            //TODO create my own version
            //scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
        }*/

        this.worldObj = worldObj;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.worldType = worldObj.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);


        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);

        if (str != null)
        {
            this.settings = ChunkProviderSettings.Factory.func_177865_a(str).func_177864_b();
        }

        NoiseGenerator[] noiseGens = {this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6};
        noiseGens = TerrainGen.getModdedNoiseGenerators(worldObj, this.rand, noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorPerlin) noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];

        this.doubles = new double[825];
        this.parabolicField = new float[25];

    }

    /*public void generateTerrain(int chunkX, int chunkZ, ChunkPrimer chunkprimer)
    {
        this.noiseGen1.setFrequency(0.0125F);
        this.noiseGen2.setFrequency(0.015F);
        this.noiseGen3.setFrequency(0.01F);
        this.noiseGen4.setFrequency(0.02F);

        for (int x = 0; x < ChunkProviderStygia.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderStygia.CHUNK_SIZE_Z; z++)
            {
                final double d = this.noiseGen1.getNoise(x + chunkX * 16, z + chunkZ * 16) * 8;
                final double d2 = this.noiseGen2.getNoise(x + chunkX * 16, z + chunkZ * 16) * 24;
                double d3 = this.noiseGen3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1;
                d3 *= 4;

                double yDev = 0;

                if (d3 < 0.0D)
                {
                    yDev = d;
                }
                else if (d3 > 1.0D)
                {
                    yDev = d2;
                }
                else
                {
                    yDev = d + (d2 - d) * d3;
                }

                for (int y = 0; y < ChunkProviderStygia.CHUNK_SIZE_Y; y++)
                {
                    if (y < ChunkProviderStygia.MID_HEIGHT + yDev)
                    {
                        idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
                        metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
                    }
                }
            }
        }
    }*/

    public void genBedrock(int chunkX, int chunkZ, ChunkPrimer chunkPrimer, BiomeGenBase[] arrayOfBiomeGenBase)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, chunkPrimer, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if(event.getResult() == Event.Result.DENY) return;

        //set stonenoise here
        double d0 = 0.03125D;
        this.stoneNoise = this.noiseGen3.generateNoiseOctaves(this.stoneNoise, (chunkX * 16), (chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int x = 0; x < CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < CHUNK_SIZE_Z; z++)
            {
                BiomeGenBase biomeGenBase =arrayOfBiomeGenBase[x+z^CHUNK_SIZE_X];
                biomeGenBase.genTerrainBlocks(this.worldObj, this.rand, chunkPrimer, chunkX * CHUNK_SIZE_X + x, chunkZ + CHUNK_SIZE_Z + z, this.stoneNoise[x + z + CHUNK_SIZE_X]);
            }
        }
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();


        this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);

        //this.generateTerrain(chunkX, chunkZ, ids, meta);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        //this.createCraters(chunkX, chunkZ, ids, meta);
        this.genBedrock(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);
        //this.caveGenerator.generate(this, this.worldObj, chunkX, chunkZ, ids, meta);
        //this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), chunkX * 16, 25, chunkZ * 16, chunkX, chunkZ, ids, meta);
        final Chunk chunk = new Chunk(this.worldObj, chunkprimer, chunkX, chunkZ);

        chunk.generateSkylightMap();
        return chunk;
    }

    //TODO
    public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer chunkPrimer)
    {
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        this.func_147423_a(chunkX * 4, 0, chunkZ * 4);

        for (int k = 0; k < 4; ++k)
        {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1)
            {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2)
                {
                    double d0 = 0.125D;
                    double d1 = this.doubles[k1 + k2];
                    double d2 = this.doubles[l1 + k2];
                    double d3 = this.doubles[i2 + k2];
                    double d4 = this.doubles[j2 + k2];
                    double d5 = (this.doubles[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.doubles[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.doubles[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.doubles[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int j3 = 0; j3 < 4; ++j3)
                            {
                                if ((d15 += d16) > 0.0D)
                                {
                                    chunkPrimer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, Blocks.stone.getDefaultState());
                                }
                                else if (k2 * 8 + l2 < this.seaLevel)
                                {
                                    chunkPrimer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, this.fillBlock.getDefaultState());
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    private void func_147423_a(int x, int y, int z)
    {
        this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, x, z, 5, 5, (double)this.settings.depthNoiseScaleX, (double)this.settings.depthNoiseScaleZ, (double)this.settings.depthNoiseScaleExponent);
        float f = this.settings.coordinateScale;
        float f1 = this.settings.heightScale;
        this.field_147427_d = this.noiseGen3.generateNoiseOctaves(this.field_147427_d, x, y, z, 5, 33, 5, (double)(f / this.settings.mainNoiseScaleX), (double)(f1 / this.settings.mainNoiseScaleY), (double)(f / this.settings.mainNoiseScaleZ));
        this.field_147428_e = this.noiseGen1.generateNoiseOctaves(this.field_147428_e, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
        this.field_147425_f = this.noiseGen2.generateNoiseOctaves(this.field_147425_f, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;

        for (int j1 = 0; j1 < 5; ++j1)
        {
            for (int k1 = 0; k1 < 5; ++k1)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f5 = this.settings.biomeDepthOffSet + biomegenbase1.minHeight * this.settings.biomeDepthWeight;
                        float f6 = this.settings.biomeScaleOffset + biomegenbase1.maxHeight * this.settings.biomeScaleWeight;

                        if (this.worldType == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.minHeight > biomegenbase.minHeight)
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 /= f4;
                f3 /= f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.field_147426_g[i1] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 /= 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 /= 1.4D;
                    d7 /= 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 /= 8.0D;
                }

                ++i1;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 += d7 * 0.2D;
                d8 = d8 * (double)this.settings.baseSize / 8.0D;
                double d0 = (double)this.settings.baseSize + d8 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2)
                {
                    double d1 = ((double)j2 - d0) * (double)this.settings.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.field_147428_e[l] / (double)this.settings.lowerLimitScale;
                    double d3 = this.field_147425_f[l] / (double)this.settings.upperLimitScale;
                    double d4 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.denormalizeClamp(d2, d3, d4) - d1;

                    if (j2 > 29)
                    {
                        double d6 = (double)((float)(j2 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.doubles[l] = d5;
                    ++l;
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn) {
        return provideChunk(blockPosIn.getX(), blockPosIn.getZ());
    }

    @Override
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    private int getIndex(int x, int y, int z)
    {
        return (x * 16 + z) * 256 + y;
    }


    /**
     * Populates chunk with ores etc etc
     */
    @Override
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;
        final int x = chunkX * 16;
        final int z = chunkZ * 16;
        this.worldObj.getBiomeGenForCoords(new BlockPos(x + 16, 0, z + 16));
        this.rand.setSeed(this.worldObj.getSeed());
        final long seedX = this.rand.nextLong() / 2L * 2L + 1L;
        final long seedZ = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * seedX + chunkZ * seedZ ^ this.worldObj.getSeed());

        boolean flag = false;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(chunkX, chunkZ);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunkProvider, worldObj, rand, chunkX, chunkZ, flag));


        //this.dungeonGenerator.handleTileEntities(this.rand);
        //TODO caves

        //if (Config.genVillages)
        {
            //this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunkProvider, worldObj, rand, chunkX, chunkZ, flag));
        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean func_177460_a(IChunkProvider chunkProvider, Chunk chunk, int x, int z) {
        return false;
    }

    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public String makeString()
    {
        return "StygiaLevelSource";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List func_177458_a(EnumCreatureType creatureType, BlockPos blockpos)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(blockpos);

        return biomegenbase.getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {

    }

    @Override
    public void saveExtraData()
    {

    }
}
