package org.cvpcs.bukkit.magickraft.runestruct;

import org.bukkit.block.Block;

import java.util.HashMap;

public class RNComplexNot implements IRuneNode {

	private static final HashMap<String, RNComplexNot> INSTANCES = new HashMap<String, RNComplexNot>();

	public static IRuneNode getInstance(IRuneNode node) {
		RNComplexNot rnc = new RNComplexNot(node);

		if(INSTANCES.containsKey(rnc.toString())) {
			return INSTANCES.get(rnc.toString());
		} else {
			INSTANCES.put(rnc.toString(), rnc);
			return rnc;
		}
	}

	private IRuneNode mNodeType;

	private RNComplexNot(IRuneNode node) {
		mNodeType = node;
	}

	@Override
	public boolean isValid(Block b) {
		return (!mNodeType.isValid(b));
	}

	@Override
	public String toString() {
		return "RNComplexNot[" + mNodeType.toString() + "]";
	}

}
