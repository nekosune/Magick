package com.nekokittygames.bukkit.magick.runeset.runecraft;

import java.util.ArrayList;
import java.util.List;

import com.nekokittygames.bukkit.magick.Magick;
import com.nekokittygames.bukkit.magick.Rune;
import com.nekokittygames.bukkit.magick.RuneSet;



public class RunecraftRuneSet extends RuneSet {

    private static final String NAME = "Runecraft";

    private final List<Rune> mRunes = new ArrayList<Rune>();

    public RunecraftRuneSet(Magick plugin) {
        super(plugin);

        mRunes.add(new ChronoTriggerRune(plugin, this)); // done
        mRunes.add(new CompassRune(plugin, this)); // done
        mRunes.add(new DoorRune(plugin, this));
        mRunes.add(new FreezerRune(plugin, this)); // done
        mRunes.add(new MagicBeaconRune(plugin, this));
        mRunes.add(new MineshaftRune(plugin, this)); // done
        mRunes.add(new OracleRune(plugin, this)); // done
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<Rune> getRunes() {
        return mRunes;
    }

}
