package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.items.itemblocks.IMetaBlockName;

import java.util.List;

/**
 * Created by Friedrich on 11/13/2015.
 */
public class BlockLogSW extends BlockLog implements IMetaBlockName
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockLogSW()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.GHOUL).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        this.setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(itemIn, 1, EnumType.GHOUL.getMetadata()));
        list.add(new ItemStack(itemIn, 1, EnumType.WEEPWILLOW.getMetadata()));
        list.add(new ItemStack(itemIn, 1, EnumType.BONEBEECH.getMetadata()));
        list.add(new ItemStack(itemIn, 1, EnumType.HAND.getMetadata()));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata((meta & 3) % 4));

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((EnumType)state.getValue(VARIANT)).getMetadata();

        switch (BlockLogSW.SwitchEnumAxis.AXIS_LOOKUP[((BlockLog.EnumAxis)state.getValue(LOG_AXIS)).ordinal()])
        {
            case 1:
                i |= 4;
                break;
            case 2:
                i |= 8;
                break;
            case 3:
                i |= 12;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT, LOG_AXIS});
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumType)state.getValue(VARIANT)).getMetadata());
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
    }

    public static enum EnumType implements IStringSerializable
    {
        GHOUL(0, "ghoul"),
        WEEPWILLOW(1, "weepwillow"),
        BONEBEECH(2, "bonebeech"),
        HAND(3, "hand"); //wax hand

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        private EnumType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = name;
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
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
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
            return this.unlocalizedName;
        }

        static
        {
            EnumType[] stateArray = values();
            int var1 = stateArray.length;

            for (int i = 0; i < var1; ++i)
            {
                EnumType metas = stateArray[i];
                META_LOOKUP[metas.getMetadata()] = metas;
            }
        }
    }

    static final class SwitchEnumAxis
    {
        static final int[] AXIS_LOOKUP = new int[BlockLog.EnumAxis.values().length];

        static
        {
            try
            {
                AXIS_LOOKUP[BlockLog.EnumAxis.X.ordinal()] = 1;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try
            {
                AXIS_LOOKUP[BlockLog.EnumAxis.Z.ordinal()] = 2;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                AXIS_LOOKUP[BlockLog.EnumAxis.NONE.ordinal()] = 3;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }
}
