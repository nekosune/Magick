package org.cvpcs.bukkit.magikraft.runestruct;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;

public class RNMaterial implements IRuneNode {

	private static final HashMap<Integer, RNMaterial> INSTANCES = new HashMap<Integer, RNMaterial>();
	
	public static IRuneNode getInstance(Material m) {
		Integer typeId = Integer.valueOf(m.getId());
		
		if(INSTANCES.containsKey(typeId)) {
			return INSTANCES.get(typeId);
		} else {
			RNMaterial rnm = new RNMaterial(m);
			INSTANCES.put(typeId, rnm);
			return rnm;
		}
	}
	
	private int mMaterialId;
	
	private RNMaterial(Material m) {
		mMaterialId = m.getId();
	}
	
	@Override
	public boolean isValid(Block b) {
		return (b.getTypeId() == mMaterialId);
	}

	@Override
	public String toString() {
		return "RNMaterial[" + mMaterialId + "]";
	}
	
}
