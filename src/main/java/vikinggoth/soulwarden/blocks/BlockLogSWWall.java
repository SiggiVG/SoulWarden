package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.reference.EnumWoodType;

import java.util.List;

/**
 * Created by Friedrich on 11/13/2015.
 */
public class BlockLogSWWall extends BlockWallBase
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumWoodType.class);

    public BlockLogSWWall(Block modelBlock)
    {
        super(modelBlock);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(VARIANT, EnumWoodType.GHOUL));
        this.setHardness(1.5F).setResistance(10.0F);
    }

    /**
     * returns a list of block with the same ID, but different meta (eg: wood returns 4 block)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        EnumWoodType[] aenumtype = EnumWoodType.values();
        int i = aenumtype.length;

        for (EnumWoodType enumtype : aenumtype) {
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }

    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumWoodType) state.getValue(VARIANT)).getMetadata();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumWoodType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumWoodType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[]{UP, NORTH, EAST, WEST, SOUTH, VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumWoodType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
    }
}
