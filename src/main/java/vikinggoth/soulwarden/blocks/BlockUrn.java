package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
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

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        return true;
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

    /**
     * In the future, there are 2 choices for Urns
     * 1: the current 8 varieties + 8 more varieties of Urn in 3 sizes with 1 special face each, on a random side
     * OR
     * 2: only the current 8 varieties of Urn in 3 sizes with 2 special faces on opposite sides, orientation base upon player facing when place
     */
    public static enum EnumType implements IStringSerializable
    {
        //normal variants
        SOULSTONE(0, "soulstone"), //clay in sepulter
        PORCELAIN(1, "porcelain"), //cook porcelain_unfired //TODO create porcelain texture
        CLAY(2, "clay"), //cook clay_unfired
        GREEK(3, "greek"), //clay + ink sack
        CLAY_UNFIRED(4, "clay_unfired"), //crafted from clay
        PORCELAIN_UNFIRED(5, "porcelain_unfired"), //crafted from porcelain clay
        ENAMELED_GOLD(6, "enameled_gold"), //porcelain + gold nuggets //TODO create enameled porcelain texture + front texture
        ENAMELED_PEWTER(7, "enameled_pewter"); //soulstone + pewter nuggets //TODO create enameled soulstone texture + front texture

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
