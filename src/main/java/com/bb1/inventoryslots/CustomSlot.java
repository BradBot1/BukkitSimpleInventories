package com.bb1.inventoryslots;

import java.util.function.Consumer;
import java.util.function.Function;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import com.bb1.interfaces.Slot;

public class CustomSlot implements Slot {
	
	private final int slot;
	private Consumer<InventoryClickEvent> clickConsumer;
	private Consumer<InventoryDragEvent> dragConsumer;
	private Function<ItemStack, Boolean> updateFunction;
	
	public CustomSlot(int slot) {
		this.slot = slot;
	}
	
	public CustomSlot(int slot, Consumer<InventoryClickEvent> consumer) {
		this.slot = slot;
		this.clickConsumer = consumer;
	}
	
	public CustomSlot(int slot, Consumer<InventoryClickEvent> consumer, Consumer<InventoryDragEvent> consumer2) {
		this.slot = slot;
		this.clickConsumer = consumer;
		this.dragConsumer = consumer2;
	}
	
	public CustomSlot(int slot, Consumer<InventoryClickEvent> consumer, Consumer<InventoryDragEvent> consumer2, Function<ItemStack, Boolean> function) {
		this.slot = slot;
		this.clickConsumer = consumer;
		this.dragConsumer = consumer2;
		this.updateFunction = function;
	}
	
	public CustomSlot setClickConsumer(Consumer<InventoryClickEvent> consumer) {
		this.clickConsumer = consumer;
		return this;
	}
	
	public CustomSlot setDragConsumer(Consumer<InventoryDragEvent> consumer) {
		this.dragConsumer = consumer;
		return this;
	}
	
	public CustomSlot setUpdateFunction(Function<ItemStack, Boolean> function) {
		this.updateFunction = function;
		return this;
	}

	@Override
	public int getSlotNumber() {
		return this.slot;
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		if (this.clickConsumer!=null) {
			this.clickConsumer.accept(event);
		}
	}

	@Override
	public void onDrag(InventoryDragEvent event) {
		if (this.dragConsumer!=null) {
			this.dragConsumer.accept(event);
		}
	}

	@Override
	public boolean update(ItemStack currentItem) {
		if (this.updateFunction!=null) {
			Boolean b = this.updateFunction.apply(currentItem);
			return (b==null) ? false : b;
		}
		return false;
	}
	
}
