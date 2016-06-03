package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import vikinggoth.soulwarden.registers.BlockRegister;

import java.util.Random;

/**
 * Created by Friedrich on 11/18/2015.
 */
public class BlockGrassCemetery extends BlockGrass
{
    public BlockGrassCemetery()
    {
        super();
        this.setStepSound(Block.soundTypeGrass);
        this.setHardness(0.6F);
    }
    /*//TODO make colorizers
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return this.getBlockColor();
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
    }*/

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getBlock().getLightOpacity(worldIn, pos.up()) > 2)
            {
                worldIn.setBlockState(pos, BlockRegister.graveSoil.getDefaultState());
            }
            else
            {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        Block block = worldIn.getBlockState(blockpos1.up()).getBlock();
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

                        if (iblockstate1.getBlock() == BlockRegister.graveSoil && worldIn.getLightFromNeighbors(blockpos1.up()) >= 4 && block.getLightOpacity(worldIn, blockpos1.up()) <= 2)
                        {
                            worldIn.setBlockState(blockpos1, BlockRegister.grassCemetery.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Blocks.dirt.getItemDropped(BlockRegister.graveSoil.getDefaultState(), rand, fortune);
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos1 = pos.up();
        int i = 0;

        while (i < 128)
        {
            BlockPos blockpos2 = blockpos1;
            int j = 0;

            while (true)
            {
                if (j < i / 16)
                {
                    blockpos2 = blockpos2.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                    if (worldIn.getBlockState(blockpos2.down()).getBlock() == BlockRegister.graveSoil && !worldIn.getBlockState(blockpos2).getBlock().isNormalCube())
                    {
                        ++j;
                        continue;
                    }
                }
                else if (worldIn.isAirBlock(blockpos2))
                {
                    if (rand.nextInt(8) == 0)
                    {
                        worldIn.getBiomeGenForCoords(blockpos2).plantFlower(worldIn, rand, blockpos2);
                    }
                    else
                    {
                        IBlockState iblockstate2 = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);

                        if (Blocks.tallgrass.canBlockStay(worldIn, blockpos2, iblockstate2))
                        {
                            worldIn.setBlockState(blockpos2, iblockstate2, 3);
                        }
                    }
                }

                ++i;
                break;
            }
        }
    }
}
