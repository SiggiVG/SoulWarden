package vikinggoth.soulwarden.tileentitites;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import vikinggoth.soulwarden.api.ISoulTransport;
import vikinggoth.soulwarden.blocks.containers.BlockSoulPylon;

/**
 * Created by Friedrich on 8/19/2015.
 */
public class TileSoulPylon extends TileEntity implements ISoulTransport
{
    private int soulAmount;
    private final int maxSoulAmount = 64000;

    @Override
    public boolean isSoulPylon(EnumFacing side)
    {
        if(((BlockSoulPylon.EnumType)this.worldObj.getBlockState(this.pos).getValue(BlockSoulPylon.VARIANT)).getMetadata() == 0) return true;
        return false;
    }

    @Override
    public boolean canSideConnectToPylon(EnumFacing side)
    {
        return true;
    }

    @Override
    public boolean canTransferSoul(EnumFacing side)
    {
        return true;
    }

    @Override
    public boolean canInputSoulFrom(EnumFacing side)
    {
        return true;
    }

    @Override
    public boolean canOutputSoulTo(EnumFacing side)
    {
        return true;
    }

    @Override
    public int takeSoul(EnumFacing side, int amount)
    {
        //Amount is less than Soul Amount
        if(amount <= this.soulAmount)
        {
            this.soulAmount -= amount;
            //soul amount will be = to 0 or > 0
            return amount;
        }
        else //Amount is larger, Soul Amount will = 0
        {
            int transferAmount = soulAmount;
            this.soulAmount = 0;
            return transferAmount;
        }
    }

    @Override
    public int addSoul(EnumFacing side, int amount)
    {
        this.soulAmount += amount;
        return amount;
    }

    @Override
    public int getSoulAmount(EnumFacing side)
    {
        return this.soulAmount;
    }
}
