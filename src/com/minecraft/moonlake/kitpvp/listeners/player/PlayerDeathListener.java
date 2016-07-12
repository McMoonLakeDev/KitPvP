package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.DataManager;
import com.minecraft.moonlake.kitpvp.manager.EntityManager;
import com.minecraft.moonlake.kitpvp.manager.OccupaManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by MoonLake on 2016/7/11.
 */
public class PlayerDeathListener implements Listener {

    private final KitPvP main;

    public PlayerDeathListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onKiller(PlayerDeathEvent event) {

        KitPvPPlayer deather = AccountManager.get(event.getEntity().getName());

        if(deather.getKiller() != null) {

        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntity(PlayerDeathEvent event) {

        KitPvPPlayer deather = AccountManager.get(event.getEntity().getName());

        if(deather.getLastDamageCause() instanceof EntityDamageByEntityEvent) {

            EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent)deather.getLastDamageCause();
            Entity killer = edbee.getDamager();

            if(killer != null && !(killer instanceof Player)) {


            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDeath(PlayerDeathEvent event) {

        KitPvPPlayer deather = AccountManager.get(event.getEntity().getName());

        if(deather != null) {

            deather.getScoreboard().updateDeath(deather.getDeath() + 1);

            EntityManager.deathFireworks(deather.getEyeLocation().subtract(0d, 0.2d, 0d), 3);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRespawn(PlayerRespawnEvent event) {

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getPlayer().getName());

        kitPvPPlayer.resetHealth();
        kitPvPPlayer.getInventory().clear();
        kitPvPPlayer.clearPotionEffect();

        if(DataManager.isSetLobbyPoint()) {

            event.setRespawnLocation(DataManager.getLobbyPoint());
        }
        OccupaManager.initOccupaPlayer(kitPvPPlayer);
    }
}
