package org.cvpcs.bukkit.magickraft.runeset.runecraft;

import java.util.List;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;

import org.cvpcs.bukkit.magickraft.BlockUtility;
import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

public class FreezerRune extends Rune {

	private static final int FREEZE_RADIUS = 10;

	public static final String NAME = "freezer";

    public FreezerRune(Magickraft plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(5, 5)
        		.setRuneMap(new IRuneNode[][]{
		    			{RNMaterial.getInstance(Material.REDSTONE_TORCH_ON), RNMaterial.getInstance(Material.GLASS)           , RNMaterial.getInstance(Material.GLASS)           , RNMaterial.getInstance(Material.GLASS)           , RNMaterial.getInstance(Material.REDSTONE_TORCH_ON)},
		    			{RNMaterial.getInstance(Material.GLASS)            , RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.GLASS)},
		    			{RNMaterial.getInstance(Material.GLASS)            , RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.GOLD_ORE)        , RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.GLASS)},
		    			{RNMaterial.getInstance(Material.GLASS)            , RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.STATIONARY_WATER), RNMaterial.getInstance(Material.GLASS)},
		    			{RNMaterial.getInstance(Material.REDSTONE_TORCH_ON), RNMaterial.getInstance(Material.GLASS)           , RNMaterial.getInstance(Material.GLASS)           , RNMaterial.getInstance(Material.GLASS)           , RNMaterial.getInstance(Material.REDSTONE_TORCH_ON)},
			    	})
			    .setRuneConsumptionMap(new int[][] {
		        		{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
		        		{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
		        		{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
		        		{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
		        		{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
			    	}));
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        if (tryRune(block)) {
        	List<Block> blocksToFreeze = BlockUtility.getCircleBlocks(block, FREEZE_RADIUS, BlockUtility.Axis.Y);

        	for(Block b : blocksToFreeze) {
        		// if not air, go up until we get there
        		while(b.getType() != Material.AIR && b.getY() < 127) {
        			b = b.getFace(BlockFace.UP, 1);
        		}

        		// now go back down to the first non-air/non-torch block
        		while(b.getY() > 1 &&
        				(b.getType() == Material.AIR ||
        							b.getType() == Material.TORCH ||
        							b.getType() == Material.REDSTONE_TORCH_OFF ||
        							b.getType() == Material.REDSTONE_TORCH_ON)) {
        			b = b.getFace(BlockFace.DOWN, 1);
        		}

        		if(b.getType() == Material.WATER ||
        				b.getType() == Material.STATIONARY_WATER) {
        			// water, change to ice
        			b.setType(Material.ICE);
        		} else if(b.getType() != Material.AIR &&
        				b.getType() != Material.TORCH &&
        				b.getType() != Material.REDSTONE_TORCH_OFF &&
        				b.getType() != Material.REDSTONE_TORCH_ON) {
        			// non-air/water/torch (we check in case we bailed out instead of reaching the end)
        			// time to put snow above it

        			// if it's lava, cobblestone it before putting snow on top
        			if(b.getType() == Material.LAVA ||
        					b.getType() == Material.STATIONARY_LAVA) {
        				b.setType(Material.COBBLESTONE);
        			}

        			b.getFace(BlockFace.UP, 1).setType(Material.SNOW);
        		}
        	}

            return true;
        }

        return false;
    }
}
