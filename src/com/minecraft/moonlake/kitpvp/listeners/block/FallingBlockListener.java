package com.minecraft.moonlake.kitpvp.listeners.block;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/6.
 */
public class FallingBlockListener implements Listener {

    private final KitPvP main;
    private final List<FallingBlock> fallingBlockList;

    public FallingBlockListener(KitPvP main) {

        this.main = main;
        this.fallingBlockList = new ArrayList<>();
    }

    public List<FallingBlock> getFallingBlockList() {

        return fallingBlockList;
    }

    @EventHandler
    public void onFalling(EntityChangeBlockEvent event) {

        if(getFallingBlockList().contains(event.getEntity())) {

            event.setCancelled(true);
            event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.BLOCK_STONE_STEP, 5f, 1f);
            event.getEntity().remove();

            getFallingBlockList().remove(event.getEntity());
        }
    }
}
