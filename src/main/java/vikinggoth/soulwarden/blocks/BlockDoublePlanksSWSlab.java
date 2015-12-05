package vikinggoth.soulwarden.blocks;


import net.minecraft.block.material.Material;

/**
 * Created by Friedrich on 11/12/2015.
 */
public class BlockDoublePlanksSWSlab extends BlockPlanksSWSlab
{

    public BlockDoublePlanksSWSlab(Material materialIn)
    {
        super(materialIn);
    }

    @Override
    public final boolean isDouble()
    {
        return true;
    }
}
