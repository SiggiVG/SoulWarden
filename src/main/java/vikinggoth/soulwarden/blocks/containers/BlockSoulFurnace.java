package vikinggoth.soulwarden.blocks.containers;

import net.minecraft.block.BlockFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockSoulFurnace extends BlockFurnace
{
    public BlockSoulFurnace(boolean isBurning)
    {
        super(isBurning);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;//new TileSoulFurnace();
    }
}
