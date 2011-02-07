package org.cvpcs.bukkit.magikraft.runestruct;

import org.bukkit.block.Block;

public class RNAnything implements IRuneNode {
	
	private static final RNAnything INSTANCE = new RNAnything();
	
	public static IRuneNode getInstance() {
		return INSTANCE;
	}
	
	private RNAnything() {}
	
	@Override
	public boolean isValid(Block b) {
		return true;
	}
	
	@Override
	public String toString() {
		return "RNAnything";
	}
	
}
