package com.bb1.interfaces;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public interface Slot {
	
	public int getSlotNumber();
	
	public void onClick(InventoryClickEvent event);
	
	public void onDrag(InventoryDragEvent event);
	
	public boolean update(ItemStack currentItem);
	
}
