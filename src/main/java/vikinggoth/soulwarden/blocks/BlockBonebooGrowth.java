package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.registers.BlockRegister;

import java.util.Random;

/**
 * Created by Friedrich on 1/27/2016.
 */
public class BlockBonebooGrowth extends BlockReed
{

    //TODO attach to an ItemBlock, such that the item you place is a 'sprout' which grows boneboo above it. Breaking boneboo grants bones
    //TODO have them also occaisionally produce fruit instead of a new section of growth

    public BlockBonebooGrowth()
    {
        super();
        float pixel = 1.0F/16F;
        this.setBlockBounds(pixel*6F, 0.0F, pixel*6F, pixel*10F, 1.0F, pixel*10F);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        //float f = 0.2F;
        float pixel = 1.0F/16F;
        return new AxisAlignedBB((double)pos.getX() + 0.5F - pixel*2, (double)pos.getY(), (double)pos.getZ() + 0.5F - pixel*2, (double)pos.getX() + 0.5F + pixel*2, (double)pos.getY() + 1.0F, (double)pos.getZ() + 0.5F + pixel*2);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (worldIn.getBlockState(pos.down()).getBlock() == BlockRegister.boneboo || this.checkForDrop(worldIn, pos, state))
        {
            if (worldIn.isAirBlock(pos.up()))
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
                        int j = ((Integer) state.getValue(AGE)).intValue();
                        //System.out.println(j);
                        if (j > 7)
                        {
                            if (rand.nextInt(10) == 0)
                            {
                                worldIn.setBlockState(pos.up(), BlockRegister.bonebooFruitBlock.getDefaultState()); //sets block above to boneboo fruit
                            }
                            else
                            {
                                worldIn.setBlockState(pos.up(), this.getDefaultState()); //sets block above to boneboo growth
                            }
                            //worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0)), 4);
                        }
                        else
                        {
                            worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(j + 1)), 2);
                        }
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
        return block == this || block == BlockRegister.bonebooBase;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return /*this.canPlaceBlockAt(worldIn, pos) */ block == this || block == BlockRegister.bonebooBase;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.bone;//BlockRegister.boneboo);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return 1 + random.nextInt(2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(BlockRegister.bonebooBase);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(BlockRegister.bonebooBase);
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Crop;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return 1;
    }


}
