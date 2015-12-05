package vikinggoth.soulwarden.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import vikinggoth.soulwarden.blocks.BlockDoublePlanksSWSlab;
import vikinggoth.soulwarden.blocks.BlockDoubleSoulStoneSlab;
import vikinggoth.soulwarden.blocks.BlockHalfPlanksSWSlab;
import vikinggoth.soulwarden.blocks.BlockHalfSoulStoneSlab;

/**
 * Created by Friedrich on 11/12/2015.
 */
public class ItemPlanksSWSlab extends ItemSlab
{
    public ItemPlanksSWSlab(Block block, BlockHalfPlanksSWSlab singleSlab, BlockDoublePlanksSWSlab doubleSlab)
    {
        super(block, singleSlab, doubleSlab);
    }
}
