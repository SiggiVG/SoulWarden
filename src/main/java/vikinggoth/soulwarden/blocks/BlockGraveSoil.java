package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Friedrich on 11/18/2015.
 * TODO: EventHandling so it tills
 */
public class BlockGraveSoil extends Block
{
    public BlockGraveSoil()
    {
        super(Material.ground);
        this.setStepSound(Block.soundTypeGravel);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        return true;
    }


}
