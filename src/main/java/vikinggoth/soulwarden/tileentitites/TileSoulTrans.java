package vikinggoth.soulwarden.tileentitites;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import vikinggoth.soulwarden.api.ISoulTransport;

/**
 * Created by Friedrich on 8/19/2015.
 */
public class TileSoulTrans extends TileEntity implements ISoulTransport
{
    private BlockPos partnerPos;
    private World partnerWorld;
    private final double transferRange = 32.0;

    public BlockPos getPartnerPos()
    {
        return this.partnerPos;
    }

    public World getPartnerWorld()
    {
        return this.partnerWorld;
    }

    public boolean hasPartner()
    {
        if (partnerWorld == null) {
            return false;
        } else if(partnerPos == null) {
            //either both or one of them is null
            return false;
        } else if (this.getPartnerWorld() != this.worldObj) {
            //partner is in a different world
            return false;
        } else if (this.worldObj.getTileEntity(partnerPos) == null) {
            //there is no TileEntity there
            return false;
        }else if (!(this.worldObj.getTileEntity(partnerPos) instanceof TileSoulTrans)) {
            //there is no TileSoulTrans there
            return false;
        } else if (!(((TileSoulTrans) this.worldObj.getTileEntity(partnerPos)).getPartnerPos().equals(this.pos))) {
            //the partner is assigned a different partner
            return false;

        } else if (!(((TileSoulTrans) this.worldObj.getTileEntity(partnerPos)).getPartnerWorld().equals(this.worldObj))) {
            //the partner is assigned a different partner
            return false;
        } else if(this.pos.getX() -  this.partnerPos.getX() > transferRange ||  this.pos.getX() -  this.partnerPos.getX() < -transferRange) {
            if(this.pos.getZ() -  this.partnerPos.getZ() > transferRange ||  this.pos.getZ() -  this.partnerPos.getZ() < -transferRange) {
                //Partner is outside range
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSoulPylon(EnumFacing side)
    {
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
        return false;
    }

    @Override
    public boolean canInputSoulFrom(EnumFacing side)
    {
        return false;
    }

    @Override
    public boolean canOutputSoulTo(EnumFacing side)
    {
        return false;
    }

    @Override
    public int takeSoul(EnumFacing side, int amount)
    {
        return 0;
    }

    @Override
    public int addSoul(EnumFacing side, int amount)
    {
        return 0;
    }

    @Override
    public int getSoulAmount(EnumFacing side)
    {
        return 0;
    }
}
