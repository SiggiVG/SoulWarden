package vikinggoth.soulwarden.api;

import net.minecraft.util.EnumFacing;

/**
 * Created by Friedrich on 8/20/2015.
 */
public interface ISoulTransport
{
    public boolean isSoulPylon(EnumFacing side);

    public boolean canSideConnectToPylon(EnumFacing side);

    public boolean canTransferSoul(EnumFacing side);

    public boolean canInputSoulFrom(EnumFacing side);

    public boolean canOutputSoulTo(EnumFacing side);

    public int takeSoul(EnumFacing side, int amount);

    public int addSoul(EnumFacing side, int amount);

    public int getSoulAmount(EnumFacing side);
}
