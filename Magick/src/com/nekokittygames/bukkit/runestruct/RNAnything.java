package com.nekokittygames.bukkit.runestruct;

import org.bukkit.block.Block;
import com.nekokittygames.bukkit.runestruct.IRuneNode;
import com.nekokittygames.bukkit.runestruct.IRuneNodeDataParser;
import com.nekokittygames.bukkit.runestruct.RuneNodeParser;

public class RNAnything implements IRuneNode {

	private static final RNAnything INSTANCE=new RNAnything();
	public static IRuneNode getInstance()
	{
		return INSTANCE;
	}
	private RNAnything(){}
	@Override
	public boolean isValid(Block b) {
		return true;
	}
	public static IRuneNodeDataParser getDataParser() {
    	return new RNAnythingDataParser();
    }

    private static class RNAnythingDataParser implements IRuneNodeDataParser {
    	private static final String ID = "A";

	    public String getNodeTypeId() { return ID; }

	    public IRuneNode parseNodeData(RuneNodeParser parser, String txt) {
	    	return getInstance();
	    }
    }

    @Override
    public String toString() {
        return "RNAnything";
    }
}
