package com.bb1.defaults;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.bb1.inventoryslots.CustomSlot;
import com.bb1.inventoryslots.UnclickableSlot;

public class PagedInventory extends ChestInventory {

	protected final List<ItemStack> items;
	protected ItemStack borderItem;
	protected ItemStack nextItem = new ItemStack(Material.ARROW);
	protected ItemStack backItem = new ItemStack(Material.ARROW);
	protected List<Integer> borderInts = Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53});
	
	protected int current = 0;

	public PagedInventory(String title, List<ItemStack> list, ItemStack borderItem) {
		super(title, 6*9);
		this.items = list;
		this.borderItem = borderItem;
		setInventory();
	}
	
	@Override
	public void loadInventory() {
		this.inventory = Bukkit.createInventory(null, this.size, this.title);
		
		for (int i = 0; i < size; i++) {
			this.slots.add(new UnclickableSlot(i));
		}
	}

	public void setInventory() {
		org.bukkit.inventory.Inventory inventory = getBukkitInventory();
		// Set border
		for (int i : this.borderInts) {
			inventory.setItem(i, this.borderItem);
		}
		removeSlot(this.size-4);
		addSlot(new CustomSlot(this.size-4, (e) -> {
			next(this.current);
			e.setCancelled(true);
		}));
		removeSlot(this.size-6);
		addSlot(new CustomSlot(this.size-6, (e) -> {
			back(this.current);
			e.setCancelled(true);
		}));
		inventory.setItem(this.size-4, this.nextItem);
		inventory.setItem(this.size-6, this.backItem);
		next(this.current);
	}
	
	public void next(int start) {
		int current = start;
		org.bukkit.inventory.Inventory inventory = getBukkitInventory();
		for (int i = 0; i < this.size; i++) {
			if (this.borderInts.contains(i)) continue;
			try {
				inventory.setItem(i, items.get(current));
			} catch (Exception e) {
				inventory.setItem(i, this.borderItem);
			}
			current++;
		}
		this.current = current+1;
	}

	public void back(int start) {
		next(start-(this.size*2)+(this.borderInts.size()*2)-2);
	}

}
