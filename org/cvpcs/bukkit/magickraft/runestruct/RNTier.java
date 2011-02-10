package org.cvpcs.bukkit.magickraft.runestruct;

import java.util.HashMap;

import org.bukkit.block.Block;

import org.cvpcs.bukkit.magickraft.TierUtility;

public class RNTier implements IRuneNode {

	private static final HashMap<Integer, RNTier> INSTANCES = new HashMap<Integer, RNTier>();

	public static IRuneNode getInstance(int t) {
		Integer tier = Integer.valueOf(t);

		if(INSTANCES.containsKey(tier)) {
			return INSTANCES.get(tier);
		} else {
			RNTier rnt = new RNTier(t);
			INSTANCES.put(tier, rnt);
			return rnt;
		}
	}

    private int mTier;

    private RNTier(int tier) {
    	int max_tier = TierUtility.getMaxTier();

    	// highest value is 6, so don't allow tiers higher
    	if(tier > max_tier) {
    		tier = max_tier;
    	}
    }

	@Override
	public boolean isValid(Block b) {
		return (mTier >= TierUtility.getTier(b));
	}

	@Override
	public String toString() {
		return "RNTier[" + mTier + "]";
	}
}
