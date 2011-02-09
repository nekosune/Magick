package org.cvpcs.bukkit.magickraft;

import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RuneRunner extends BlockListener {

    @Override
    public void onBlockRightClick(BlockRightClickEvent event)
    {
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
    public void onBlockRedstoneChange(BlockFromToEvent event) {
        BlockRedstoneEvent redstoneEvent = (BlockRedstoneEvent) event;

        for(Rune rune:Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneUseRedstone(redstoneEvent)) {
                    return;
                }
            }
        }

        for(Rune rune:Magickraft.RUNES.values()) {
            if (rune.getEnabled()) {
                if (rune.onRuneRedstone(redstoneEvent)) {
                    return;
                }
            }
        }
    }
}
