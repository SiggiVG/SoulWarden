package vikinggoth.soulwarden.world.biome;

import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.chunk.ChunkPrimer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vikinggoth.soulwarden.registries.BlockRegister;
import vikinggoth.soulwarden.world.biome.decorators.BiomeDecoratorStygia;

import java.util.Random;

/**
 * Created by Friedrich on 1/28/2016.
 */
public class BiomeGenStygia extends BiomeGenBase
{
    //use default logger
    private static final Logger logger = LogManager.getLogger();
    /** Heights, These will all be different from the overworld, as sea level is half the world height **/
    protected static final BiomeGenBase.Height height_Stygia = new BiomeGenBase.Height(0.1F, 0.2F);

    /** Overworld biomes */

    /** All the Biomes in Stygia */
    public static final BiomeGenBase biomeStygianSea = new BiomeGenOcean(64).setColor(112).setBiomeName("Ocean").setHeight(height_Oceans);

    public BiomeGenStygia(int biomeId)
    {
        super(biomeId);
        this.topBlock = BlockRegister.grassCemetery.getDefaultState();
        this.fillerBlock = BlockRegister.graveSoil.getDefaultState();
        this.fillerBlockMetadata = 0;
        this.theBiomeDecorator = new BiomeDecoratorStygia();

        this.spawnableMonsterList = Lists.newArrayList();
        this.spawnableCreatureList = Lists.newArrayList();
        this.spawnableWaterCreatureList = Lists.newArrayList();
        this.spawnableCaveCreatureList = Lists.newArrayList();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 12, 4, 4));
        this.spawnableWaterCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySquid.class, 10, 4, 4));
        this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 10, 8, 8));
    }

    @Override
    public void genTerrainBlocks(World worldObj, Random rand, ChunkPrimer primer, int chunkX, int chunkZ, double num)
    {
        generateSWBiomeTerrain(worldObj, rand, primer, chunkX, chunkZ, num);
    }

    public final void generateSWBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimer, int x, int z, double dnum)
    {
        boolean flag = true;
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int k = -1;
        int l = (int) (dnum / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = z & 15;

        for (int k1 = 255; k1 >= 0; --k1)
        {
            if (k1 <= rand.nextInt(5))
            {
                chunkPrimer.setBlockState(j1, k1, i1, Blocks.bedrock.getDefaultState());
            } else
            {
                IBlockState iblockstate2 = chunkPrimer.getBlockState(j1, k1, i1);

                if (iblockstate2.getBlock().getMaterial() == Material.air)
                {
                    k = -1;
                } else if (iblockstate2 == BlockRegister.stoneSW.getDefaultState())
                {
                        // || iblockstate2 == BlockRegister.stoneSW.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.CRACKED)) {
                    if (k == -1)
                    {
                        if (l <= 0)
                        {
                            iblockstate = null;
                            iblockstate1 = BlockRegister.stoneSW.getDefaultState();
                        } else if (k1 >= 59 && k1 <= 64)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (k1 < 63 && (iblockstate == null || iblockstate.getBlock().getMaterial() == Material.air)) {
                            if (this.getFloatTemperature(new BlockPos(x, k1, z)) < 0.15F)
                            {
                                iblockstate = Blocks.ice.getDefaultState();
                            } else
                            {
                                iblockstate = Blocks.water.getDefaultState();
                            }
                        }

                        k = l;

                        if (k1 >= 62)
                        {
                            chunkPrimer.setBlockState(j1, k1, i1, iblockstate);
                        } else if (k1 < 56 - l)
                        {
                            iblockstate = null;
                            iblockstate1 = BlockRegister.soulStone.getDefaultState();
                            chunkPrimer.setBlockState(j1, k1, i1, BlockRegister.bonePile.getDefaultState());
                        } else
                        {
                            chunkPrimer.setBlockState(j1, k1, i1, iblockstate1);
                        }
                    } else if (k > 0)
                    {
                        --k;
                        chunkPrimer.setBlockState(j1, k1, i1, iblockstate1);

                        /*if (k == 0 && iblockstate1.getBlock() == Blocks.sand) {
                            k = rand.nextInt(4) + Math.max(0, k1 - 63);
                            iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? Blocks.red_sandstone.getDefaultState() : Blocks.sandstone.getDefaultState();
                        }*/
                    }
                }
            }
        }
    }


    /**
     * Adds the default flowers, as of 1.7, it is 2 yellow, and 1 red. I chose 10 to allow some wiggle room in the numbers.
     */
    @Override
    public void addDefaultFlowers()
    {
        //addFlower(Blocks.yellow_flower.getDefaultState().withProperty(Blocks.yellow_flower.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 20);
        //addFlower(Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 20);
    }
}