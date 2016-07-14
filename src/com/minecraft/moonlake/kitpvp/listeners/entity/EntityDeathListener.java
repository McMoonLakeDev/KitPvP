package com.minecraft.moonlake.kitpvp.listeners.entity;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Created by MoonLake on 2016/7/11.
 */
public class EntityDeathListener implements Listener {

    private final KitPvP main;

    public EntityDeathListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {

        Entity entity = event.getEntity();

        if(!(entity instanceof Player)) {


        }
    }
}
