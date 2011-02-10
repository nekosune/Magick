package org.cvpcs.bukkit.magickraft;

import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Magickraft extends JavaPlugin {
	// a list of all rune sets
	private static final HashMap<String, RuneSet> RUNE_SETS = new HashMap<String, RuneSet>();
	private static final RuneSetLoader RUNE_SET_LOADER = new RuneSetLoader();

	private static final Logger log = Logger.getLogger("Minecraft");

	public static MagickraftConfig CONFIG = null;

    private RuneRunner mRuneRunner;

    public Magickraft(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) {
        super(pluginLoader, instance, desc, folder, plugin, cLoader);
        CONFIG = new MagickraftConfig(this);

        loadRuneSets();

        mRuneRunner = new RuneRunner();
    }

    public void onDisable() {
    	mRuneRunner.unloadRunes();

        log.info("unloaded!");
    }

    public void onEnable() {
    	getServer().getPluginManager().registerEvent(Event.Type.BLOCK_RIGHTCLICKED, mRuneRunner, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_DAMAGED, mRuneRunner, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.REDSTONE_CHANGE, mRuneRunner, Priority.Normal, this);

    	ArrayList<Rune> runes = new ArrayList<Rune>();

    	for(RuneSet rs : RUNE_SETS.values()) {
    		log.info("Loading runes from rune set [" + rs.getName() + "]");
    		for(Rune rune : rs.getRunes()) {
    			boolean enable = CONFIG.getRuneSetsRunesBoolean(rs.getName(), rune.getName(), MagickraftConfig.RUNESETS_RUNES_ENABLED_KEY);

    			log.info("  Loading rune [" + rune.getName() + "]: " + (enable ? "enabled" : "disabled"));

    			rune.setEnabled(enable);
    			runes.add(rune);
    		}
    	}

        mRuneRunner.loadRunes(runes);

        log.info(getDescription().getName() + " v" + getDescription().getVersion() + " loaded!");
    }

    private void loadRuneSets() {
    	RUNE_SETS.clear();

    	// create our rune set dir
    	File runeSetDir = new File(getDataFolder(), RuneSet.RUNE_SET_DIRNAME);
    	runeSetDir.mkdirs();

    	// load all of the available rune sets
        for(File runeSetFile : runeSetDir.listFiles()) {
        	boolean isRuneSetFile = false;
        	for(Pattern p : RUNE_SET_LOADER.getRuneSetFileFilters()) {
        		if(p.matcher(runeSetFile.getAbsolutePath()).find()) {
        			isRuneSetFile = true;
        			break;
        		}
        	}

        	if(isRuneSetFile) {
        		// we have a runeset file, try to load it
        		RuneSet rs = null;
        		try {
	        		rs = RUNE_SET_LOADER.loadRuneSet(this, runeSetFile);
        		} catch(InvalidRuneSetException e) {
        			log.severe("ERROR: Inavlid rune set found ... skipping");
        			e.printStackTrace();
        		}

        		if(rs != null) {
        			if(!RUNE_SETS.containsKey(rs.getName())) {
        				log.info("Loaded rune set [" + rs.getName() + "]");
        				RUNE_SETS.put(rs.getName(), rs);
        			} else {
        				log.warning("ERROR: Another rune set by name [" + rs.getName() + "] already loaded, skipping this one");
        			}
        		}
        	}
        }
    }
}


