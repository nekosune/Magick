package org.cvpcs.bukkit.magickraft;

import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import org.cvpcs.bukkit.magickraft.runes.ChronoTriggerRune;
import org.cvpcs.bukkit.magickraft.runes.CompassRune;
import org.cvpcs.bukkit.magickraft.runes.DoorRune;
import org.cvpcs.bukkit.magickraft.runes.OracleRune;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author UserXP
 */
public class Magickraft extends JavaPlugin {
	// a list of all of our runes
	public static final HashMap<String, Rune> RUNES = new HashMap<String, Rune>();
	
	public static MagickraftConfig CONFIG = null;
	
    private RuneRunner runeRunner;
    
    public Magickraft(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) {
        super(pluginLoader, instance, desc, folder, plugin, cLoader);
        CONFIG = new MagickraftConfig(this);
        
        RUNES.clear();
        RUNES.put(OracleRune.NAME, new OracleRune(this));
        RUNES.put(CompassRune.NAME, new CompassRune(this));
        RUNES.put(ChronoTriggerRune.NAME, new ChronoTriggerRune(this));
        RUNES.put(DoorRune.NAME, new DoorRune(this));
        
        runeRunner = new RuneRunner();
    }

    public void onDisable() {
        Log("unloaded!");
    }

    public void onEnable() {
    	for(Map.Entry<String, Rune> entry : RUNES.entrySet()) {
    		entry.getValue().setEnabled(CONFIG.getRuneBoolean(entry.getKey(), MagickraftConfig.RUNE_ENABLED_KEY));
    	}
        
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_RIGHTCLICKED, runeRunner, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_DAMAGED, runeRunner, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.REDSTONE_CHANGE, runeRunner, Priority.Normal, this);
        
        Log("loaded!");
    }
    
    public static void Log(String s) {
    	System.out.println("[Magickraft]: " + s);
    }
}


