package com.nekokittygames.bukkit.magick.runeset.runecraft;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nekokittygames.bukkit.magick.Magick;
import com.nekokittygames.bukkit.magick.RedstoneUtility;
import com.nekokittygames.bukkit.magick.Rune;
import com.nekokittygames.bukkit.magick.RuneSet;
import com.nekokittygames.bukkit.runestruct.IRuneNode;
import com.nekokittygames.bukkit.runestruct.RNAnything;
import com.nekokittygames.bukkit.runestruct.RNComplexAnd;
import com.nekokittygames.bukkit.runestruct.RNMaterial;
import com.nekokittygames.bukkit.runestruct.RNMaterialGroup;
import com.nekokittygames.bukkit.runestruct.RNTier;
import com.nekokittygames.bukkit.runestruct.RuneStructure;

public class DoorRune extends Rune {

    public static final String NAME = "door";

    public DoorRune(Magick plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(3, 1, 3)
                .setClickHeight(2)
                .setRuneMap(new IRuneNode[][][]{
                        {
                            {
                                RNComplexAnd.getInstance(
                                        RNTier.getInstance(),
                                        RNMaterialGroup.getInstance(0)),

                                RNMaterial.getInstance(Material.IRON_ORE),

                                RNComplexAnd.getInstance(
                                        RNTier.getInstance(),
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
                                RNComplexAnd.getInstance(
                                        RNTier.getInstance(),
                                        RNMaterialGroup.getInstance(0)),

                                RNComplexAnd.getInstance(
                                        RNTier.getInstance(),
                                        RNMaterialGroup.getInstance(0)),

                                RNComplexAnd.getInstance(
                                        RNTier.getInstance(),
                                        RNMaterialGroup.getInstance(0)),
                            },
                        }
                       })
                .setRuneConsumptionMap(new int[][][]{
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

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

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
        }

        return false;
    }

    @Override
    public boolean onRuneUseRightClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        // look for a door
        Door door = getDoor(block.getLocation());

        if(door != null) {
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
    public boolean onRuneUseRedstone(BlockRedstoneEvent event) {
        for(Block block : RedstoneUtility.getAffectedBlocks(event)) {
            Door door = getDoor(block.getLocation());

            // only use redstone if the door exists and doesn't have a key
            if(door != null && door.key < 0) {
                // we have a door! change its status based on redstone event
                if(event.getNewCurrent() > 0) {
                    // powered, open the door
                    block.getFace(BlockFace.DOWN, 1).setType(Material.AIR);
                    block.getFace(BlockFace.DOWN, 2).setType(Material.AIR);
                } else {
                    // unpowered, close the door
                    block.getFace(BlockFace.DOWN, 1).setType(block.getType());
                    block.getFace(BlockFace.DOWN, 2).setType(block.getType());
                }
            }
        }

        // never report if we used redstone, as someone else might be using this
        // redstone event, since we are technically watching the blocks AROUND
        // the redstone, not the redstone itself
        return false;
    }

    @Override
    public boolean onRuneDamage(BlockDamageEvent event) {
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

        return false;
    }

    private class Door {
        public Location loc;
        public int key;
    }

    private Door getDoor(Location loc) {
        Connection sqlConn = null;
        Door d = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
            sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            PreparedStatement stmt = sqlConn.prepareStatement(
                    "select * from doors where w = ? and x = ? and y = ? and z = ?");
            stmt.setLong(1, loc.getWorld().getId());
            stmt.setInt(2, loc.getBlockX());
            stmt.setInt(3, loc.getBlockY());
            stmt.setInt(4, loc.getBlockZ());

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
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
            sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            Statement stmt = sqlConn.createStatement();
            stmt.executeUpdate("create table if not exists doors ("
                    + "w INTEGER NOT NULL, "
                    + "x INTEGER NOT NULL, "
                    + "y INTEGER NOT NULL, "
                    + "z INTEGER NOT NULL, "
                    + "key INTEGER, "
                    + "PRIMARY KEY (w, x, y, z));");

            PreparedStatement pstmt = sqlConn.prepareStatement(
                    "insert into doors values (?, ?, ?, ?, ?);");
            pstmt.setLong(1, d.loc.getWorld().getId());
            pstmt.setInt(2, d.loc.getBlockX());
            pstmt.setInt(3, d.loc.getBlockY());
            pstmt.setInt(4, d.loc.getBlockZ());
            pstmt.setInt(5, d.key);
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
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
            sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            PreparedStatement pstmt = sqlConn.prepareStatement(
                    "delete from doors where w = ? and x = ? and y = ? and z = ?");
            pstmt.setLong(1, d.loc.getWorld().getId());
            pstmt.setInt(2, d.loc.getBlockX());
            pstmt.setInt(3, d.loc.getBlockY());
            pstmt.setInt(4, d.loc.getBlockZ());
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
