package org.cvpcs.bukkit.magickraft.runeset.magickraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.event.block.BlockRightClickEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import org.cvpcs.bukkit.magickraft.BlockUtility;
import org.cvpcs.bukkit.magickraft.Magickraft;
import org.cvpcs.bukkit.magickraft.Rune;
import org.cvpcs.bukkit.magickraft.RuneSet;
import org.cvpcs.bukkit.magickraft.runeset.runecraft.RNTier;
import org.cvpcs.bukkit.magickraft.runeset.runecraft.TierUtility;
import org.cvpcs.bukkit.magickraft.runestruct.IRuneNode;
import org.cvpcs.bukkit.magickraft.runestruct.RNComplexAnd;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterial;
import org.cvpcs.bukkit.magickraft.runestruct.RNMaterialGroup;
import org.cvpcs.bukkit.magickraft.runestruct.RuneStructure;

public class AerogaRune extends Rune {

	private static final int AEROGA_RADIUS_PER_TIER = 4;

	private static final int AEROGA_MAX_HEIGHT = 122;
	private static final int AEROGA_MIN_RAISE = 20;

	public static final String NAME = "aeroga";

    public AerogaRune(Magickraft plugin, RuneSet set) {
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
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    			}
        				},
        				{//3
			    			{
			    				RNMaterial.getInstance(Material.AIR),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNMaterial.getInstance(Material.AIR),
			    			}
        				},
        				{//4
			    			{
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNMaterial.getInstance(Material.DIAMOND_BLOCK),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    			}
        				},
        				{//5
			    			{
			    				RNMaterial.getInstance(Material.AIR),
			    				RNMaterial.getInstance(Material.AIR),
			    				RNMaterial.getInstance(Material.IRON_BLOCK),
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
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
			    				RNComplexAnd.getInstance(RNMaterialGroup.getInstance(0), RNTier.getInstance(1)),
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
			    				Material.GLASS.getId(),
			    				Material.GLASS.getId(),
			    				Material.GLASS.getId(),
			    				Material.GLASS.getId(),
			    				Material.GLASS.getId(),
			    				Material.GLASS.getId(),
			    				Material.GLASS.getId(),
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

            // make sure there's no ceiling
            if (block.getWorld().getHighestBlockYAt(block.getX(), block.getZ()) != block.getFace(BlockFace.UP, 4).getY()) {
            	event.getPlayer().sendMessage("Unable to perform Aeroga due to elements in the way");
            	return false;
            }

            // oh lordy it's time to do this shit.

            // get our radius
            int radius = AEROGA_RADIUS_PER_TIER * (TierUtility.getTier(block.getFace(BlockFace.DOWN, 1)) + 1);

            // center for our sphere
            Block center = block.getFace(BlockFace.DOWN, 4);
            World world = center.getWorld();

            // first get a circle around our base
            List<Block> circle = BlockUtility.getCircleBlocks(center, radius, BlockUtility.Axis.Y);

            // next get our sphere
            List<Block> sphere = BlockUtility.getSphereBlocks(center, radius);

            // chunk off the top of the sphere
            List<Block> bowl = new ArrayList<Block>();
            for(Block b : sphere) {
            	if(b.getY() < center.getY()) {
            		bowl.add(b);
            	}
            }

            // ok we now have our bowl and our circle.  now to start raising.  first we need to figure out how much
            // we're raising by, so cycle the circle and find the highest block
            int highest_block = center.getY();
            for(Block b : circle) {
            	int height = world.getHighestBlockYAt(b.getX(), b.getY());

            	if(height > highest_block) {
            		highest_block = height;
            	}
            }

            int raise = AEROGA_MAX_HEIGHT - highest_block;

            // if raise is > 0, then we raise!
            if(raise >= AEROGA_MIN_RAISE) {

            	// time to make some raise maps, these store the start/stop locations for a given X and Z value when raising
            	Map<Block, Integer> raise_start_map = new HashMap<Block, Integer>();
            	Map<Block, Integer> raise_stop_map = new HashMap<Block, Integer>();

            	// fill the start map and stop maps
            	for(Block b : circle) {
            		raise_start_map.put(b, world.getHighestBlockYAt(b.getX(), b.getZ()));
            		raise_stop_map.put(b, b.getY());
            	}

            	// fill the stop map
            	for(Block b : bowl) {
            		for(Block k : circle) {
            			if(k.getX() == b.getX() &&
            					k.getZ() == b.getZ() &&
            					b.getY() < raise_stop_map.get(k)) {
            				raise_stop_map.put(k, b.getY());
            			}
            		}
            	}

            	// we are definitely raising now, so consume the rune
            	tryRune(block);

            	// now we perform the raising!
            	for(Block b : circle) {
            		int starty = raise_start_map.get(b);
            		int stopy = raise_stop_map.get(b);

            		int cury = starty;

            		do {
            			int newy = cury + raise;

            			Block ob = world.getBlockAt(b.getX(), cury, b.getZ());
            			Block nb = world.getBlockAt(b.getX(), newy, b.getZ());

            			nb.setData(ob.getData());
            			nb.setType(ob.getType());
            			ob.setType(Material.AIR);
            		} while(cury-- >= stopy);
            	}

            	// zomg we are raised! time to raise the player as well
            	Location playerLoc = event.getPlayer().getLocation();
            	playerLoc.setY(playerLoc.getY() + (double)raise);
            	event.getPlayer().teleportTo(playerLoc);

            	// alert the player of their awesomeness
            	event.getPlayer().sendMessage("Aeroga has raised the land into the sky!");
            } else {
            	event.getPlayer().sendMessage("Aeroga can't be performed as the land cannot be raised any higher");
            }

            return true;
        }

        return false;
    }
}
