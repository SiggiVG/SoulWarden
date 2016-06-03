package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockBonePile extends BlockFalling
{
    public BlockBonePile()
    {
        super();
        this.setStepSound(Block.soundTypeGravel);
        this.setHardness(0.5F);
        this.setHarvestLevel("spade", 0);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.bone; //: Item.getItemFromBlock(this);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 3 + random.nextInt(7);
    }
}
