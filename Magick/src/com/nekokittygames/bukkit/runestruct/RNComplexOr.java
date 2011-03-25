package com.nekokittygames.bukkit.runestruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.bukkit.block.Block;

public class RNComplexOr implements IRuneNode {

    private static final HashMap<String, RNComplexOr> INSTANCES = new HashMap<String, RNComplexOr>();

    public static IRuneNode getInstance(IRuneNode n1, IRuneNode n2) {
        RNComplexOr rnc = new RNComplexOr(n1, n2);

        if(INSTANCES.containsKey(rnc.toString())) {
            return INSTANCES.get(rnc.toString());
        } else {
            INSTANCES.put(rnc.toString(), rnc);
            return rnc;
        }
    }

    private IRuneNode mNodeType1;
    private IRuneNode mNodeType2;

    private RNComplexOr(IRuneNode n1, IRuneNode n2) {
        mNodeType1 = n1;
        mNodeType2 = n2;
    }

    public boolean isValid(Block b) {
        return (mNodeType1.isValid(b) || mNodeType2.isValid(b));
    }

    public static IRuneNodeDataParser getDataParser() {
    	return new RNComplexOrDataParser();
    }

    private static class RNComplexOrDataParser implements IRuneNodeDataParser {
    	private static final String ID = "CO";

    	public String getNodeTypeId() { return ID; }

    	public IRuneNode parseNodeData(RuneNodeParser parser, String txt) {
    		IRuneNode node = null;

    		ArrayList<IRuneNode> subnodes = new ArrayList<IRuneNode>();

    		Stack<Character> s = new Stack<Character>();
    		int start = 0;

    		// parse out sub-nodes
    		for(int i = 0; i < txt.length(); i++) {
    			char c = txt.charAt(i);

    			switch(c) {
    			case '(':
    				if(s.size() == 0) {
    					start = i;
    				}

    				s.push(c);
    				break;
    			case ')':
    				s.pop();

    				if(s.size() == 0) {
    					IRuneNode subnode = parser.parseRuneNode(txt.substring(start, i + 1));

    					if(subnode == null) {
    						// this is bad so bail out
    						return null;
    					} else {
    						subnodes.add(subnode);
    					}
    				}
    			}
    		}

    		// now we have our subnodes, so deal with them
    		if(subnodes.size() == 1) {
    			// only one node specified..., deal with it gracefully
    			node = getInstance(subnodes.get(0), RNAnything.getInstance());
    		} else if(subnodes.size() >= 2) {
    			node = getInstance(subnodes.get(0), subnodes.get(1));

    			for(int i = 2; i < subnodes.size(); i++) {
    				node = getInstance(node, subnodes.get(i));
    			}
    		}

    		return node;
    	}
    }

    @Override
    public String toString() {
        return "RNComplexOr[" + mNodeType1.toString() + "," + mNodeType2.toString() + "]";
    }

}
