package org.cvpcs.bukkit.magickraft;

import java.io.File;
import org.bukkit.util.config.Configuration;

public class MagickraftConfig extends Configuration {

	private static final String CONFIG_FILE = "config.yaml";
	
	// rune key format
	private static final String RUNE_KEY_FORMAT = "runes.%s.%s";

	// keys global to all runes
	public static final String RUNE_ENABLED_KEY = "enabled"; 
	
	public MagickraftConfig(Magickraft plugin) {
		super(new File(plugin.getDataFolder(), CONFIG_FILE));
		
		// make sure we have a configuration directory
		plugin.getDataFolder().mkdirs();
		
		// construct our configuration object
		load();
	}
	
	public boolean getRuneBoolean(String rune, String key) {
		String prop = getRuneKeyString(rune, key);
		if(getProperty(prop) == null) {
			// property not found, set default and save
			setProperty(prop, true);
			save();
		}
		
		return getBoolean(prop, true);
	}
	
	private String getRuneKeyString(String rune, String key) {
		return String.format(RUNE_KEY_FORMAT, rune, key);
	}
}
