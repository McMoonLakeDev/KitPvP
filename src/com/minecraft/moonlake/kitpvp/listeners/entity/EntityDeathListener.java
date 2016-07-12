package com.minecraft.moonlake.kitpvp.listeners.entity;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

            if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent) {

                EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent)entity.getLastDamageCause();
                Entity killerEntity = edbee.getDamager();

                if(killerEntity != null && killerEntity instanceof Player) {

                    KitPvPPlayer kitPvPPlayer = AccountManager.get(killerEntity.getName());

                    kitPvPPlayer.getScoreboard().updateKill(kitPvPPlayer.getKill() + 1);
                }
            }
        }
    }
}
