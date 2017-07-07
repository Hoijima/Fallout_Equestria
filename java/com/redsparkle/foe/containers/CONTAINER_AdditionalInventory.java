package com.redsparkle.foe.containers;

import com.redsparkle.foe.containers.Slots.*;
import com.redsparkle.foe.inventory.Additional_Inventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by hoijima on 29.06.17.
 */
public class CONTAINER_AdditionalInventory extends Container {

    public Additional_Inventory additional_inventory;
    public InventoryPlayer inventoryPlayer;

    //TODO: FInish this class
    public CONTAINER_AdditionalInventory(EntityPlayer thePlayer, Additional_Inventory additional_inventory) {
        this.inventoryPlayer = thePlayer.inventory;
        this.additional_inventory = additional_inventory;

        int i;
        int j;

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(thePlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(thePlayer.inventory, i, 8 + i * 18, 142));
        }

        /*
         * SLOTS:
         *
         * Additional inventory 140-151 ........ 0  - 8
         * Player Inventory     9-35 .. 9  - 35
         * Player Hotbar        0-8 ... 36 - 44
         */

        this.addSlotToContainer(new SlotPipBuck(additional_inventory, 140, 27, 4));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 141, 6, 5));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 142, 6, 24));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 143, 6, 43));
        this.addSlotToContainer(new SlotDevice(additional_inventory, 144, 6, 62));
        this.addSlotToContainer(new SlotHarness(additional_inventory, 145, 123, 50));
        this.addSlotToContainer(new SlotGun(additional_inventory, 146, 152, 15));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 147, 113, 6));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 148, 132, 6));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 149, 113, 25));
        this.addSlotToContainer(new SlotAmmo(additional_inventory, 150, 132, 25));


    }

    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {
        ItemStack var2 = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(par1);

        if (slot != null && slot.getHasStack()) {
            final ItemStack stack = slot.getStack();
            var2 = stack.copy();

            if (par1 >= 36) {
                if (!this.mergeItemStack(stack, 0, 36, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                boolean flag = false;
                for (int j = 140; j < 151; j++) {
                    if ((this.inventorySlots.get(j)).isItemValid(stack)) {
                        if (!this.mergeOneItem(stack, j, j + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                        flag = true;
                        break;
                    }
                }


                if (!flag) {
                    if (par1 < 27) {
                        if (!this.mergeItemStack(stack, 27, 36, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.mergeItemStack(stack, 0, 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == var2.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(par1EntityPlayer, stack);
        }

        return var2;
    }


    protected boolean mergeOneItem(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
        boolean flag1 = false;
        if (par1ItemStack.getCount() > 0) {
            Slot slot;
            ItemStack slotStack;

            for (int k = par2; k < par3; k++) {
                slot = this.inventorySlots.get(k);
                slotStack = slot.getStack();

                if (slotStack.isEmpty()) {
                    ItemStack stackOneItem = par1ItemStack.copy();
                    stackOneItem.setCount(1);
                    par1ItemStack.shrink(1);
                    slot.putStack(stackOneItem);
                    slot.onSlotChanged();
                    flag1 = true;
                    break;
                }
            }
        }

        return flag1;
    }
}