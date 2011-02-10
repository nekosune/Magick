package org.cvpcs.bukkit.magickraft;

import java.util.List;

public abstract class RuneSet {
	// returns a unique name for this rune set
	public abstract String getName();

	// returns a list of runes for this rune set
	public abstract List<Rune> getRunes();
}
