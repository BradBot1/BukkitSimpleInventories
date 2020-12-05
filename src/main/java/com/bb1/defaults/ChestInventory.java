package com.bb1.defaults;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import com.bb1.InventoryEventHandler;
import com.bb1.interfaces.Inventory;
import com.bb1.interfaces.Slot;
import com.bb1.inventoryslots.DefaultSlot;

public class ChestInventory implements Inventory {
	
	protected final int size;
	protected final String title;
	protected org.bukkit.inventory.Inventory inventory;
	
	protected final List<Slot> slots = new ArrayList<>();
	
	public ChestInventory(String title, int size) {
		this.title = title;
		this.size = size;
		loadInventory();
	}

	public void loadInventory() {
		this.inventory = Bukkit.createInventory(null, this.size, this.title);
		
		for (int i = 0; i < size; i++) {
			this.slots.add(new DefaultSlot(i));
		}
	}
	
	public void removeSlot(int index) {
		this.slots.remove(getSlot(index));
	}
	
	// Interface stuff
	
	@Override
	public Slot getSlot(int index) {
		for (Slot slot : slots) {
			if (slot.getSlotNumber()==index) {
				return slot;
			}
		}
		return null;
	}
	
	@Override
	public void setSlot(int index, Slot slot) {
		this.slots.remove(getSlot(index));
		this.slots.add(slot);
	}
	
	@Override
	public void addSlot(Slot slot) {
		this.slots.add(slot);
	}
	
	@Override
	public void onClose(InventoryCloseEvent event) {}

	@Override
	public void onClick(InventoryClickEvent  event) {
		getSlot(event.getSlot()).onClick(event);
	}
	
	@Override
	public void onDrag(InventoryDragEvent event) {}

	@Override
	public org.bukkit.inventory.Inventory getBukkitInventory() {
		return this.inventory;
	}
	
	@Override
	public boolean allowDragging() {
		return true;
	}

	@Override
	public void updateSlots() {
		org.bukkit.inventory.Inventory inventory = getBukkitInventory();
		for (int i = 0; i < this.slots.size(); i++) {
			Slot slot = getSlot(i);
			if (slot==null) continue;
			ItemStack currentItem = inventory.getItem(i);
			boolean update = slot.update(currentItem);
			if (update) {
				this.getBukkitInventory().clear(i);
				if (currentItem!=null && inventory.getViewers().size()>0) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(InventoryEventHandler.getPlugin(), new Runnable() {

						@Override
						public void run() {
							for (HumanEntity h : inventory.getViewers()) {
								if (h==null) continue;
								h.getLocation().getWorld().dropItemNaturally(h.getInventory().getLocation(), currentItem);
								return;
							}
						}
						
					}, 1l);
				}
			}
		}
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {}
	

}
