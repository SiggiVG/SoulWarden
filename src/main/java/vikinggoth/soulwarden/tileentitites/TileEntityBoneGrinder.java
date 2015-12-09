package vikinggoth.soulwarden.tileentitites;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import vikinggoth.soulwarden.api.ISoulTransport;

/**
 * Created by Friedrich on 12/8/2015.
 */
public class TileEntityBoneGrinder extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory, ISoulTransport
{
    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return null;
    }

    @Override
    public String getGuiID() {
        return null;
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
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean isSoulPylon(EnumFacing side) {
        return false;
    }

    @Override
    public boolean canSideConnectToPylon(EnumFacing side) {
        return false;
    }

    @Override
    public boolean canTransferSoul(EnumFacing side) {
        return false;
    }

    @Override
    public boolean canInputSoulFrom(EnumFacing side) {
        return false;
    }

    @Override
    public boolean canOutputSoulTo(EnumFacing side) {
        return false;
    }

    @Override
    public int takeSoul(EnumFacing side, int amount) {
        return 0;
    }

    @Override
    public int addSoul(EnumFacing side, int amount) {
        return 0;
    }

    @Override
    public int getSoulAmount(EnumFacing side) {
        return 0;
    }

    @Override
    public void update() {

    }
}
