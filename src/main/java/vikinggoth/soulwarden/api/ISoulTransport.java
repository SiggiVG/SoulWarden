package vikinggoth.soulwarden.api;

import net.minecraft.util.EnumFacing;

/**
 * Created by Friedrich on 8/20/2015.
 */
public interface ISoulTransport
{
    /**
     * Only instances of TileSoulPylon should return this.
     * @param side
     * @return
     */
    public boolean isSoulPylon(EnumFacing side);

    /**
     * If side can connect to a SoulPylon
     * @param side
     * @return
     */
    public boolean canSideConnectToPylon(EnumFacing side);

    /**
     * if a side can connect to a soulTransport network and can either
     * Input Soul
     * Output Soul
     * or Both
     * @param side
     * @return
     */
    public boolean canTransferSoul(EnumFacing side);

    /**
     * if soul can be passed into this machine through the side
     * @param side
     * @return
     */
    public boolean canInputSoulFrom(EnumFacing side);

    /**
     * if soul can be passed out of this machine through the side
     * @param side
     * @return
     */
    public boolean canOutputSoulTo(EnumFacing side);

    /**
     * The actual method that handles soul being taken from this machine
     * @param side
     * @param amount
     * @return
     */
    public int takeSoul(EnumFacing side, int amount);

    /**
     * The actual method that handles soul being put into this machine
     * @param side
     * @param amount
     * @return
     */
    public int addSoul(EnumFacing side, int amount);

    /**
     * Also used by Soul Resonator
     * So in most cases, nothing is done with side
     * @param side
     * @return the amount od soul contained within
     */
    public int getSoulAmount(EnumFacing side);
}
