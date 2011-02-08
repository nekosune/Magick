package org.cvpcs.bukkit.magickraft.runestruct;

import org.bukkit.block.Block;

import java.util.HashMap;

public class RNComplexAnd implements IRuneNode {

	private static final HashMap<String, RNComplexAnd> INSTANCES = new HashMap<String, RNComplexAnd>();
	
	public static IRuneNode getInstance(IRuneNode n1, IRuneNode n2) {
		RNComplexAnd rnc = new RNComplexAnd(n1, n2);
		
		if(INSTANCES.containsKey(rnc.toString())) {
			return INSTANCES.get(rnc.toString());
		} else {
			INSTANCES.put(rnc.toString(), rnc);
			return rnc;
		}
	}
	
	private IRuneNode mNodeType1;
	private IRuneNode mNodeType2;
	
	private RNComplexAnd(IRuneNode n1, IRuneNode n2) {
		mNodeType1 = n1;
		mNodeType2 = n2;
	}
	
	@Override
	public boolean isValid(Block b) {
		return (mNodeType1.isValid(b) && mNodeType2.isValid(b));
	}

	@Override
	public String toString() {
		return "RNComplexAnd[" + mNodeType1.toString() + "," + mNodeType2.toString() + "]";
	}
	
}
