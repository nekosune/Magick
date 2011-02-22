package org.cvpcs.bukkit.magickraft.runestruct;

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

    public boolean isValid(Block b) {
        return (b.getTypeId() == mMaterialId);
    }


    public static IRuneNodeDataParser getDataParser() {
    	return new RNMaterialDataParser();
    }

    private static class RNMaterialDataParser implements IRuneNodeDataParser {
    	private static final String ID = "M";

	    public String getNodeTypeId() { return ID; }

	    public IRuneNode parseNodeData(RuneNodeParser parser, String txt) {
	    	Material mat = null;

	    	try {
	    		int mat_id = Integer.parseInt(txt);
	    		mat = Material.getMaterial(mat_id);
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		mat = null;
	    	}

	    	if(mat == null) {
	    		return null;
	    	} else {
	    		return getInstance(mat);
	    	}
	    }
    }

    @Override
    public String toString() {
        return "RNMaterial[" + mMaterialId + "]";
    }

}
