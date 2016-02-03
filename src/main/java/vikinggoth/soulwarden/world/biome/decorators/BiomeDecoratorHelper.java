package vikinggoth.soulwarden.world.biome.decorators;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class BiomeDecoratorHelper
{

    // private static WorldGenerator glowStone;

    public BiomeDecoratorHelper()
    {
        //		glowStone = new WorldGenMinable(Blocks.glowstone, 30, Blockss.lightStone);
    }

    protected static void decorateBiome(BiomeGenBase biome)
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(BiomeDecoratorStygia.currentWorld, BiomeDecoratorStygia.rand, new BlockPos(BiomeDecoratorStygia.chunkX, 0, BiomeDecoratorStygia.chunkZ)));
        //perpere ores for generation
        initOres();
        //GenerateOres
        generateOreInBiome(biome);

        if (biome == BiomeRegistry.biomeStygianSea) {
            //BiomeDecoratorStygia.howMenyTrees = BiomeLightForest.treesPerChunk;
            //int i = BiomeDecoratorStygia.howMenyTrees;
            /** Chunk Postions **/
            int chunkX;
            int chunkZ;
            int y;
            /** get blocks at the given locations **/
            //Block block;
            //Block blockA;
            /** Generates Small tree **
             for(int a = 0; a < i; ++a){
             if(i == BiomeDecoratorStygia.rand.nextInt(8)){
             chunkX = BiomeDecoratorStygia.chunk_X + BiomeDecoratorStygia.rand.nextInt(16) + 8;
             chunkZ = BiomeDecoratorStygia.chunk_Z + BiomeDecoratorStygia.rand.nextInt(16) + 8;
             y = BiomeDecoratorStygia.currentWorld.getTopSolidOrLiquidBlock(chunkX, chunkZ);
             block = BiomeDecoratorStygia.currentWorld.getBlock(chunkX, y, chunkZ);
             blockA = BiomeDecoratorStygia.currentWorld.getBlock(chunkX, y - 1, chunkZ);
             if(block != Blocks.air || block != Blocks.water){
             if(blockA != Blocks.air || blockA != Blocks.water){
             BiomeDecoratorStygia.smallTree.generate(BiomeDecoratorStygia.currentWorld, BiomeDecoratorStygia.rand, chunkX, y, chunkZ);
             }
             }
             }
             /** Generates Big tree **
             if(i == BiomeDecoratorStygia.rand.nextInt(15)){
             chunkX = BiomeDecoratorStygia.chunk_X + BiomeDecoratorStygia.rand.nextInt(16) + 8;
             chunkZ = BiomeDecoratorStygia.chunk_Z + BiomeDecoratorStygia.rand.nextInt(16) + 8;
             y = BiomeDecoratorStygia.currentWorld.getTopSolidOrLiquidBlock(chunkX, chunkZ);
             block = BiomeDecoratorStygia.currentWorld.getBlock(chunkX, y, chunkZ);
             blockA = BiomeDecoratorStygia.currentWorld.getBlock(chunkX, y - 1, chunkZ);
             if(block != Blocks.air || block != Blocks.water){
             if(blockA != Blocks.air || blockA != Blocks.water){
             BiomeDecoratorStygia.bigTree.generate(BiomeDecoratorStygia.currentWorld, BiomeDecoratorStygia.rand, chunkX, y, chunkZ);
             }
             }
             }
             }
             if(biome == BiomeRegistry.biomeStygianSea){
             //GENERARTE Stygian Sea STUFF HERE
             }*/
        }
    }

    /**
     * Prepare ores for generation
     */
    private static void initOres()
    {
        //glowstone used fotr testing generation
        //ore = new WorldGenMinable(Blocks.ore, 30, BlockHelper.forBlock(Blocks.stone);
    }

    /**
     * Geberate Ores In a Biome
     *
     * @param biome
     */
    private static void generateOreInBiome(BiomeGenBase biome)
    {
        if (biome == BiomeRegistry.biomeStygianSea) {
            //genStandardOre(20, glowStone, 0, 128);
        }
    }

    /**
     * Generate ores in chunks.
     *
     * @param spawnWeight
     * @param generatorToSpawn
     * @param minSpawnHeight
     * @param maxYSpawnHeight
     */
    private static void genStandardOre(int spawnWeight, WorldGenerator generatorToSpawn, int minSpawnHeight, int maxYSpawnHeight)
    {
        for (int l = 0; l < spawnWeight; ++l) {
            int i1 = BiomeDecoratorStygia.chunkX + BiomeDecoratorStygia.rand.nextInt(16);
            int j1 = BiomeDecoratorStygia.rand.nextInt(maxYSpawnHeight - minSpawnHeight) + minSpawnHeight;
            int k1 = BiomeDecoratorStygia.chunkZ + BiomeDecoratorStygia.rand.nextInt(16);
            generatorToSpawn.generate(BiomeDecoratorStygia.currentWorld, BiomeDecoratorStygia.rand, new BlockPos(i1, j1, k1));
        }
    }
}