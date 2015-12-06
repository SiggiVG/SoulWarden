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
}
