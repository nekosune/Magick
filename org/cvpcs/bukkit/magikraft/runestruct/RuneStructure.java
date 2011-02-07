package org.cvpcs.bukkit.magikraft.runestruct;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.cvpcs.bukkit.magikraft.Magikraft;

import java.util.Arrays;
import java.util.HashMap;

public class RuneStructure {
	private int mWidth;			// width of the rune
	private int mLength;		// length of the rune
	private int mHeight;		// height of the rune
	private int mClickHeight;	// click height of the rune
	
	/**
	 * A 3D array of values.  Any positive values are seen as "tier levels" that must be matched.
	 * Any negative values are seen as inverted type ids for specific block types that must be present. 
	 */
	private IRuneNode[][][] mRuneMap;
	
	/**
	 * A 3D array of values.  Any positive values are seen as block ids for blocks that should be placed
	 * in place of the present ones upon "consumption" of the rune.  Any negative values mean that position
	 * is left unconsumed.
	 */
	private int[][][] mRuneConsumptionMap;
	
	public RuneStructure(int width, int length, IRuneNode map[][]) {
		this(width, length, map, new int[][]{});
	}
	
	public RuneStructure(int width, int length, IRuneNode map[][], int cmap[][]) {
		this(width, length, 1, new IRuneNode[][][]{map}, new int[][][]{cmap});
	}
	
	public RuneStructure(int width, int length, int height, IRuneNode map[][][]) {
		this(width, length, height, map, new int[][][]{});
	}
	
	public RuneStructure(int width, int length, int height, int clickheight, IRuneNode map[][][]) {
		this(width, length, height, clickheight, map, new int[][][]{});
	}
	
	public RuneStructure(int width, int length, int height, IRuneNode map[][][], int cmap[][][]) {
		this(width, length, height, 0, map, cmap);
	}
	
	public RuneStructure(int width, int length, int height, int clickheight, IRuneNode map[][][], int cmap[][][]) {
		mWidth = width;
		mLength = length;
		mHeight = height;
		mClickHeight = clickheight;
		
		mRuneMap = new IRuneNode[height][length][width];
		mRuneConsumptionMap = new int[height][length][width];
		
		// zero out rune map by default
		for(int h = 0; h < height; h++) {
			for(int l = 0; l < length; l++) {
				Arrays.fill(mRuneMap[h][l], RNAnything.getInstance());
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
	
	private void setRuneMapSlice(int height, IRuneNode slice[][]) {
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
		// check all rotations
		for(int r = 0; r < 4; r++) {
			boolean found = true;
			
			// we need to reset some of the node types
			RNMaterialGroup.resetGroups();
			
			// find our "top-left" corner block at the base height
			Block tlBlock = block.getFace(getDirection(BlockFace.WEST, r), (mWidth - 1)/2); // move "west"
			tlBlock = tlBlock.getFace(getDirection(BlockFace.NORTH, r), (mLength - 1)/2); // move "north"
			tlBlock = tlBlock.getFace(BlockFace.DOWN, mClickHeight); // move down if our click height was elevated
			
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
						if(!mRuneMap[h][l][w].isValid(wBlock)) {
							found = false;
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
}
