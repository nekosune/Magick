package org.cvpcs.bukkit.magickraft.runeset.magickraft;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.block.Block;
import org.bukkit.block.BlockDamageLevel;
import org.bukkit.block.ContainerBlock;
import org.bukkit.Material;
import org.bukkit.Location;

import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNAnything;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class NetherChestRune extends Rune {

    public static final String NAME = "netherchest";

    public NetherChestRune(Magickraft plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(5, 5, 2)
                .setRuneMap(new IRuneNode[][][]{
                        {
                            {
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNMaterial.getInstance(Material.TORCH),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                            },
                            {
                                RNAnything.getInstance(),
                                RNMaterial.getInstance(Material.REDSTONE_TORCH_ON),
                                RNMaterial.getInstance(Material.REDSTONE_WIRE),
                                RNMaterial.getInstance(Material.REDSTONE_TORCH_ON),
                                RNAnything.getInstance(),
                            },
                            {
                                RNMaterial.getInstance(Material.TORCH),
                                RNMaterial.getInstance(Material.REDSTONE_WIRE),
                                RNMaterial.getInstance(Material.CHEST),
                                RNMaterial.getInstance(Material.REDSTONE_WIRE),
                                RNMaterial.getInstance(Material.TORCH),
                            },
                            {
                                RNAnything.getInstance(),
                                RNMaterial.getInstance(Material.REDSTONE_TORCH_ON),
                                RNMaterial.getInstance(Material.REDSTONE_WIRE),
                                RNMaterial.getInstance(Material.REDSTONE_TORCH_ON),
                                RNAnything.getInstance(),
                            },
                            {
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNMaterial.getInstance(Material.TORCH),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                            },
                        },
                        {
                            {
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                            },
                            {
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                            },
                            {
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNMaterial.getInstance(Material.REDSTONE_WIRE),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                            },
                            {
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                            },
                            {
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                                RNAnything.getInstance(),
                            },
                        },
                    })
                .setRuneConsumptionMap(new int[][][]{
                        {
                            {-1                  , -1                  , Material.AIR.getId(), -1                  , -1                  },
                            {-1                  , Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), -1                  },
                            {Material.AIR.getId(), Material.AIR.getId(), -1                  , Material.AIR.getId(), Material.AIR.getId()},
                            {-1                  , Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), -1                  },
                            {-1                  , -1                  , Material.AIR.getId(), -1                  , -1                  },
                        },
                        {
                            {-1, -1, -1                  , -1, -1},
                            {-1, -1, -1                  , -1, -1},
                            {-1, -1, Material.AIR.getId(), -1, -1},
                            {-1, -1, -1                  , -1, -1},
                            {-1, -1, -1                  , -1, -1},
                        },
                    }));
    }

    public String getName() { return NAME; }
    
    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        return false;
    }

    @Override
    public boolean onRuneUseRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        if(block.getType() == Material.CHEST) {
            NetherChest nc = new NetherChest();
            if(nc.load(event.getPlayer(), block.getLocation())) {
                // we have a nether chest! time to fill this chest's contents
                
                ContainerBlock chest = (ContainerBlock)block.getState();
            }
        }
        
        return false;
    }

    @Override
    public boolean onRuneDamage(BlockDamageEvent event) {
        // only care if destroying the block
        if(event.getDamageLevel() == BlockDamageLevel.BROKEN) {
            Block block = event.getBlock();
            
            ContainerBlock b = (ContainerBlock)block.getState();
            Inventory i = b.getInventory();
            ItemStack[] is = i.getContents();
            is[0].getData();
            
            MaterialData md = new MaterialData(1);
        }

        return false;
    }

    private class NetherChest {
        public ItemStack[] mContents = new ItemStack[0];
        public String mPlayerName = "";
        public Location mLocation = null;
        
        public boolean load(Player p, Location loc) {
            boolean loaded = false;
            
            mContents = new ItemStack[0];
            mLocation = null;
            mPlayerName = "";

            Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
                sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

                PreparedStatement pstmt = sqlConn.prepareStatement(
                        "select * from locations where w = ? and x = ? and y = ? and z = ?;");
                pstmt.setLong(1, loc.getWorld().getId());
                pstmt.setInt(2, loc.getBlockX());
                pstmt.setInt(3, loc.getBlockY());
                pstmt.setInt(4, loc.getBlockZ());

                ResultSet rs = pstmt.executeQuery();

                if(rs.next()) {
                    mLocation = loc;
                    mPlayerName = p.getDisplayName();
                }
                rs.close();
                
                // did we find a location?
                if(mLocation != null) {
                    pstmt = sqlConn.prepareStatement(
                            "select * from chests where player = ?;");
                    pstmt.setString(1, mPlayerName);
                    
                    rs = pstmt.executeQuery();
                    
                    if(rs.next()) {
                        mContents = unserializeContents(rs.getBytes("contents"));
                        loaded = true;
                    }
                    rs.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
                loaded = false;
            } finally {
                if(sqlConn != null) {
                    try {
                        sqlConn.close();
                    } catch(Exception e) { }
                }
            }

            if(!loaded) {
                mLocation = null;
                mContents = new ItemStack[0];
                mPlayerName = "";
            }

            return loaded;
        }

        public void save() {
            Connection sqlConn = null;
            File dbfile = new File(getRuneSet().getDataFolder(), NAME + ".db");
            try {
                sqlConn = DriverManager.getConnection("jdbc:sqlite:" + dbfile.getAbsolutePath());

                Statement stmt = sqlConn.createStatement();
                stmt.executeUpdate("create table if not exists chests ("
                            + "player TEXT NOT NULL, "
                            + "contents BLOB NOT NULL, "
                            + "PRIMARY KEY (player));");

                PreparedStatement pstmt = sqlConn.prepareStatement(
                        "select * from chests where player = ?;");
                pstmt.setString(1, mPlayerName);
                ResultSet rs = pstmt.executeQuery();
                boolean chestExists = rs.next();
                rs.close();
                
                if(!chestExists) {
                    pstmt = sqlConn.prepareStatement(
                        "insert into chests (player, contents) values (?, ?);");
                    pstmt.setString(1, mPlayerName);
                    pstmt.setBytes(2, serializeContents(mContents));
                    pstmt.executeUpdate();
                } else {
                    pstmt = sqlConn.prepareStatement(
                        "update chests set contents = ? where player = ?;");
                    pstmt.setBytes(1, serializeContents(mContents));
                    pstmt.setString(2, mPlayerName);
                    pstmt.executeUpdate();
                }

                stmt.executeUpdate("create table if not exists locations ("
                        + "w INTEGER NOT NULL, "
                        + "x INTEGER NOT NULL, "
                        + "y INTEGER NOT NULL, "
                        + "z INTEGER NOT NULL, "
                        + "PRIMARY KEY (w, x, y, z));");
                
                pstmt = sqlConn.prepareStatement(
                        "select * from locations where w = ? AND x = ? AND y = ? AND z = ?;");
                pstmt.setLong(1, mLocation.getWorld().getId());
                pstmt.setInt(2, mLocation.getBlockX());
                pstmt.setInt(3, mLocation.getBlockY());
                pstmt.setInt(4, mLocation.getBlockZ());
                rs = pstmt.executeQuery();
                boolean locationExists = rs.next();
                rs.close();
                
                if(!locationExists) {
                    pstmt = sqlConn.prepareStatement(
                        "insert into locations (w, x, y, z) values (?, ?, ?, ?);");
                    pstmt.setLong(1, mLocation.getWorld().getId());
                    pstmt.setInt(2, mLocation.getBlockX());
                    pstmt.setInt(3, mLocation.getBlockY());
                    pstmt.setInt(4, mLocation.getBlockZ());
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
                        "delete from locations where w = ? AND x = ? AND y = ? AND z = ?;");
                pstmt.setLong(1, mLocation.getWorld().getId());
                pstmt.setInt(2, mLocation.getBlockX());
                pstmt.setInt(3, mLocation.getBlockY());
                pstmt.setInt(4, mLocation.getBlockZ());
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
        
        private byte[] serializeContents(ItemStack[] contents) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            
            try {
                // write the number of elements first
                dos.writeInt(contents.length);
                
                for(ItemStack stack : contents) {
                    // type id
                    dos.writeInt(stack.getTypeId());
                    
                    // amount
                    dos.writeInt(stack.getAmount());
                    
                    // durability
                    dos.writeShort(stack.getDurability());
                    
                    // data
                    dos.writeByte(stack.getData().getData());
                }
                
                dos.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
            
            return baos.toByteArray();
        }
    }
    
    private ItemStack[] unserializeContents(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bais);
        
        ItemStack[] contents = new ItemStack[0];
        
        try {
            // get the number of elements
            int num = dis.readInt();
            
            contents = new ItemStack[num];
            
            for(int i = 0; i < num; i++) {
                // type id
                int type = dis.readInt();
                
                // amount
                int amount = dis.readInt();
                
                // durability
                short durability = dis.readShort();
                
                // data
                byte data = dis.readByte();
                
                contents[i] = new ItemStack(type, amount, durability, data);
            }
            
            dis.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        return contents;
    }
}
