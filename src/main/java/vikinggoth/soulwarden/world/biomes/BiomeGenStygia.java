package vikinggoth.soulwarden.world.biomes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import vikinggoth.soulwarden.blocks.BlockSoulStone;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.registers.BlockRegister;

import java.util.Random;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class BiomeGenStygia extends BiomeGenBase
{
    public static final BiomeGenBase stygiaSea = (new BiomeGenStygiaSea(ConfigurationHandler.biomeStygiaSeaID)).setBiomeName("StygiaSea").setHeight(height_DeepOceans);
    public static final BiomeGenBase boneBeach = (new BiomeGenBoneBeach(ConfigurationHandler.biomeBoneBeachID)).setBiomeName("BoneBeach").setHeight(height_PartiallySubmerged);
    public static final BiomeGenBase forestNormal = (new BiomeGenStygiaForest(ConfigurationHandler.biomeForestID)).setBiomeName("StygianForest").setHeight(height_RockyWaters);

    private Block stone;

    public BiomeGenStygia(int id)
    {
        this(id, true);
    }

    public BiomeGenStygia(int id, boolean register)
    {
        super(id, register);
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = BlockRegister.soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.CRACKED);
        this.fillerBlock = BlockRegister.soulStone.getDefaultState();
        this.stone = BlockRegister.soulStone;
        this.waterColorMultiplier = 0x4B006E;
        this.theBiomeDecorator = new BiomeDecoratorStygia();
        this.setDisableRain();
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int p_180622_4_, int p_180622_5_, double p_180622_6_)
    {
        this.generateModdedBiomeTerrain(worldIn, rand, chunkPrimerIn, p_180622_4_, p_180622_5_, p_180622_6_);
    }

    public void generateModdedBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int p_180628_4_, int p_180628_5_, double p_180628_6_)
    {
        int i = worldIn.getSeaLevel();
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int)(p_180628_6_ / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = p_180628_4_ & 15;
        int i1 = p_180628_5_ & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j1 = 255; j1 >= 0; --j1)
        {
            if (j1 <= rand.nextInt(5))
            {
                chunkPrimerIn.setBlockState(i1, j1, l, Blocks.bedrock.getDefaultState());
            }
            else
            {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getBlock().getMaterial() == Material.air)
                {
                    j = -1;
                }
                else if (iblockstate2.getBlock() == this.stone)
                {
                    if (j == -1)
                    {
                        if (k <= 0)
                        {
                            iblockstate = null;
                            iblockstate1 = this.stone.getDefaultState();
                        }
                        else if (j1 >= i - 4 && j1 <= i + 1)
                        {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        /*
                        if (j1 < i && (iblockstate == null || iblockstate.getBlock().getMaterial() == Material.air))
                        {
                            if (this.getFloatTemperature(blockpos$mutableblockpos.set(p_180628_4_, j1, p_180628_5_)) < 0.15F)
                            {
                                iblockstate = Blocks.ice.getDefaultState();
                            }
                            else
                            {
                                iblockstate = Blocks.water.getDefaultState();
                            }
                        }*/

                        j = k;

                        if (j1 >= i - 1)
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        }
                        else if (j1 < i - 7 - k)
                        {
                            iblockstate = null;
                            iblockstate1 = this.stone.getDefaultState();
                            chunkPrimerIn.setBlockState(i1, j1, l, topBlock);
                        }
                        else
                        {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                        }
                    }
                    else if (j > 0)
                    {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

                        /*if (j == 0 && iblockstate1.getBlock() == BlockRegister.bonePile)
                        {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
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

    }

    /**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     */
    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new BiomeDecoratorStygia());
    }
}
