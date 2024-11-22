package com.ariks.MolecularRF.Block.RfMolecular;

import com.ariks.MolecularRF.Block.Core.ExampleContainer;
import com.ariks.MolecularRF.util.SlotOut;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ContainerRfMolecular extends ExampleContainer {
    public ContainerRfMolecular(InventoryPlayer inventoryPlayer, TileRfMolecular tileEntity, EntityPlayer entityPlayer) {
        super(tileEntity);
        this.addSlotToContainer(new Slot(tileEntity,0,6,6));
        this.addSlotToContainer(new SlotOut(tileEntity,1,6,55));
        PlayerInventory(inventoryPlayer);
    }
    @Override
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer player, int slotIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();
            if (slotIndex < 2) {
                if (!this.mergeItemStack(slotStack, 2, 36 + 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.mergeItemStack(slotStack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return itemstack;
    }
}