package com.nekokittygames.bukkit.runestruct;

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class RuneStructure {
    private int mWidth;            // width of the rune
    private int mLength;        // length of the rune
    private int mHeight;        // height of the rune
    private int mClickHeight;    // click height of the rune
    private static final Logger log=Logger.getLogger("Minecraft");
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

    /**
     * An array of "rotation" values.  The structure always considers up to be "north", so setting one of
     * these values forces the rune to only be accepted if facing one of the given directions.
     */
    private Rotation[] mAllowedRotations;

    public RuneStructure(int width, int length) {
        this(width, length, 1);
    }

    public RuneStructure(int width, int length, int height) {
        mWidth = width;
        mLength = length;
        mHeight = height;
        mClickHeight = 0;
        mAllowedRotations = Rotation.values();

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
    }

    /** begin methods used to set structure data upon creation **/
    public RuneStructure setRuneMap(IRuneNode map[][]) {
        setRuneMapSlice(0, map);
        return this;
    }

    public RuneStructure setRuneMap(IRuneNode map[][][]) {
        for(int h = 0; h < map.length; h++) {
            setRuneMapSlice(h, map[h]);
        }
        return this;
    }

    public RuneStructure setRuneConsumptionMap(int cmap[][]) {
        setRuneConsumptionMapSlice(0, cmap);
        return this;
    }

    public RuneStructure setRuneConsumptionMap(int cmap[][][]) {
        // now attempt to fill the consumption map with the given one
        for(int h = 0; h < cmap.length; h++) {
            setRuneConsumptionMapSlice(h, cmap[h]);
        }
        return this;
    }

    public RuneStructure setClickHeight(int height) {
        if(height < mHeight) {
            mClickHeight = height;
        }
        return this;
    }

    public RuneStructure setAllowedRotations(Rotation[] rotations) {
        mAllowedRotations = rotations;
        return this;
    }

    public RuneStructure setAllowedRotation(Rotation rotation) {
        mAllowedRotations = new Rotation[]{rotation};
        return this;
    }
    /** end methods used to set structure data upon creation **/

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

    public int getSize() {
        return mWidth * mLength * mHeight;
    }

    public boolean tryRune(Block block, boolean performConsumption,boolean logthis) {
        // check all rotations
        for(Rotation r : mAllowedRotations) {
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
                            if(logthis)
                            	log.info("Block: h="+h+" l="+l+" w="+w+"Block not right tier: block was: "+wBlock.getTypeId()+" r:"+r+" wanted: "+mRuneMap[h][l][w]);
                            break;
                        }
                        if(logthis)
                        	log.info("Block: h="+h+" l="+l+" w="+w+" is ok block was: "+wBlock.getTypeId()+" r:"+r);
                    }

                    // break out if we didn't find anything
                    if(!found) {
                    	if(logthis)
                    		log.info("not found1");
                        break;
                    }
                }

                // break out if we didn't find anything
                if(!found) {
                	if(logthis)
                		log.info("Not found2");
                    break;
                }
                
            }

            // did we validate all height levels?
            if(found) {
                if(performConsumption) {
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
                }

                return true;
            }
        }

        // didn't find anything, sad panda
        return false;
    }

    public BlockFace getDirection(BlockFace desiredDirection, Rotation rotation) {
        // rotation == 0 for none (north), 1 for 90 degrees (east), 2 for 180 degrees (south), 3 for 270 degrees (west)
        switch(rotation) {
        case EAST:
            if(desiredDirection == BlockFace.NORTH) {
                return BlockFace.EAST;
            } else if(desiredDirection == BlockFace.EAST) {
                return BlockFace.SOUTH;
            } else if(desiredDirection == BlockFace.SOUTH) {
                return BlockFace.WEST;
            } else {
                return BlockFace.NORTH;
            }
        case SOUTH:
            if(desiredDirection == BlockFace.NORTH) {
                return BlockFace.SOUTH;
            } else if(desiredDirection == BlockFace.EAST) {
                return BlockFace.WEST;
            } else if(desiredDirection == BlockFace.SOUTH) {
                return BlockFace.NORTH;
            } else {
                return BlockFace.EAST;
            }
        case WEST:
            if(desiredDirection == BlockFace.NORTH) {
                return BlockFace.WEST;
            } else if(desiredDirection == BlockFace.EAST) {
                return BlockFace.NORTH;
            } else if(desiredDirection == BlockFace.SOUTH) {
                return BlockFace.EAST;
            } else {
                return BlockFace.SOUTH;
            }
        case NORTH:
        default:
            return desiredDirection;
        }
    }

    public enum Rotation {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}