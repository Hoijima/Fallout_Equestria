package com.redsparkle.foe.inventory;

import com.redsparkle.api.inventory.IInvenotoryFOE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

/**
 * Created by hoijima on 07.07.17.
 */
public class Additional_Inventory implements IInvenotoryFOE {
    public NonNullList<ItemStack> stacks = NonNullList.withSize(12, ItemStack.EMPTY);

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getSizeInventory() {
        return this.stacks.size();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int index) {
        return this.stacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.stacks, index, count);

        if (!itemstack.isEmpty()) {
            this.markDirty();
        }

        return itemstack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.stacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
    }

    @Override
    public String getName() {
        return "Fallout Equestria additional inventory";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return false;
    }

    @Override
    public void dropExtendedItems(EntityPlayer player) {
        for (int i = 0; i < this.stacks.size(); i++) {
            ItemStack stack = this.stacks.get(i);

            if (!stack.isEmpty()) {
                player.dropItem(stack, true);
            }

            this.stacks.set(i, net.minecraft.item.ItemStack.EMPTY);
        }
    }

    public void readFromNBT(NBTTagList tagList) {
        this.stacks = NonNullList.withSize(12, ItemStack.EMPTY);

        for (int i = 0; i < tagList.tagCount(); ++i) {
            final NBTTagCompound nbttagcompound = tagList.getCompoundTagAt(i);
            final int j = nbttagcompound.getByte("Slot") & 255;
            final ItemStack itemstack = new ItemStack(nbttagcompound);

            if (!itemstack.isEmpty()) {
                this.stacks.set(j, itemstack);
            }
        }
    }

    public NBTTagList writeToNBT(NBTTagList tagList) {
        NBTTagCompound nbttagcompound;

        for (int i = 0; i < this.stacks.size(); ++i) {
            if (!this.stacks.get(i).isEmpty()) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                this.stacks.get(i).writeToNBT(nbttagcompound);
                tagList.appendTag(nbttagcompound);
            }
        }

        return tagList;
    }

    @Override
    public void copyInventory(IInvenotoryFOE par1InventoryPlayer) {
        Additional_Inventory toCopy = (Additional_Inventory) par1InventoryPlayer;
        for (int i = 0; i < this.stacks.size(); ++i) {
            this.stacks.set(i, toCopy.stacks.get(i).copy());
        }
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
    public ITextComponent getDisplayName() {
        return null;
    }
}

