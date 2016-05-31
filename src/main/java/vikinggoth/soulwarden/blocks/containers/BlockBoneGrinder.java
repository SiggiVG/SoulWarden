package vikinggoth.soulwarden.blocks.containers;

import net.minecraft.block.BlockFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Friedrich on 12/8/2015.
 */
public class BlockBoneGrinder extends BlockFurnace
{
    public BlockBoneGrinder(boolean isGrinding)
    {
        super(isGrinding);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;//new TileEntityBoneGrinder();
    }
}
