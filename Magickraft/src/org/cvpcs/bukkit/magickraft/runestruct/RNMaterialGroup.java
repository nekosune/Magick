package org.cvpcs.bukkit.magickraft.runestruct;

import org.bukkit.block.Block;

import java.util.HashMap;

public class RNMaterialGroup implements IRuneNode {

    private static final HashMap<Integer, RNMaterialGroup> INSTANCES = new HashMap<Integer, RNMaterialGroup>();

    public static IRuneNode getInstance(int gid) {
        Integer groupId = Integer.valueOf(gid);

        if(INSTANCES.containsKey(groupId)) {
            return INSTANCES.get(groupId);
        } else {
            RNMaterialGroup rnmg = new RNMaterialGroup(gid);
            INSTANCES.put(groupId, rnmg);
            return rnmg;
        }
    }

    private static final HashMap<Integer, Integer> GROUPS = new HashMap<Integer, Integer>();

    // this should be called before processing begins
    public static void resetGroups() {
        GROUPS.clear();
    }

    private Integer mGroupId;

    private RNMaterialGroup(int groupId) {
        mGroupId = Integer.valueOf(groupId);
    }

    public boolean isValid(Block b) {
        Integer blockTypeId = Integer.valueOf(b.getTypeId());

        if(GROUPS.containsKey(mGroupId)) {
            Integer groupTypeId = GROUPS.get(mGroupId);
            return groupTypeId.equals(blockTypeId);
        } else {
            GROUPS.put(mGroupId, blockTypeId);
            return true;
        }
    }

    public static IRuneNodeDataParser getDataParser() {
    	return new RNMaterialGroupDataParser();
    }

    private static class RNMaterialGroupDataParser implements IRuneNodeDataParser {
    	private static final String ID = "MG";

	    public String getNodeTypeId() { return ID; }

	    public IRuneNode parseNodeData(RuneNodeParser parser, String txt) {
	    	Integer group_id = null;

	    	try {
	    		int gid = Integer.parseInt(txt);
	    		group_id = Integer.valueOf(gid);
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		group_id = null;
	    	}

	    	if(group_id == null) {
	    		return null;
	    	} else {
	    		return getInstance(group_id.intValue());
	    	}
	    }
    }

    @Override
    public String toString() {
        return "RNMaterialGroup[" + mGroupId.toString() + "]";
    }

}
