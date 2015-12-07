package vikinggoth.soulwarden.blocks.containers;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockSoulPylon extends BlockContainer
{

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);
    public static final PropertyBool HASPINNACLE = PropertyBool.create("haspinnacle");

    public BlockSoulPylon()
    {
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        if(meta == 0)
        {
            //return new tilesoulpylon
        }
        else if (meta == 3)
        {
            //retuen new tilesoulpinnacle
        }
        return null;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT, HASPINNACLE});
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        return new ItemStack(Item.getItemFromBlock(this));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        /*
        for(int i = 0; i < 4; ++i) {
            BlockPos currentPos = new BlockPos(pos);
            if (worldIn.getBlockState(currentPos).getBlock() == BlockRegistry.soulPylon && ((BlockSoulPylon.EnumType)worldIn.getBlockState(currentPos).getValue(VARIANT)).getMetadata() == 3)
            {
                return state.withProperty(HASPINNACLE, true);
            }
            currentPos.add(0, i, 0);
        }*/
        return state.withProperty(HASPINNACLE, false);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
        int meta = state.getBlock().getMetaFromState(state);
        pos = pos.offset(EnumFacing.DOWN, meta);
        state = worldIn.getBlockState(pos);

        breakBlock(worldIn, pos, state);
        worldIn.setBlockToAir(pos);
        worldIn.setBlockToAir(pos.offset(EnumFacing.UP));
        worldIn.setBlockToAir(pos.offset(EnumFacing.UP, 2));

        //TODO have it break attached Pinnacles and trans
    }

    public static enum EnumType implements IStringSerializable
    {
        //normal variants
        BASE(0, "base"),
        MIDDLE(1, "middle"),
        TOP(2, "top"),
        PINNACLE(3, "pinnacle");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name;

        private EnumType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.name;
        }

        static {
            EnumType[] stateArray = values();
            int var1 = stateArray.length;

            for (int i = 0; i < var1; ++i) {
                EnumType metas = stateArray[i];
                META_LOOKUP[metas.getMetadata()] = metas;
            }
        }
    }
}
