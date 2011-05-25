package com.niccholaspage.CustomFood;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class CustomFoodPlayerListener extends PlayerListener{
	 public CustomFood plugin;
	public final Material[] interactableBlocks = new Material[]{
			Material.CHEST,
			Material.IRON_DOOR_BLOCK,
			Material.WOOD_DOOR,
			Material.STONE_BUTTON,
			Material.LEVER,
			Material.DISPENSER,
			Material.BURNING_FURNACE,
			Material.FURNACE,
			Material.CAKE_BLOCK,
			Material.NOTE_BLOCK,
			Material.WORKBENCH
	};
	 public CustomFoodPlayerListener(CustomFood instance) {
		 plugin = instance;
	 }
	 public boolean contains(Object obj, Object[] objects){
		 for (Object object : objects){
			 if (obj == object) return true;
		 }
		 return false;
	 }
	 public void onPlayerInteract(PlayerInteractEvent event){
		 if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			 Player player = event.getPlayer();
			 Block block = event.getClickedBlock();
			 if (block != null && contains(block.getType(), interactableBlocks)) return;
			 if (player.getHealth() == 20) return;
			 ItemStack itemInHand = player.getItemInHand();
			 Integer Id = itemInHand.getTypeId();
			 if (!plugin.items.contains(Id)) return;
			 if (itemInHand.getAmount() == 1){
				 player.setItemInHand(null);
			 }else {
				 itemInHand.setAmount(itemInHand.getAmount() - 1);
			 }
			 player.setHealth(player.getHealth() + plugin.healAmounts.get(plugin.items.indexOf(Id)));
		 }
	 }
}
