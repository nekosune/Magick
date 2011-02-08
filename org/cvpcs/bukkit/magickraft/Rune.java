package org.cvpcs.bukkit.magickraft;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

public abstract class Rune
{
    protected Magickraft mPlugin;
    private RuneStructure mStructure;
    private boolean mEnabled;

    public Rune(Magickraft plugin, RuneStructure struct) {
        mPlugin = plugin;
        mStructure = struct;
    }

    public boolean onRuneRightClick(BlockRightClickEvent event) {
        System.out.println("Method not implemented by rune, returning false");
        return false;
    }

    public boolean onRuneDamage(BlockDamageEvent event) {
        return false;
    }
    
    /*
     * unused - no runes are created redstone
    public boolean runRuneRedstone(BlockRedstoneEvent event) {
        return false;
    }*/

    public boolean isRuneRightClickUsingBlock(BlockRightClickEvent event) {
        return false;
    }
    
    public boolean isRuneDamageUsingBlock(BlockDamageEvent event) {
        return false;
    }
    
    public boolean isRuneRedstoneUsingBlock(BlockRedstoneEvent event) {
        return false;
    }
    
    public boolean getEnabled() { return this.mEnabled; }
    public void setEnabled(boolean enabled) { this.mEnabled = enabled; }
    
    protected boolean tryRune(Block b) {
    	return mStructure.tryRune(b);
    }
}
