package com.bb1.inventoryslots;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import com.bb1.interfaces.Slot;

public class EmptySlot implements Slot {

	private final int slot;

	public EmptySlot(int slot) {
		this.slot = slot;
	}

	@Override
	public int getSlotNumber() {
		return this.slot;
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		event.setCancelled(true);
	}

	@Override
	public void onDrag(InventoryDragEvent event) {
		event.setCancelled(true);
	}

	@Override
	public boolean update(ItemStack currentItem) {
		return true;
	}

}
