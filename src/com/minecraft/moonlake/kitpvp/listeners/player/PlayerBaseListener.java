package com.minecraft.moonlake.kitpvp.listeners.player;

import com.minecraft.moonlake.kitpvp.api.KitPvP;
import com.minecraft.moonlake.kitpvp.api.player.KitPvPPlayer;
import com.minecraft.moonlake.kitpvp.language.l18n;
import com.minecraft.moonlake.kitpvp.manager.AccountManager;
import com.minecraft.moonlake.kitpvp.manager.DataManager;
import com.minecraft.moonlake.kitpvp.rank.KitPvPRank;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by MoonLake on 2016/7/9.
 */
public class PlayerBaseListener implements Listener {

    private final KitPvP main;

    public PlayerBaseListener(KitPvP main) {

        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(!AccountManager.has(player.getName())) {

            KitPvPPlayer kitPvPPlayer = AccountManager.create(player.getName());
            kitPvPPlayer.setRank(KitPvPRank.LEVEL_0);
            kitPvPPlayer.getKitPvPScoreboard().register();
            kitPvPPlayer.getOccupaGUI().loadKitPvPOccupaData();

            DataManager.resetKitPvPState(kitPvPPlayer);
            DataManager.loadKitPvPPlayerData(kitPvPPlayer);
        }
        if(DataManager.isSetLobbyPoint()) {

            event.getPlayer().teleport(DataManager.getLobbyPoint());
        }
        event.setJoinMessage(l18n.$("player.kitPvP.join.server", player.getName()));
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {

        KitPvPPlayer kitPvPPlayer = AccountManager.get(event.getPlayer().getName());

        if(kitPvPPlayer != null) {

            DataManager.saveKitPvPPlayerData(kitPvPPlayer);
            DataManager.resetKitPvPState(kitPvPPlayer);

            kitPvPPlayer.getKitPvPScoreboard().unregister();
        }
        AccountManager.remove(event.getPlayer().getName());
        event.setQuitMessage(l18n.$("player.kitPvP.quit.server", event.getPlayer().getName()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent event) {

        if(event.getPlayer().isOp()) {

            if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {

                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPickup(PlayerPickupItemEvent event) {

        if(event.getPlayer().isOp()) {

            if(event.getPlayer().getGameMode() == GameMode.CREATIVE) {

                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFood(FoodLevelChangeEvent event) {

        if(DataManager.contains(event.getEntity().getLocation())) {

            event.setCancelled(true);
        }
    }
}
