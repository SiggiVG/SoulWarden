package vikinggoth.soulwarden.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Friedrich on 8/19/2015.
 */
public class ItemSoulPylon extends ItemBlock
{
    public ItemSoulPylon(Block block)
    {
        super(block);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        if(side == EnumFacing.DOWN)
        {
            pos = pos.offset(EnumFacing.DOWN, 4);
            side = EnumFacing.UP;
        }
        if (block == Blocks.snow_layer && ((Integer)iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1)
        {
            side = EnumFacing.UP;
        }
        else if (!(block.isReplaceable(worldIn, pos)))
        {
            pos = pos.offset(side);
        }

        //TODO check if the above blocks are available

        /*if((block.isAir(worldIn, pos.offset(EnumFacing.UP)) && block.isAir(worldIn, pos.offset(EnumFacing.UP, 2))))
        {
            System.out.println("above blocks not air");
            return false;
        }*/

        if (stack.stackSize == 0)
        {
            System.out.println("stacksize == 0");
            return false;
        }
        else if (!playerIn.canPlayerEdit(pos, side, stack))
        {
            System.out.println("player cant edit");
            return false;
        }
        else if (pos.getY() == 255 && this.block.getMaterial().isSolid())
        {
            System.out.println("too high");
            return false;
        }
        else if (worldIn.canBlockBePlaced(this.block, pos, false, side, (Entity)null, stack))
        {
            IBlockState iblockstate1 = this.block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, playerIn);
            IBlockState iblockstate2 = this.block.onBlockPlaced(worldIn, pos.offset(EnumFacing.UP), side, hitX, hitY, hitZ, 1, playerIn);
            IBlockState iblockstate3 = this.block.onBlockPlaced(worldIn, pos.offset(EnumFacing.UP, 2), side, hitX, hitY, hitZ, 2, playerIn);

            if (placeBlockAt(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ, iblockstate1) && placeBlockAt(stack, playerIn, worldIn, pos.offset(EnumFacing.UP), side, hitX, hitY, hitZ, iblockstate2) && placeBlockAt(stack, playerIn, worldIn, pos.offset(EnumFacing.UP, 2), side, hitX, hitY, hitZ, iblockstate3))
            {
                worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 1.5F), (double)((float)pos.getZ() + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume() + 1.0F) / 2.0F, this.block.stepSound.getFrequency() * 0.8F);
                --stack.stackSize;
            }

            return true;
        }
        else
        {
            System.out.println("couldnt place");
            return false;
        }
    }
}
