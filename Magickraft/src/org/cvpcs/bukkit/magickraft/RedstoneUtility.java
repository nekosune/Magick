package org.cvpcs.bukkit.magickraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.ArrayList;

public class RedstoneUtility {

    /**
     * Takes a given redstone event and returns a list of blocks that are going
     * to be effected by this redstone's change.
     *
     * @param event The redstone event to check
     * @return A list of blocks that will have a status change due to this event
     */
    public static ArrayList<Block> getAffectedBlocks(BlockRedstoneEvent event) {
        // array list! yay
        ArrayList<Block> blocks = new ArrayList<Block>();

        // get the redstone block
        Block redstone = event.getBlock();

        // cycle through affected block faces and return blocks in those directions
        for(BlockFace face : getAffectedFaces(redstone)) {
            blocks.add(redstone.getFace(face, 1));
        }

        return blocks;
    }

    /**
     * Takes a redstone block and returns an arraylist of block faces that will
     * be effected by changes to it.
     *
     * @param redstone The redstone block in question
     * @return The effected block faces
     */
    public static ArrayList<BlockFace> getAffectedFaces(Block redstone) {
        // array list! yay
        ArrayList<BlockFace> blockfaces = new ArrayList<BlockFace>();

        if(redstone.getTypeId() == Material.REDSTONE_WIRE.getId()) {
            // redstone wire effects blocks around and below it
            blockfaces.add(BlockFace.NORTH);
            blockfaces.add(BlockFace.EAST);
            blockfaces.add(BlockFace.SOUTH);
            blockfaces.add(BlockFace.WEST);
            blockfaces.add(BlockFace.DOWN);
        } else if(redstone.getTypeId() == Material.REDSTONE_TORCH_OFF.getId() ||
                redstone.getTypeId() == Material.REDSTONE_TORCH_ON.getId()) {
            // redstone torches always power above themselves
            blockfaces.add(BlockFace.UP);

            switch(redstone.getData()) {
            case 1: // 1 = pointing south, all directions but north
                blockfaces.add(BlockFace.EAST);
                blockfaces.add(BlockFace.SOUTH);
                blockfaces.add(BlockFace.WEST);
                break;
            case 2: // 2 = pointing north, all directions but south
                blockfaces.add(BlockFace.NORTH);
                blockfaces.add(BlockFace.EAST);
                blockfaces.add(BlockFace.WEST);
                break;
            case 3: // 3 = pointing west, all directions but east
                blockfaces.add(BlockFace.NORTH);
                blockfaces.add(BlockFace.SOUTH);
                blockfaces.add(BlockFace.WEST);
                break;
            case 4: // 4 = pointing east, all directions but west
                blockfaces.add(BlockFace.NORTH);
                blockfaces.add(BlockFace.EAST);
                blockfaces.add(BlockFace.SOUTH);
                break;
            default:
            case 5: // 5 = standing upright, all directions
                blockfaces.add(BlockFace.NORTH);
                blockfaces.add(BlockFace.EAST);
                blockfaces.add(BlockFace.SOUTH);
                blockfaces.add(BlockFace.WEST);
                break;
            }
        }

        return blockfaces;
    }
}
