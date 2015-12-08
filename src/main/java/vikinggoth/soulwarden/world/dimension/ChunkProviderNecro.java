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
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.registries.BlockRegistry;

import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
/**
 *
 */
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class ChunkProviderNecro implements IChunkProvider
{
    /** the World Object*/
    private World worldObj;
    /** are map structures going to be generated (e.g. strongholds) */
    private final boolean mapFeaturesEnabled; //based off the option when you first create a world
    private WorldType worldType;

    /** RNG */
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noisePer4;
    /** NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen5;
    /** NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise; //If I want Mob Spawners

    private final double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private final float[] parabolicField;
    private ChunkProviderSettings settings;
    /** What the Oceans are made of */
    private Block liquidBlock;
    private int seaLevel;
    /** What the Land is made of */
    private Block groundBlock;
    /** if I want caves */
    private MapGenBase caveGenerator;
    /** custom MapGenCity extends MapGenBase */
    //private MapGenCity caveGenerator = new MapGenCity();
    /** Scattered Features such as Witch Huts, Desert and Jungle Temples */
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    /** The biome that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;
    /** A double array that hold terrain noise */
    double[] noise1;
    double[] noise2;
    double[] noise3;
    double[] noise4;

    public ChunkProviderNecro(World worldObj, long seed, boolean mapFeaturesEnabled, String s)
    {
        this.liquidBlock =  Blocks.water;
        this.seaLevel = ConfigurationHandler.seaLevelNecro;
        this.groundBlock = BlockRegistry.soulStone;

        this.caveGenerator = new MapGenCaves();
        {
            //Cave
            caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
            //TODO create my own version
            //scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
        }

        this.worldObj = worldObj;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.worldType = worldObj.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);

        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noisePer4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseArray = new double[825];
        this.parabolicField = new float[25];

        for (int j = -2; j <= 2; ++j)
        {
            for (int k = -2; k <= 2; ++k)
            {
                float f = 10.0F / MathHelper.sqrt_float((float)(j * j + k * k) + 0.2F);
                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }

        if(s != null)
        {
            /**
             * No clue what this does, I assume it is due to custom game worlds
             */
            this.settings = ChunkProviderSettings.Factory.func_177865_a(s).func_177864_b();
            //this.liquidBlock = this.settings.useLavaOceans ? Blocks.lava : this.liquidBlock;
        }

        NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noisePer4, noiseGen5, noiseGen6, mobSpawnerNoise};
        noiseGens = TerrainGen.getModdedNoiseGenerators(worldObj, this.rand, noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noisePer4 = (NoiseGeneratorPerlin) noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
    }


    @Override
    public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);

        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBiomeBlocks(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);

        //cave generator TODO make a big open caves one???
        if (this.settings.useCaves)
        {
            this.caveGenerator.func_175792_a(this, this.worldObj, chunkX, chunkZ, chunkprimer);
        }
        //ravine generator

        //code from recreateStructures, but with the chunkprimer not set to null

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, chunkX, chunkZ);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void replaceBiomeBlocks(int chunkX, int chunkZ, ChunkPrimer chunkprimer, BiomeGenBase[] biomesForGeneration)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, chunkprimer, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if(event.getResult() == Event.Result.DENY) return;

        /**
         * has something to do with noise
         */
        double d0 = 0.03125D;
        this.stoneNoise = this.noisePer4.func_151599_a(this.stoneNoise, (double)(chunkX * 16), (double)(chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        /**
         * generates terrain based on biome
         */
        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                BiomeGenBase biomegenbase = biomesForGeneration[l + k * 16];
                biomegenbase.genTerrainBlocks(this.worldObj, this.rand, chunkprimer, chunkX * 16 + k, chunkZ * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    /**
     * Actually Generates the Terrain
     * @param chunkX
     * @param chunkZ
     * @param chunkPrimer
     */
    private void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer chunkPrimer)
    {
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        this.genBiomes(chunkX * 4, 0, chunkZ * 4);

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
                    double d1 = this.noiseArray[k1 + k2];
                    double d2 = this.noiseArray[l1 + k2];
                    double d3 = this.noiseArray[i2 + k2];
                    double d4 = this.noiseArray[j2 + k2];
                    double d5 = (this.noiseArray[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.noiseArray[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.noiseArray[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.noiseArray[j2 + k2 + 1] - d4) * d0;

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
                                    chunkPrimer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, this.groundBlock.getDefaultState());//TODO change this to use Soulstone
                                }
                                else if (k2 * 8 + l2 < this.seaLevel)
                                {
                                    chunkPrimer.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, this.liquidBlock.getDefaultState());
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

    private void genBiomes(int chunkX, int y, int chunkZ)
    {
        this.noise4 = this.noiseGen6.generateNoiseOctaves(this.noise4, chunkX, chunkZ, 5, 5, (double)this.settings.depthNoiseScaleX, (double)this.settings.depthNoiseScaleZ, (double)this.settings.depthNoiseScaleExponent);
        float f = this.settings.coordinateScale;
        float f1 = this.settings.heightScale;
        this.noise1 = this.noiseGen3.generateNoiseOctaves(this.noise1, chunkX, y, chunkZ, 5, 33, 5, (double)(f / this.settings.mainNoiseScaleX), (double)(f1 / this.settings.mainNoiseScaleY), (double)(f / this.settings.mainNoiseScaleZ));
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, chunkX, y, chunkZ, 5, 33, 5, (double)f, (double)f1, (double)f);
        this.noise3 = this.noiseGen1.generateNoiseOctaves(this.noise3, chunkX, y, chunkZ, 5, 33, 5, (double)f, (double)f1, (double)f);
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
                double d7 = this.noise4[i1] / 8000.0D;

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

                    double d2 = this.noise2[l] / (double)this.settings.lowerLimitScale;
                    double d3 = this.noise3[l] / (double)this.settings.upperLimitScale;
                    double d4 = (this.noise1[l] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.denormalizeClamp(d2, d3, d4) - d1;

                    if (j2 > 29)
                    {
                        double d6 = (double)((float)(j2 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.noiseArray[l] = d5;
                    ++l;
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn) {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }

    /**
     * Does structure terrain gen
     *
     * TerrainGen.populate(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ, boolean hasVillageGenerated, Populate.EventType type)
     * seems important to tell if it is allowed to generate or not
     * Use EventType.Custom
     *
     */
    @Override
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;
        int k = chunkX * 16;
        int l = chunkZ * 16;
        BlockPos blockpos = new BlockPos(k, 0, l);
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)blockpos.getX() * i1 + (long)chunkZ * j1 ^ this.worldObj.getSeed());
        boolean hasVillageGenerated = false;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(chunkX, chunkZ);

        /**
         * For Custome Features:
         * TerrainGen.populate(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ, boolean hasVillageGenerated, Populate.EventType type)
         * seems important to tell if it is allowed to generate or not
         * Use EventType.Custom
         */

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunkProvider, worldObj, rand, chunkX, chunkZ, hasVillageGenerated));

        if(mapFeaturesEnabled)
        {
            //Mineshafts
            //Villages
            //hasVillageGenerated = this.villageGenerator.func_175794_a(this.worldObj, this.rand, chunkcoordintpair);
            //Strongholds
            //DesertTemples
            //OceanMonuments
        }

        int k1;
        int l1;
        int i2;

        //Water Lakes
        if (biome != BiomeGenBase.desert && biome != BiomeGenBase.desertHills && this.settings.useWaterLakes && !hasVillageGenerated && this.rand.nextInt(this.settings.waterLakeChance) == 0
                && TerrainGen.populate(chunkProvider, worldObj, rand, chunkX, chunkZ, hasVillageGenerated, LAKE))
        {
            k1 = this.rand.nextInt(16) + 8;
            l1 = this.rand.nextInt(256);
            i2 = this.rand.nextInt(16) + 8;
            //TODO change this to ConfigBlocks.waterStygia
            (new WorldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, blockpos.add(k1, l1, i2));
        }

        //Lava Lakes

        //Dungeons

        //BiomeDecorator
        biome.decorate(this.worldObj, this.rand, new BlockPos(k, 0, l));

        //Animals

        //Ice and Snow

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunkProvider, worldObj, rand, chunkX, chunkZ, hasVillageGenerated));

        BlockFalling.fallInstantly = false;
    }

    //Seems to do something related to ocean monuments in the Overworld and nothing in the Nether
    @Override
    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
        return false;
    }

    @Override
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "NecroRandomLevelSource";
    }

    /**
     * getPossibleCreatures
     * This method does witch in witch huts, netherbridge mobs, and oceanmonument mobs.
     * @param creatureType
     * @param pos
     * @return
     */
    @Override
    public List func_177458_a(EnumCreatureType creatureType, BlockPos pos) {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
        return biomegenbase.getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String s, BlockPos pos) {
        return null; //there is no stronghold gen
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        //calls on structure generators
        //func_175792_a(this, this.worldObj, chunkX, chunkZ, (ChunkPrimer)null)
        //from mapGenBase to create structures
    }

    @Override
    public void saveExtraData() {}
}
