package com.niccholaspage.CustomFood;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class CustomFood extends JavaPlugin {
	//Player Listener
	private final CustomFoodPlayerListener playerListener = new CustomFoodPlayerListener(this);
	//Items
	public final List<Integer> items = new ArrayList<Integer>();
	//Heal amounts
	public final List<Integer> healAmounts = new ArrayList<Integer>();
    @Override
	public void onDisable() {
		System.out.println("CustomFood Disabled");
	}
    @Override
	public void onEnable() {
		//Create the pluginmanage pm.
		PluginManager pm = getServer().getPluginManager();
		//Create PlayerCommand listener
	    pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
	    //Setup the Config
	    setupConfig();
       //Get the infomation from the yml file.
        PluginDescriptionFile pdfFile = this.getDescription();
        //Print that the plugin has been enabled!
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}
    private boolean isInt(String i){
    	try {
    		Integer.parseInt(i);
    		return true;
    	} catch(NumberFormatException nfe){
    		return false;
    	}
    }
    private void setupConfig(){
    	//Writing Part
    	getDataFolder().mkdir();
    	File file = new File(getDataFolder(), "config.yml");
    	try {
			file.createNewFile();
		} catch (IOException e) {
			
		}
    	Configuration config = new Configuration(file);
    	config.load();
    	writeNode("CustomFood", "", config);
    	writeNode("CustomFood.items", "353", config);
    	writeNode("CustomFood.healamounts", "1", config);
    	config.save();
    	//Reading Part
    	String[] itemsSplit = config.getString("CustomFood.items").split(",");
    	for (String item : itemsSplit){
    		if (isInt(item))
    			items.add(Integer.parseInt(item));
    	}
    	String[] healAmountsSplit = config.getString("CustomFood.healamounts").split(",");
    	for (String healAmount : healAmountsSplit){
    		if (isInt(healAmount))
    			healAmounts.add(Integer.parseInt(healAmount));
    	}
    }
	private void writeNode(String node,Object value, Configuration config){
		if (config.getProperty(node) == null) config.setProperty(node, value);
	}
}
