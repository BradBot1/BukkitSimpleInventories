package com.bb1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.bb1.defaults.PagedInventory;
import com.bb1.interfaces.Inventory;

import lombok.Getter;

public class InventoryEventHandler implements Listener {
	
	private static @Getter Plugin plugin;
	private static final Set<Inventory> set = new HashSet<>();
	
	public static void addInventory(Inventory inv) {
		set.add(inv);
	}
	
	public InventoryEventHandler(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		InventoryEventHandler.plugin = plugin; 
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void InventoryClickEvent(InventoryClickEvent event) {
		try {
			if (event.getRawSlot()!=event.getSlot() || event.getSlot()==-1 || event.getRawSlot()==-1) return; // Bottom inventory (the players)
			for (Inventory inv : set) {
				if (inv.getBukkitInventory().equals(event.getInventory())) {
					inv.onClick(event);
				}
			}
		} catch (NullPointerException e) {
			return;
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void InventoryDragEvent(InventoryDragEvent event) {
		try {
			for (Inventory inv : set) {
				if (inv.getBukkitInventory().equals(event.getInventory())) {
					if (!inv.allowDragging()) {
						event.setCancelled(true);
					} else {
						inv.onDrag(event);
					}
				}
			}
		} catch (NullPointerException e) {
			return;
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void InventoryCloseEvent(InventoryCloseEvent event) {
		for (Inventory inv : set) {
			if (inv.getBukkitInventory().equals(event.getInventory())) {
				inv.onClose(event);
				inv.updateSlots();
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void InventoryOpenEvent(InventoryOpenEvent event) {
		for (Inventory inv : set) {
			if (inv.getBukkitInventory().equals(event.getInventory())) {
				inv.onOpen(event);
				inv.updateSlots();
			}
		}
	}
	
	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent event) {
		if (event.getMessage().equalsIgnoreCase("open")) {
			event.setCancelled(true);
			List<ItemStack> items = new ArrayList<>();
			for (Material material : Material.values()) {
				if (!material.isItem()) continue;
				items.add(new ItemStack(material));
			}
			Inventory inventory = new PagedInventory("UwU chest", items, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
			set.add(inventory);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

				@Override
				public void run() {
					event.getPlayer().openInventory(inventory.getBukkitInventory());
				}
				
			});
		}
	}
	
}
