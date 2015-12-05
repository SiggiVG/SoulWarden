package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockSoulGravel extends BlockFalling
{
    public BlockSoulGravel()
    {
        super();
        this.setStepSound(Block.soundTypeSand);
    }
}
