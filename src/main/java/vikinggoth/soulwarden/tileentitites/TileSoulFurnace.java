package vikinggoth.soulwarden.tileentitites;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import vikinggoth.soulwarden.api.ISoulTransport;
import vikinggoth.soulwarden.blocks.containers.BlockSoulFurnace;
import vikinggoth.soulwarden.registries.ItemRegister;

/**
 * Created by Friedrich on 8/19/2015.
 */
public class TileSoulFurnace extends TileEntityLockable implements ISoulTransport, IUpdatePlayerListBox, ISidedInventory
{
    /**
     * Soul Energy Stuff
     */
    private int soulAmount;
    final int MAX_SOUL_AMOUNT = 1024;

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
        //TODO: handle refillables
        /*
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }
        */
        return true;

    }

    @Override
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.furnaceItemStacks[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.furnaceItemStacks[index] != null)
        {
            ItemStack itemstack;

            if (this.furnaceItemStacks[index].stackSize <= count)
            {
                itemstack = this.furnaceItemStacks[index];
                this.furnaceItemStacks[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.furnaceItemStacks[index].splitStack(count);

                if (this.furnaceItemStacks[index].stackSize == 0)
                {
                    this.furnaceItemStacks[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.furnaceItemStacks[index] != null)
        {
            ItemStack itemstack = this.furnaceItemStacks[index];
            this.furnaceItemStacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        /*
        boolean flag = stack != null && stack.isItemEqual(this.furnaceItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.furnaceItemStacks[index]);
        this.furnaceItemStacks[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.totalCookTime = this.func_174904_a(stack);
            this.cookTime = 0;
            this.markDirty();
        }*/
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 2 ? false : (index != 1 ? true : isItemFuel(stack));
    }

    private boolean isItemFuel(ItemStack stack)
    {
        if(stack.getItem().equals(ItemRegister.soulgem) || stack.getItem().equals(ItemRegister.soulgem_black))
        {
            //TODO: check if soultype NBTData is present
            if(stack.getTagCompound().getTag("soultype") != null)
            {
                return true;
            }
        }
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
        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
        {
            this.furnaceItemStacks[i] = null;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.furnaceCustomName : "container.furnace";
    }

    @Override
    public boolean hasCustomName() {
        return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
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
