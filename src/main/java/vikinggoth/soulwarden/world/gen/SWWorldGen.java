package vikinggoth.soulwarden.world.gen;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.registers.BlockRegister;

import java.util.Random;

/**
 * Created by Friedrich on 5/31/2016.
 */
public class SWWorldGen implements IWorldGenerator
{
    //Ores
    private WorldGenerator soulgemOre;

    //Nether Ores
    private WorldGenerator netherSoulgemOre;

    //Stygian Ores
    private WorldGenerator stygiaSoulgemOre;
    private WorldGenerator stygiaSoulgemOreBlack;
    private WorldGenerator stygiaPewterOre;
    private WorldGenerator stygiaHematiteOre;
    private WorldGenerator stygiaEmberOre;

    public SWWorldGen()
    {
        //Overworld
        this.soulgemOre = new SWWorldGenMinable(BlockRegister.ore_soulgem.getStateFromMeta(0), 8);

        //Nether
        this.netherSoulgemOre = new SWWorldGenNetherMinable(BlockRegister.ore_soulgem.getStateFromMeta(1), 11);

        //Stygia
        this.stygiaSoulgemOre = new SWWorldGenStygiaMinable(BlockRegister.ore_soulgem.getStateFromMeta(2), 15);

        //TODO make a custom one for black soul gems to handle the single block
        this.stygiaSoulgemOreBlack = new SWWorldGenStygiaMinable(BlockRegister.ore_soulgem_black.getDefaultState(), 1);

        this.stygiaPewterOre = new SWWorldGenStygiaMinable(BlockRegister.ore_pewter.getDefaultState(), 8);

        this.stygiaHematiteOre = new SWWorldGenStygiaMinable(BlockRegister.ore_hematite.getDefaultState(), 4);

        this.stygiaEmberOre = new SWWorldGenStygiaMinable(BlockRegister.ore_ember.getDefaultState(), 8);

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
                         IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimensionId())
        {
            case 0: //Overworld
            {
                //Ores
                this.runGenerator(this.soulgemOre, world, random, chunkX, chunkZ, 40, 0, 255);
            }
            case -1: //Nether
            {
                //Ores
                this.runGenerator(this.netherSoulgemOre, world, random, chunkX, chunkZ, 20, 0, 127);
            }
            case 1: //End
            {

            }
            default:
            {
                if(world.provider.getDimensionId() == ConfigurationHandler.dimStygiaID)
                {
                    //Ores
                    this.runGenerator(this.stygiaSoulgemOre, world, random, chunkX, chunkZ, 20, 0, 255);
                    this.runGenerator(this.stygiaSoulgemOreBlack, world, random, chunkX, chunkZ, 15, 0, 255);
                    this.runGenerator(this.stygiaPewterOre, world, random, chunkX, chunkZ, 25, 0, 128);
                    this.runGenerator(this.stygiaHematiteOre, world, random, chunkX, chunkZ, 20, 0, 255);
                    this.runGenerator(this.stygiaEmberOre, world, random, chunkX, chunkZ, 20, 64, 255);
                }
            }
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ,
                              int chanceToSpawn, int minHeight, int maxHeight)
    {
        if(minHeight < 0)
        {
            throw new IllegalArgumentException("Ore Generation: Minimum Height out of bounds");
        }
        else if(maxHeight > 256)
        {
            throw new IllegalArgumentException("Ore Generation: Maximum Height out of bounds");
        }
        else if(minHeight > maxHeight)
        {
            throw new IllegalArgumentException("Ore Generation: Minimum Height greater than Maximum Height");
        }

        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < chanceToSpawn; ++i)
        {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }

    }
}
