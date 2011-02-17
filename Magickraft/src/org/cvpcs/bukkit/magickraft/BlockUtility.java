package org.cvpcs.bukkit.magickraft;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockUtility {

    public enum Axis {
        X, Y, Z
    }

    /**
     * Returns a list of all of the blocks within a radius of this block in two dimentions (a circle)
     *
     * @param center The block to be the center of the circle
     * @param radius The radius of the circle to get
     * @param axis The axis that the slice comes from (this determines which axis value will remain constant,
     *             so for example, if it is Y then the circle returned will be horizontal.
     * @return
     */
    public static List<Block> getCircleBlocks(Block center, int radius, Axis axis) {
        List<Block> result = new ArrayList<Block>();

        // find our diameter, always odd since we need a center block
        int diameter = (2 * radius) + 1;

        int bx = center.getX();
        int by = center.getY();
        int bz = center.getZ();

        // we'll need this later
        World w = center.getWorld();

        // this should always be true, but check anyway
        if(w != null) {
            // time to find the corner block to search through
            int cx = bx - radius;
            int cy = by - radius;
            int cz = bz - radius;

            // now we cycle!
            for(int i = 0; i < diameter; i++) {
                for(int j = 0; j < diameter; j++) {
                    int tx;
                    int ty;
                    int tz;

                    switch(axis) {
                    case X:
                        ty = cy + i;
                        tz = cz + j;

                        if(Math.round(Math.sqrt(
                                Math.pow(by - ty, 2) + Math.pow(bz - tz, 2)
                                )) <= radius) {
                            // gotta get this block and return it!
                            Block b = w.getBlockAt(center.getX(), ty, tz);
                            result.add(b);
                        }
                        break;
                    case Z:
                        tx = cx + i;
                        ty = cy + j;

                        if(Math.round(Math.sqrt(
                                Math.pow(bx - tx, 2) + Math.pow(by - ty, 2)
                                )) <= radius) {
                            // gotta get this block and return it!
                            Block b = w.getBlockAt(tx, ty, center.getZ());
                            result.add(b);
                        }
                        break;
                    default:
                    case Y:
                        tx = cx + i;
                        tz = cz + j;

                        if(Math.round(Math.sqrt(
                                Math.pow(bx - tx, 2) + Math.pow(bz - tz, 2)
                                )) <= radius) {
                            // gotta get this block and return it!
                            Block b = w.getBlockAt(tx, center.getY(), tz);
                            result.add(b);
                        }
                        break;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Returns a list of all of the blocks within a radius of this block in three dimentions (a sphere)
     *
     * @param center The block to be the center of the circle
     * @param radius The radius of the circle to get
     * @param axis The axis that the slice comes from (this determines which axis value will remain constant,
     *             so for example, if it is Y then the circle returned will be horizontal.
     * @return
     */
    public static List<Block> getSphereBlocks(Block center, int radius) {
        List<Block> result = new ArrayList<Block>();

        // find our diameter, always odd since we need a center block
        int diameter = (2 * radius) + 1;

        int bx = center.getX();
        int by = center.getY();
        int bz = center.getZ();

        // we'll need this later
        World w = center.getWorld();

        // this should always be true, but check anyway
        if(w != null) {
            // time to find the corner block to search through
            int cx = bx - radius;
            int cy = by - radius;
            int cz = bz - radius;

            // now we cycle!
            for(int i = 0; i < diameter; i++) {
                for(int j = 0; j < diameter; j++) {
                    for(int k = 0; k < diameter; k++) {
                        int tx = cx + i;
                        int ty = cy + j;
                        int tz = cz + k;

                        if(Math.round(Math.sqrt(
                                Math.pow(bx - tx, 2) + Math.pow(by - ty, 2) + Math.pow(bz - tz, 2)
                                )) <= radius) {
                            // gotta get this block and return it!
                            Block b = w.getBlockAt(tx, ty, tz);
                            result.add(b);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void setCircleBlocks(Block center, int radius, Axis axis, Material mat) {
        for(Block b : getCircleBlocks(center, radius, axis)) {
            b.setType(mat);
        }
    }

    public static void setSphereBlocks(Block center, int radius, Material mat) {
        for(Block b : getSphereBlocks(center, radius)) {
            b.setType(mat);
        }
    }
}
