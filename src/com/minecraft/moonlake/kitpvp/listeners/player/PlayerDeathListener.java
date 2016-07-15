package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.DataManager;
import com.minecraft.moonlake.kitpvp.manager.EntityManager;
import com.minecraft.moonlake.kitpvp.manager.OccupaManager;
import org.bukkit.entity.Arrow;
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKiller(PlayerDeathEvent event) {

        KitPvPPlayer deather = AccountManager.get(event.getEntity().getName());

        if(deather.getLastDamageCause() instanceof EntityDamageByEntityEvent) {

            EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent)deather.getLastDamageCause();
            Entity killer = edbee.getDamager();

            if(killer != null && killer instanceof Player && deather.getKiller() != null) {

                if(AccountManager.get(killer.getName()).getName().equalsIgnoreCase(deather.getKiller().getName())) {

                    KitPvPPlayer killerPlayer = deather.getKiller();

                    killerPlayer.getScoreboard().updateKill(killerPlayer.getKill() + 1);
                    OccupaManager.onKillPlayer(killerPlayer, deather);
                    OccupaManager.supplyKitPvP(killerPlayer);
                }
            }
            else if(killer != null && !(killer instanceof Player)) {

                if(killer instanceof Arrow && ((Arrow)killer).getShooter() instanceof Player) {

                    KitPvPPlayer killerPlayer = AccountManager.get(((Player)((Arrow)killer).getShooter()).getName());

                    if(killerPlayer.getName().equalsIgnoreCase(deather.getName())) {

                        event.setDeathMessage("玩家 " + deather.getName() + " 被自己的箭给射了个透心凉...");
                    }
                    else {

                        killerPlayer.getScoreboard().updateKill(killerPlayer.getKill() + 1);
                        OccupaManager.onKillPlayer(killerPlayer, deather);
                        OccupaManager.supplyKitPvP(killerPlayer);

                        event.setDeathMessage("玩家 " + deather.getName() + " 被 " + killerPlayer.getName() + " 的箭射中了膝盖,倒地不起...");
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent event) {

        KitPvPPlayer deather = AccountManager.get(event.getEntity().getName());

        if(deather != null) {

            deather.getScoreboard().updateDeath(deather.getDeath() + 1);

            EntityManager.deathFireworks(deather.getEyeLocation().subtract(0d, 0.2d, 0d), 3);
        }
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onRespawn(PlayerRespawnEvent event) {

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getPlayer().getName());

        DataManager.resetKitPvPState(kitPvPPlayer);

        if(DataManager.isSetLobbyPoint()) {

            event.setRespawnLocation(DataManager.getLobbyPoint());
        }
        //OccupaManager.initOccupaPlayer(kitPvPPlayer);
    }
}
