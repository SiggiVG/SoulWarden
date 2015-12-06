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
public class BlockUrnSmall extends BlockUrn
{

    public BlockUrnSmall()
    {
        super(Material.rock);
        this.setBlockBounds((1.0F/16.0F)*5.0F, 0.0F, (1.0F/16.0F)*5.0F, (1.0F/16.0F)*11.0F, (1.0F/16.0F)*8.0F, (1.0F/16.0F)*11.0F);
    }
}
