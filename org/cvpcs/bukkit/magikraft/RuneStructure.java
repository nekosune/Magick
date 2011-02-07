package org.cvpcs.bukkit.magikraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Arrays;
import java.util.HashMap;

public class RuneStructure {
    private static final HashMap<Integer, Integer> TIERS = new HashMap<Integer, Integer>();
    static {
    	// tier 0
        TIERS.put(Material.AIR.getId(), 0);
        TIERS.put(Material.DIRT.getId(), 0);
        TIERS.put(Material.GRASS.getId(), 0);
        TIERS.put(Material.STONE.getId(), 0);
        // tier 1
        TIERS.put(Material.COBBLESTONE.getId(), 1);
        TIERS.put(Material.YELLOW_FLOWER.getId(), 1);
        TIERS.put(Material.RED_ROSE.getId(), 1);
        TIERS.put(Material.GRAVEL.getId(), 1);
        TIERS.put(Material.LOG.getId(), 1);
        TIERS.put(Material.SAND.getId(), 1);
        TIERS.put(Material.WOOD.getId(), 1);
        TIERS.put(Material.FIRE.getId(), 1);
        TIERS.put(Material.NETHERRACK.getId(), 1);
        TIERS.put(Material.SOUL_SAND.getId(), 1);
        TIERS.put(Material.WOOL.getId(), 1);
        TIERS.put(Material.SANDSTONE.getId(), 1);
        // tier 2
        TIERS.put(Material.CHEST.getId(), 2);
        TIERS.put(Material.SOIL.getId(), 2);
        TIERS.put(Material.FURNACE.getId(), 2);
        TIERS.put(Material.GLASS.getId(), 2);
        TIERS.put(Material.LAVA.getId(), 2);
        TIERS.put(Material.LEAVES.getId(), 2);
        TIERS.put(Material.RED_MUSHROOM.getId(), 2);
        TIERS.put(Material.BROWN_MUSHROOM.getId(), 2);
        TIERS.put(Material.STEP.getId(), 2);
        TIERS.put(Material.COBBLESTONE_STAIRS.getId(), 2);
        TIERS.put(Material.WOOD_STAIRS.getId(), 2);
        TIERS.put(Material.WATER.getId(), 2);
        TIERS.put(Material.WORKBENCH.getId(), 2);
        TIERS.put(Material.GLOWSTONE.getId(), 2);
        TIERS.put(Material.NOTE_BLOCK.getId(), 2);
        // tier 3
        TIERS.put(Material.BRICK.getId(), 3);
        TIERS.put(Material.CACTUS.getId(), 3);
        TIERS.put(Material.CLAY.getId(), 3);
        TIERS.put(Material.IRON_ORE.getId(), 3);
        TIERS.put(Material.REDSTONE_ORE.getId(), 3);
        TIERS.put(Material.SUGAR_CANE_BLOCK.getId(), 3);
        TIERS.put(Material.TORCH.getId(), 3);
        TIERS.put(Material.LAPIS_BLOCK.getId(), 3);
        TIERS.put(Material.LAPIS_ORE.getId(), 3);
        TIERS.put(Material.CAKE_BLOCK.getId(), 3);
        TIERS.put(Material.REDSTONE_WIRE.getId(), 3);
        // tier 4
        TIERS.put(Material.BEDROCK.getId(), 4);
        TIERS.put(Material.BOOKSHELF.getId(), 4);
        TIERS.put(Material.GOLD_ORE.getId(), 4);
        TIERS.put(Material.IRON_BLOCK.getId(), 4);
        TIERS.put(Material.MOSSY_COBBLESTONE.getId(), 4);
        TIERS.put(Material.TNT.getId(), 4);
        // tier 5
        TIERS.put(Material.GOLD_BLOCK.getId(), 5);
        TIERS.put(Material.JUKEBOX.getId(), 5);
        TIERS.put(Material.OBSIDIAN.getId(), 5);
        // tier 6
        TIERS.put(Material.DIAMOND_BLOCK.getId(), 6);
    }
    
	private Magikraft mPlugin;
	private int mWidth;
	private int mLength;
	private int mHeight;
	private int mClickHeight;
	
	/**
	 * A 3D array of values.  Any positive values are seen as "tier levels" that must be matched.
	 * Any negative values are seen as inverted type ids for specific block types that must be present. 
	 */
	private int[][][] mRuneMap;
	/**
	 * A 3D array of values.  Any positive values are seen as block ids for blocks that should be placed
	 * in place of the present ones upon "consumption" of the rune.  Any negative values mean that position
	 * is left unconsumed.
	 */
	private int[][][] mRuneConsumptionMap;
	
	public RuneStructure(Magikraft plugin, int width, int length, int map[][]) {
		this(plugin, width, length, map, new int[][]{});
	}
	
	public RuneStructure(Magikraft plugin, int width, int length, int map[][], int cmap[][]) {
		this(plugin, width, length, 1, new int[][][]{map}, new int[][][]{cmap});
	}
	
	public RuneStructure(Magikraft plugin, int width, int length, int height, int map[][][]) {
		this(plugin, width, length, height, map, new int[][][]{});
	}
	
	public RuneStructure(Magikraft plugin, int width, int length, int height, int clickheight, int map[][][]) {
		this(plugin, width, length, height, clickheight, map, new int[][][]{});
	}
	
	public RuneStructure(Magikraft plugin, int width, int length, int height, int map[][][], int cmap[][][]) {
		this(plugin, width, length, height, 0, map, cmap);
	}
	
	public RuneStructure(Magikraft plugin, int width, int length, int height, int clickheight, int map[][][], int cmap[][][]) {
		mPlugin = plugin;
		
		mWidth = width;
		mLength = length;
		mHeight = height;
		mClickHeight = clickheight;
		
		mRuneMap = new int[height][length][width];
		mRuneConsumptionMap = new int[height][length][width];
		
		// zero out rune map by default
		for(int h = 0; h < height; h++) {
			for(int l = 0; l < length; l++) {
				Arrays.fill(mRuneMap[h][l], 0);
			}
		}

		// no consumption by default
		for(int h = 0; h < height; h++) {
			for(int l = 0; l < length; l++) {
				Arrays.fill(mRuneConsumptionMap[h][l], -1);
			}
		}
		
		// now attempt to fill the map with the given one
		for(int h = 0; h < map.length; h++) {
			setRuneMapSlice(h, map[h]);
		}
		
		// now attempt to fill the consumption map with the given one
		for(int h = 0; h < cmap.length; h++) {
			setRuneConsumptionMapSlice(h, cmap[h]);
		}
	}
	
	private void setRuneMapSlice(int height, int slice[][]) {
		if(height < mHeight) {
			for(int l = 0; l < mLength && l < slice.length; l++) {
				for(int w = 0; w < mWidth && w < slice[l].length; w++) {
					mRuneMap[height][l][w] = slice[l][w];
				}
			}
		}
	}
	
	private void setRuneConsumptionMapSlice(int height, int slice[][]) {
		if(height < mHeight) {
			for(int l = 0; l < mLength && l < slice.length; l++) {
				for(int w = 0; w < mWidth && w < slice[l].length; w++) {
					mRuneConsumptionMap[height][l][w] = slice[l][w];
				}
			}
		}
	}
	
	public boolean isRune(Block block) {
System.out.println("Running rune checker...");
		
		// check all rotations
		for(int r = 0; r < 4; r++) {
			boolean found = true;
			
System.out.println("Checking rotation " + r);
			
			// find our "top-left" corner block at the base height
			Block tlBlock = block.getFace(getDirection(BlockFace.WEST, r), (mWidth - 1)/2); // move "west"
			tlBlock = tlBlock.getFace(getDirection(BlockFace.NORTH, r), (mLength - 1)/2); // move "north"
			tlBlock = tlBlock.getFace(BlockFace.DOWN, mClickHeight); // move down if our click height was elevated
			
System.out.println("Top left block: " + tlBlock.getType().toString() + " " + tlBlock.getLocation().toString());
			
			// now we start scanning
			for(int h = 0; h < mHeight; h++) {
				// tlBlock for this height
				Block hBlock = tlBlock.getFace(BlockFace.UP, h);
				
				for(int l = 0; l < mLength; l++) {
					// hBlock for this row
					Block lBlock = hBlock.getFace(getDirection(BlockFace.SOUTH, r), l);
					
					for(int w = 0; w < mWidth; w++) {
						// block to test
						Block wBlock = lBlock.getFace(getDirection(BlockFace.EAST, r), w);
						
						// is this block at the proper tier level?
						int mapVal = mRuneMap[h][l][w];
						
						if(mapVal < -1) {
							// if the value is negative, then we're specifying a specific block
							int blockTypeId = -(mapVal + 2);
							
							if(wBlock.getTypeId() != blockTypeId) {
System.out.println("Block type invalid!");
System.out.println("  " + wBlock.getTypeId() + " != " + blockTypeId);
								// INVALID!
								found = false;
							}
						} else {
							// value is positive, specifying a tier level
							if(getTier(wBlock) < mapVal) {
System.out.println("Block tier invalid!");
System.out.println("  " + getTier(wBlock) + " < " + mapVal);
								// INVALID!
								found = false;
							}
						}

						// break out if we didn't find anything
						if(!found) {
							break;
						}
					}

					// break out if we didn't find anything					
					if(!found) {
						break;
					}
				}
				
				// break out if we didn't find anything
				if(!found) {
					break;
				}
			}
			
			// did we validate all height levels?
			if(found) {
				// we did! time to consume!
				
				// now we start consuming
				for(int h = 0; h < mHeight; h++) {
					// tlBlock for this height
					Block hBlock = tlBlock.getFace(BlockFace.UP, h);
					
					for(int l = 0; l < mLength; l++) {
						// hBlock for this row
						Block lBlock = hBlock.getFace(getDirection(BlockFace.SOUTH, r), l);
						
						for(int w = 0; w < mWidth; w++) {
							// block to test
							Block wBlock = lBlock.getFace(getDirection(BlockFace.EAST, r), w);
							
							int consumeValue = mRuneConsumptionMap[h][l][w];
							
							if(consumeValue >= 0) {
								// it's consumin' time!
								wBlock.setType(Material.getMaterial(consumeValue));
							}
						}
					}
				}
				
				return true;
			}
		}
		
		// didn't find anything, sad panda
		return false;
	}
	
	public BlockFace getDirection(BlockFace desiredDirection, int rotation) {
		// rotation == 0 for none (north), 1 for 90 degrees (east), 2 for 180 degrees (south), 3 for 270 degrees (west)
		switch(rotation) {
		case 1:
			if(desiredDirection == BlockFace.NORTH) {
				return BlockFace.EAST;
			} else if(desiredDirection == BlockFace.EAST) {
				return BlockFace.SOUTH;
			} else if(desiredDirection == BlockFace.SOUTH) {
				return BlockFace.WEST;
			} else {
				return BlockFace.NORTH;
			}
		case 2:
			if(desiredDirection == BlockFace.NORTH) {
				return BlockFace.SOUTH;
			} else if(desiredDirection == BlockFace.EAST) {
				return BlockFace.WEST;
			} else if(desiredDirection == BlockFace.SOUTH) {
				return BlockFace.NORTH;
			} else {
				return BlockFace.EAST;
			}
		case 3:
			if(desiredDirection == BlockFace.NORTH) {
				return BlockFace.WEST;
			} else if(desiredDirection == BlockFace.EAST) {
				return BlockFace.NORTH;
			} else if(desiredDirection == BlockFace.SOUTH) {
				return BlockFace.EAST;
			} else {
				return BlockFace.SOUTH;
			}
		case 0:
		default:
			return desiredDirection;
		}
	}
	
	public int getTier(Block block) {
		if(TIERS.containsKey(block.getTypeId())) {
			return TIERS.get(block.getTypeId());
		} else {
			// anything not identified is returned as -1
			return -1;
		}
	}
}
