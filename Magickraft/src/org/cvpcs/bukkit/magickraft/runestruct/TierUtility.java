package org.cvpcs.bukkit.magickraft.runestruct;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class TierUtility {
    private static final HashMap<Integer, Integer> TIERS = new HashMap<Integer, Integer>();
    static {
        // tier 0
        TIERS.put(Material.AIR.getId(), 0);
        TIERS.put(Material.DIRT.getId(), 0);
        TIERS.put(Material.GRASS.getId(), 0);
        TIERS.put(Material.STONE.getId(), 0);
        TIERS.put(Material.SNOW.getId(), 0);
        TIERS.put(Material.SIGN.getId(), 0);
        TIERS.put(Material.SIGN_POST.getId(), 0);
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
        TIERS.put(Material.MOB_SPAWNER.getId(), 1);
        TIERS.put(Material.SPONGE.getId(), 1);
        TIERS.put(Material.SNOW_BLOCK.getId(), 1);
        TIERS.put(Material.ICE.getId(), 1);
        TIERS.put(Material.SAPLING.getId(), 1);
        // tier 2
        TIERS.put(Material.CHEST.getId(), 2);
        TIERS.put(Material.SOIL.getId(), 2);
        TIERS.put(Material.FURNACE.getId(), 2);
        TIERS.put(Material.GLASS.getId(), 2);
        TIERS.put(Material.LAVA.getId(), 2);
        TIERS.put(Material.STATIONARY_LAVA.getId(), 2);
        TIERS.put(Material.LEAVES.getId(), 2);
        TIERS.put(Material.RED_MUSHROOM.getId(), 2);
        TIERS.put(Material.BROWN_MUSHROOM.getId(), 2);
        TIERS.put(Material.STEP.getId(), 2);
        TIERS.put(Material.DOUBLE_STEP.getId(), 2);
        TIERS.put(Material.WOOD_STAIRS.getId(), 2);
        TIERS.put(Material.COBBLESTONE_STAIRS.getId(), 2);
        TIERS.put(Material.WATER.getId(), 2);
        TIERS.put(Material.STATIONARY_WATER.getId(), 2);
        TIERS.put(Material.WORKBENCH.getId(), 2);
        TIERS.put(Material.GLOWSTONE.getId(), 2);
        TIERS.put(Material.NOTE_BLOCK.getId(), 2);
        TIERS.put(Material.LEVER.getId(), 2);
        TIERS.put(Material.FENCE.getId(), 2);
        TIERS.put(Material.PUMPKIN.getId(), 2);
        TIERS.put(Material.STONE_PLATE.getId(), 2);
        TIERS.put(Material.WOOD_PLATE.getId(), 2);
        TIERS.put(Material.CROPS.getId(), 2);
        // tier 3
        TIERS.put(Material.BRICK.getId(), 3);
        TIERS.put(Material.CACTUS.getId(), 3);
        TIERS.put(Material.CLAY.getId(), 3);
        TIERS.put(Material.IRON_ORE.getId(), 3);
        TIERS.put(Material.COAL_ORE.getId(), 3);
        TIERS.put(Material.REDSTONE_ORE.getId(), 3);
        TIERS.put(Material.SUGAR_CANE_BLOCK.getId(), 3);
        TIERS.put(Material.TORCH.getId(), 3);
        TIERS.put(Material.LAPIS_BLOCK.getId(), 3);
        TIERS.put(Material.LAPIS_ORE.getId(), 3);
        TIERS.put(Material.CAKE_BLOCK.getId(), 3);
        TIERS.put(Material.REDSTONE_WIRE.getId(), 3);
        TIERS.put(Material.JACK_O_LANTERN.getId(), 3);
        TIERS.put(Material.RAILS.getId(), 3);
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
        TIERS.put(Material.DIAMOND_ORE.getId(), 5);
        TIERS.put(Material.PORTAL.getId(), 5);
        // tier 6
        TIERS.put(Material.DIAMOND_BLOCK.getId(), 6);
    }

    public static int getMaxTier() {
        int tier = -1;
        for(Integer i : TIERS.values()) {
            if(tier < i) {
                tier = i;
            }
        }

        return tier;
    }

    public static int getTier(Block b) {
        int bTier = -1;
        if(TIERS.containsKey(b.getTypeId())) {
            bTier = TIERS.get(b.getTypeId());
        }

        return bTier;
    }
}
