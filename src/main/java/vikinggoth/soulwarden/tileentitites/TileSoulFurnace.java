package vikinggoth.soulwarden.tileentitites;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import vikinggoth.soulwarden.api.ISoulTransport;
import vikinggoth.soulwarden.blocks.containers.BlockSoulFurnace;

/**
 * Created by Friedrich on 8/19/2015.
 */
public class TileSoulFurnace extends TileEntityLockable implements ISoulTransport, IUpdatePlayerListBox, ISidedInventory
{
    /**
     * Soul Energy Stuff
     */
    private int soulAmount;
    final int MAX_SOUL_AMOUNT = 1280;

    /**
     * The hopper input/output slots
     */
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};

    private ItemStack[] furnaceItemStacks = new ItemStack[3];

    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    private int currentBurnTime;

    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;


    @Override
    public boolean isSoulPylon(EnumFacing side) {
        return false;
    }

    /**
     * Only the back of the SoulFurnace can connect to a Soul Network
     */
    @Override
    public boolean canSideConnectToPylon(EnumFacing side)
    {

        EnumFacing front = (EnumFacing)this.worldObj.getBlockState(this.pos).getValue(BlockSoulFurnace.FACING);

        if(side == front.getOpposite())
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean canTransferSoul(EnumFacing side)
    {
        EnumFacing front = (EnumFacing)this.worldObj.getBlockState(this.pos).getValue(BlockSoulFurnace.FACING);

        if(side == front.getOpposite())
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean canInputSoulFrom(EnumFacing side)
    {
        return canTransferSoul(side);
    }

    @Override
    public boolean canOutputSoulTo(EnumFacing side)
    {
        return false;
    }

    @Override
    public int takeSoul(EnumFacing side, int amount)
    {
        if(this.canOutputSoulTo(side))
        {
            if(this.soulAmount >= amount)
            {
                this.soulAmount -= amount;
                return amount;
            }
            else
            {
                amount = this.soulAmount;
                this.soulAmount = 0;
                return amount;
            }
        }
        return 0;
    }

    @Override
    public int addSoul(EnumFacing side, int amount)
    {
        if(this.canInputSoulFrom(side))
        {
            if(this.soulAmount + amount <= this.MAX_SOUL_AMOUNT)
            {
                this.soulAmount += amount;
                return amount;
            }
            else
            {
                int num = (this.soulAmount + amount) - this.MAX_SOUL_AMOUNT;
                this.soulAmount = this.MAX_SOUL_AMOUNT;
                return amount - num;
            }
        }
        return 0;
    }

    @Override
    public int getSoulAmount(EnumFacing side)
    {
        return this.soulAmount;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return null;
    }

    @Override
    public String getGuiID() {
        return null;
    }
}
