package vikinggoth.soulwarden.world.dimension;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.List;
import java.util.Random;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class ChunkProviderNecro implements IChunkProvider
{
    /** RNG */
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    /** NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen5;
    /** NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen6;
    private NoiseGeneratorOctaves mobSpawnerNoise; //If I want Mob Spawners

    /** the World Object*/
    private World worldObj;

    /** are map structures going to be generated (e.g. strongholds) */
    private final boolean mapFeaturesEnabled; //based off the option when you first create a world

    private WorldType worldType;
    private final double[] noiseArray;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    /** if I want caves */
    private MapGenBase caveGenerator = new MapGenCaves();

    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();

    /** Holds ravine generator
     * I'm not having ravines
     */
    //private MapGenBase ravineGenerator = new MapGenRavine();

    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;

    /** A double array that hold terrain noise */
    double[] noise1;
    double[] noise2;
    double[] noise3;
    double[] noise4;
    int[][] field_73219_j = new int[32][32];

    {
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
        scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
        //ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
    }

    //TODO

    @Override
    public boolean chunkExists(int x, int z) {
        return true;
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        //this.setBlocksInChunk(x, z, chunkprimer);

    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn) {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }

    /**
     * Does terraingen
     *
     * TerrainGen.populate(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ, boolean hasVillageGenerated, Populate.EventType type)
     * seems important to tell if it is allowed to generate or not
     * Use EventType.Custom
     *
     */
    @Override
    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ) {
        BlockFalling.fallInstantly = true;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunkProvider, worldObj, rand, chunkX, chunkZ, false));

        BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

        /**
         * TerrainGen.populate(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ, boolean hasVillageGenerated, Populate.EventType type)
         * seems important to tell if it is allowed to generate or not
         * Use EventType.Custom
         */
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
    public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
        return null;
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
