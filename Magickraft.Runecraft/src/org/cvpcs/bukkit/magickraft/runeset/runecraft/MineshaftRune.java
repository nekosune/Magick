package org.cvpcs.bukkit.magickraft.runeset.runecraft;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;

import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNComplexAnd;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterialGroup;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

public class MineshaftRune extends Rune {

	public static final String NAME = "mineshaft";

	private static final int LEVELS_PER_TIER = 8;
	private static final int MIN_LEVEL = 5;

    public MineshaftRune(Magickraft plugin, RuneSet set) {
        super(plugin, set, new RuneStructure(7, 7, 5)
        		.setRuneMap(new IRuneNode[][][]{
        				{//1
        					{//1
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        					},
        					{//2
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        					},
        					{//3
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        					},
        					{//4
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNTier.getInstance(1),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        					},
        					{//5
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        					},
        					{//6
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        					},
        					{//7
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        						RNMaterial.getInstance(Material.REDSTONE_WIRE),
        					},
        				},
        				{//2
        					{//1
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//2
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//3
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//4
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//5
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//6
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//7
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        				},
        				{//3
        					{//1
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//2
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//3
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//4
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//5
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//6
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//7
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        				},
        				{//4
        					{//1
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//2
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//3
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//4
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//5
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//6
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//7
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        				},
        				{//5
        					{//1
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//2
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//3
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//4
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNComplexAnd.getInstance(
        								RNMaterialGroup.getInstance(0),
        								RNTier.getInstance(1)),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//5
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//6
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        					{//7
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        						RNMaterial.getInstance(Material.AIR),
        					},
        				},
        			})
        		.setRuneConsumptionMap(new int[][][]{
        				{//1
        					{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
        					{Material.AIR.getId(), -1                  , -1                  , -1                  , -1                  , -1                  , Material.AIR.getId()},
        					{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), -1                  , Material.AIR.getId()},
        					{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), -1                  , Material.AIR.getId(), -1                  , Material.AIR.getId()},
        					{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), -1                  , Material.AIR.getId()},
        					{Material.AIR.getId(), -1                  , -1                  , -1                  , -1                  , -1                  , Material.AIR.getId()},
        					{Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId()},
        				},
        				{//2
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , -1                  , Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), -1                  , -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        				},
        				{//3
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), Material.AIR.getId(), -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        				},
        				{//4
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        				},
        				{//5
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , Material.AIR.getId(), -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        					{-1                  , -1                  , -1                  , -1                  , -1                  , -1                  , -1                  },
        				},
        			}));
    }

    public String getName() { return NAME; }

    @Override
    public boolean onRuneRightClick(BlockRightClickEvent event) {
        Block block = event.getBlock();

        if (tryRune(block)) {
        	// we have a valid mineshaft rune! get our tier level!
        	int tier = TierUtility.getTier(block);

        	// reset this block to air now that we have the tier level
        	block.setType(Material.AIR);

        	// move down
        	block = block.getFace(BlockFace.DOWN, 1);

        	// start cycling
        	for(int t = 0; t < tier; t++) {
        		for(int l = 0; l < LEVELS_PER_TIER; l++) {
        			// have we reached the lowest level we'll mine to?
        			if(block.getY() < MIN_LEVEL) {
        				// yes we have, return and call it good
        				return true;
        			}

        			// clear all blocks surrounding this one including this one
        			block.getFace(BlockFace.NORTH).setType(Material.AIR);
        			block.getFace(BlockFace.NORTH_EAST).setType(Material.AIR);
        			block.getFace(BlockFace.EAST).setType(Material.AIR);
        			block.getFace(BlockFace.SOUTH_EAST).setType(Material.AIR);
        			block.getFace(BlockFace.SOUTH).setType(Material.AIR);
        			block.getFace(BlockFace.SOUTH_WEST).setType(Material.AIR);
        			block.getFace(BlockFace.WEST).setType(Material.AIR);
        			block.getFace(BlockFace.NORTH_WEST).setType(Material.AIR);
        			block.setType(Material.AIR);

        			// now we find the rotation of our step for this level
        			int rotation = ((t * LEVELS_PER_TIER) + l) % 4;

        			// set the step
        			switch(rotation) {
        			case 0:
        				block.getFace(BlockFace.NORTH_WEST).setType(Material.DIRT);
        				block.getFace(BlockFace.NORTH).setType(Material.DIRT);
        				break;
        			case 1:
        				block.getFace(BlockFace.NORTH_EAST).setType(Material.DIRT);
        				block.getFace(BlockFace.EAST).setType(Material.DIRT);
        				break;
        			case 2:
        				block.getFace(BlockFace.SOUTH_EAST).setType(Material.DIRT);
        				block.getFace(BlockFace.SOUTH).setType(Material.DIRT);
        				break;
       				default:
        			case 3:
        				block.getFace(BlockFace.SOUTH_WEST).setType(Material.DIRT);
        				block.getFace(BlockFace.WEST).setType(Material.DIRT);
        				break;
        			}

        			// increment the depth
        			block = block.getFace(BlockFace.DOWN, 1);
        		}
        	}

            return true;
        }

        return false;
    }
}
