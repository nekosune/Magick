package com.nekokittygames.bukkit.magick.runeset.runecraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerInteractEvent;
import com.nekokittygames.bukkit.magick.Magick;
import com.nekokittygames.bukkit.magick.RuneSet;
import com.nekokittygames.bukkit.runestruct.RuneStructure;

import com.nekokittygames.bukkit.magick.Rune;

public class CompassRune extends Rune{
	public static final String NAME = "compass";

    public CompassRune(Magick plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(3, 3), "/compass.runestruct");
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

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
