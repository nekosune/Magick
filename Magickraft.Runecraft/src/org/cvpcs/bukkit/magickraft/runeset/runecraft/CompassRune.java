package org.cvpcs.bukkit.magickraft.runeset.runecraft;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;

import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

public class CompassRune extends Rune {

    public static final String NAME = "compass";

    public CompassRune(Magickraft plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(3, 3), "/compass.runestruct");
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        if (tryRune(block)) {
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
