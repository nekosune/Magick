/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvpcs.bukkit.magikraft;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public abstract class Rune
{
    protected Magikraft mPlugin;
    protected RuneStructure mStructure;
    private boolean mEnabled;

    public Rune(Magikraft plugin, RuneStructure struct)
    {
        mPlugin = plugin;
        mStructure = struct;
    }

    public boolean runRuneRightClick(BlockRightClickEvent event)
    {
        System.out.println("Method not implemented by rune, returning false");
        return false;
    }

    public boolean runRuneDamage(BlockDamageEvent event)
    {
        return false;
    }
    
    /*
     * unused - no runes are created redstone
    public boolean runRuneRedstone(BlockRedstoneEvent event)
    {
        return false;
    }*/

    public boolean runRuneRightClickUsingBlock(BlockRightClickEvent event)
    {
        return false;
    }
    
    public boolean runRuneDamageUsingBlock(BlockDamageEvent event)
    {
        return false;
    }
    
    public boolean runRuneRedstoneUsingBlock(BlockRedstoneEvent event)
    {
        return false;
    }
    
    public boolean getIsEnabled() { return this.mEnabled; }
    public void setEnabled(boolean enabled) { this.mEnabled = enabled; }
    
    public Location stringToLoc(String locstr)
    {
        String[] coords = locstr.split(" ");
        for(World world : this.mPlugin.getServer().getWorlds())
        {
        	try {
        		if(world.getId() == Long.parseLong(coords[0]))
        		{
        			Location loc = world.getBlockAt(Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3])).getLocation();
        			return loc;
        		}
        	} catch(NumberFormatException e) { /* ignore this for now */ }
        }
        return null;
    }

    public String locToString(Location loc)
    {
        return loc.getWorld().getId() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
    }
}
