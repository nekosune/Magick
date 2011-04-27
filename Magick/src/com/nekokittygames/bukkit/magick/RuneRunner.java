package com.nekokittygames.bukkit.magick;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;


public class RuneRunner extends BlockListener
{
	private List<Rune> mRunes;
	public RuneRunner() {
        unloadRunes();
    }

    public RuneRunner(Collection<Rune> runes) {
        loadRunes(runes);
    }

    public void loadRunes(Collection<Rune> runes) {
        mRunes = new ArrayList<Rune>(runes);
        Collections.sort(mRunes);
    }

    public void unloadRunes() {
        mRunes = new ArrayList<Rune>();
    }
    
    @Override
	public void onBlockBreak(BlockBreakEvent event) {
    	 for(Rune rune : mRunes) {
    		 boolean enabled=false;
    		 if(!Magick.hasPermissions())
    		 {
    			 if (rune.getEnabled()) 
    				 enabled=true;
    		 }
    		 else
    		 {
    			 if(Magick.permissionHandler.has(event.getPlayer(), "magick."+rune.getName()))
    				 enabled=true;
    		 }
    		 if(enabled)
    		 {
                 if (rune.onRuneUseBreak(event)) {
                     return;
                 }
             }
         }

         for(Rune rune : mRunes) {
        	 boolean enabled=false;
    		 if(!Magick.hasPermissions())
    		 {
    			 if (rune.getEnabled()) 
    				 enabled=true;
    		 }
    		 else
    		 {
    			 if(Magick.permissionHandler.has(event.getPlayer(), "magick."+rune.getName()))
    				 enabled=true;
    		 }
    		 if(enabled)
    		 {
                 if (rune.onRuneBreak(event)) {
                     return;
                 }
             }
         }
	}

	@Override
	public void onBlockDamage(BlockDamageEvent event) {
		for(Rune rune : mRunes) {
			boolean enabled=false;
   		 if(!Magick.hasPermissions())
   		 {
   			 if (rune.getEnabled()) 
   				 enabled=true;
   		 }
   		 else
   		 {
   			 if(Magick.permissionHandler.has(event.getPlayer(), "magick."+rune.getName()))
   				 enabled=true;
   		 }
   		 if(enabled)
   		 {
                if (rune.onRuneUseDamage(event)) {
                    return;
                }
            }
        }

        for(Rune rune : mRunes) {
        	boolean enabled=false;
   		 if(!Magick.hasPermissions())
   		 {
   			 if (rune.getEnabled()) 
   				 enabled=true;
   		 }
   		 else
   		 {
   			 if(Magick.permissionHandler.has(event.getPlayer(), "magick."+rune.getName()))
   				 enabled=true;
   		 }
   		 if(enabled)
   		 {
                if (rune.onRuneDamage(event)) {
                    return;
                }
            }
        }
	}

	@Override
	public void onBlockRedstoneChange(BlockRedstoneEvent event) {
		for(Rune rune : mRunes) {
			boolean enabled=false;
   			 if (rune.getEnabled()) 
   				 enabled=true;
   		 if(enabled)
   		 {
                if (rune.onRuneUseRedstone(event)) {
                    return;
                }
            }
        }

        for(Rune rune : mRunes) {
        	boolean enabled=false;
   		 
   			 if (rune.getEnabled()) 
   				 enabled=true;
   		 
   		 if(enabled)
   		 {
                if (rune.onRuneRedstone(event)) {
                    return;
                }
            }
        }
	}

	public class RuneRunnerPlayer extends PlayerListener
    {

		@Override
		public void onPlayerInteract(PlayerInteractEvent event) {
			if(event.getAction()==Action.RIGHT_CLICK_BLOCK)
			{
				if(event.getPlayer().getItemInHand().getType().isBlock() &&
		                event.getPlayer().getItemInHand().getType() != Material.AIR) {
		            return;
		        }

		        for(Rune rune : mRunes) {
		        	boolean enabled=false;
		    		 if(!Magick.hasPermissions())
		    		 {
		    			 if (rune.getEnabled()) 
		    				 enabled=true;
		    		 }
		    		 else
		    		 {
		    			 if(Magick.permissionHandler.has(event.getPlayer(), "magick."+rune.getName()))
		    				 enabled=true;
		    		 }
		    		 if(enabled)
		    		 {
		                if (rune.onRuneUseRightClick(event)) {
		                    return;
		                }
		            }
		        }

		        for(Rune rune : mRunes) {
		        	boolean enabled=false;
		    		 if(!Magick.hasPermissions())
		    		 {
		    			 if (rune.getEnabled()) 
		    				 enabled=true;
		    		 }
		    		 else
		    		 {
		    			 if(Magick.permissionHandler.has(event.getPlayer(), "magick."+rune.getName()))
		    				 enabled=true;
		    		 }
		    		 if(enabled)
		    		 {
		                if (rune.onRuneRightClick(event)) {
		                    return;
		                }
		            }
		        }
			}
		}
    	
    }
}
