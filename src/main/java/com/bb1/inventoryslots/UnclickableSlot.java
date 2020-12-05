package com.bb1.inventoryslots;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import com.bb1.interfaces.Slot;
/**
 * 
 * @author BradBot_1
 * 
 * This is basically {@link EmptySlot} but it does not return a true update response
 *
 */
public class UnclickableSlot implements Slot {

	private final int slot;

	public UnclickableSlot(int slot) {
		this.slot = slot;
	}

	@Override
	public int getSlotNumber() {
		return this.slot;
	}

	@Override
	public void onClick(InventoryClickEvent event) { event.setCancelled(true); }

	@Override
	public void onDrag(InventoryDragEvent event) {}

	@Override
	public boolean update(ItemStack currentItem) { return false; }
	
}
