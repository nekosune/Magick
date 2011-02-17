package org.cvpcs.bukkit.magickraft.runeset.magickraft;

import java.util.ArrayList;
import java.util.List;

import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;

public class MagickraftRuneSet extends RuneSet {

	private static final String NAME = "Magickraft";

	private final List<Rune> mRunes = new ArrayList<Rune>();

	public MagickraftRuneSet(Magickraft plugin) {
		super(plugin);

		mRunes.add(new AerogaRune(plugin, this));
		mRunes.add(new FiregaRune(plugin, this));
		//mRunes.add(new NetherChestRune(plugin, this)); incomplete, requires bukkit features to be implemented
		mRunes.add(new WaypointRune(plugin, this));
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
