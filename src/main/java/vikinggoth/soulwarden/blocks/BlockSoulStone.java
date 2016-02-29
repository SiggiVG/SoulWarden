package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.items.itemblocks.IMetaBlockName;

import java.util.List;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockSoulStone extends Block implements IMetaBlockName
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockSoulStone()
    {
        super(Material.rock);
        this.setHardness(1.5F);
        this.setResistance(10F);
        this.setHarvestLevel("pickaxe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.DEFAULT));
    }

    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of block with the same ID, but different meta (eg: wood returns 4 block)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        EnumType[] aenumtype = EnumType.values();
        int i = aenumtype.length;

        for (int j = 0; j < i; ++j)
        {
            EnumType enumtype = aenumtype[j];
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
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
        return new BlockState(this, new IProperty[] {VARIANT});
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
    }

    public static enum EnumType implements IStringSerializable
    {
        //normal variants
        DEFAULT(0, "default"), //has a special slab with different top texture, cooked from vanilla stone in sepulter //has special slab
        MOSSY(1, "mossy"), // recipe: default + vine, Perhaps make this a smooth variant //has a wall
        CRACKED(2, "cracked"), //cooked from default in furnace
        SMOOTH(3, "smooth"), //has slab, has wall, 2x2 default = 4 smooth //has slab, wall
        //brick variants
        BRICK(4, "brick"), //has slab, has wall, recipe: 2x2 smooth = 4 brick, cooked from stone bricks in sepulter //has stair, slab, wall
        BRICK_MOSSY(5, "brick_mossy"), //has wall, recipe: brick + vine //has a wall
        BRICK_CRACKED(6, "brick_cracked"), //cooked from default in furnace
        CHISELED(7, "chiseled"), //brick slab over brick slab
        //cobble variants
        COBBLE(8, "cobble"), //has slab, has wall, cooked from cracked default in furnace, cooked from cobblestone in sepulter //has stair, slab, wall
        COBBLE_MOSSY(9, "cobble_mossy"), //has wall, recipe: cobble + vine //has a wall
        SCATTERED(10, "scattered"), //smallstone, cooked from BRICK_SMALL in furnace //has stair, slab, wall
        SMALL(11, "small"), //has slab, recipe: 2x2 cobble = 4 smallbrick //has stair, slab, wall
        //stone variants
        SODALITE(12, "sodalite"),
        SODALITE_POLISHED(13, "sodalite_smooth"),
        NACHTITE(14, "nachtite"),
        NACHTITE_POLISHED(15, "nachtite_smooth");

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
