package vikinggoth.soulwarden.world.dimension;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.world.biome.BiomeRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 */

/**
 * Created by Friedrich on 12/6/2015.
 */
public class ChunkProviderNecro extends ChunkProviderGenerate
{

    /** What the Oceans are made of */
    final Block liquidBlock = Blocks.water;
    final int seaLevel = ConfigurationHandler.seaLevelNecro;
    /** What the Land is made of */
    final Block fillBlock = BlockRegistry.soulStone;
    final byte fillBlockMeta = 0;
    final Block veinBlock = BlockRegistry.soulStone;
    final byte veinBlockMeta = 2;
    final Block dirtBlock = BlockRegistry.graveSoil;
    final Block grassBlock = BlockRegistry.grassCemetery;

    /** RNG */
    private Random rand;

    private final NoiseModule noiseGen1;
    private final NoiseModule noiseGen2;
    private final NoiseModule noiseGen3;
    private final NoiseModule noiseGen4;

    //BiomeDecorator Here

    private final World worldObj;
    private final WorldType worldType;
    public final boolean mapFeaturesEnabled;

    //private MapGenCaves caveGenerator;

    //MapGenVillage Here

    //MapGenDungeon Here

    private BiomeGenBase[] biomesForGeneration = {BiomeRegistry.biomeStygianSea};

    private static final int MID_HEIGHT = 127;
    private static final int CHUNK_SIZE_X = 16;
    private static final int CHUNK_SIZE_Y = 256;
    private static final int CHUNK_SIZE_Z = 16;


    public ChunkProviderNecro(World worldObj, long seed, boolean mapFeaturesEnabled)
    {

        super(worldObj, seed, mapFeaturesEnabled, null);

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

        this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
        this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, 0.25F);
        this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, 0.25F);

        /*
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
        */
    }

    public void generateTerrain(int chunkX, int chunkZ, ChunkPrimer chunkprimer)
    {
        this.noiseGen1.setFrequency(0.0125F);
        this.noiseGen2.setFrequency(0.015F);
        this.noiseGen3.setFrequency(0.01F);
        this.noiseGen4.setFrequency(0.02F);

        for (int x = 0; x < ChunkProviderNecro.CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < ChunkProviderNecro.CHUNK_SIZE_Z; z++)
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

                for (int y = 0; y < ChunkProviderNecro.CHUNK_SIZE_Y; y++)
                {
                    if (y < ChunkProviderNecro.MID_HEIGHT + yDev)
                    {
                        idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
                        metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
                    }
                }
            }
        }
    }

    @Override
    public void func_180517_a(int chunkX, int chunkZ, ChunkPrimer chunkPrimer, BiomeGenBase[] arrayOfBiomeGenBase)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent(ReplaceBiomeBlocks(chunkX, chunkZ, chunkPrimer, this.worldObj));
        MinecraftForge.EVENT_BUS.post(event);
        if(event.getResult() == Event.Result.DENY) return;

        for (int x = 0; x < CHUNK_SIZE_X; x++)
        {
            for (int z = 0; z < CHUNK_SIZE_Z; z++)
            {
                final int var1;
            }
        }
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ)
    {
        this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();


        Arrays.fill(ids, Blocks.air);
        this.generateTerrain(chunkX, chunkZ, ids, meta);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        //this.createCraters(chunkX, chunkZ, ids, meta);
        this.func_180517_a(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);
        //this.caveGenerator.generate(this, this.worldObj, chunkX, chunkZ, ids, meta);
        //this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), chunkX * 16, 25, chunkZ * 16, chunkX, chunkZ, ids, meta);
        //TODO make this use a chunkprimer
        final Chunk chunk = new Chunk(this.worldObj, ids, meta, chunkX, chunkZ);

        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public boolean chunkExists(int par1, int par2)
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

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockFalling.fallInstantly = true;
        final int var4 = par2 * 16;
        final int var5 = par3 * 16;
        this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        final long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        final long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(par2 * var7 + par3 * var9 ^ this.worldObj.getSeed());

        //this.dungeonGenerator.handleTileEntities(this.rand);
        //TODO caves

        //if (!ConfigManagerCore.disableMoonVillageGen)
        {
            //this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        }

        //this.decoratePlanet(this.worldObj, this.rand, var4, var5);
        BlockFalling.fallInstantly = false;
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
        return "MoonLevelSource";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List func_177458_a(EnumCreatureType creatureType, BlockPos blockpos)
    {
        /*if (creatureType == EnumCreatureType.monster)
        {
            final List monsters = new ArrayList();
            monsters.add(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
            monsters.add(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
            monsters.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
            monsters.add(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
            return monsters;
        }
        else*/
        {
            return null;
        }
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        //if (!ConfigManagerCore.disableMoonVillageGen)
        {
            //this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
        }
    }



    /*
    @Override
    public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     *
    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);

        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBiomeBlocks(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);

        //cave generator TOD make a big open caves one???
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
         *
        double d0 = 0.03125D;
        this.stoneNoise = this.noisePer4.func_151599_a(this.stoneNoise, (double)(chunkX * 16), (double)(chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        /**
         * generates terrain based on biome
         *
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
     *
    private void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer chunkPrimer)
    {
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        this.initializeNoiseField(chunkX * 4, 0, chunkZ * 4);

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

    private void initializeNoiseField(int chunkX, int y, int chunkZ)
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
     *
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
         *
         *
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
            //TOD change this to ConfigBlocks.waterStygia
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
     *
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
    */
}
