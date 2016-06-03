package vikinggoth.soulwarden.world.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import vikinggoth.soulwarden.blocks.BlockSoulStone;
import vikinggoth.soulwarden.registers.BlockRegister;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class TeleporterStygia extends Teleporter
{
    private final WorldServer worldServerInstance;

    public TeleporterStygia(WorldServer worldIn)
    {
        super(worldIn);
        this.worldServerInstance = worldIn;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw)
    {
        /*BlockPos pos = entityIn.getPosition();
        while(this.worldServerInstance.getBlockState(pos) != Blocks.air  && pos.getY() < 256)
        {
            pos = pos.up();
        }*/

        int i = MathHelper.floor_double(entityIn.posX);
        int j = MathHelper.floor_double(entityIn.posY) - 1;
        int k = MathHelper.floor_double(entityIn.posZ);
        int l = 1;
        int i1 = 0;

        //This part creates the platform they'll spawn on
        for (int j1 = -2; j1 <= 2; ++j1)
        {
            for (int k1 = -2; k1 <= 2; ++k1)
            {
                for (int l1 = -1; l1 < 3; ++l1)
                {
                    int i2 = i + k1 * l + j1 * i1;
                    int j2 = j + l1;
                    int k2 = k + k1 * i1 - j1 * l;
                    boolean flag = l1 < 0;
                    this.worldServerInstance.setBlockState(new BlockPos(i2, j2, k2), flag ? BlockRegister.soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.BRICK) : Blocks.air.getDefaultState());
                }
            }
        }

        entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
        entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
    }


}
