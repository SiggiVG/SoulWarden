package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.registers.BlockRegister;
import vikinggoth.soulwarden.registers.ItemRegister;

import java.util.Random;

/**
 * Created by Friedrich on 6/1/2016.
 */
public class BlockBonebooFruit extends BlockBush
{

    public BlockBonebooFruit()
    {
        super(Material.gourd);
        float pixel = 1.0F/16F;
        this.setBlockBounds(0.5F - pixel*6, 0.0F, 0.5F - pixel*6, 0.5F + pixel*6, 1.0F - pixel*4, 0.5F + pixel*6);
        this.setStepSound(SLIME_SOUND);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if(worldIn.getBlockState(pos.down()).getBlock() == BlockRegister.boneboo)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemRegister.bonebooFruit;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return ItemRegister.bonebooFruit;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        return new ItemStack(ItemRegister.bonebooFruit);
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Crop;
    }
}
