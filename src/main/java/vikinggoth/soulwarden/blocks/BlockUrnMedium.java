package vikinggoth.soulwarden.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockUrnMedium extends BlockUrn
{

    public BlockUrnMedium()
    {
        super(Material.rock);
        this.setBlockBounds((1.0F/16.0F)*4.0F, 0.0F, (1.0F/16.0F)*4.0F, (1.0F/16.0F)*12.0F, (1.0F/16.0F)*13.0F, (1.0F/16.0F)*12.0F);
    }

    /**
     * returns a list of block with the same ID, but different meta (eg: wood returns 4 block)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        EnumType[] aenumtype = EnumType.values();
        int i = aenumtype.length - 4;

        for (int j = 0; j < i; ++j)
        {
            EnumType enumtype = aenumtype[j];
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }


}
