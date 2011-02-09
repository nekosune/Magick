package org.cvpcs.bukkit.magickraft;

import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RuneRunner extends BlockListener {

    @Override
    public void onBlockRightClick(BlockRightClickEvent event)
    {
    	// we don't run runes if they're holding a block besides air (we're just like that)
    	if(event.getPlayer().getItemInHand().getType().isBlock() &&
    			event.getPlayer().getItemInHand().getType() != Material.AIR) {
    		return;
    	}

        for(Rune rune : Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneUseRightClick(event)) {
                    return;
                }
            }
        }

        for(Rune rune : Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneRightClick(event)) {
                    return;
                }
            }
        }
    }

    @Override
    public void onBlockDamage(BlockDamageEvent event) {
        for(Rune rune : Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneUseDamage(event)) {
                    return;
                }
            }
        }

        for(Rune rune:Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneDamage(event)) {
                    return;
                }
            }
        }
    }

    @Override
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
        for(Rune rune:Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneUseRedstone(event)) {
                    return;
                }
            }
        }

        for(Rune rune:Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneRedstone(event)) {
                    return;
                }
            }
        }
    }
}
