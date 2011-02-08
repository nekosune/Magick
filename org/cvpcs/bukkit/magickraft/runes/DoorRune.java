package org.cvpcs.bukkit.magickraft.runes;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockDamageLevel;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.Location;

import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNAnything;
import org.cvpcs.bukkit.magickraft.runestruct.RNComplex;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterialGroup;
import org.cvpcs.bukkit.magickraft.runestruct.RNTier;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class DoorRune extends Rune {
	
	public static final String NAME = "door";
	
    public DoorRune(Magickraft plugin) {
        super(plugin, new RuneStructure(3, 1, 3, 2, new IRuneNode[][][]{
        		{
        			{
            			RNComplex.getInstance(
            					RNTier.getInstance(1),
            					RNMaterialGroup.getInstance(0)),

            			RNMaterial.getInstance(Material.IRON_ORE),

            			RNComplex.getInstance(
            					RNTier.getInstance(1),
            					RNMaterialGroup.getInstance(0)),
        			},
        		},
        		{
        			{
            			RNMaterial.getInstance(Material.IRON_ORE),

            			RNAnything.getInstance(),

            			RNMaterial.getInstance(Material.IRON_ORE),
        			},
        		},
        		{
        			{
            			RNComplex.getInstance(
            					RNTier.getInstance(1),
            					RNMaterialGroup.getInstance(0)),

            			RNComplex.getInstance(
            					RNTier.getInstance(1),
            					RNMaterialGroup.getInstance(0)),

            			RNComplex.getInstance(
            					RNTier.getInstance(1),
            					RNMaterialGroup.getInstance(0)),
        			},
        		}
       	}, new int[][][]{
        		{
        			{Material.AIR.getId(), -1, Material.AIR.getId()},
        		},
        		{
        			{Material.AIR.getId(), -1, Material.AIR.getId()},
        		},
        		{
        			{Material.AIR.getId(), -1, Material.AIR.getId()},
        		}
        }));
    }
    
    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();
        
        // look for a door
    	Door door = getDoor(block.getLocation());
    	
    	if(door == null) {
    		// no door, is the rune valid?
            if (tryRune(block)) {

        		// time to create a door!
        		door = new Door();
        		door.key = block.getFace(BlockFace.DOWN, 1).getTypeId();
        		if(door.key <= 0) {
        			// invalidate the key for air
        			door.key = -1;
        		}
        		door.loc = block.getLocation();
        		// SAVE!
        		saveDoor(door);
        		
        		// now we reset the door
        		block.getFace(BlockFace.DOWN, 1).setType(block.getType());
        		block.getFace(BlockFace.DOWN, 2).setType(block.getType());
        		
        		event.getPlayer().sendMessage("Door accepted, fill in the side holes to conceal");	
            		
                return true;
            }
    	} else {
    		if(block.getFace(BlockFace.DOWN, 1).getTypeId() == Material.AIR.getId()) {
    			// door is showing, hide that bitch!
        		block.getFace(BlockFace.DOWN, 1).setType(block.getType());
        		block.getFace(BlockFace.DOWN, 2).setType(block.getType());
    		} else {
    			// door is hiding, show that bitch!
    			if(door.key > 0) {
    				// we have a key, make sure the key is present
    				boolean keyFound = false;
    				Block curBlock;
    				if((curBlock = block.getFace(BlockFace.DOWN, 2).getFace(BlockFace.NORTH)).getTypeId() == door.key) {
    					curBlock.setType(Material.AIR);
    					keyFound = true;
    				} else if((curBlock = block.getFace(BlockFace.DOWN, 2).getFace(BlockFace.EAST)).getTypeId() == door.key) {
    					curBlock.setType(Material.AIR);
    					keyFound = true;
    				} else if((curBlock = block.getFace(BlockFace.DOWN, 2).getFace(BlockFace.SOUTH)).getTypeId() == door.key) {
    					curBlock.setType(Material.AIR);
    					keyFound = true;
    				} else if((curBlock = block.getFace(BlockFace.DOWN, 2).getFace(BlockFace.WEST)).getTypeId() == door.key) {
    					curBlock.setType(Material.AIR);
    					keyFound = true;
    				}
    				
    				if(!keyFound) {
    					return false;
    				}
    			}
    			
    			block.getFace(BlockFace.DOWN, 1).setType(Material.AIR);
        		block.getFace(BlockFace.DOWN, 2).setType(Material.AIR);
    		}
    		
    		return true;
    	}
        
        return false;
    }
    
    @Override
    public boolean onRuneDamage(BlockDamageEvent event) {
    	if(event.getDamageLevel() == BlockDamageLevel.BROKEN) {
    		Block block = event.getBlock();
    		Door door = null;
    		
    		for(int i = 0; i < 3 && door == null; i++) {
    			block = event.getBlock().getFace(BlockFace.UP, i);
    			door = getDoor(block.getLocation());
    		}
    		
    		if(door != null) {
    			deleteDoor(door);
    			event.getPlayer().sendMessage("Door rune destroyed");
    			
    			// players don't get minerals back for this 
    			event.setCancelled(true);
    			
    			// wipe out the door
    			block.setType(Material.AIR);
    			block.getFace(BlockFace.DOWN, 1).setType(Material.AIR);
    			block.getFace(BlockFace.DOWN, 2).setType(Material.AIR);
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    @Override
    public boolean isRuneRedstoneUsingBlock(BlockRedstoneEvent event) {
    	Block block = event.getBlock();
    	Door door = getDoor(block.getLocation());
		
    	// only works if we have a door and it has no key
    	if(door != null && door.key < 0) {
    		System.out.println("door block redstone event");
    		System.out.println("  block:    " + block.getLocation().toString());
    		System.out.println("  powered:  " + (block.isBlockPowered() ? "true" : "false"));
    		System.out.println("  ipowered: " + (block.isBlockIndirectlyPowered() ? "true" : "false"));
    		System.out.println("  oldcur:   " + event.getOldCurrent());
    		System.out.println("  newcur:   " + event.getNewCurrent());
    		
    		if(event.getNewCurrent() == 0) {
        		block.getFace(BlockFace.DOWN, 1).setType(block.getType());
        		block.getFace(BlockFace.DOWN, 2).setType(block.getType());
    		} else {
    			block.getFace(BlockFace.DOWN, 1).setType(Material.AIR);
    			block.getFace(BlockFace.DOWN, 2).setType(Material.AIR);
    		}
    		
    		return true;
    	}
    	
    	return false;
    }
    
    private class Door {
    	public Location loc;
    	public int key;
    }
    
    private Door getDoor(Location loc) {
    	Connection sqlConn = null;
    	Door d = null;
        File dbfile = new File(mPlugin.getDataFolder(), NAME + ".db");
        try {
        	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());
        	
        	PreparedStatement stmt = sqlConn.prepareStatement(
        			"select * from doors where x = ? and y = ? and z = ?");
        	stmt.setInt(1, loc.getBlockX());
        	stmt.setInt(2, loc.getBlockY());
        	stmt.setInt(3, loc.getBlockZ());
        	
        	ResultSet rs = stmt.executeQuery();
        	
        	if(rs.next()) {
        		d = new Door();
        		d.loc = loc;
        		d.key = rs.getInt("key");
        	}
        	rs.close();
        } catch(Exception e) {
        	d = null;
        } finally {
        	if(sqlConn != null) {
        		try {
        			sqlConn.close();
        		} catch(Exception e) { }
        	}
        }
        
        return d;
    }
    
    private void saveDoor(Door d) {
    	Connection sqlConn = null;
        File dbfile = new File(mPlugin.getDataFolder(), NAME + ".db");
        try {
        	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());
        	
        	Statement stmt = sqlConn.createStatement();
        	stmt.executeUpdate("create table if not exists doors (x, y, z, key);");
        	
        	PreparedStatement pstmt = sqlConn.prepareStatement(
        			"insert into doors values (?, ?, ?, ?);");
        	pstmt.setInt(1, d.loc.getBlockX());
        	pstmt.setInt(2, d.loc.getBlockY());
        	pstmt.setInt(3, d.loc.getBlockZ());
        	pstmt.setInt(4, d.key);
        	pstmt.executeUpdate();
        } catch(Exception e) { 
        } finally {
        	if(sqlConn != null) {
        		try {
        			sqlConn.close();
        		} catch(Exception e) { }
        	}
        }
    }
    
    private void deleteDoor(Door d) {
    	Connection sqlConn = null;
        File dbfile = new File(mPlugin.getDataFolder(), NAME + ".db");
        try {
        	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());
        	
        	PreparedStatement pstmt = sqlConn.prepareStatement(
        			"delete from doors where x = ? and y = ? and z = ?");
        	pstmt.setInt(1, d.loc.getBlockX());
        	pstmt.setInt(2, d.loc.getBlockY());
        	pstmt.setInt(3, d.loc.getBlockZ());
        	pstmt.executeUpdate();
        } catch(Exception e) { 
        } finally {
        	if(sqlConn != null) {
        		try {
        			sqlConn.close();
        		} catch(Exception e) { }
        	}
        }
    }
}
