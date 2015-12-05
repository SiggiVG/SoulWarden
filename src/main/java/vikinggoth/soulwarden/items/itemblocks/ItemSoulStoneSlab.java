package vikinggoth.soulwarden.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import vikinggoth.soulwarden.blocks.BlockDoubleSoulStoneSlab;
import vikinggoth.soulwarden.blocks.BlockHalfSoulStoneSlab;

/**
 * Created by Friedrich on 11/12/2015.
 */
public class ItemSoulStoneSlab extends ItemSlab
{
    public ItemSoulStoneSlab(Block block, BlockHalfSoulStoneSlab singleSlab, BlockDoubleSoulStoneSlab doubleSlab)
    {
        super(block, singleSlab, doubleSlab);
    }
}
