package org.cvpcs.bukkit.magickraft.runeset.magickraft;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockDamageLevel;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;

import org.cvpcs.bukkit.magickraft.BlockUtility;
import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runeset.runecraft.RNTier;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNAnything;
import org.cvpcs.bukkit.magickraft.runestruct.RNComplexAnd;
import org.cvpcs.bukkit.magickraft.runestruct.RNComplexOr;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterialGroup;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class WaypointRune extends Rune {

	public static final String NAME = "waypoint";

	private static final Material WAYPOINT_MATERIAL = Material.GOLD_BLOCK;
	private static final Material TELEPORT_MATERIAL = Material.OBSIDIAN;

	private static final int RUNE_WIDTH = 7;

    public WaypointRune(Magickraft plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(RUNE_WIDTH, RUNE_WIDTH)
        		.setRuneMap(new IRuneNode[][]{
			    		{
			    			RNAnything.getInstance(),
			    			RNAnything.getInstance(),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNAnything.getInstance(),
			    			RNAnything.getInstance(),
			    		},
			    		{
			    			RNAnything.getInstance(),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNAnything.getInstance(),
			    		},
			    		{
			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNAnything.getInstance(),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),
			    		},
			    		{
			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNAnything.getInstance(),

			    			RNComplexOr.getInstance(
			    					RNMaterial.getInstance(WAYPOINT_MATERIAL),
			    					RNMaterial.getInstance(TELEPORT_MATERIAL)),

			    			RNAnything.getInstance(),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),
			    		},
			    		{
			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNAnything.getInstance(),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),
			    		},
			    		{
			    			RNAnything.getInstance(),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(2),
			    					RNMaterialGroup.getInstance(1)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNAnything.getInstance(),
			    		},
			    		{
			    			RNAnything.getInstance(),
			    			RNAnything.getInstance(),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNComplexAnd.getInstance(
			    					RNTier.getInstance(1),
			    					RNMaterialGroup.getInstance(0)),

			    			RNAnything.getInstance(),
			    			RNAnything.getInstance(),
			    		},
			        }));
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        // look for a waypoint and see if it's a valid teleporter
        Waypoint wp = new Waypoint();
        if(!wp.load(block.getLocation())) {
        	if(tryRune(block)) {
        		// we have a valid rune, time to do some shit!
        		wp.mIsTeleporter = (block.getTypeId() == TELEPORT_MATERIAL.getId());
        		wp.mLocation = block.getLocation();
        		wp.mSignature = block.getFace(BlockFace.NORTH, 1).getType().toString() + "."
        				+ block.getFace(BlockFace.EAST, 1).getType().toString() + "."
        				+ block.getFace(BlockFace.SOUTH, 1).getType().toString() + "."
        				+ block.getFace(BlockFace.WEST, 1).getType().toString();

        		// search to make sure another waypoint with this signature doesn't exist
        		Waypoint sigCheck = new Waypoint();
        		if(sigCheck.load(wp.mSignature)) {
        			if(sigCheck.mIsTeleporter == wp.mIsTeleporter) {
        				// two unregistered waypoints of the same type with the same signature, can't have that shit
        				event.getPlayer().sendMessage("Waypoint casting failed: Another unregistered waypoint with the same identification already exists");
        				return false;
        			} else {
        				// make sure our signature dest world exists
        				World sigWorld = sigCheck.mLocation.getWorld();
        				if(sigWorld != null) {
            				// OH SHIT! LOOKS LIKE WE BE MAKIN A CONNECTION!
            				wp.mWaypointId = sigCheck.mId;
            				wp.save();
            				wp.load(block.getLocation());
            				sigCheck.mWaypointId = wp.mId;
            				sigCheck.save();

                    		setWaypointBlock(wp, Material.GLOWSTONE);
                    		setWaypointBlock(sigCheck, Material.GLOWSTONE);

            				event.getPlayer().sendMessage("Waypoint successfully connected");
        				} else {
        					// destination world not found and not connected, just delete and hope for the best
        					sigCheck.delete();
        				}
        			}
        		} else {
        			wp.save();

        			// set our waypoint blocks
        			setWaypointBlock(wp, Material.COBBLESTONE);

        			event.getPlayer().sendMessage("Waypoint created and awaiting connection");
        		}

        		return true;
        	}
    	}

        return false;
    }

    @Override
    public boolean onRuneUseRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        // look for a waypoint and see if it's a valid teleporter
        Waypoint wp = new Waypoint();
        if(wp.load(block.getLocation())) {
        	if(wp.mWaypointId >= 0) {
        		if(wp.mIsTeleporter) {
		        	// HOLY SHIT IT IS!  time to get our destination id
		        	Waypoint dest = new Waypoint();
		        	if(dest.load(wp.mWaypointId)) {
		        		// find our destination world
		        		World destWorld = dest.mLocation.getWorld();
		        		if(destWorld != null) {
			        		// get our destination block
			        		Block destBlock = destWorld.getBlockAt(
									dest.mLocation.getBlockX(), dest.mLocation.getBlockY(), dest.mLocation.getBlockZ());

			        		// load the destination chunk
			        		destWorld.loadChunk(destWorld.getChunkAt(destBlock));

			        		// continually raise the teleport destination up until we are in a "safe" zone
			        		while(!(destBlock.getTypeId() == Material.AIR.getId() &&
			        				destBlock.getFace(BlockFace.DOWN, 1).getTypeId() == Material.AIR.getId())) {
			        			destBlock = destBlock.getFace(BlockFace.UP, 1);
			        		}

			        		// teleport!
			        		event.getPlayer().teleportTo(destBlock.getLocation());
		        		} else {
		        			// the destination world can't be found, so wipe out that shit
		        			dest.delete();

			        		// turn off this teleporter, we can't find our destination
			        		wp.mWaypointId = -1;
			        		wp.save();
			        		setWaypointBlock(wp, Material.COBBLESTONE);

			        		event.getPlayer().sendMessage("Could not find the destination waypoint, resetting teleporter");
		        		}
		        	} else {
		        		// turn off this teleporter, we can't find our destination
		        		wp.mWaypointId = -1;
		        		wp.save();
		        		setWaypointBlock(wp, Material.COBBLESTONE);

		        		event.getPlayer().sendMessage("Could not find the destination waypoint, resetting teleporter");
		        	}
        		} else {
        			event.getPlayer().sendMessage("This is the destination waypoint, nothing happens");
        		}
        	} else if(wp.mIsTeleporter) {
        		event.getPlayer().sendMessage("Waypoint casted but not connected yet");
        	}
        }

        return false;
    }

    private void setWaypointBlockAll(Waypoint wp, Material mat) {
    	int radius = (RUNE_WIDTH - 1)/2;

		int wx = wp.mLocation.getBlockX();
		int wy = wp.mLocation.getBlockY();
		int wz = wp.mLocation.getBlockZ();

		// we'll need this later
		World wpWorld = wp.mLocation.getWorld();

		// this should always be true, but check anyway
		if(wpWorld != null) {
			// gotta get this block and set it to cobblestone!
			Block b = wpWorld.getBlockAt(wx, wy, wz);

			// set our blocks to cobblestone
			BlockUtility.setCircleBlocks(b, radius, BlockUtility.Axis.Y, Material.COBBLESTONE);
    	}
    }

    private void setWaypointBlock(Waypoint wp, Material mat) {
		World wpWorld = wp.mLocation.getWorld();

		if(wpWorld != null) {
			Block block = wpWorld.getBlockAt(
					wp.mLocation.getBlockX(),
					wp.mLocation.getBlockY(),
					wp.mLocation.getBlockZ());

	    	// make sure we are talking about a valid waypoint
	    	if(tryRune(block)) {
	    		Block nBlock = block.getFace(BlockFace.NORTH, 2);
	    		nBlock.setType(mat);
	    		nBlock.getFace(BlockFace.WEST, 1).setType(mat);
	    		nBlock.getFace(BlockFace.EAST, 1).setType(mat);

	    		Block eBlock = block.getFace(BlockFace.EAST, 2);
	    		eBlock.setType(mat);
	    		eBlock.getFace(BlockFace.NORTH, 1).setType(mat);
	    		eBlock.getFace(BlockFace.SOUTH, 1).setType(mat);

	    		Block sBlock = block.getFace(BlockFace.SOUTH, 2);
	    		sBlock.setType(mat);
	    		sBlock.getFace(BlockFace.WEST, 1).setType(mat);
	    		sBlock.getFace(BlockFace.EAST, 1).setType(mat);

	    		Block wBlock = block.getFace(BlockFace.WEST, 2);
	    		wBlock.setType(mat);
	    		wBlock.getFace(BlockFace.NORTH, 1).setType(mat);
	    		wBlock.getFace(BlockFace.SOUTH, 1).setType(mat);
	    	}
		}
    }

    @Override
    public boolean onRuneDamage(BlockDamageEvent event) {
    	// only care if destroying the block
    	if(event.getDamageLevel() == BlockDamageLevel.BROKEN) {
	    	Block block = event.getBlock();

	    	int radius = (RUNE_WIDTH - 1)/2;

	    	// are we destroying a waypoint?
	    	Waypoint wp = new Waypoint();
	    	if(wp.findAndLoad(block.getLocation(), radius)) {
	    		// to save time and processing power, we determine if they're destroying a part of the
	    		// waypoint based on mathematics, and not straight comparison of the rune
	    		int bx = block.getLocation().getBlockX();
	    		int bz = block.getLocation().getBlockZ();
	    		int wx = wp.mLocation.getBlockX();
	    		int wz = wp.mLocation.getBlockZ();

	    		double dist = Math.sqrt(Math.pow(wx - bx, 2) + Math.pow(wz - bz, 2));

	    		// if dist == 1, then we are at one of the signature blocks, which we allow people to replace,
	    		// so let it go this time
	    		if(dist == 1) {
	    			return true;
	    		}

	    		// if the rounded distance is > radius, we are out of bounds and good to go
	    		if(dist > radius) {
	    			return true;
	    		}

	    		// damn, shit is going down disable the destruction
	    		event.setCancelled(true);

	    		// set the waypoint to cobblestone
	    		setWaypointBlockAll(wp, Material.COBBLESTONE);

	    		// set the block we destroyed to air
	    		block.setType(Material.AIR);

	    		// was this waypoint connected?
	    		if(wp.mWaypointId >= 0) {
	    			// SHIT, need to destroy the other end too
	    			Waypoint wpOther = new Waypoint();
	    			if(wpOther.load(wp.mWaypointId)) {
	    				// reset its blocks to cobblestone
	    				setWaypointBlockAll(wpOther, Material.COBBLESTONE);

	    				// delete
	    				wpOther.delete();
	    			}
	    		}

	    		// lastly, delete this waypoint
	    		wp.delete();

	    		// submit a message to the player
	    		event.getPlayer().sendMessage("Waypoint destroyed");
	    	}
    	}

    	return false;
    }

    private class Waypoint {
    	public int mId = -1;
    	public Location mLocation;
    	public String mSignature;
    	public int mWaypointId = -1;
    	public boolean mIsTeleporter = false;

    	public boolean findAndLoad(Location loc, int radius) {
        	boolean loaded = false;

        	Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
            	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            	PreparedStatement stmt = sqlConn.prepareStatement(
            			"select * from waypoints where "
            			+ "w = ? and "
            			+ "y = ? and "
            			+ "(x - ?) <= ? and "
            			+ "? <= (x + ?) and "
            			+ "(z - ?) <= ? and "
            			+ "? <= (z + ?);");

            	stmt.setLong(1, loc.getWorld().getId());
            	stmt.setInt(2, loc.getBlockY());

            	stmt.setInt(3, radius);
            	stmt.setInt(4, loc.getBlockX());
            	stmt.setInt(5, loc.getBlockX());
            	stmt.setInt(6, radius);

            	stmt.setInt(7, radius);
            	stmt.setInt(8, loc.getBlockZ());
            	stmt.setInt(9, loc.getBlockZ());
            	stmt.setInt(10, radius);

            	ResultSet rs = stmt.executeQuery();

            	if(rs.next()) {
            		mLocation = new Location(findWorld(rs.getLong("w")),
            				rs.getInt("x"), rs.getInt("y"), rs.getInt("z"));
            		mId = rs.getInt("id");
            		mSignature = rs.getString("signature");
            		mWaypointId = rs.getInt("waypointid");
            		mIsTeleporter = (rs.getInt("isteleporter") == 1);
            		loaded = true;
            	}
            	rs.close();
            } catch(Exception e) {
            	loaded = false;
            } finally {
            	if(sqlConn != null) {
            		try {
            			sqlConn.close();
            		} catch(Exception e) { }
            	}
            }

            if(!loaded) {
            	mId = -1;
            }

            return loaded;
    	}

    	public boolean load(Location loc) {
        	boolean loaded = false;

        	Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
            	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            	PreparedStatement stmt = sqlConn.prepareStatement(
            			"select * from waypoints where w = ? and x = ? and y = ? and z = ?;");
            	stmt.setLong(1, loc.getWorld().getId());
            	stmt.setInt(2, loc.getBlockX());
            	stmt.setInt(3, loc.getBlockY());
            	stmt.setInt(4, loc.getBlockZ());

            	ResultSet rs = stmt.executeQuery();

            	if(rs.next()) {
            		mLocation = loc;
            		mId = rs.getInt("id");
            		mSignature = rs.getString("signature");
            		mWaypointId = rs.getInt("waypointid");
            		mIsTeleporter = (rs.getInt("isteleporter") == 1);
            		loaded = true;
            	}
            	rs.close();
            } catch(Exception e) {
            	loaded = false;
            } finally {
            	if(sqlConn != null) {
            		try {
            			sqlConn.close();
            		} catch(Exception e) { }
            	}
            }

            if(!loaded) {
            	mId = -1;
            }

            return loaded;
    	}

    	public boolean load(int id) {
        	boolean loaded = false;

        	Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
            	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            	PreparedStatement stmt = sqlConn.prepareStatement(
            			"select * from waypoints where id = ?;");
            	stmt.setInt(1, id);

            	ResultSet rs = stmt.executeQuery();

            	if(rs.next()) {
            		mLocation = new Location(
            				WaypointRune.super.findWorld(rs.getLong("w")),
            				rs.getInt("x"), rs.getInt("y"), rs.getInt("z"));
            		mId = rs.getInt("id");
            		mSignature = rs.getString("signature");
            		mWaypointId = rs.getInt("waypointid");
            		mIsTeleporter = (rs.getInt("isteleporter") == 1);
            		loaded = true;
            	}
            	rs.close();
            } catch(Exception e) {
            	loaded = false;
            } finally {
            	if(sqlConn != null) {
            		try {
            			sqlConn.close();
            		} catch(Exception e) { }
            	}
            }

            if(!loaded) {
            	mId = -1;
            }

            return loaded;
    	}


    	public boolean load(String signature) {
        	boolean loaded = false;

        	Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
            	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            	PreparedStatement stmt = sqlConn.prepareStatement(
            			"select * from waypoints where signature = ? and waypointid < 0;");
            	stmt.setString(1, signature);

            	ResultSet rs = stmt.executeQuery();

            	if(rs.next()) {
            		mLocation = new Location(
            				WaypointRune.super.findWorld(rs.getLong("w")),
            				rs.getInt("x"), rs.getInt("y"), rs.getInt("z"));
            		mId = rs.getInt("id");
            		mSignature = rs.getString("signature");
            		mWaypointId = rs.getInt("waypointid");
            		mIsTeleporter = (rs.getInt("isteleporter") == 1);
            		loaded = true;
            	}
            	rs.close();
            } catch(Exception e) {
            	loaded = false;
            } finally {
            	if(sqlConn != null) {
            		try {
            			sqlConn.close();
            		} catch(Exception e) { }
            	}
            }

            if(!loaded) {
            	mId = -1;
            }

            return loaded;
    	}

        public void save() {
        	Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
            	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            	Statement stmt = sqlConn.createStatement();
            	stmt.executeUpdate("create table if not exists waypoints ("
            				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            				+ "w INTEGER NOT NULL, "
            				+ "x INTEGER NOT NULL, "
            				+ "y INTEGER NOT NULL, "
            				+ "z INTEGER NOT NULL, "
            				+ "signature TEXT DEFAULT '', "
            				+ "waypointid INTEGER DEFAULT -1, "
            				+ "isteleporter INTEGER DEFAULT 0, "
            				+ "UNIQUE (w, x, y, z));");

            	if(mId < 0) {
            		PreparedStatement pstmt = sqlConn.prepareStatement(
	            		"insert into waypoints (w, x, y, z, signature, waypointid, isteleporter) values (?, ?, ?, ?, ?, ?, ?);");
            		pstmt.setLong(1, mLocation.getWorld().getId());
	            	pstmt.setInt(2, mLocation.getBlockX());
	            	pstmt.setInt(3, mLocation.getBlockY());
	            	pstmt.setInt(4, mLocation.getBlockZ());
	            	pstmt.setString(5, mSignature);
	            	pstmt.setInt(6, mWaypointId);
	            	pstmt.setInt(7, (mIsTeleporter ? 1 : 0));
	            	pstmt.executeUpdate();
            	} else {
            		PreparedStatement pstmt = sqlConn.prepareStatement(
	            		"update waypoints set w = ?, x = ?, y = ?, z = ?, signature = ?, waypointid = ?, isteleporter = ? where id = ?;");
            		pstmt.setLong(1, mLocation.getWorld().getId());
	            	pstmt.setInt(2, mLocation.getBlockX());
	            	pstmt.setInt(3, mLocation.getBlockY());
	            	pstmt.setInt(4, mLocation.getBlockZ());
	            	pstmt.setString(5, mSignature);
	            	pstmt.setInt(6, mWaypointId);
	            	pstmt.setInt(7, (mIsTeleporter ? 1 : 0));
	            	pstmt.setInt(8, mId);
	            	pstmt.executeUpdate();
            	}
            } catch(Exception e) {
            	e.printStackTrace();
            } finally {
            	if(sqlConn != null) {
            		try {
            			sqlConn.close();
            		} catch(Exception e) { }
            	}
            }
        }

        public void delete() {
        	Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
            	sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            	PreparedStatement pstmt = sqlConn.prepareStatement(
            			"delete from waypoints where id = ?;");
            	pstmt.setInt(1, mId);
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
}
