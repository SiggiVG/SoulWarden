package vikinggoth.soulwarden.tileentitites;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import vikinggoth.soulwarden.api.ISoulTransport;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;

/**
 * Created by Friedrich on 8/19/2015.
 */
public class TileSoulPylon extends TileEntity implements ISoulTransport, IUpdatePlayerListBox
{
    private int soulAmount;
    private final int maxSoulAmount = 64000;
    private EnumFacing lastInput;
    private static final int tickRate = ConfigurationHandler.pylonTickRate;;
    private int tickcount;

    private EnumFacing pullRequest;
    private int pullAmount;

    public TileSoulPylon()
    {
        tickcount = 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.soulAmount = compound.getInteger("soul");
        this.lastInput = (EnumFacing.VALUES[compound.getByte("lastInput")]);

    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("soul", this.soulAmount);
        compound.setByte("lastInput", (byte)this.lastInput.ordinal());
    }

    @Override
    public void update()
    {



        --tickcount;
        if(tickcount <= 0)
        {
            tickcount = tickRate;
            if (canTransferSoul(EnumFacing.DOWN)) {
                if (attemptBurySoul() > 0) return;
            }
            EnumFacing side = lastInput.getOpposite();
            if (canTransferSoul(side)) {
                if (attemptBurySoul() > 0) return;
            }
            if (canTransferSoul(side.rotateY())) {
                if (attemptTransferSoul(side) > 0) return;
            }
            if (canTransferSoul(side.rotateYCCW())) {
                if (attemptTransferSoul(side.rotateYCCW()) > 0) return;
            }
            if (canTransferSoul(side.getOpposite())) {
                if (attemptTransferSoul(side.getOpposite()) > 0) return;
            }
        }
    }

    private int attemptTransferSoul(EnumFacing side) {
        return 0;
    }

    /**
     * sends soul down
     * @return amount sent down
     */
    private int attemptBurySoul()
    {
        return 0;
    }

    @Override
    public boolean isSoulPylon(EnumFacing side)
    {
        if(this.getBlockMetadata() == 0) return true;
        return false;
    }

    @Override
    public boolean canSideConnectToPylon(EnumFacing side)
    {
        return false;
    }

    @Override
    public boolean canTransferSoul(EnumFacing side)
    {
        if(this.getBlockMetadata() == 0)
        {
            if(this.worldObj.getTileEntity(this.pos.offset(side)) instanceof ISoulTransport)
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean canInputSoulFrom(EnumFacing side)
    {
        return this.canTransferSoul(side);
    }

    @Override
    public boolean canOutputSoulTo(EnumFacing side)
    {
        return this.canTransferSoul(side);
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
        this.lastInput = side;
        return amount;
    }

    @Override
    public int getSoulAmount(EnumFacing side)
    {
        return this.soulAmount;
    }
}
