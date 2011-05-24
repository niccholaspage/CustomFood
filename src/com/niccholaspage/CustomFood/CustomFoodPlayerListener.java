package com.niccholaspage.CustomFood;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class CustomFoodPlayerListener extends PlayerListener{
	 public CustomFood plugin;
	 public CustomFoodPlayerListener(CustomFood instance) {
		 plugin = instance;
	 }
	 public void onPlayerInteract(PlayerInteractEvent event){
		 if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			 Player player = event.getPlayer();
			 if (player.getHealth() == 20) return;
			 ItemStack itemInHand = player.getItemInHand();
			 Integer Id = itemInHand.getTypeId();
			 if (!plugin.items.contains(Id)) return;
			 if (itemInHand.getAmount() == 1){
				 player.getInventory().remove(itemInHand);
			 }else {
				 itemInHand.setAmount(itemInHand.getAmount() - 1);
			 } 
			 player.setHealth(player.getHealth() + plugin.healAmounts.get(plugin.items.indexOf(Id)));
		 }
	 }
}
