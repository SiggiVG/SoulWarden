package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import vikinggoth.soulwarden.SoulWarden;

/**
 * Created by Friedrich on 11/9/2015.
 */
public class BlockStairsSW extends BlockStairs
{

    public BlockStairsSW(IBlockState modelState)
    {
        super(modelState);
        this.setCreativeTab(SoulWarden.SWTab);
        this.useNeighborBrightness  = true;
        this.setHardness(1.5F).setResistance(10.0F);
    }
}
