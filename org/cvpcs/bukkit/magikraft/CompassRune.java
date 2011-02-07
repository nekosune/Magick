/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvpcs.bukkit.magikraft;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
/**
 *
 * @author UserXP
 */
public class CompassRune extends Rune
{
	public static final String NAME = "compass";
	
    public CompassRune(Magikraft plugin) {
        super(plugin, new RuneStructure(plugin, 3, 3, new int[][]{
        		{1                          , -(Material.AIR.getId() + 2), 1                          },
        		{-(Material.AIR.getId() + 2), 1                          , -(Material.AIR.getId() + 2)},
        		{1                          , -(Material.AIR.getId() + 2), 1                          },
        }));
    }
    
    @Override
    public boolean runRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();
        
        if (super.mStructure.isRune(block) &&
        		(event.getItemInHand().getType()==Material.AIR || !event.getItemInHand().getType().isBlock())) {
        	block.getFace(BlockFace.NORTH).setType(block.getType());
            block.setType(Material.AIR);
            block.getFace(BlockFace.EAST).setType(block.getFace(BlockFace.NORTH_EAST).getType());
            block.getFace(BlockFace.NORTH_EAST).setType(Material.AIR);
            block.getFace(BlockFace.WEST).setType(block.getFace(BlockFace.NORTH_WEST).getType());
            block.getFace(BlockFace.NORTH_WEST).setType(Material.AIR);
            return true;
        }
        
        return false;
    }
}
