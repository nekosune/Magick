/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvpcs.bukkit.magikraft;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.Material;

public class OracleRune extends Rune
{
	public static final String NAME = "oracle";
	
    public OracleRune(Magikraft plugin) {
        super(plugin, new RuneStructure(plugin, 3, 3, new int[][]{
        		{-(Material.REDSTONE_WIRE.getId() + 2), -(Material.REDSTONE_WIRE.getId() + 2), -(Material.REDSTONE_WIRE.getId() + 2)},
        		{-(Material.REDSTONE_WIRE.getId() + 2), -1                                   , -(Material.REDSTONE_WIRE.getId() + 2)},
        		{-(Material.REDSTONE_WIRE.getId() + 2), -(Material.REDSTONE_WIRE.getId() + 2), -(Material.REDSTONE_WIRE.getId() + 2)},
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
        	int blockTier = super.mStructure.getTier(block);
        	
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
