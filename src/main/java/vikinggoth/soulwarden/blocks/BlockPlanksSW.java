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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.items.itemblocks.IMetaBlockName;
import vikinggoth.soulwarden.reference.EnumWoodType;
import vikinggoth.soulwarden.registries.ConfigBlocks;

import java.util.List;

/**
 * Created by Friedrich on 11/13/2015.
 */
public class BlockPlanksSW extends Block implements IMetaBlockName
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumWoodType.class);

    public BlockPlanksSW()
    {
        super(Material.wood);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumWoodType.GHOUL));
        this.setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood);
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return ((EnumWoodType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        EnumWoodType[] aenumtype = EnumWoodType.values();
        int i = aenumtype.length;

        for (int j = 0; j < i; ++j)
        {
            EnumWoodType enumtype = aenumtype[j];
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumWoodType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumWoodType)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumWoodType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
    }
}
