package vikinggoth.soulwarden.blocks.containers;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vikinggoth.soulwarden.tileentitites.TileSoulTrans;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockSoulTrans extends BlockContainer
{
    public static final PropertyDirection PYLONSIDE = PropertyDirection.create("pylonside");
    public static final PropertyBool HASPARTNER = PropertyBool.create("haspartner");

    protected BlockSoulTrans()
    {
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(PYLONSIDE, BlockSoulPylon.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockSoulPylon.EnumType)state.getValue(PYLONSIDE)).getMetadata();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {PYLONSIDE, HASPARTNER});
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        return state.withProperty(HASPARTNER, ((TileSoulTrans)worldIn.getTileEntity(pos)).hasPartner());
    }
}
