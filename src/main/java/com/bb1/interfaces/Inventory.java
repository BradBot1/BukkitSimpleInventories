package com.bb1.interfaces;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface Inventory {
	
	public Slot getSlot(int index);
	
	public void setSlot(int index, Slot slot);
	
	public void addSlot(Slot slot);
	
	public void onOpen(InventoryOpenEvent event);
	
	public void onClose(InventoryCloseEvent event);
	
	public void onClick(InventoryClickEvent event);
	
	public void onDrag(InventoryDragEvent event);
	
	public org.bukkit.inventory.Inventory getBukkitInventory();
	
	public boolean allowDragging();
	
	public void updateSlots();
	
}
