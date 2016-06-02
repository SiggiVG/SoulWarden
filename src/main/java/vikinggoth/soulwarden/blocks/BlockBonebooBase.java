package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vikinggoth.soulwarden.registers.BlockRegister;

import java.util.List;
import java.util.Random;

/**
 * Created by Friedrich on 6/1/2016.
 */
public class BlockBonebooBase extends BlockCrops
{
    public BlockBonebooBase()
    {
        super();
        float pixel = 1.0F/16F; //float f = 0.375F;
        this.setBlockBounds(0.5F - pixel*5, 0.0F, 0.5F - pixel*5, 0.5F + pixel*5, 1.0F, 0.5F + pixel*5);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        //float f = 0.2F;
        float pixel = 1.0F/16F;
        return new AxisAlignedBB((double)pos.getX() + 0.5F - pixel*5, (double)pos.getY(), (double)pos.getZ() + 0.5F - pixel*5, (double)pos.getX() + 0.5F + pixel*5, (double)pos.getY() + 1.0F, (double)pos.getZ() + 0.5F + pixel*5);
    }

    /**
     * is the block grass, dirt or farmland
     */
    @Override
    protected boolean canPlaceBlockOn(Block ground)
    {
        return ground == Blocks.farmland || ground == BlockRegister.graveSoilTilled; //have grow on bone piles?
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            if (worldIn.isAirBlock(pos.up()))
            {
                if (((Integer) state.getValue(AGE)).intValue() >= 7)
                {
                    float f = getGrowthChance(this, worldIn, pos);

                    if (rand.nextInt((int) (25.0F / f) + 1) == 0)
                    {
                        worldIn.setBlockState(pos.up(), BlockRegister.boneboo.getDefaultState(), 3);
                    }
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockRegister.bonebooBase);
    }

    @Override
    protected Item getSeed()
    {
        return Item.getItemFromBlock(BlockRegister.bonebooBase);
    }

    @Override
    protected Item getCrop()
    {
        return Items.bone;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }

    @Override
    public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);
        for(int i = 0; i < count; i++)
        {
            Item item = this.getItemDropped(state, rand, fortune);
            if (item != null)
            {
                ret.add(new ItemStack(item, 1, this.damageDropped(state)));
            }
        }
        return ret;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(BlockRegister.bonebooBase);
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Crop;
    }
}
