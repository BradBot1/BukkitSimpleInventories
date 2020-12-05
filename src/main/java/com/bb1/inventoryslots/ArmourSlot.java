package com.bb1.inventoryslots;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import com.bb1.interfaces.Slot;

public class ArmourSlot implements Slot {
	
	private final int slot;
	
	public ArmourSlot(int slot) {
		this.slot = slot;
	}

	@Override
	public int getSlotNumber() {
		return this.slot;
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		if (!isArmour(event.getCurrentItem()) && !isArmour(event.getWhoClicked().getItemOnCursor())) {
			event.setCancelled(true);
		}
	}
	
	@Override
	public void onDrag(InventoryDragEvent event) {}
	
	public boolean isArmour(ItemStack itemStack) {
		if (itemStack==null) return false;
		switch (itemStack.getType()) {
		case LEATHER_BOOTS:
		case LEATHER_LEGGINGS:
		case LEATHER_CHESTPLATE:
		case LEATHER_HELMET:
		case CHAINMAIL_BOOTS:
		case CHAINMAIL_LEGGINGS:
		case CHAINMAIL_CHESTPLATE:
		case CHAINMAIL_HELMET:
		case GOLDEN_BOOTS:
		case GOLDEN_LEGGINGS:
		case GOLDEN_CHESTPLATE:
		case GOLDEN_HELMET:
		case IRON_BOOTS:
		case IRON_LEGGINGS:
		case IRON_CHESTPLATE:
		case IRON_HELMET:
		case DIAMOND_BOOTS:
		case DIAMOND_LEGGINGS:
		case DIAMOND_CHESTPLATE:
		case DIAMOND_HELMET:
		case NETHERITE_BOOTS:
		case NETHERITE_LEGGINGS:
		case NETHERITE_CHESTPLATE:
		case NETHERITE_HELMET:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean update(ItemStack currentItem) { return !isArmour(currentItem); }
	
}
