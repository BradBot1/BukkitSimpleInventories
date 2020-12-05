package com.bb1.inventoryslots;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import com.bb1.interfaces.Slot;

import lombok.NonNull;

public class ItemSlot implements Slot {
	
	private final int slot;
	private ItemStack itemStack;
	
	public ItemSlot(int slot, @NonNull ItemStack itemStack) {
		this.slot = slot;
		this.itemStack = itemStack;
	}
	
	@Override
	public int getSlotNumber() {
		return this.slot;
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		if (event.getCurrentItem()==null) return;
		if (event.getCurrentItem().isSimilar(this.itemStack) || event.getWhoClicked().getItemOnCursor().isSimilar(this.itemStack)) {
			
		} else {
			event.setCancelled(true);
		}
	}

	@Override
	public void onDrag(InventoryDragEvent event) {}

	@Override
	public boolean update(ItemStack currentItem) {
		return (currentItem.isSimilar(this.itemStack));
	}

}
