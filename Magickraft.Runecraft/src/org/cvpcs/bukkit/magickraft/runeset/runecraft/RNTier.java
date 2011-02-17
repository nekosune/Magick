package org.cvpcs.bukkit.magickraft.runeset.runecraft;

import java.util.HashMap;

import org.bukkit.block.Block;

import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;

public class RNTier implements IRuneNode {

	private static final HashMap<Integer, RNTier> INSTANCES = new HashMap<Integer, RNTier>();

	// helper function that gets an instance of any tiered block being accepted (tier >= 0)
	public static IRuneNode getInstance() {
		return getInstance(0);
	}
	
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

    	// don't allow tiers higher than the highest value
    	if(tier > max_tier) {
    		mTier = max_tier;
    	} else {
    		mTier = tier;
    	}
    }

	@Override
	public boolean isValid(Block b) {
		return (TierUtility.getTier(b) >= mTier);
	}

	@Override
	public String toString() {
		return "RNTier[" + mTier + "]";
	}
}
