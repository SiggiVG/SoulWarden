package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockUrnLarge extends BlockUrn
{

    public BlockUrnLarge()
    {
        super(Material.rock);
        this.setBlockBounds((1.0F/16.0F)*2.0F, 0.0F, (1.0F/16.0F)*2.0F, (1.0F/16.0F)*14.0F, 1.0F, (1.0F/16.0F)*14.0F);
    }
}
