package vikinggoth.soulwarden.world.biome.decorators;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import vikinggoth.soulwarden.registries.BlockRegister;

import java.util.Random;

/**
 * Created by Friedrich on 1/28/2016.
 */
public class BiomeDecoratorStygia extends BiomeDecorator
{
    /** The world the BiomeDecorator is currently decorating */
    public static World currentWorld;
    /** The Biome Decorator's random number generator. */
    public static Random rand;
    /** The X-coordinate of the chunk currently being decorated */
    public static int chunkX;
    /** The Z-coordinate of the chunk currently being decorated */
    public static int chunkZ;
    /** True if decorator should generate surface lava & water */
    //public static boolean generateLakes;
    /** How many trees per chunk, set in each biome class **/
    public static int howManyTrees;

    public WorldGenerator pewterGen;
    public WorldGenerator rostyGen;
    public WorldGenerator hematiteGen;
    public WorldGenerator soulgemGen;
    public WorldGenerator soulgemBlackGen;
    public WorldGenerator emberGen;

    /** Trees here */
    //public static WorldGenTrees tree;

    public BiomeDecoratorStygia()
    {
        //Ores
        pewterGen = new WorldGenMinable(BlockRegister.ore_pewter.getDefaultState(), 8, BlockHelper.forBlock(BlockRegister.soulStone));
        rostyGen = new WorldGenMinable(BlockRegister.ore_pewter.getDefaultState(), 8, BlockHelper.forBlock(BlockRegister.soulStone));
        hematiteGen = new WorldGenMinable(BlockRegister.ore_pewter.getDefaultState(), 4, BlockHelper.forBlock(BlockRegister.soulStone));
        soulgemGen = new WorldGenMinable(BlockRegister.ore_pewter.getDefaultState(), 12, BlockHelper.forBlock(BlockRegister.soulStone));
        soulgemBlackGen = new WorldGenMinable(BlockRegister.ore_pewter.getDefaultState(), 2, BlockHelper.forBlock(BlockRegister.soulStone));
        emberGen = new WorldGenMinable(BlockRegister.ore_pewter.getDefaultState(), 16, BlockHelper.forBlock(BlockRegister.soulStone));

        //Trees


        //Lakes

    }

    public void decorateChunk(World world, Random random, BiomeGenBase biomeGenBase, int chunkX, int chunkZ) {
        if (currentWorld != null) {
            throw new RuntimeException("Already decorating " + currentWorld.getWorldInfo().getWorldName());
        } else {
            currentWorld = world;
            randomGenerator = random;
            chunkX = chunkX;
            chunkZ = chunkZ;
            genDecorationsForBiome(biomeGenBase);
            currentWorld = null;
            randomGenerator = null;
        }
    }

    /**
     * Decorate's biome.
     *
     * @param biome
     */
    protected void genDecorationsForBiome(BiomeGenBase biome) {
        BiomeDecoratorHelper.decorateBiome(biome);
    }

}
