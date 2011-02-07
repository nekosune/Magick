package org.cvpcs.bukkit.magikraft.runes;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.Material;

import org.cvpcs.bukkit.magikraft.Magikraft;
import org.cvpcs.bukkit.magikraft.Rune;
import org.cvpcs.bukkit.magikraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magikraft.runestruct.RuneStructure;
import org.cvpcs.bukkit.magikraft.runestruct.RNAnything;
import org.cvpcs.bukkit.magikraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magikraft.runestruct.RNTier;

public class OracleRune extends Rune {
	
	public static final String NAME = "oracle";
	
    public OracleRune(Magikraft plugin) {
        super(plugin, new RuneStructure(3, 3, new IRuneNode[][]{
    			{RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE)},
    			{RNMaterial.getInstance(Material.REDSTONE_WIRE), RNAnything.getInstance()                      , RNMaterial.getInstance(Material.REDSTONE_WIRE)},
    			{RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE)},
    	}, new int[][] {
        		{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
        		{Material.AIR.getId(), -1                  , Material.AIR.getId()},
        		{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
        }));
    }
    
    @Override
    public boolean runRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();
        
        if (super.mStructure.isRune(block)) {
        	int blockTier = RNTier.getTier(block);
        	
            if(blockTier > -1) {
                event.getPlayer().sendMessage("This block is tier " + blockTier + ".");
            } else {
                event.getPlayer().sendMessage("This block cannot be used in runes.");
            }
            
            return true;
        }
        
        return false;
    }
}
