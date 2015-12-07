package vikinggoth.soulwarden.blocks.containers;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockSoulFurnace extends BlockContainer
{
    public BlockSoulFurnace()
    {
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }
}
