package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.registers.BlockRegister;

/**
 * Created by Friedrich on 12/10/2015.
 */
public class BlockSoulgem extends Block
{

    private boolean ignoreSimilarity;

    public BlockSoulgem() {
        super(Material.rock,  MapColor.diamondColor);

        this.ignoreSimilarity = true;

        this.setHardness(5.0F);
        this.setResistance(10.0F);

    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    public boolean isFullCube()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (this == BlockRegister.block_soulgem || this == BlockRegister.block_soulgem_black)
        {
            if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate)
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }
        }

        return !(!this.ignoreSimilarity && block == this) && super.shouldSideBeRendered(worldIn, pos, side);
    }
}
