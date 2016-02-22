package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.items.itemblocks.IMetaBlockName;
import vikinggoth.soulwarden.registries.ItemRegister;

import java.util.List;
import java.util.Random;

/**
 * Created by Friedrich on 12/2/2015.
 *
 * This is the base class to the urns added by Soul Warden
 *
 *
 */
public abstract class BlockUrn extends Block implements IMetaBlockName
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockUrn()
    {
        super(Material.clay);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.SOULSTONE));
        this.useNeighborBrightness = true;
        //this.setHarvestLevel();
        this.setStepSound(Block.soundTypeGlass);
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

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        int num = random.nextInt(10) - 4;
        if (num < 0) num = 0;
        return num;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.dye;
    }

    public int damageDropped(IBlockState state)
    {
        return EnumDyeColor.WHITE.getDyeDamage();
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && World.doesBlockHaveSolidTopSurface(worldIn, pos.down());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
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

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
    }

    public static enum EnumType implements IStringSerializable
    {
        //normal variants
        SOULSTONE(0, "soulstone"),
        PORCELAIN(1, "porcelain"),
        CLAY(2, "clay"),
        GREEK(3, "greek");

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
