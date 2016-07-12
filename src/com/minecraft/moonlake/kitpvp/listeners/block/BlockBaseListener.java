package com.minecraft.moonlake.kitpvp.listeners.block;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by MoonLake on 2016/7/12.
 */
public class BlockBaseListener implements Listener {

    private final KitPvP main;

    public BlockBaseListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {

        if(event.getPlayer().isOp()) {

            if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {

                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent event) {

        if(event.getPlayer().isOp()) {

            if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {

                return;
            }
        }
        event.setCancelled(true);
    }
}
