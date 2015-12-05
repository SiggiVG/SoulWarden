package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
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

import java.util.List;

/**
 * Created by Friedrich on 11/13/2015.
 */
public class BlockSoulStoneWall extends BlockWallBase
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockSoulStoneWall(Block modelBlock)
    {
        super(modelBlock);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(VARIANT, EnumType.SMOOTH));

    }

    /**
     * returns a list of block with the same ID, but different meta (eg: wood returns 4 block)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(itemIn, 1, 3));
        list.add(new ItemStack(itemIn, 1, 1));
        list.add(new ItemStack(itemIn, 1, 2));
        list.add(new ItemStack(itemIn, 1, 4));
        list.add(new ItemStack(itemIn, 1, 5));
        list.add(new ItemStack(itemIn, 1, 6));
        list.add(new ItemStack(itemIn, 1, 8));
        list.add(new ItemStack(itemIn, 1, 9));
        list.add(new ItemStack(itemIn, 1, 10));
        list.add(new ItemStack(itemIn, 1, 11));
        /*
        EnumType[] aenumtype = EnumType.values();
        int i = aenumtype.length - 1;

        for (int j = 0; j < i; ++j) {
            EnumType enumtype = aenumtype[j];
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }*/
    }

    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumType) state.getValue(VARIANT)).getMetadata();
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
        return ((EnumType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[]{UP, NORTH, EAST, WEST, SOUTH, VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
    }

    public static enum EnumType implements IStringSerializable
    {
        //normal variants
        DEFAULT(0, "default"), //has a special slab with different top texture, cooked from vanilla stone in sepulter //has special slab, and a wall, but wall is command given only
        MOSSY(1, "mossy"), // recipe: default + vine, Perhaps make this a smooth variant //has a wall
        CRACKED(2, "cracked"), //cooked from default in furnace, stalagmite wall? add a fence too? will this be the natural variety in the Necropolis?
        SMOOTH(3, "smooth"), //has slab, has wall, 2x2 default = 4 smooth //has slab, wall
        //brick variants
        BRICK(4, "brick"), //has slab, has wall, recipe: 2x2 smooth = 4 brick, cooked from stone bricks in sepulter //has stair, slab, wall
        BRICK_MOSSY(5, "brick_mossy"), //has wall, recipe: brick + vine //has a wall
        BRICK_CRACKED(6, "brick_cracked"), //cooked from default in furnace
        CHISELED(7, "chiseled"), //brick slab over brick slab //has a wall, but is command given only
        //cobble variants
        COBBLE(8, "cobble"), //has slab, has wall, cooked from cracked default in furnace, cooked from cobblestone in sepulter //has stair, slab, wall
        COBBLE_MOSSY(9, "cobble_mossy"), //has wall, recipe: cobble + vine //has a wall
        SCATTERED(10, "scattered"), //smallstone, cooked from BRICK_SMALL in furnace //has stair, slab, wall
        SMALL(11, "small"); //has slab, recipe: 2x2 cobble = 4 smallbrick //has stair, slab, wall

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
            return this.name;
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
}
