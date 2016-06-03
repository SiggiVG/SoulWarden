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
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import vikinggoth.soulwarden.registers.BlockRegister;
import vikinggoth.soulwarden.world.biomes.BiomeGenStygia;

import java.util.List;
import java.util.Random;

public class ChunkProviderStygia implements IChunkProvider
{

    /** RNG. */
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    //public NoiseGeneratorOctaves mobSpawnerNoise;
    /** Reference to the World object. */
    private World worldObj;
    private WorldType worldType;
    private final double[] noiseField;
    private final float[] parabolicField;



    private double[] stoneNoise = new double[256];
    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;

    //Settings
    private Block water = Blocks.water;

    float mainNoiseScaleX = 80.0F; //80
    float mainNoiseScaleY = 160.0F; //160
    float mainNoiseScaleZ = 80.0F; //80
    float depthNoiseScaleX = 200.0F; //200
    float depthNoiseScaleZ = 200.0F; //200
    float depthNoiseScaleExponent = 0.5F; //0.5
    float depthBaseSize = 8.5F; //85
    float coordinateScale = 684.412F; //684.412
    float heightScale = 684.412F; //684.412
    float heightStretch = 12.0F; //12
    float upperLimitScale = 512.0F; //512
    float lowerLimitScale = 512.0F; //512
    float biomeDepthWeight = 1.0F; //1
    float biomeDepthOffSet = 0.0F; //0
    float biomeScaleWeight = 1.0F; //1
    float biomeScaleOffset = 0.0F; //0

    private int seaLevel = 63; //63



    public ChunkProviderStygia(World worldIn, long seed)
    {
        this.worldObj = worldIn;
        this.worldType = worldIn.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);

        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        //this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseField = new double[825];
        this.parabolicField = new float[25];

        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt_float((float)(i * i + j * j) + 0.2F);
                this.parabolicField[i + 2 + (j + 2) * 5] = f;
            }
        }

        NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5, noiseGen6};//, mobSpawnerNoise};
        noiseGens = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
        //this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
    }

    /**
     * Generates the shape of the terrain for the chunk though its all stone
     * though the water is frozen if the temperature is low enough
     */
    public void setBlocksInChunk(int p_180518_1_, int p_180518_2_, ChunkPrimer p_180518_3_)
    {
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, p_180518_1_ * 4 - 2, p_180518_2_ * 4 - 2, 10, 10);
        this.replaceBlocksForBiome(p_180518_1_ * 4, 0, p_180518_2_ * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = this.noiseField[i1 + i2];
                    double d2 = this.noiseField[j1 + i2];
                    double d3 = this.noiseField[k1 + i2];
                    double d4 = this.noiseField[l1 + i2];
                    double d5 = (this.noiseField[i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.noiseField[j1 + i2 + 1] - d2) * d0;
                    double d7 = (this.noiseField[k1 + i2 + 1] - d3) * d0;
                    double d8 = (this.noiseField[l1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    p_180518_3_.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, BlockRegister.soulStone.getDefaultState());
                                }
                                else if (i2 * 8 + j2 < this.seaLevel)
                                {
                                    p_180518_3_.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.water.getDefaultState());
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

    public void replaceBlocksForBiome(int x, int z, ChunkPrimer chunkPrimer, BiomeGenBase[] biomeGenBases)
    {
        ///
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, x, z, chunkPrimer, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return;

        double d0 = 0.03125D;
        //uses perlin noise
        this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double)(x * 16), (double)(z * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                //Maybe this will only make it gen Stygian Biomes?
                //Will crash with a ClassCastException if the biome does not extend BiomeGenStygia
                BiomeGenStygia biomegenbase = (BiomeGenStygia) biomeGenBases[j + i * 16];
                biomegenbase.genTerrainBlocks(this.worldObj, this.rand, chunkPrimer, x * 16 + i, z * 16 + j, this.stoneNoise[j + i * 16]);
            }
        }
    }

    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int x, int z)
    {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.replaceBlocksForBiome(x, z, chunkprimer, this.biomesForGeneration);

        //Generators for across the dimension, currently handled in SWWorldGen

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)this.biomesForGeneration[i].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void replaceBlocksForBiome(int p_147423_1_, int p_147423_2_, int p_147423_3_)
    {
        this.noiseData4 = this.noiseGen6.generateNoiseOctaves(this.noiseData4, p_147423_1_, p_147423_3_, 5, 5, (double)this.depthNoiseScaleX, (double)this.depthNoiseScaleZ, (double)this.depthNoiseScaleExponent);
        this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)(coordinateScale / this.mainNoiseScaleX), (double)(heightScale / this.mainNoiseScaleY), (double)(coordinateScale / this.mainNoiseScaleZ));
        this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)coordinateScale, (double)heightScale, (double)coordinateScale);
        this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)coordinateScale, (double)heightScale, (double)coordinateScale);
        int i = 0;
        int j = 0;
        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -i1; j1 <= i1; ++j1)
                {
                    for (int k1 = -i1; k1 <= i1; ++k1)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = this.biomeDepthOffSet + biomegenbase1.minHeight * this.biomeDepthWeight;
                        float f6 = this.biomeScaleOffset + biomegenbase1.maxHeight * this.biomeScaleWeight;

                        if (this.worldType == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.parabolicField[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.minHeight > biomegenbase.minHeight)
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.noiseData4[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double)this.depthBaseSize / 8.0D;
                double d0 = (double)this.depthBaseSize + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = ((double)l1 - d0) * (double)this.heightStretch * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.noiseData2[i] / (double)this.lowerLimitScale;
                    double d3 = this.noiseData3[i] / (double)this.upperLimitScale;
                    double d4 = (this.noiseData1[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.denormalizeClamp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (double)((float)(l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.noiseField[i] = d5;
                    ++i;
                }
            }
        }
    }

    /**
     * Checks to see if a chunk exists at x, z
     */
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider chunkProvider, int x, int z)
    {
        BlockFalling.fallInstantly = true;
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.worldObj.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)x * k + (long)z * l ^ this.worldObj.getSeed());
        boolean flag = false;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(x, z);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.PopulateChunkEvent.Pre(chunkProvider, worldObj, rand, x, z, flag));

        //biomegenbase.decorate(this.worldObj, this.rand, new BlockPos(i, 0, j));

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.PopulateChunkEvent.Post(chunkProvider, worldObj, rand, x, z, flag));

        BlockFalling.fallInstantly = false;
    }

    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_)
    {
        boolean flag = false;

        return flag;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate progressCallback)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData()
    {
    }

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);

        return biomegenbase.getSpawnableList(creatureType);
    }

    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
    {

    }


}