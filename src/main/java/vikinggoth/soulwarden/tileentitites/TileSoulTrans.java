package vikinggoth.soulwarden.tileentitites;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Friedrich on 8/19/2015.
 */
public class TileSoulTrans extends TileEntity
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
        if (partnerWorld == null || partnerPos == null) {
            //either both or one of them is null
            return false;
        } else if (partnerWorld != this.worldObj) {
            //partner is in a different world
            return false;
        } else if (!(this.worldObj.getTileEntity(partnerPos) instanceof TileSoulTrans)) {
            //there is no SoulTrans there
            return false;
        } else if (this.worldObj.getTileEntity(partnerPos) == null)
        {
            return false;
        } else if (!(((TileSoulTrans) this.worldObj.getTileEntity(partnerPos)).getPartnerPos().equals(this.pos))) {
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
}
