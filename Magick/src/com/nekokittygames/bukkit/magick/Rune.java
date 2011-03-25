package com.nekokittygames.bukkit.magick;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nekokittygames.bukkit.runestruct.RuneStructure;
import com.nekokittygames.bukkit.runestruct.RuneStructureParser;


public abstract class Rune
implements Comparable<Rune>
{
private Magick mPlugin;
private RuneSet mRuneSet;
private RuneStructure mStructure;
private boolean mEnabled;

public Rune(Magick plugin, RuneSet set, RuneStructure struct) {
mPlugin = plugin;
mRuneSet = set;
mStructure = struct;
}

public Rune(Magick plugin, RuneSet set, RuneStructure struct, String runeStructAsset) {
this(plugin, set, struct);

mStructure.setRuneMap(new RuneStructureParser().parseStructure(
		new BufferedReader(new InputStreamReader(
		this.getClass().getResourceAsStream(runeStructAsset)))));
}

public abstract String getName();

public boolean onRuneRightClick(PlayerInteractEvent event) {
return false;
}

public boolean onRuneDamage(BlockDamageEvent event) {
return false;
}

public boolean onRuneBreak(BlockBreakEvent event) {
return false;
}

public boolean onRuneRedstone(BlockRedstoneEvent event) {
return false;
}

public boolean onRuneUseRightClick(PlayerInteractEvent event) {
return false;
}

public boolean onRuneUseDamage(BlockDamageEvent event) {
return false;
}

public boolean onRuneUseBreak(BlockBreakEvent event) {
return false;
}

public boolean onRuneUseRedstone(BlockRedstoneEvent event) {
return false;
}

public boolean getEnabled() { return this.mEnabled; }
public void setEnabled(boolean enabled) { this.mEnabled = enabled; }

public int getSize() {
return mStructure.getSize();
}

protected Magick getPlugin() { return mPlugin; }
protected RuneSet getRuneSet() { return mRuneSet; }

/**
* Try a rune and return whether or not it was found.  If the rune was found the
* consumption constraints will be run against it.
*
* @param b The block to consider as the "clickable" block in the rune.
*
* @return true if the rune was ran, false otherwise
*/
protected boolean tryRune(Block b) {
return mStructure.tryRune(b, true,false);
}

/**
* Try a rune and return whether or not it was found.  If the rune was found the
* consumption constraints will not be run against it.
*
* @param b The block to consider as the "clickable" block in the rune.
*
* @return true if the rune was ran, false otherwise
*/
protected boolean tryRuneWithoutConsumption(Block b) {
return mStructure.tryRune(b, false,false);
}
protected boolean tryRuneWithoutConsumptionLog(Block b) {
	return mStructure.tryRune(b, false,true);
	}
/**
* Find a world by its directory name.
*
* @param name The name of the directory the world is saved in
* @return The world if found, null if not found.
*/
protected World findWorld(String name) {
for(World world : mPlugin.getServer().getWorlds()) {
    if(world.getName().equals(name)) {
        return world;
    }
}

return null;
}

/**
* Find a world by its random seed value
*
* @param seed The seed of the world you are searching for
* @return The world if found, null if not found.
*/
protected World findWorld(long seed) {
for(World world : mPlugin.getServer().getWorlds()) {
    if(world.getId() == seed) {
        return world;
    }
}

return null;
}

// comparable interface
public int compareTo(Rune rune) {
// we want sorting to be largest to smallest, so reverse this
return rune.getSize() - this.getSize();
}
}
