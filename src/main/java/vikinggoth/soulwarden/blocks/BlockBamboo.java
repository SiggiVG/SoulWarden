package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.registers.BlockRegister;

import java.util.Random;

/**
 * Created by Friedrich on 1/27/2016.
 */
public class BlockBamboo extends BlockReed
{
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (worldIn.getBlockState(pos.down()).getBlock() == BlockRegister.boneboo || this.checkForDrop(worldIn, pos, state))
        {
            if (worldIn.isAirBlock(pos.up()))
            {
                int i;

                for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i)
                {
                    ;
                }
                //This right here is the growth height. Sugarcane defaults to 3
                //TODO have them grow taller in Stygia
                if (i < 5)
                {
                    int j = ((Integer)state.getValue(AGE)).intValue();
                    System.out.println(j);
                    if (j > 5)
                    {
                        worldIn.setBlockState(pos.up(), this.getDefaultState());
                        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0)), 4);
                    }
                    else
                    {
                        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 4);
                    }
                }
            }
        }
    }

    //TODO make placable on the boneboo sapling, which will turn the sapling into boneboo.
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return true;//block == this;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return /*this.canPlaceBlockAt(worldIn, pos) */ block == this || block == BlockRegister.graveSoil || block == BlockRegister.grassCemetery || block == BlockRegister.bonePile;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockRegister.boneboo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(BlockRegister.boneboo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return 16777215;
    }


}
