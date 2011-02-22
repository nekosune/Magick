package org.cvpcs.bukkit.magickraft.runeset.magickraft;

import java.util.List;
import java.util.Random;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;

import org.cvpcs.bukkit.magickraft.BlockUtility;
import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNComplexAnd;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterialGroup;
import org.cvpcs.bukkit.magickraft.runestruct.RNTier;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;
import org.cvpcs.bukkit.magickraft.runestruct.TierUtility;

public class FiregaRune extends Rune {

    private static final int FIREGA_RADIUS_PER_TIER = 4;
    private static final int FIREGA_SAFETY_RADIUS = 4;

    public static final String NAME = "firega";

    private static final Random RAND = new Random();

    public FiregaRune(Magickraft plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(7, 1, 7)
                .setClickHeight(3)
                .setRuneMap(new IRuneNode[][][]{
                        {//1
                            {
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                            }
                        },
                        {//2
                            {
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                            }
                        },
                        {//3
                            {
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.AIR),
                            }
                        },
                        {//4
                            {
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNMaterial.getInstance(Material.DIAMOND_BLOCK),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.AIR),
                            }
                        },
                        {//5
                            {
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                            }
                        },
                        {//6
                            {
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance()),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                            }
                        },
                        {//7
                            {
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.IRON_BLOCK),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                                RNMaterial.getInstance(Material.AIR),
                            }
                        },
                    })
                .setRuneConsumptionMap(new int[][][]{
                        {//1
                            {
                                Material.GLASS.getId(),
                                Material.GLASS.getId(),
                                Material.GLASS.getId(),
                                Material.GLASS.getId(),
                                Material.GLASS.getId(),
                                Material.GLASS.getId(),
                                Material.GLASS.getId(),
                            }
                        },
                        {//2
                            {
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                            }
                        },
                        {//3
                            {
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                            }
                        },
                        {//4
                            {
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                            }
                        },
                        {//5
                            {
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                            }
                        },
                        {//6
                            {
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                            }
                        },
                        {//7
                            {
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.GLASS.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                                Material.AIR.getId(),
                            }
                        },
                    }));
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        // is it an Aeroga rune?
        if (tryRuneWithoutConsumption(block)) {

            // oh lord time to burn stuff, first get our tier value though
            int tier = TierUtility.getTier(block.getFace(BlockFace.DOWN, 1));

            // now we can consume our rune
            tryRune(block);

            // get our radius
            int radius = FIREGA_SAFETY_RADIUS + (FIREGA_RADIUS_PER_TIER * (tier + 1));

            // center for our radius
            Block center = block.getFace(BlockFace.DOWN, 4);

            // get our safe sphere
            List<Block> safety = BlockUtility.getSphereBlocks(center, FIREGA_SAFETY_RADIUS);

            // next get our sphere
            List<Block> sphere = BlockUtility.getSphereBlocks(center, radius);

            // initial pass to set block types
            for(Block b : sphere) {
                // only process non-safe elements
                if(!safety.contains(b)) {
                    switch(b.getType()) {
                    // we set all of this to obsidian, and it will be changed to lava on the 2nd pass
                    case ICE:
                    case WATER:
                    case STATIONARY_WATER:
                        b.setType(Material.OBSIDIAN);
                        break;
                    // this goes to netherrack
                    case SOIL:
                    case GRASS:
                    case DIRT:
                    case STONE:
                    case SNOW_BLOCK:
                        b.setType(Material.NETHERRACK);
                        break;
                    // some sand stuffs
                    case GRAVEL:
                    case SAND:
                        b.setType(Material.SOUL_SAND);
                        break;
                    // no flowers in hell
                    case RED_ROSE:
                        b.setType(Material.RED_MUSHROOM);
                        break;
                    case YELLOW_FLOWER:
                        b.setType(Material.BROWN_MUSHROOM);
                        break;
                    // no snow or baby trees
                    case SNOW:
                    case SAPLING:
                        b.setType(Material.AIR);
                        break;
                    default:
                        break;
                    }
                }
            }

            // second pass to set shit on fire/create lava
            for(Block b : sphere) {
                // only process non-safe elements
                if(!safety.contains(b)) {
                    switch(b.getType()) {
                    case AIR:
                        Block[] surrounding = new Block[] {
                                b.getFace(BlockFace.UP, 1),
                                b.getFace(BlockFace.DOWN, 1),
                                b.getFace(BlockFace.NORTH, 1),
                                b.getFace(BlockFace.SOUTH, 1),
                                b.getFace(BlockFace.EAST, 1),
                                b.getFace(BlockFace.WEST, 1),
                            };

                        boolean burn = false;
                        for(Block s : surrounding) {
                            switch(s.getType()) {
                            // netherrack should only set on fire 25% of the time
                            case NETHERRACK:
                                if(RAND.nextDouble() < 0.25) {
                                    burn = true;
                                }
                                break;
                            // burn all crops/wood/trees/etc 15% of the time since they burn like wildfire
                            case LEAVES:
                            case WOOD:
                            case BOOKSHELF:
                            case WOOL:
                            case CROPS:
                            case SUGAR_CANE:
                            case CACTUS:
                                if(RAND.nextDouble() < 0.15) {
                                    burn = true;
                                }
                                break;
                            default:
                                break;
                            }
                        }

                        if(burn) {
                            b.setType(Material.FIRE);
                        }
                        break;
                    // turn this to lava
                    case OBSIDIAN:
                        b.setType(Material.STATIONARY_LAVA);
                        break;
                    default:
                        break;
                    }
                }
            }

               // alert the player of their awesomeness
               event.getPlayer().sendMessage("Firega has seared the land!");

            return true;
        }

        return false;
    }
}
