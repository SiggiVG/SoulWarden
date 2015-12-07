package vikinggoth.soulwarden.world.dimension;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
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

    /** NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noisePer1;

    /** NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen5;

    /** NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen6;
    private NoiseGeneratorOctaves mobSpawnerNoise; //If I want Mob Spawners

    /** the World Object*/
    private World WorldObj;

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
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    int[][] field_73219_j = new int[32][32];

    {
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
        scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
        //ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
    }

    //TODO

    @Override
    public boolean chunkExists(int x, int z) {
        return false;
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        return null;
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn) {
        return null;
    }

    @Override
    public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {

    }

    @Override
    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
        return false;
    }

    @Override
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
        return false;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public String makeString() {
        return null;
    }

    @Override
    public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
        return null;
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
    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {

    }

    @Override
    public void saveExtraData() {

    }
}
