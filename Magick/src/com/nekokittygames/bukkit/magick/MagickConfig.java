package com.nekokittygames.bukkit.magick;

import java.io.File;

import org.bukkit.util.config.Configuration;


public class MagickConfig extends Configuration {

    private static final String CONFIG_FILE = "config.yaml";

    // rune key format
    private static final String RUNESETS_RUNES_KEY_FORMAT = "runesets.%s.runes.%s.%s";

    // keys global to all runes
    public static final String RUNESETS_RUNES_ENABLED_KEY = "enabled";

    public MagickConfig(Magick plugin) {
        super(new File(plugin.getDataFolder(), CONFIG_FILE));

        // make sure we have a configuration directory
        plugin.getDataFolder().mkdirs();

        // construct our configuration object
        load();
    }

    public boolean getRuneSetsRunesBoolean(String runeset, String rune, String key) {
        String prop = String.format(RUNESETS_RUNES_KEY_FORMAT, runeset, rune, key);
        if(getProperty(prop) == null) {
            // property not found, set default and save
            setProperty(prop, true);
            save();
        }

        return getBoolean(prop, true);
    }
}