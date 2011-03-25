package com.nekokittygames.bukkit.magick.runeset.runecraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nekokittygames.bukkit.runestruct.IRuneNode;
import com.nekokittygames.bukkit.runestruct.RNAnything;
import com.nekokittygames.bukkit.runestruct.RNMaterial;
import com.nekokittygames.bukkit.magick.Magick;
import com.nekokittygames.bukkit.magick.Rune;
import com.nekokittygames.bukkit.magick.RuneSet;
import com.nekokittygames.bukkit.runestruct.RuneStructure;

public class ChronoTriggerRune extends Rune
{
    public static final String NAME = "chronotrigger";

    public ChronoTriggerRune(Magick plugin, RuneSet set)
    {
        super(plugin, set, new RuneStructure(3, 3, 3)
                .setClickHeight(2)
                .setRuneMap(new IRuneNode[][][]{
                        {
                            {RNAnything.getInstance(), RNAnything.getInstance()                   , RNAnything.getInstance()},
                            {RNAnything.getInstance(), RNMaterial.getInstance(Material.IRON_BLOCK), RNAnything.getInstance()},
                            {RNAnything.getInstance(), RNAnything.getInstance()                   , RNAnything.getInstance()},
                        },
                        {
                            {RNAnything.getInstance(), RNAnything.getInstance()                   , RNAnything.getInstance()},
                            {RNAnything.getInstance(), RNMaterial.getInstance(Material.IRON_BLOCK), RNAnything.getInstance()},
                            {RNAnything.getInstance(), RNAnything.getInstance()                   , RNAnything.getInstance()},
                        },
                        {
                            {RNMaterial.getInstance(Material.GOLD_BLOCK), RNMaterial.getInstance(Material.GOLD_BLOCK)   , RNMaterial.getInstance(Material.GOLD_BLOCK)},
                            {RNMaterial.getInstance(Material.GOLD_BLOCK), RNMaterial.getInstance(Material.DIAMOND_BLOCK), RNMaterial.getInstance(Material.OBSIDIAN)},
                            {RNMaterial.getInstance(Material.OBSIDIAN), RNMaterial.getInstance(Material.OBSIDIAN)     , RNMaterial.getInstance(Material.OBSIDIAN)},
                        },
                    })
                .setAllowedRotation(RuneStructure.Rotation.NORTH));
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(PlayerInteractEvent event)
    {
        Block block = event.getClickedBlock();

        if (canChrono(block))
        {
            if (block.getY()<108)
                event.getPlayer().sendMessage("Chrono Trigger is too far from the sun.");
            else if (hasCeiling(block))
                event.getPlayer().sendMessage("The sun cannot see your rune.");
            else
            {
                if (block.getFace(BlockFace.WEST,2).getType()==Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.WEST,2).getType()==Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.WEST,2).setType(Material.AIR);
                    block.getWorld().setTime(750);
                }
                else if(block.getFace(BlockFace.WEST,2).getFace(BlockFace.NORTH).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.WEST,2).getFace(BlockFace.NORTH).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.WEST,2).getFace(BlockFace.NORTH).setType(Material.AIR);
                    block.getWorld().setTime(2250);
                }
                else if(block.getFace(BlockFace.NORTH_WEST,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.NORTH_WEST,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.NORTH_WEST,2).setType(Material.AIR);
                    block.getWorld().setTime(3750);
                }
                else if(block.getFace(BlockFace.WEST).getFace(BlockFace.NORTH,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.WEST).getFace(BlockFace.NORTH,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.WEST).getFace(BlockFace.NORTH,2).setType(Material.AIR);
                    block.getWorld().setTime(5250);
                }
                else if(block.getFace(BlockFace.NORTH,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.NORTH,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.NORTH,2).setType(Material.AIR);
                    block.getWorld().setTime(6750);
                }
                else if(block.getFace(BlockFace.NORTH,2).getFace(BlockFace.EAST).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.NORTH,2).getFace(BlockFace.EAST).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.NORTH,2).getFace(BlockFace.EAST).setType(Material.AIR);
                    block.getWorld().setTime(8250);
                }
                else if(block.getFace(BlockFace.NORTH_EAST,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.NORTH_EAST,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.NORTH_EAST,2).setType(Material.AIR);
                    block.getWorld().setTime(9750);
                }
                else if(block.getFace(BlockFace.NORTH).getFace(BlockFace.EAST,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.NORTH).getFace(BlockFace.EAST,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.NORTH).getFace(BlockFace.EAST,2).setType(Material.AIR);
                    block.getWorld().setTime(11250);
                }
                else if(block.getFace(BlockFace.EAST,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.EAST,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.EAST,2).setType(Material.AIR);
                    block.getWorld().setTime(12750);
                }
                else if(block.getFace(BlockFace.EAST,2).getFace(BlockFace.SOUTH).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.EAST,2).getFace(BlockFace.SOUTH).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.EAST,2).getFace(BlockFace.SOUTH).setType(Material.AIR);
                    block.getWorld().setTime(14250);
                }
                else if(block.getFace(BlockFace.SOUTH_EAST,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.SOUTH_EAST,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.SOUTH_EAST,2).setType(Material.AIR);
                    block.getWorld().setTime(15750);
                }
                else if(block.getFace(BlockFace.EAST).getFace(BlockFace.SOUTH,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.EAST).getFace(BlockFace.SOUTH,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.EAST).getFace(BlockFace.SOUTH,2).setType(Material.AIR);
                    block.getWorld().setTime(17250);
                }
                else if(block.getFace(BlockFace.SOUTH,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.SOUTH,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.SOUTH,2).setType(Material.AIR);
                    block.getWorld().setTime(18750);
                }
                else if(block.getFace(BlockFace.SOUTH,2).getFace(BlockFace.WEST).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.SOUTH,2).getFace(BlockFace.WEST).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.SOUTH,2).getFace(BlockFace.WEST).setType(Material.AIR);
                    block.getWorld().setTime(20250);
                }
                else if(block.getFace(BlockFace.SOUTH_WEST,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.SOUTH_WEST,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.SOUTH_WEST,2).setType(Material.AIR);
                    block.getWorld().setTime(21750);
                }
                else if(block.getFace(BlockFace.SOUTH).getFace(BlockFace.WEST,2).getType() == Material.REDSTONE_TORCH_ON || block.getFace(BlockFace.SOUTH).getFace(BlockFace.WEST,2).getType() == Material.REDSTONE_WIRE)
                {
                    block.getFace(BlockFace.SOUTH).getFace(BlockFace.WEST,2).setType(Material.AIR);
                    block.getWorld().setTime(23250);
                }
            }
            return true;
        }
        return false;
    }

    private boolean canChrono(Block block)
    {
        int redCount=0;
        for(int i=-2;i<3;i++)
        {
            for(int j=-2;j<3;j++)
            {
                Block block2 = block.getRelative(i, 0, j);
                if (((i+2)%4==0) || ((j+2)%4==0))
                {
                    if (block2.getType()==Material.REDSTONE_WIRE||block2.getType()==Material.REDSTONE_TORCH_ON)
                        redCount++;
                }
            }
        }
        if (redCount!=1)return false;
        return
            (tryRune(block));
    }

    private boolean hasCeiling(Block block)
    {
        for(int i=-1;i<2;i++)
        {
            for(int j=-1;j<2;j++)
            {
                if (block.getWorld().getHighestBlockYAt(block.getX()+i, block.getZ()+j)>block.getY()+1)
                {
                    return true;
                }
            }
        }
        return false;
    }
}