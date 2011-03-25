package com.nekokittygames.bukkit.magick.runeset.runecraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nekokittygames.bukkit.magick.Magick;
import com.nekokittygames.bukkit.magick.Rune;
import com.nekokittygames.bukkit.magick.RuneSet;
import com.nekokittygames.bukkit.runestruct.IRuneNode;
import com.nekokittygames.bukkit.runestruct.RNAnything;
import com.nekokittygames.bukkit.runestruct.RNMaterial;
import com.nekokittygames.bukkit.runestruct.RuneStructure;
import com.nekokittygames.bukkit.runestruct.TierUtility;

public class OracleRune extends Rune {

    public static final String NAME = "oracle";

    public OracleRune(Magick plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(3, 3)
                .setRuneMap(new IRuneNode[][]{
                        {RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE)},
                        {RNMaterial.getInstance(Material.REDSTONE_WIRE), RNAnything.getInstance()                      , RNMaterial.getInstance(Material.REDSTONE_WIRE)},
                        {RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE), RNMaterial.getInstance(Material.REDSTONE_WIRE)},
                    })
                .setRuneConsumptionMap(new int[][] {
                        {Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
                        {Material.AIR.getId(), -1                  , Material.AIR.getId()},
                        {Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
                    }));
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        if (tryRuneWithoutConsumption(block)) {
        	tryRune(block);
            int blockTier = TierUtility.getTier(block);

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
