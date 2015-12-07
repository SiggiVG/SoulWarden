package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockSlab;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.SoulWarden;
import vikinggoth.soulwarden.registries.BlockRegistry;

import java.util.List;
import java.util.Random;

/**
 * Created by Friedrich on 11/12/2015.
 */
public abstract class BlockSoulStoneSlab extends BlockSlab
{
    private static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockSoulStoneSlab(Material materialIn)
    {
        super(materialIn);
        IBlockState blockState = this.blockState.getBaseState();
        if(!this.isDouble())
        {
            blockState = blockState.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        this.setDefaultState(blockState.withProperty(VARIANT, EnumType.DEFAULT));
        this.setCreativeTab(SoulWarden.SWTab);
        this.useNeighborBrightness = !this.isDouble();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockRegistry.soulStoneSlab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(BlockRegistry.soulStoneSlab);
    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + EnumType.byMetadata(meta).getUnlocalizedName();
    }

    @Override
    public IProperty getVariantProperty()
    {
        return this.VARIANT;
    }

    @Override
    public Object getVariant(ItemStack stack)
    {
        return EnumType.byMetadata(stack.getMetadata() & 7);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        if (itemIn != Item.getItemFromBlock(BlockRegistry.soulStoneSlabDouble))
        {
            EnumType[] aenumtype = EnumType.values();
            int i = aenumtype.length - 2;

            for (int j = 0; j < i; ++j) {
                EnumType enumtype = aenumtype[j];
                list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(final int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 7));

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((EnumType)state.getValue(VARIANT)).getMetadata();

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return this.isDouble() ? new BlockState(this, new IProperty[] {VARIANT}): new BlockState(this, new IProperty[] {HALF, VARIANT});
    }

    @Override
    public int damageDropped(final IBlockState state) {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    public static enum EnumType implements IStringSerializable
    {
        //normal variants
        DEFAULT(0, "default"), //1x3 default or cooked from vanilla stoneslab in sepulter //has special slab
        SMOOTH(1, "smooth"), //1x3 smooth //has slab, wall
        //brick variants
        BRICK(2, "brick"), //1x3 brick or cooked from brickslab in sepulter = 4 brick, cooked from stone bricks in sepulter //has stair, slab, wall
        //cobble variants
        COBBLE(3, "cobble"), //1x3 soulcobble, cooked from cobblestoneslab in sepulter //has stair, slab, wall
        SCATTERED(4, "scattered"), //1x3 scattered //has stair, slab, wall
        SMALL(5, "small"), //1x3 small //has stair, slab, wall
        //special variants
        OTHER(6, "unused"),
        OTHER2(7, "unused2");

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
                meta = 0 & 7;
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
