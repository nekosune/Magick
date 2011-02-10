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
import java.util.regex.Pattern;

public class Magickraft extends JavaPlugin {
	// a list of all rune sets
	private static final HashMap<String, RuneSet> RUNE_SETS = new HashMap<String, RuneSet>();
	private static final RuneSetLoader mRuneSetLoader = new RuneSetLoader();

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

        Log("unloaded!");
    }

    public void onEnable() {
    	getServer().getPluginManager().registerEvent(Event.Type.BLOCK_RIGHTCLICKED, mRuneRunner, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_DAMAGED, mRuneRunner, Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.REDSTONE_CHANGE, mRuneRunner, Priority.Normal, this);

    	ArrayList<Rune> runes = new ArrayList<Rune>();

    	for(RuneSet rs : RUNE_SETS.values()) {
    		for(Rune entry : rs.getRunes()) {
    			entry.setEnabled(CONFIG.getRuneSetsRunesBoolean(rs.getName(), entry.getName(), MagickraftConfig.RUNESETS_RUNES_ENABLED_KEY));
    			runes.add(entry);
    		}
    	}

        mRuneRunner.loadRunes(runes);

        Log("loaded!");
    }

    public static void Log(String s) {
    	System.out.println("[Magickraft]: " + s);
    }

    private void loadRuneSets() {
    	RUNE_SETS.clear();

    	// load all of the available rune sets
        for(File runeSetFile : new File(getDataFolder(), RuneSet.RUNE_SET_DIRNAME).listFiles()) {
        	boolean isRuneSetFile = false;

        	for(Pattern p : mRuneSetLoader.getRuneSetFileFilters()) {
        		if(p.matcher(runeSetFile.getAbsolutePath()).matches()) {
        			isRuneSetFile = true;
        			break;
        		}
        	}

        	if(isRuneSetFile) {
        		// we have a runeset file, try to load it
        		RuneSet rs = null;
        		try {
	        		rs = mRuneSetLoader.loadRuneSet(this, runeSetFile);
        		} catch(InvalidRuneSetException e) {
        			Log("ERROR: Inavlid rune set found ... skipping");
        			e.printStackTrace();
        		}

        		if(rs != null) {
        			if(!RUNE_SETS.containsKey(rs.getName())) {
        				Log("Loaded rune set [" + rs.getName() + "]");
        				RUNE_SETS.put(rs.getName(), rs);
        			} else {
        				Log("ERROR: Another rune set by name [" + rs.getName() + "] already loaded, skipping this one");
        			}
        		}
        	}
        }
    }
}


