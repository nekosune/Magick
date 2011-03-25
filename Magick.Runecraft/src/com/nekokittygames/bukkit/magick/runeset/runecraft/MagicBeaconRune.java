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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nekokittygames.bukkit.magick.Magick;
import com.nekokittygames.bukkit.magick.Rune;
import com.nekokittygames.bukkit.magick.RuneSet;
import com.nekokittygames.bukkit.runestruct.RuneStructure;

public class MagicBeaconRune extends Rune {

    public static final String NAME = "magicbeacon";

    public MagicBeaconRune(Magick plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(3, 3)
                .setRuneConsumptionMap(new int[][] {
                        {Material.AIR.getId(), Material.AIR.getId()      , Material.AIR.getId()},
                        {Material.AIR.getId(), Material.GLOWSTONE.getId(), Material.AIR.getId()},
                        {Material.AIR.getId(), Material.AIR.getId()      , Material.AIR.getId()},
                    }), "/magicbeacon.runestruct");
        Connection sqlConn = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
        	Class.forName("org.sqlite.JDBC");
            sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            Statement stmt = sqlConn.createStatement();
            stmt.executeUpdate("create table if not exists beacons ("
                    + "w INTEGER NOT NULL, "
                    + "x INTEGER NOT NULL, "
                    + "z INTEGER NOT NULL, "
                    + "PRIMARY KEY (w, x, z));");
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

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        // look for a beacon
        MagicBeacon beacon = getMagicBeacon(block.getLocation());

        // wewt, we only care if there isn't a beacon
        if(beacon == null) {
            // no beacon, is the rune valid?
            if (tryRune(block)) {
                // time to create a beacon!
                beacon = new MagicBeacon();
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
                block = event.getClickedBlock();
                while(block.getLocation().getBlockY() > 0) {
                    if(block.getTypeId() == Material.AIR.getId()) {
                        block.setType(Material.GLOWSTONE);
                    }
                    block = block.getFace(BlockFace.DOWN, 1);
                }


                event.getPlayer().sendMessage("Magic beacon created");

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onRuneBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        MagicBeacon beacon = getMagicBeacon(block.getLocation());

        if(beacon != null) {
            deleteMagickBeacon(beacon);
            event.getPlayer().sendMessage("Magic beacon destroyed");

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

        return false;
    }

    private class MagicBeacon {
        public long w;
        public int x;
        public int z;
    }

    private MagicBeacon getMagicBeacon(Location loc) {
        Connection sqlConn = null;
        MagicBeacon b = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
        	Class.forName("org.sqlite.JDBC");
            sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            PreparedStatement stmt = sqlConn.prepareStatement(
                    "select * from beacons where w = ? and x = ? and z = ?");
            stmt.setLong(1, loc.getWorld().getId());
            stmt.setInt(2, loc.getBlockX());
            stmt.setInt(3, loc.getBlockZ());

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                b = new MagicBeacon();
                b.w = loc.getWorld().getId();
                b.x = loc.getBlockX();
                b.z = loc.getBlockZ();
            }
            rs.close();
        } catch(Exception e) {
        	e.printStackTrace();
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

    private void saveMagickBeacon(MagicBeacon b) {
        Connection sqlConn = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
        	Class.forName("org.sqlite.JDBC");
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
        	e.printStackTrace();
        } finally {
            if(sqlConn != null) {
                try {
                    sqlConn.close();
                } catch(Exception e) { }
            }
        }
    }

    private void deleteMagickBeacon(MagicBeacon b) {
        Connection sqlConn = null;
        File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
        try {
        	Class.forName("org.sqlite.JDBC");
            sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

            PreparedStatement pstmt = sqlConn.prepareStatement(
                    "delete from beacons where w = ? and x = ? and z = ?");
            pstmt.setLong(1, b.w);
            pstmt.setInt(2, b.x);
            pstmt.setInt(3, b.z);
            pstmt.executeUpdate();
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
}
