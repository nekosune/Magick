package org.cvpcs.bukkit.magickraft.runeset.runecraft;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockDamageLevel;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.Location;

import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNComplexAnd;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterialGroup;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class MagickBeaconRune extends Rune {

	public static final String NAME = "magickbeacon";

    public MagickBeaconRune(Magickraft plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(3, 3)
        		.setRuneMap(new IRuneNode[][]{
			        	{
			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

							RNComplexAnd.getInstance(
									RNTier.getInstance(1),
									RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),
			        	},
			        	{
			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(1)),

							RNMaterial.getInstance(Material.GLOWSTONE),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(1)),
			        	},
			        	{
			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

							RNComplexAnd.getInstance(
									RNTier.getInstance(1),
									RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),
			        	},
			       	})
			    .setRuneConsumptionMap(new int[][] {
		        		{Material.AIR.getId(), Material.AIR.getId()      , Material.AIR.getId()},
		        		{Material.AIR.getId(), Material.GLOWSTONE.getId(), Material.AIR.getId()},
		        		{Material.AIR.getId(), Material.AIR.getId()      , Material.AIR.getId()},
			    	}));
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        // look for a beacon
    	MagickBeacon beacon = getMagickBeacon(block.getLocation());

    	// wewt, we only care if there isn't a beacon
    	if(beacon == null) {
    		// no beacon, is the rune valid?
            if (tryRune(block)) {
        		// time to create a beacon!
        		beacon = new MagickBeacon();
        		beacon.w = block.getWorld().getId();
        		beacon.x = block.getLocation().getBlockX();
        		beacon.z = block.getLocation().getBlockZ();

        		// SAVE!
        		saveMagickBeacon(beacon);

        		// now we create the beacon itself
        		while(block.getLocation().getBlockY() < 127) {
    				if(block.getTypeId() == Material.AIR.getId()) {
    					block.setType(Material.GLOWSTONE);
    				}
        			block = block.getFace(BlockFace.UP, 1);
    			}
        		block = event.getBlock();
        		while(block.getLocation().getBlockY() > 0) {
    				if(block.getTypeId() == Material.AIR.getId()) {
    					block.setType(Material.GLOWSTONE);
    				}
        			block = block.getFace(BlockFace.DOWN, 1);
        		}


        		event.getPlayer().sendMessage("Magick beacon created");

                return true;
            }
    	}

        return false;
    }

    @Override
    public boolean onRuneDamage(BlockDamageEvent event) {
    	if(event.getDamageLevel() == BlockDamageLevel.BROKEN) {
    		Block block = event.getBlock();
    		MagickBeacon beacon = getMagickBeacon(block.getLocation());

    		if(beacon != null) {
    			deleteMagickBeacon(beacon);
    			event.getPlayer().sendMessage("Magick beacon destroyed");

    			// players don't get minerals back for this
    			event.setCancelled(true);

        		// now we create the beacon itself
        		while(block.getLocation().getBlockY() < 127) {
    				if(block.getTypeId() == Material.GLOWSTONE.getId()) {
    					block.setType(Material.AIR);
    				}
        			block = block.getFace(BlockFace.UP, 1);
    			}
        		block = event.getBlock();
        		while(block.getLocation().getBlockY() > 0) {
    				if(block.getTypeId() == Material.GLOWSTONE.getId()) {
    					block.setType(Material.AIR);
    				}
        			block = block.getFace(BlockFace.DOWN, 1);
        		}


    			return true;
    		}
    	}

    	return false;
    }

    private class MagickBeacon {
    	public long w;
    	public int x;
    	public int z;
    }

    private MagickBeacon getMagickBeacon(Location loc) {
    	Connection sqlConn = null;
    	MagickBeacon b = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
        	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

        	PreparedStatement stmt = sqlConn.prepareStatement(
        			"select * from beacons where w = ? and x = ? and z = ?");
        	stmt.setLong(1, loc.getWorld().getId());
        	stmt.setInt(2, loc.getBlockX());
        	stmt.setInt(3, loc.getBlockZ());

        	ResultSet rs = stmt.executeQuery();

        	if(rs.next()) {
        		b = new MagickBeacon();
        		b.w = loc.getWorld().getId();
        		b.x = loc.getBlockX();
        		b.z = loc.getBlockZ();
        	}
        	rs.close();
        } catch(Exception e) {
        	b = null;
        } finally {
        	if(sqlConn != null) {
        		try {
        			sqlConn.close();
        		} catch(Exception e) { }
        	}
        }

        return b;
    }

    private void saveMagickBeacon(MagickBeacon b) {
    	Connection sqlConn = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
        	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

        	Statement stmt = sqlConn.createStatement();
        	stmt.executeUpdate("create table if not exists beacons ("
        			+ "w INTEGER NOT NULL, "
        			+ "x INTEGER NOT NULL, "
        			+ "z INTEGER NOT NULL, "
        			+ "PRIMARY KEY (w, x, z));");

        	PreparedStatement pstmt = sqlConn.prepareStatement(
        			"insert into beacons values (?, ?, ?);");
        	pstmt.setLong(1, b.w);
        	pstmt.setInt(2, b.x);
        	pstmt.setInt(3, b.z);
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

    private void deleteMagickBeacon(MagickBeacon b) {
    	Connection sqlConn = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
        	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

        	PreparedStatement pstmt = sqlConn.prepareStatement(
        			"delete from beacons where w = ? and x = ? and z = ?");
        	pstmt.setLong(1, b.w);
        	pstmt.setInt(2, b.x);
        	pstmt.setInt(3, b.z);
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
