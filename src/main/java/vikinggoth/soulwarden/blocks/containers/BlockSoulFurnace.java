package vikinggoth.soulwarden.blocks.containers;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vikinggoth.soulwarden.tileentitites.TileSoulFurnace;

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
        return new TileSoulFurnace();
    }
}
