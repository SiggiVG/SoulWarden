package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Friedrich on 12/7/2015.
 */
public class BlockSoulstoneTwisted extends BlockRotatedPillar
{

    public BlockSoulstoneTwisted() {
        super(Material.rock);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, this.fromFacingAxis(facing.getAxis()));
    }

    public static EnumFacing.Axis fromFacingAxis(EnumFacing.Axis axis)
    {
        switch (axis.ordinal())
        {
            case 0:
                return EnumFacing.Axis.X;
            case 1:
                return EnumFacing.Axis.Y;
            case 2:
                return EnumFacing.Axis.Z;
            default:
                return EnumFacing.Axis.Y;
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AXIS, meta == 0 ? EnumFacing.Axis.X : meta == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.Y);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing.Axis)state.getValue(AXIS)).ordinal();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {AXIS});
    }
}
