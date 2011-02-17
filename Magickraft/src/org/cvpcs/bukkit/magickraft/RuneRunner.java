package org.cvpcs.bukkit.magickraft;

import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RuneRunner extends BlockListener {

    private List<Rune> mRunes;

    public RuneRunner() {
        unloadRunes();
    }

    public RuneRunner(Collection<Rune> runes) {
        loadRunes(runes);
    }

    public void loadRunes(Collection<Rune> runes) {
        mRunes = new ArrayList<Rune>(runes);
        Collections.sort(mRunes);
    }

    public void unloadRunes() {
        mRunes = new ArrayList<Rune>();
    }

    @Override
    public void onBlockRightClick(BlockRightClickEvent event)
    {
        // we don't run runes if they're holding a block besides air (we're just like that)
        if(event.getPlayer().getItemInHand().getType().isBlock() &&
                event.getPlayer().getItemInHand().getType() != Material.AIR) {
            return;
        }

        for(Rune rune : mRunes) {
            if (rune.getEnabled()) {
                if (rune.onRuneUseRightClick(event)) {
                    return;
                }
            }
        }

        for(Rune rune : mRunes) {
            if (rune.getEnabled()) {
                if (rune.onRuneRightClick(event)) {
                    return;
                }
            }
        }
    }

    @Override
    public void onBlockDamage(BlockDamageEvent event) {
        for(Rune rune : mRunes) {
            if (rune.getEnabled()) {
                if (rune.onRuneUseDamage(event)) {
                    return;
                }
            }
        }

        for(Rune rune : mRunes) {
            if (rune.getEnabled()) {
                if (rune.onRuneDamage(event)) {
                    return;
                }
            }
        }
    }

    @Override
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
        for(Rune rune : mRunes) {
            if (rune.getEnabled()) {
                if (rune.onRuneUseRedstone(event)) {
                    return;
                }
            }
        }

        for(Rune rune : mRunes) {
            if (rune.getEnabled()) {
                if (rune.onRuneRedstone(event)) {
                    return;
                }
            }
        }
    }
}
