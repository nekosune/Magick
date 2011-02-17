package org.cvpcs.bukkit.magickraft;

import java.io.File;
import java.util.List;

public abstract class RuneSet {
    public static final String RUNE_SET_DIRNAME = "runesets";

    private File mDataFolder;

    public RuneSet(Magickraft plugin) {
        mDataFolder = new File(
                new File(plugin.getDataFolder(), RUNE_SET_DIRNAME),
                getName());

        mDataFolder.mkdirs();
    }

    // returns a unique name for this rune set
    public abstract String getName();

    // returns a list of runes for this rune set
    public abstract List<Rune> getRunes();

    // returns the data directory this runeset can use
    public File getDataFolder() { return mDataFolder; }
}
