package org.cvpcs.bukkit.magickraft.runestruct;

import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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

    public boolean isValid(Block b) {
        return (!mNodeType.isValid(b));
    }

    public static IRuneNodeDataParser getDataParser() {
    	return new RNComplexNotDataParser();
    }

    private static class RNComplexNotDataParser implements IRuneNodeDataParser {
    	private static final String ID = "CN";

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
    			// only one node specified... good
    			node = getInstance(subnodes.get(0));
    		}

    		return node;
    	}
    }

    @Override
    public String toString() {
        return "RNComplexNot[" + mNodeType.toString() + "]";
    }

}
