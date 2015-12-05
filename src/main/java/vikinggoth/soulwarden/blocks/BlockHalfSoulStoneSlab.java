package vikinggoth.soulwarden.blocks;

import net.minecraft.block.material.Material;

/**
 * Created by Friedrich on 11/12/2015.
 */
public class BlockHalfSoulStoneSlab extends BlockSoulStoneSlab
{
    public BlockHalfSoulStoneSlab(Material materialIn)
    {
        super(materialIn);
    }

    @Override
    public final boolean isDouble()
    {
        return false;
    }
}
